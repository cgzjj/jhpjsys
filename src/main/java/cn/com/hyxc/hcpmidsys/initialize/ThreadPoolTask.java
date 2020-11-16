package cn.com.hyxc.hcpmidsys.initialize;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * 定时任务线程池 初始化
 *
 * @author yuanyc
 */
@Component//被spring容器管理
@Order(2) //表示执行顺序
public class ThreadPoolTask implements ApplicationRunner {

    /*
     * 定时任务线程存储器
     */
    public static ConcurrentHashMap<String , ScheduledFuture> map ;

    /**
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
           map =  new ConcurrentHashMap<String,ScheduledFuture>();
    }

    /**
     * 创建线程池
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(20);
        executor.setThreadNamePrefix("taskExecutor-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}
