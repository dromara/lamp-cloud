package com.github.zuihou.log.init;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 通过环境变量的形式注入 logging.file
 * 自动维护 Spring Boot Admin Logger Viewer
 *
 * @author zuihou
 * @date 2019-06-25
 */
public class ApplicationLoggerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String logBase = environment.getProperty("logging.path", "/data/projects/logs");
        String appName = environment.getProperty("spring.application.name");
        // spring boot admin 直接加载日志
        System.setProperty("logging.file", String.format("%s/%s/root.log", logBase, appName));

        // nacos的日志文件路径
        System.setProperty("nacos.logging.path", String.format("%s/%s", logBase, appName));
        //这里设置了无效，跟启动时，传递 -Dcom.alibaba.nacos.naming.log.level=warn 一样，可能是nacos的bug
//        System.setProperty("com.alibaba.nacos.naming.log.level", "warn");
//        System.setProperty("com.alibaba.nacos.config.log.level", "info");
    }
}
