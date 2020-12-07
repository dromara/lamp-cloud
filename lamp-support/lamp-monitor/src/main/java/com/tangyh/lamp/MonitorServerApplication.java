package com.tangyh.lamp;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zuihou
 * @date 2018-01-14 11:11
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
@ComponentScan({
        "com.tangyh.lamp", "com.tangyh.basic"
})
public class MonitorServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorServerApplication.class, args);
    }
}
