package com.github.zuihou.file;

import com.github.zuihou.auth.client.EnableZuihouAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zuihou
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableTransactionManagement
@EnableZuihouAuthClient
public class FileManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileManagerApplication.class, args);
    }
}
