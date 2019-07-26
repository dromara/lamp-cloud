///*
// * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
// * All rights reserved.
// * Official Web Site: http://www.xiaominfo.com.
// * Developer Web Site: http://open.xiaominfo.com.
// */
//
//package com.github.zuihou.gateway.config;
//
//import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.web.client.RestTemplate;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///***
// * 配置
// *
// * @since: swagger-bootstrap-ui 1.0
// * @author zuihou
// * @date 2019-07-25 22:13
// * 2019/05/04 12:44
// */
//@EnableSwagger2
//@EnableSwaggerBootstrapUI
//@Configuration
//public class SwaggerConfig {
//    @Value("http://${zuihou.ip.nginx}:${zuihou.port.nginx}")
//    private String host;
//
//
//    @Bean
//    @Order(value = 1)
//    public Docket groupRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .host(host)
//                .apiInfo(groupApiInfo());
//    }
//
//    private ApiInfo groupApiInfo() {
//        return new ApiInfoBuilder()
//                .title("zuihou网关统一Swagger文档")
//                .description("<div style='font-size:14px;color:red;'>zuihou网关统一Swagger文档</div>")
//                .termsOfServiceUrl("http://wzroom.cn/api/gate/doc.html")
//                .contact(new Contact("zuihou", "http://wzroom.cn/api/gate/doc.html", "244387066@qq.com"))
//                .version("1.0")
//                .build();
//    }
//}
