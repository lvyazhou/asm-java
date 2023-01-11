package asm.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: TaskPoolConfig <br/>
 * date: 2019年11月18日 上午11:11:11 <br/>
 * @author lvyazhou@360.cn
 */
@EnableAsync
@Configuration
public class TaskPoolConfig {

    /**
     * 声明一个线程池
     *
     * @return
     */
    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(20);
        executor.setAllowCoreThreadTimeOut(true);
        //最大线程数
        executor.setMaxPoolSize(30);
        executor.setKeepAliveSeconds(30000);
        //配置队列大小
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("asm-executor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        return executor;
    }
}
