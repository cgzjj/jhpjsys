package cn.com.hyxc.hcpmidsys.modulequeuing.service;

import cn.com.hyxc.hcpmidsys.container.Queue;
import com.alibaba.fastjson.JSONObject;

public interface QueuingService {

    /**
     * 取号机启动
     * @return
     */
    JSONObject queuingInit();

    /**
     * 取号人取号
     * @return
     */
    JSONObject queuingTakeno(Queue queue);
}
