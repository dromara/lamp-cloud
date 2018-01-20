package com.github.zuihou.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zuihou
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZuihouFileManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuihouFileManagerApplication.class, args);
    }
}
