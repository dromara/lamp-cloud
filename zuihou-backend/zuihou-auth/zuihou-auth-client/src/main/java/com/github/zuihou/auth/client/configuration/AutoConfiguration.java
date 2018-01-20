package com.github.zuihou.auth.client.configuration;

import com.github.zuihou.auth.client.config.AppAuthConfig;
import com.github.zuihou.auth.client.config.ServiceAuthConfig;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zuihou
 * @createTime 2017-12-13 15:27
 */
@Configuration
@ComponentScan({"com.github.zuihou.auth.client", "com.github.zuihou.auth.common.event"})
@RemoteApplicationEventScan(basePackages = "com.github.zuihou.auth.common.event")
public class AutoConfiguration {
    @Bean
    ServiceAuthConfig getServiceAuthConfig() {
        return new ServiceAuthConfig();
    }

    @Bean
    AppAuthConfig getUserAuthConfig() {
        return new AppAuthConfig();
    }
}