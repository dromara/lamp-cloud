package com.github.zuihou.executor;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */
@Configuration
@ComponentScan(basePackages = "com.github.zuihou.executor.jobhandler")
public class XxlJobConfig {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;
    @Value("${xxl.job.executor.registryLazy}")
    private long registryLazy;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;


    /**
     * 执行器 启动时，构建【XxlJobSpringExecutor】，并执行如下动作
     * 1， 扫描构建器中JobHandler，并缓存在内存中【XxlJobExecutor.jobHandlerRepository】
     * 2， 刷新 GlueFactory
     * 3， 初始化日志路径
     * // init logpath
     * <p>
     * 4, 初始化 调度器客户端， 用于执行回调方法
     * // init admin-client
     * <p>
     * <p>
     * 5， 初始化 日志文件清理线程
     * // init JobLogFileCleanThread
     * <p>
     * 6, 初始化 调度器回调线程
     * // init TriggerCallbackThread
     * <p>
     * 7，初始化执行器服务端
     * // init executor-server
     *
     * @return
     */
    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppName(appName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setRegistryLazy(registryLazy);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }

}
