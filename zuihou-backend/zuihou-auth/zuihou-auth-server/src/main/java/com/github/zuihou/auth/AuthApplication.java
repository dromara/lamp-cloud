package com.github.zuihou.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zuihou
 * @createTime 2018-01-18 21:13
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.github.zuihou.auth"})
@ComponentScan(basePackages = {
        "com.github.zuihou.admin.repository.account.service",
        "com.github.zuihou.admin.repository.authority.service",
        "com.github.zuihou.base.id",
})
@RemoteApplicationEventScan(basePackages = "com.github.zuihou.auth.common.event")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }
}
