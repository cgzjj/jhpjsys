package cn.com.hyxc.hcpmidsys.container;

import java.util.*;

/**
 * 容器管理器
 *
 * @author yuanyc
 */
public class ContainerManager extends AbsContainerManager {

    private Vector<Queue> queue;

    private Vector<ControlComputer> controlComputers;

    private static volatile ContainerManager manager = null;

    private ContainerManager() {
        queue = new Vector<>();
        onQueueCreate(queue);
        controlComputers = new Vector<>();
        onComputerCreate(controlComputers);
    }

    public static ContainerManager getContainerManager() {
        if (manager == null) {
            synchronized (ContainerManager.class) {
                if (manager == null) {
                    manager = new ContainerManager();
                    return manager;
                }
            }
        }
        return manager;
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

    public synchronized void putAddOne() {
        onAddNewQueue(queue);
    }

    @Override
    public synchronized void onAddNewQueue(List<Queue> queue) {
        super.onAddNewQueue(queue);
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
    public synchronized void putNewQueueing(Queue queueing) {
        queue.add(queueing);
        onAddNewQueue(queue);
    }

    /**
     * 取出一条排队信息
     * 从队伍首位取出一位排队信息
     *
     * @author yuanyc
     */
    public synchronized Queue pullQueueing() {
        return queue.remove(0);
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
        return queue.remove(index);
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

}
