package com.github.zuihou.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * 启动类
 *
 * 指定URL使用不同的采样率
 * https://blog.csdn.net/u012394095/article/details/82785745
 *
 * http://c.biancheng.net/view/5496.html
 *
 * @author zuihou
 * @createTime 2018-01-14 12:04
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class ZipkinApplication {
    /**
     * 请注意:
     * 默认情况下，各个服务是不会进行采集的，想要测试采集功能，务必修改如下配置：
     * 详细介绍参考： ZipkinClientConfiguration
     * <p>
     * 1， nacos 中common.yml
     * zuihou:
     * zipkin:
     * enabled: true
     * <p>
     * 2, nacos 中 rabbitmq.yml
     * zuihou:
     * rabbitmq:
     * enabled: true
     * ip: 127.0.0.1
     * port: 5672
     * username: zuihou
     * password: zuihou
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }
}
