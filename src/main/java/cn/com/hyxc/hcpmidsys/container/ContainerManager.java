package cn.com.hyxc.hcpmidsys.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器管理器
 *
 * @author yuanyc
 */
@Component
@Scope("singleton")
public class ContainerManager extends AbsContainerManager {

    @Autowired
    private QueueListener queueListener;

    @Autowired
    private static volatile ContainerManager manager;

    private Vector<Queue> queue;

    private Vector<ControlComputer> controlComputers;

    private ConcurrentHashMap<String, Integer> lastIntGroupByYwlb = new ConcurrentHashMap<String, Integer>() {
        {
            put("01", 0);
            put("02", 0);
            put("04", 0);
        }

        @Override
        public Integer get(Object key) {
            Integer value = super.get(key);
            super.put(String.valueOf(key), ++ value);
            return value;
        }
    };

    private ContainerManager() {
        queue = new Vector<>();
        onQueueCreate(queue);
        controlComputers = new Vector<>();
        onComputerCreate(controlComputers);
    }

    @Override
    void destroy() {
        super.destroy();
        queue = null;
        controlComputers = null;
        manager = null;
    }

    /**
     * 获取排队队列
     *
     * @return
     * @author yuanyc
     */
    public synchronized List<Queue> getQueue() {
        return queue;
    }

    /**
     * 获取控制计算机配置信息
     *
     * @return
     * @author yuanyc
     */
    public synchronized List<ControlComputer> getControlComputers() {
        return controlComputers;
    }


    /**
     * 当队列中添加新的排队信息时
     * 为其生成排队号
     *
     * @param queue
     * @param queuinng
     */
    @Override
    public synchronized Queue onAddNewQueue(List<Queue> queue, Queue queuinng) {
        super.onAddNewQueue(queue, queuinng);
        Queue newQueuing = queue.get(queue.indexOf(queuinng));
        String ywlb = newQueuing.getYwlb();
        String pdh = newQueuing.setPdh(ywlb + String.format("%04d", lastIntGroupByYwlb.get(ywlb)));
        newQueuing.setQhxxxlh(new SimpleDateFormat("yyMMdd").format(System.currentTimeMillis()) + "8888888888" + pdh);
        return newQueuing;
    }

    @Override
    public synchronized void onQueueCreate(List<Queue> queue) {
        super.onQueueCreate(queue);
    }

    @Override
    public synchronized void onComputerCreate(Vector<ControlComputer> controlComputers) {
        super.onComputerCreate(controlComputers);
    }


    /**
     * 添加 业务/制证窗口计算机配置信息
     *
     * @param computerList
     * @author yuanyc
     */
    public synchronized void putControlComputerList(List<ControlComputer> computerList) {
        for (ControlComputer computer : computerList
        ) {
            controlComputers.add(computer);
        }
    }

    /**
     * 添加一条排队信息
     *
     * @param queueing 排队信息
     * @author yuanyc
     */
    public synchronized Queue putNewQueueing(Queue queueing) {
        queue.add(queueing);
        Queue newQueuing = onAddNewQueue(this.queue, queueing);
        queueListener.onUpdate(queue);
        return newQueuing;
    }

    /**
     * 取出一条排队信息
     * 从队伍首位取出一位排队信息
     *
     * @author yuanyc
     */
    public synchronized Queue pullQueueing() {
        Queue queuing = queue.remove(0);
        queueListener.onUpdate(queue);
        return queuing;
    }

    /**
     * 取出中间某位的排队号
     *
     * @param index
     * @return
     */
    public synchronized Queue pullQueueing(int index) {
        if (index == -1) {
            return null;
        }
        Queue queuing = queue.remove(index);
        queueListener.onUpdate(queue);
        return queuing;
    }

    /**
     * 通过业务类别匹配 获取排队信息
     *
     * @param kbywlb 可办业务类别
     * @return queue
     * 01#02#04
     * @author yuanyc
     */
    public synchronized Queue pullQueuing(String kbywlb) {
        for (Queue queueing : queue
        ) {
            if (kbywlb.startsWith(queueing.getYwlb())) {
                return pullQueueing(queue.indexOf(queueing));
            }
        }
        return null;
    }

    public synchronized Queue pullQueuingByQhxxxlh(String qhxxxlh) {
        for (Queue queueing : queue
        ) {
            if (qhxxxlh.equals(queueing.getQhxxxlh())) {
                return pullQueueing(queue.indexOf(queueing));
            }
        }
        return null;
    }

    /**
     * 排队置顶
     * 给特殊的排队号进行置顶操作
     *
     * @param queueing 排队信息
     * @author yuanyc
     */
    public synchronized boolean putQueuing2Top(Queue queueing) {
        int index;
        if ((index = queue.indexOf(queueing)) == -1) {
            return false;
        }
        queue.add(0, queue.remove(index));
        return true;
    }

    /**
     * 排队置顶
     * 给特殊的排队号进行置顶操作
     *
     * @param pdh 排队号
     * @author yuanyc
     */
    public synchronized boolean putQueuing2Top(String pdh) {
        for (Queue queueing : queue
        ) {
            if (pdh.equals(queueing.getPdh())) {
                return putQueuing2Top(queueing);
            }
        }
        return false;
    }


    /**
     * 更新计算机状态
     *
     * @param status
     * @author yuanyc
     */
    public synchronized boolean updateComputerStatus(String uuid, String status) {
        for (ControlComputer computer :
                controlComputers) {
            if (uuid.equals(computer.getUuid())) {
                computer.setStatus(status);
                return true;
            }
        }
        return false;
    }


    /**
     * 更新取号信息序列号
     *
     * @param
     * @author yuanyc
     */
    public synchronized boolean updateQhxxxlhInComputers(String uuid, String qhxxxlh) {
        for (ControlComputer computer :
                controlComputers) {
            if (uuid.equals(computer.getUuid())) {
                computer.setQhxxxlh(qhxxxlh);
                return true;
            }
        }
        return false;
    }

    /**
     * 更新计算机配置中的队列
     *
     * @author yuanyc
     */
    public synchronized boolean updateQueuingInComputers(String uuid, Queue queuing) {
        for (ControlComputer computer :
                controlComputers) {
            if (uuid.equals(computer.getUuid())) {
                computer.setQueuing(queuing);
                return true;
            }
        }
        return false;
    }
}
