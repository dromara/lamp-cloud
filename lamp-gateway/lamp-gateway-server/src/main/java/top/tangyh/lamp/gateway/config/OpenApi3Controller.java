package top.tangyh.lamp.gateway.config;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import top.tangyh.basic.utils.StrPool;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Swagger配置
 *
 * @author zuihou
 * @date 2019/07/31
 */
@RestController
@Slf4j
public class OpenApi3Controller {
    public final static String SWAGGER_RESOURCES_URI = "/v3/api-docs/swagger-config";

    @Autowired
    private GatewayProperties gatewayProperties;
    @Resource(name = "lbRestTemplate")
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${server.servlet.context-path:/api}")
    private String contextPath;

    private ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());


    @GetMapping(SWAGGER_RESOURCES_URI)
    public Mono<ResponseEntity> swaggerResources() {
        return Mono.just((new ResponseEntity<>(get(), HttpStatus.OK)));
    }


    private Map<String, Object> get() {
        List<Map<String, Object>> list = gatewayProperties.getRoutes().stream().
                map(this::swaggerConfig).filter(Objects::nonNull).collect(Collectors.toList());
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<String, Object> map = new HashMap<>();
        map.putAll(list.get(0));

        for (int i = 1; i < list.size(); i++) {
            Map<String, Object> item = list.get(i);
            if (map.containsKey("urls")) {
                List urls = (List) item.get("urls");
                ((List) map.get("urls")).addAll(urls != null ? urls : Collections.emptyList());
            } else {
                List urls = (List) item.get("urls");
                map.put("urls", urls != null ? urls : Collections.emptyList());
            }
        }

        return map;
    }

    private Map<String, Object> swaggerConfig(RouteDefinition route) {
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
            Future<Map<String, Object>> future = executorService.submit(() ->
                    restTemplate.getForObject("http://" + route.getUri().getHost() + SWAGGER_RESOURCES_URI, Map.class)
            );
            Map<String, Object> map = future.get();
            if (map.isEmpty()) {
                return null;
            }
            map.forEach((k, v) -> {
                if (k.equals("urls")) {
                    if (v instanceof List) {
                        List<Map<String, Object>> list = (List<Map<String, Object>>) v;
                        for (Map<String, Object> item : list) {
                            String url = (String) item.get("url");
                            item.put("url", contextPath + StrPool.SLASH + route.getId() + url);
                        }

                    }
                }
            });

            return map;
        } catch (Exception e) {
            log.debug("加载 {} 的swagger文档信息失败。 请确保该服务成功启动，并注册到了nacos。", route.getUri().getHost(), e);
        }
        return null;
    }


}

