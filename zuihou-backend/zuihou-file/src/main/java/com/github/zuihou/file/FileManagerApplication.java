package com.github.zuihou.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zuihou
 */
@SpringBootApplication
@EnableDiscoveryClient
public class FileManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileManagerApplication.class, args);
    }
}
