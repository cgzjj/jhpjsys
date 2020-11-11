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
        putIfAbsent();
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

    public List<Queue> getQueue() {
        return queue;
    }

    public List<ControlComputer> getControlComputers() {
        return controlComputers;
    }

    public synchronized void putAddOne() {
        //this.queue.add(new Queue.Builder().setPdh("202011100002").setQhrxm("袁月璨").setRylb(1).build());
        onAddNew(queue);
    }

    @Override
    public synchronized void onAddNew(List<Queue> queue) {
        super.onAddNew(queue);
        System.out.println(queue.size() + "gg");
    }

    @Override
    public synchronized void onQueueCreate(List<Queue> queue) {
        super.onQueueCreate(queue);
    }

    @Override
    public synchronized void onComputerCreate(Vector<ControlComputer> controlComputers) {
        super.onComputerCreate(controlComputers);
    }

    private void putIfAbsent() {
        //queue.add(new Queue.Builder().setPdh("202011100002").setQhrxm("袁月璨").setRylb(1).build());
        //queue.add(new Queue.Builder().setPdh("202011100003").setQhrxm("蒲冰").setRylb(1).build());
        controlComputers.add(new ControlComputer.Builder().setCkbh("01").setJsjip("192.168.0.1").setJsjlb("1").build());
        controlComputers.add(new ControlComputer.Builder().setCkbh("02").setJsjip("192.168.0.2").setJsjlb("2").build());
    }

    @Override
    void destroy() {
        queue = null;
        controlComputers = null;
        manager = null;
        super.destroy();
    }

}
