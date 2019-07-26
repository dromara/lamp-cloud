/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.xiaominfo.com.
 * Developer Web Site: http://open.xiaominfo.com.
 */

package com.github.zuihou.gateway.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/***
 * 资源配置
 * @since:swagger-bootstrap-ui 1.0
 * @author zuihou
 * @date 2019-07-25 22:13
 */
@Component
@Primary
@Slf4j
public class SwaggerResourceConfig implements SwaggerResourcesProvider {


    @Autowired
    RouteLocator routeLocator;
    @Autowired
    RestTemplate restTemplate;

    @Value("${server.servlet.context-path:/api}")
    private String contextPath;

    @Override
    public List<SwaggerResource> get() {
        String url = "/swagger-resources";

        //获取所有router
        List<SwaggerResource> resources = new ArrayList<>();
        List<Route> routes = routeLocator.getRoutes();
        log.info("Route Size:{}", routes.size());
        Set<String> services = new HashSet<>();
        for (Route route : routes) {
            if (services.contains(route.getLocation())) {
                continue;
            }
            try {
                JSONArray list = restTemplate.getForObject("http://" + route.getLocation() + url, JSONArray.class);
                if (!list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        SwaggerResource sr = list.getObject(i, SwaggerResource.class);
                        resources.add(swaggerResource(route.getId() + "-" + sr.getName(), contextPath + route.getPrefix() + sr.getUrl()));
                    }
                }
                services.add(route.getLocation());
            } catch (Exception e) {
                log.info(route.getLocation() + "服务尚未启动,无法获取swagger信息");
            }
        }
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        log.info("name:{},location:{}", name, location);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
