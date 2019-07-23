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

        String appName = environment.getProperty("spring.application.name");

        String logBase = environment.getProperty("LOGGING_PATH", "logs");
        // spring boot admin 直接加载日志
        System.setProperty("logging.file", String.format("%s/%s/debug.log", logBase, appName));
    }
}
