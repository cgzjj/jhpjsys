package cn.com.hyxc.hcpmidsys.container;

import java.util.List;
import java.util.Vector;

public abstract class AbsContainerManager {

    AbsContainerManager() {
        System.out.println("ContainerManager被创建" + this);
    }

    public synchronized void onQueueCreate(List<Queue> queue) {
        System.out.println("容器被创建，初始容量为 : " + queue.size());
    }

    public synchronized void onComputerCreate(Vector<ControlComputer> controlComputers) {
        System.out.println("容器被创建，初始容量为 : " + controlComputers.size());
    }

    public synchronized void onAddNewQueue(List<Queue> queue) {
        System.out.println("当前容器的容量 : " + queue.size());
    }

    void destroy() {
        System.out.println("ContainerManager 被销毁");
    }


    interface QueueListener {
         void onUpdate();
    }
}
