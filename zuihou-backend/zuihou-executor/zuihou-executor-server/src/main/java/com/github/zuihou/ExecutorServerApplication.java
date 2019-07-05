package com.github.zuihou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 执行器服务
 *
 * @author zuihou
 * @createTime 2018-01-25 10:13
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class ExecutorServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExecutorServerApplication.class, args);
    }
}
