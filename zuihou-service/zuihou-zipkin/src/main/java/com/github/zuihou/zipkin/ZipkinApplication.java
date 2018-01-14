package com.github.zuihou.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

/**
 * https://my.oschina.net/roccn/blog/834278
 * 启动类
 * @author tyh
 * @createTime 2018-01-14 12:04
 */
@SpringBootApplication
@EnableDiscoveryClient //注册到eureka
@EnableZipkinStreamServer //使用Stream方式启动ZipkinServer
public class ZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }
}
