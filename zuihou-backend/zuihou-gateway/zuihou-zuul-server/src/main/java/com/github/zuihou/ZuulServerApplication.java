package com.github.zuihou;

import com.github.zuihou.auth.client.EnableAuthClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zuihou
 * @createTime 2017-12-13 15:02
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"com.github.zuihou"})
@EnableZuulProxy
@EnableAuthClient
public class ZuulServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
}
