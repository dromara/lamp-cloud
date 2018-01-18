package com.github.zuihou.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zuihou
 * @createTime 2018-01-13 1:34
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableTransactionManagement
//@ComponentScan(excludeFilters = {
//    @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.github.zuihou.admin.api.[a-zA-Z]+.api.hystrix[.a-zA-Z]+")
//})
//@EnableZuihouAuthClient
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
