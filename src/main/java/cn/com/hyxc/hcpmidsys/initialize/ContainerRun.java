package cn.com.hyxc.hcpmidsys.initialize;

import cn.com.hyxc.hcpmidsys.container.ContainerManager;
import cn.com.hyxc.hcpmidsys.util.XmlQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 初始化容器类
 *
 * @author yuanyc
 */
@Component//被spring容器管理
@Order(1) //表示执行顺序
public class ContainerRun implements ApplicationRunner {

    @Autowired
    private ContainerManager containerManager;
    /**
     * 读取 业务/制证计算机的配置xml启动
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        containerManager.putControlComputerList(XmlQuery.getControlComputerConfig());
    }


   /* public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");

        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        //设置线程池大小
        scheduler.setPoolSize(10);
         //设置线程名前缀

        scheduler.setThreadNamePrefix("task-");
        //设置关闭前最多等几秒
        scheduler.setAwaitTerminationSeconds(10);
        //设置等待任务执行完毕后再关闭
        scheduler.setWaitForTasksToCompleteOnShutdown(true);

        ScheduledFuture<?> future = scheduler.schedule(()->{
                    System.out.println("ThreadPoolTaskScheduler: " + sdf.format(new Date()));
                    throw new RuntimeException("");
                },
//cron表达式的含义是，2秒执行一次
                new CronTrigger("0/2 * * * * ? "));
//是否可以中断正在执行的任务
//boolean mayInterruptIfRunning = true;
//终止任务
//future.cancel(mayInterruptIfRunning);

    }*/

}
