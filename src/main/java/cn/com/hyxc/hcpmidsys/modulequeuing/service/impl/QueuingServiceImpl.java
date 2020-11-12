package cn.com.hyxc.hcpmidsys.modulequeuing.service.impl;

import cn.com.hyxc.hcpmidsys.container.ContainerManager;
import cn.com.hyxc.hcpmidsys.container.Queue;
import cn.com.hyxc.hcpmidsys.modulequeuing.service.QueuingService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QueuingServiceImpl implements QueuingService {

    @Autowired
    private ContainerManager manager;
    /**
     * 取号机启动
     *
     * @author yuanyc
     * @return
     */
    @Override
    public JSONObject queuingInit() {
        List<Queue> queue = manager.getQueue();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number",queue.size());
        return jsonObject;
    }

    /**
     * 取号人取号
     *
     * @author yuanyc
     * @return
     */
    @Override
    public JSONObject queuingTakeno(Queue queuing) {
        manager.putNewQueueing(queuing);
        List<Queue> queue = manager.getQueue();
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }



}
