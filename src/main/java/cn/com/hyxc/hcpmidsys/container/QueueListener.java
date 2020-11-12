package cn.com.hyxc.hcpmidsys.container;

import org.springframework.stereotype.Component;

import java.util.Vector;

/**
 * 队列监听器
 * 主要监听排队序列的增减变化，并及时反馈给取号器
 *
 * @author yuanyc
 */
@Component
public interface QueueListener {

    /**
     * 此方法监听到数量并返回给取号机，作为一个重要监听器
     *
     * @author yuanyc
     */
    void onUpdate(Vector<Queue> queue);
}
