package cn.com.hyxc.hcpmidsys.modulequeuing.service.impl;

import cn.com.hyxc.hcpmidsys.container.ContainerManager;
import cn.com.hyxc.hcpmidsys.container.ControlComputer;
import cn.com.hyxc.hcpmidsys.container.Queue;
import cn.com.hyxc.hcpmidsys.modulequeuing.service.QueuingService;
import cn.com.hyxc.hcpmidsys.util.XmlQuery;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueuingServiceImpl implements QueuingService {

    @Autowired
    private ContainerManager containerManager;

    /**
     * 取号机启动
     *
     * @return
     * @author yuanyc
     */
    @Override
    public JSONObject queuingInit() {
        List<Queue> queue = containerManager.getQueue();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", queue.size());
        return jsonObject;
    }

    /**
     * 取号人取号
     *
     * @return
     * @author yuanyc
     */
    @Override
    public JSONObject queuingTakeno(Queue queuing) {
        Queue newQueuing = containerManager.putNewQueueing(queuing);
        List<ControlComputer> controlComputers = containerManager.getControlComputers();
        for (ControlComputer computer :
                controlComputers) {
            if ("3".equals(computer.getStatus()) && computer.getKbywlb().startsWith(newQueuing.getYwlb())) {
                //从队列中取出该队列
                Queue selectQueuing = containerManager.pullQueuingByQhxxxlh(newQueuing.getQhxxxlh());
                //更改计算机状态
                containerManager.updateQueuingInComputers(computer.getUuid(), selectQueuing);
                containerManager.updateComputerStatus(computer.getUuid(), "1");
                //补充写入信息
                JSONObject jsonObject = replenishWriteQueuing(selectQueuing,computer);
                return jsonObject;
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("queuing", newQueuing);
        return jsonObject;
    }

    /**
     * 取号后由于相应的计算机的业务已经在等待办理（空等状态）
     * 直接补充写入
     *
     * @author yuanyc
     */
    public JSONObject replenishWriteQueuing(Queue queuing, ControlComputer computer) {
        String url = "http://192.168.0.1/xxxxx";
        XmlQuery.sendHttps(XmlQuery.replenishWriteXml(queuing, computer), url)
        return null;
    }


}
