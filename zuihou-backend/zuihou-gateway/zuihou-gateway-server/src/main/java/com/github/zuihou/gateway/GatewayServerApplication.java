package com.github.zuihou.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author zuihou
 * @createTime 2017-12-13 15:02
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"com.github.zuihou.gateway.feign"})
@EnableZuulProxy
//@EnableScheduling
//@EnableZuihouAuthClient
//@EnableAceGateRateLimit
public class GatewayServerApplication {
    public static void main(String[] args) {
        //DBLog.getInstance().start();
        SpringApplication.run(GatewayServerApplication.class, args);
    }
}
