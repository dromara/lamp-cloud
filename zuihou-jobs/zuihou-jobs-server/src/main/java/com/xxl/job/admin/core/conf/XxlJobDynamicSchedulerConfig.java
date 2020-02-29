package com.xxl.job.admin.core.conf;

import com.xxl.job.admin.core.schedule.XxlJobDynamicScheduler;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * @author xuxueli 2018-10-28 00:18:17
 */
@Configuration
public class XxlJobDynamicSchedulerConfig {

    @Bean
    public SchedulerFactoryBean getSchedulerFactoryBean(DataSource dataSource) {

        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setDataSource(dataSource);
        schedulerFactory.setAutoStartup(true);                  // 自动启动
        schedulerFactory.setStartupDelay(20);                   // 延时启动，应用启动成功后在启动
        schedulerFactory.setOverwriteExistingJobs(true);        // 覆盖DB中JOB：true、以数据库中已经存在的为准：false
        schedulerFactory.setApplicationContextSchedulerContextKey("applicationContext");
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

        return schedulerFactory;
    }

    /**
     * 项目启动时，首先创建【SchedulerFactoryBean】， 然后注入 XxlJobDynamicScheduler，
     * 并执行初始化方法，将【JobRegistryMonitorHelper】、【JobFailMonitorHelper】、【initRpcProvider】初始化
     * JobRegistryMonitorHelper： 注册监控工具类，间隔30秒，
     * JobFailMonitorHelper： 失败监控工具类， 用一个守护线程监控任务执行情况，发生失败时，启动重试机制。 间隔10秒
     *
     * @param schedulerFactory
     * @return
     */
    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobDynamicScheduler getXxlJobDynamicScheduler(SchedulerFactoryBean schedulerFactory) {
        Scheduler scheduler = schedulerFactory.getScheduler();

        XxlJobDynamicScheduler xxlJobDynamicScheduler = new XxlJobDynamicScheduler();
        xxlJobDynamicScheduler.setScheduler(scheduler);

        return xxlJobDynamicScheduler;
    }

}
