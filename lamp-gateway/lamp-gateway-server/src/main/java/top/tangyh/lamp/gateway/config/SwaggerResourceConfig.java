
package top.tangyh.lamp.gateway.config;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONArray;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private final GatewayProperties gatewayProperties;
    @Resource(name = "lbRestTemplate")
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    private ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    private final static String SWAGGER_RESOURCES_URI = "/swagger-resources";

    /**
     * 遍历网关的路由，并通过restTemplate访问后端服务的swagger信息，聚合后返回
     *
     * @return
     */
    @Override
    public List<SwaggerResource> get() {
        return gatewayProperties.getRoutes().stream().
                flatMap(this::swaggerResources).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private Stream<SwaggerResource> swaggerResources(RouteDefinition route) {
        try {
            if (route.getUri().getHost() == null) {
                return null;
            }
            // knife4j 官方提供的demo中，只能聚合group=default的文档，我这里做了增强，能聚合所有group
            List<ServiceInstance> instances = discoveryClient.getInstances(route.getUri().getHost());
            if (CollUtil.isEmpty(instances)) {
                return null;
            }
            // WebFlux异步调用，同步会报错
            Future future = executorService.submit(() ->
                    restTemplate.getForObject("http://" + route.getUri().getHost() + SWAGGER_RESOURCES_URI, JSONArray.class)
            );
            JSONArray list = (JSONArray) future.get();
            if (list.isEmpty()) {
                return null;
            }

            List<SwaggerResource> srList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                SwaggerResource sr = list.getObject(i, SwaggerResource.class);
                sr.setName(route.getId() + "-" + sr.getName());
                sr.setUrl("/" + route.getId() + sr.getUrl());
                srList.add(sr);
            }

            return srList.stream();
        } catch (Exception e) {
            log.debug("加载 {} 的swagger文档信息失败。 请确保该服务成功启动，并注册到了nacos。", route.getUri().getHost(), e);
        }
        return null;
    }

}
