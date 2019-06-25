package com.github.zuihou.center;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({
        "com.github.zuihou",
})
@EnableHystrix
public class ManageCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCenterApplication.class, args);
    }
}
