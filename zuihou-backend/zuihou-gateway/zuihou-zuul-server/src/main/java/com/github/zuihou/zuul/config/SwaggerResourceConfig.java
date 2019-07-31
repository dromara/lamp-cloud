package com.github.zuihou.zuul.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.github.zuihou.swagger2.SwaggerProperties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
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
    private SwaggerProperties swaggerProperties;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

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

        if (swaggerProperties != null) {
            resources.add(swaggerResource(swaggerProperties.getTitle(), "/v2/api-docs?group=" + swaggerProperties.getTitle()));
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
