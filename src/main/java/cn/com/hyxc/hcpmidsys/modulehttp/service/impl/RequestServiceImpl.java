package cn.com.hyxc.hcpmidsys.modulehttp.service.impl;

import cn.com.hyxc.hcpmidsys.container.ContainerManager;
import cn.com.hyxc.hcpmidsys.container.ControlComputer;
import cn.com.hyxc.hcpmidsys.container.Queue;
import cn.com.hyxc.hcpmidsys.modulehttp.service.RequestService;
import cn.com.hyxc.hcpmidsys.util.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *处理所有的请求
 *
 * @author jiangt
 */
@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private ContainerManager containerManager;

    /**
     * 处理请求
     * @param request
     * @param json
     * @return
     */
    @Override
    public JSONObject ProcessingRequests(HttpServletRequest request,String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(json);
        } catch (ClassCastException e){
            e.printStackTrace();
        }
        JSONObject data = jsonObject.getJSONObject("respData");
        if (data == null){
            return null;
        }
        JSONObject result = null;
        if (request.getParameter("onType").equals("TMRI_CALLOUT")){
            //叫号请求处理
            result = callRequest(data);
        } else if (request.getParameter("onType").equals("TMRI_RECALL")){
            //再次呼叫请求处理
            result = callRequestAgain(data);
        } else if (request.getParameter("onType").equals("TMRI_SKIP")){
            //过号请求处理
            result = skipCallRequest(data);
        } else if (request.getParameter("onType").equals("TMRI_EVALUATION")){
            //提请评价请求处理
        } else if (request.getParameter("onType").equals("TMRI_SUSPEND")){
            //暂停叫号请求处理
        } else if (request.getParameter("onType").equals("TMRI_RECOVER")){
            //恢复叫号请求处理
        } else if (request.getParameter("onType").equals("TMRI_RECEIVE")){
            //待领取牌证信息写入请求处理
        }
        return result;
    }

    /**
     * 过号请求处理
     * @param data
     * @return
     */
    private JSONObject skipCallRequest(JSONObject data) {
        JSONObject result = null;

        return result;
    }

    /**
     * 再次呼叫请求处理
     * @param data
     * @return
     */
    private JSONObject callRequestAgain(JSONObject data) {
        JSONObject result = null;
        String qhxxxlh = data.getString("qhxxxlh");
        boolean bool = CommonUtil.isNullString(qhxxxlh);
        if (bool){
            JSONObject writeData = new JSONObject();
            writeData.put("code",2);
            writeData.put("message","取号信息序列号上传失败");
            result = writeMessage(200,"取号失败",writeData);
            return result;
        }
        //通过qhxxxlh去找到电脑的备案信息
        ControlComputer selectComputer = getComputerByQhxxxlh(qhxxxlh);
        if (selectComputer == null){
            System.out.println("通过qhxxxlh未获取到该窗口的计算机");
            return result;
        }
        //通过喇叭喊话和LED显示屏上显示申请人到相应窗口办理业务
        //截取出从取号序列号中截取phd
        String pdh = qhxxxlh.substring(qhxxxlh.length()-6);


        //将数据写入json对象中
        JSONObject writeData = new JSONObject();
        writeData.put("code",1);
        writeData.put("message","");
        result = writeMessage(200,"再次叫号请求成功",writeData);
        //修改电脑备案信息数组内的qhxxxlh为空
        containerManager.updateQhxxxlhInComputers(selectComputer.getUuid(),null);
        return result;
    }

    /**
     * 处理叫号请求
     * @param data
     * @return
     */
    private JSONObject callRequest(JSONObject data){
        String ywckjsjip = data.getString("ywckjsjip");
        //通过窗口电脑ip获取电脑备案信息
        ControlComputer selectControlComputer = getComputerByIp(ywckjsjip);
        JSONObject result = null;
        Queue selectQueue = null;
        if(selectControlComputer == null){
            //未获取到该窗口的计算机队列
            System.out.println("未获取到该窗口的计算机");
            return result;
        }
        String kbywlb = selectControlComputer.getKbywlb();
        if (CommonUtil.isNullString(kbywlb)){
            //未获取到该窗口的可办理业务类别
            System.out.println("未获取到该窗口的可办理业务类别");
            return result;
        }
        //通过可办理业务类型找到排队人员
        selectQueue = containerManager.pullQueuing(kbywlb);
        if (selectQueue== null){
            //记录该计算机暂时没有可办理业务
            containerManager.updateComputerStatus(selectControlComputer.getUuid(),"3");
            return result;
        }
        //通过喇叭喊话和LED显示屏上显示申请人到相应窗口办理业务


        //将数据写入json对象中
        result = writeData(selectQueue);
        //将该电脑的备案信息数组内的qhxxxlh值写入
        containerManager.updateQhxxxlhInComputers(selectControlComputer.getUuid(),selectQueue.getQhxxxlh());

        return result;

    }

    /**
     * 向json对象里写入数据
     * @param selectQueue
     * @return
     */

    public JSONObject writeData(Queue selectQueue){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        JSONObject writeData = new JSONObject();
        writeData.put("sbkzjsjip",selectQueue.getSbkzjsjip());
        writeData.put("qhxxxlh",selectQueue.getQhxxxlh());
        writeData.put("pdh",selectQueue.getPdh());
        writeData.put("ywlb",selectQueue.getYwlb());
        writeData.put("sfzmhm",selectQueue.getSfzmhm());
        writeData.put("dlrsfzmhm",selectQueue.getDlrsfzmhm());
        writeData.put("qhrxm",selectQueue.getQhrxm());
        writeData.put("qhsj",dateString);
        writeData.put("rylb",selectQueue.getRylb());
        writeData.put("rxbdjg",selectQueue.getRxbdjg());
        writeData.put("rxbdxsd",selectQueue.getRxbdxsd());
        writeData.put("jzzply",selectQueue.getJzzply());
        writeData.put("xczp",selectQueue.getXczp());
        writeData.put("jzbdzp",selectQueue.getJzbdzp());
        JSONObject result = writeMessage(100,"叫号成功",writeData);
        return result;
    }

    /**
     * 通过窗口电脑ip获取电脑备案信息
     * @param ywckjsjip
     * @return
     */
    public ControlComputer getComputerByIp(String ywckjsjip){
        ControlComputer selectControlComputer = null;
        List<ControlComputer>computerList = containerManager.getControlComputers();
        for (ControlComputer controlComputer:computerList) {
            if (controlComputer.getJsjip().equals(ywckjsjip)){
                selectControlComputer = controlComputer;
            }
        }
        return selectControlComputer;
    }

    /**
     * 通过可办理业务类型找到排队人员
     * @param kbywlb
     * @return
     */
    public Queue getQueueByKbywlb(String kbywlb){
        String [] strs = kbywlb.split("#");
        List<Queue>queueList = containerManager.getQueue();
        for (Queue queue:queueList) {
            for (String str: strs) {
                if (queue.getYwlb().equals(str)){
                    return queue;
                }
            }
        }
        return null;
    }

    /**
     * 请求数据写入json
     * @param respCode
     * @param respMsg
     * @param writeData
     * @return
     */
    public JSONObject writeMessage(Integer respCode,String respMsg,JSONObject writeData){
        JSONObject result = new JSONObject();
        result.put("respCode",respCode);
        result.put("respMsg",respMsg);
        result.put("respData",writeData);
        return result;
    }

    /**
     * 通过qhxxxlh去找到电脑的备案信息
     * @param qhxxxlh
     * @return
     */
    public ControlComputer getComputerByQhxxxlh(String qhxxxlh){
        ControlComputer selectControlComputer = null;
        List<ControlComputer>computerList = containerManager.getControlComputers();
        for (ControlComputer controlComputer:computerList) {
            if (controlComputer.getQhxxxlh().equals(qhxxxlh)){
                selectControlComputer = controlComputer;
            }
        }
        return selectControlComputer;
    }
}
