package cn.com.hyxc.hcpmidsys.timer;

import cn.com.hyxc.hcpmidsys.initialize.ThreadPoolTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

/**
 * 更新容器管理器定时任务
 *
 * @author yuanyc
 */
@Component
@Scope("prototype")
public class UpdateContainerManagerTask {

    private String cron;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture future;

    public void startCron(){
        cron = "0 0/1 * * * ?";
        System.out.println(Thread.currentThread().getName());
        String name = Thread.currentThread().getName();
        future = threadPoolTaskScheduler.schedule(new MyTask(name),new CronTrigger(cron));
        ThreadPoolTask.map.put(name,future);
    }

    public void stop(){
        if(future != null){
            future.cancel(true);
        }
    }

    private class MyTask implements Runnable {
        private String name;

        MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + "正在执行定时任务");
        }
    }
}
