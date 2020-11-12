package cn.com.hyxc.hcpmidsys.modulehttp.service.impl;

import cn.com.hyxc.hcpmidsys.container.ContainerManager;
import cn.com.hyxc.hcpmidsys.container.ControlComputer;
import cn.com.hyxc.hcpmidsys.container.Queue;
import cn.com.hyxc.hcpmidsys.modulehttp.service.Handler;
import cn.com.hyxc.hcpmidsys.modulehttp.service.RequestService;
import cn.com.hyxc.hcpmidsys.util.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

/**
 *处理所有的请求
 *
 * @author jiangt
 */
@Service
public class RequestServiceImpl implements RequestService {

    private static   Map<String,Object> handlerMap = new HashMap<String,Object>(){
        {
            //叫号请求处理
            put("TMRI_CALLOUT", new RequestServiceImpl.CallOutHandler());
            //再次呼叫请求处理
            put("TMRI_RECALL", new RequestServiceImpl.ReCallHandler());
            //过号请求处理
            put("TMRI_SKIP", new RequestServiceImpl.SkipHandler());
            //提请评价请求处理
            put("TMRI_EVALUATION", new RequestServiceImpl.EvaluationHandler());
            //暂停叫号请求处理
            put("TMRI_SUSPEND", new RequestServiceImpl.SuspendHandler());
            //恢复叫号请求处理
            put("TMRI_RECOVER", new RequestServiceImpl.RecoverHandler());
            //待领取牌证信息写入请求处理
            put("TMRI_RECEIVE", new RequestServiceImpl.ReceiveHandler());
        }
    };

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
        Handler handler = (Handler)handlerMap.get(request.getParameter("onType"));
        result = handler.execute(data);
        /*if (("TMRI_CALLOUT").equals(request.getParameter("onType"))){
            //叫号请求处理
            result = callRequest(data);
        } else if (("TMRI_RECALL").equals(request.getParameter("onType"))){
            //再次呼叫请求处理
            result = reCallRequest(data);
        } else if (("TMRI_SKIP").equals(request.getParameter("onType"))){
            //过号请求处理
            result = skipCallRequest(data);
        } else if (("TMRI_EVALUATION").equals(request.getParameter("onType"))){
            //提请评价请求处理
        } else if (("TMRI_SUSPEND").equals(request.getParameter("onType"))){
            //暂停叫号请求处理
        } else if (("TMRI_RECOVER").equals(request.getParameter("onType"))){
            //恢复叫号请求处理
        } else if (("TMRI_RECEIVE").equals(request.getParameter("onType"))){
            //待领取牌证信息写入请求处理
        }*/
        return result;
    }

    /**
     * 待领取牌证信息写入请求处理
     * @param data
     * @return
     */
    private JSONObject receiveRequest(JSONObject data) {
        JSONObject result = null;

        return result;
    }

    /**
     * 恢复叫号请求处理
     * @param data
     * @return
     */
    private JSONObject recoverRequest(JSONObject data) {
        JSONObject result = null;

        return result;
    }

    /**
     * 暂停叫号请求处理
     * @param data
     * @return
     */
    private JSONObject suspendRequest(JSONObject data) {
        JSONObject result = null;

        return result;
    }
    /**
     * 提请评价请求处理
     * @param data
     * @return
     */
    private JSONObject evaluationRequest(JSONObject data) {
        JSONObject result = null;
        String qhxxxlh = data.getString("qhxxxlh");
        if (CommonUtil.isNullString(qhxxxlh)){
            JSONObject writeData = new JSONObject();
            writeData.put("code",2);
            writeData.put("message","取号信息序列号为空");
            writeMessage(100,"取号信息序列号为空",writeData);
            return result;
        }
        //通过qhxxxlh去找到电脑的备案信息
        ControlComputer selectControlComputer = getComputerByQhxxxlh(qhxxxlh);
        //提请评价
        String msg = "S02";
       //TcpUtil.senderTcp(selectControlComputer.getUrl(),selectControlComputer.getPort(),msg);

        return result;
    }

    /**
     * 过号请求处理
     * @param data
     * @return
     */
    private JSONObject skipCallRequest(JSONObject data) {
        JSONObject result = null;
        String qhxxxlh = data.getString("qhxxxlh");
        if (CommonUtil.isNullString(qhxxxlh)){
            return result;
        }
        //通过qhxxxlh去找到电脑的备案信息
        ControlComputer selectControlComputer = getComputerByQhxxxlh(qhxxxlh);
        Queue selectQueue = null;
        if(selectControlComputer == null){
            //未获取到该窗口的计算机队列
            System.out.println("未获取到该窗口的计算机");
            return result;
        }
        /*//获取这个电脑上一个处理的人的信息
        Queue sQueue = selectControlComputer.getQueuing();
        //传到后台做处理
        requestDao.saveQueue(selectControlComputer,sQueue);*/
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
        JSONObject writeData = new JSONObject();
        writeData.put("ywckjsjip",selectControlComputer.getJsjip());
        result = writeData(selectQueue,writeData);
        //将处理的队列信息写入电脑的备案信息中
        containerManager.uodateQueuingInComputers(selectControlComputer.getUuid(),selectQueue);
        return result;
    }

    /**
     * 再次呼叫请求处理
     * @param data
     * @return
     */
    private JSONObject reCallRequest(JSONObject data) {
        JSONObject result = null;
        String qhxxxlh = data.getString("qhxxxlh");
        if (CommonUtil.isNullString(qhxxxlh)){
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
        //获取phd
        String pdh = selectComputer.getQueuing().getPdh();


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
        /*//获取这个电脑上一个处理的人的信息
        Queue sQueue = selectControlComputer.getQueuing();
        //传到后台做处理
        requestDao.saveQueue(selectControlComputer,sQueue);*/
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
        JSONObject writeData = new JSONObject();
        result = writeData(selectQueue,writeData);
        //将处理的队列信息写入电脑的备案信息中
        containerManager.uodateQueuingInComputers(selectControlComputer.getUuid(),selectQueue);


        return result;

    }

    /**
     * 向json对象里写入数据
     * @param selectQueue
     * @return
     */

    public JSONObject writeData(Queue selectQueue,JSONObject writeData){
        writeData.put("sbkzjsjip",selectQueue.getSbkzjsjip());
        writeData.put("qhxxxlh",selectQueue.getQhxxxlh());
        writeData.put("pdh",selectQueue.getPdh());
        writeData.put("ywlb",selectQueue.getYwlb());
        writeData.put("sfzmhm",selectQueue.getSfzmhm());
        writeData.put("dlrsfzmhm",selectQueue.getDlrsfzmhm());
        writeData.put("qhrxm",selectQueue.getQhrxm());
        writeData.put("qhsj",selectQueue.getQhsj());
        writeData.put("rylb",selectQueue.getRylb());
        writeData.put("rxbdjg",selectQueue.getRxbdjg());
        writeData.put("rxbdxsd",selectQueue.getRxbdxsd());
        writeData.put("jzzply",selectQueue.getJzzply());
        writeData.put("jzbdzp",selectQueue.getJzbdzp());
        writeData.put("xczp",selectQueue.getXczp());
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
            if (ywckjsjip.equals(controlComputer.getJsjip())){
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
    /*public Queue getQueueByKbywlb(String kbywlb){
        String [] strs = kbywlb.split("#");
        List<Queue>queueList = containerManager.getQueue();
        for (Queue queue:queueList) {
            for (String str: strs) {
                if (str.equals(queue.getYwlb())){
                    return queue;
                }
            }
        }
        return null;
    }*/

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
            if (qhxxxlh.equals(controlComputer.getQueuing().getQhxxxlh())){
                selectControlComputer = controlComputer;
            }
        }
        return selectControlComputer;
    }

    static class CallOutHandler implements Handler {
        @Override
        public JSONObject execute(JSONObject data) {
            return new RequestServiceImpl().callRequest(data);
        }
    }

    static class ReCallHandler implements Handler {
        @Override
        public JSONObject execute(JSONObject data) {
            return new RequestServiceImpl().reCallRequest(data);
        }
    }

    static class SkipHandler implements Handler {
        @Override
        public JSONObject execute(JSONObject data) {
            return new RequestServiceImpl().skipCallRequest(data);
        }
    }
    static class EvaluationHandler implements Handler {
        @Override
        public JSONObject execute(JSONObject data) {
            return new RequestServiceImpl().evaluationRequest(data);
        }
    }

    static class SuspendHandler implements Handler {
        @Override
        public JSONObject execute(JSONObject data) {
            return new RequestServiceImpl().suspendRequest(data);
        }
    }

    static class RecoverHandler implements Handler {
        @Override
        public JSONObject execute(JSONObject data) {
            return new RequestServiceImpl().recoverRequest(data);
        }
    }

    static class ReceiveHandler implements Handler {
        @Override
        public JSONObject execute(JSONObject data) {
            return new RequestServiceImpl().receiveRequest(data);
        }
    }
}
