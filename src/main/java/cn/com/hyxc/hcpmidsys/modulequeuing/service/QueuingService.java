package cn.com.hyxc.hcpmidsys.modulequeuing.service;

import cn.com.hyxc.hcpmidsys.container.ControlComputer;
import cn.com.hyxc.hcpmidsys.container.Queue;
import com.alibaba.fastjson.JSONObject;
import org.junit.runner.Computer;

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
    JSONObject queuingTakeno(Queue queuing);

    /**
     * 取号后由于相应的计算机的业务已经在等待办理（空等状态）
     * 直接补充写入
     */
    JSONObject replenishWriteQueuing(Queue queuing, ControlComputer computer);
}
