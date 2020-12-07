
package com.tangyh.lamp.gateway.config;

import com.alibaba.fastjson.JSONArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/***
 * 资源配置
 * @since:swagger-bootstrap-ui 1.0
 * @author zuihou
 * @date 2019-07-25 22:13
 */
@Component
@Primary
@Slf4j
@RequiredArgsConstructor
public class SwaggerResourceConfig implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;
    @Resource(name = "lbRestTemplate")
    private RestTemplate restTemplate;

    /**
     * 遍历网关的路由，并通过restTemplate访问后端服务的swagger信息，聚合后返回
     *
     * @return
     */
    @Override
    public List<SwaggerResource> get() {
        String url = "/swagger-resources?lamp=";
        //获取所有router
        List<SwaggerResource> resources = new ArrayList<>();

        List<String> routes = new ArrayList<>();
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        gatewayProperties.getRoutes().stream()
                .filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(route -> {
                    route.getPredicates().stream()
                            .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                            .forEach(predicateDefinition -> {
                                        try {
                                            // knife4j 官方提供的demo中，只能聚合group=default的文档，我这里做了增强，能聚合所有group
                                            JSONArray list = restTemplate.getForObject("http://" + route.getUri().getHost() + url, JSONArray.class);
                                            if (!list.isEmpty()) {
                                                for (int i = 0; i < list.size(); i++) {
                                                    SwaggerResource sr = list.getObject(i, SwaggerResource.class);
                                                    resources.add(swaggerResource(route.getId() + "-" + sr.getName(), "/" + route.getId() + sr.getUrl()));
                                                }
                                            }
                                        } catch (Exception e) {
                                            log.warn("加载后端资源时失败{}", route.getUri().getHost(), e);
                                        }
                                    }

                            );
                });

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
