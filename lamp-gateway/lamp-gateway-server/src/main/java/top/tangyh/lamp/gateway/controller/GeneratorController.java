package top.tangyh.lamp.gateway.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.result.view.Rendering;
import top.tangyh.basic.base.R;
import top.tangyh.lamp.model.vo.result.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 常用Controller
 *
 * @author zuihou
 * @date 2019-06-21 18:22
 */
@Controller
public class GeneratorController {

    @Value("${server.servlet.context-path:}")
    private String contextPath;
    @Value("${spring.application.name:}")
    private String application;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private GatewayProperties gatewayProperties;

    /**
     * 兼容zuul 文档
     */
    @GetMapping("/gate/doc.html")
    public Rendering doc() {
        String uri = String.format("%s/doc.html", contextPath);
        return Rendering.redirectTo(uri).build();
    }


    @ResponseBody
    @Operation(summary = "查询在线服务的前缀")
    @GetMapping("/gateway/findOnlineServicePrefix")
    public R<Map<String, String>> findOnlineServicePrefix() {
        List<String> services = discoveryClient.getServices();

        Map<String, String> map = MapUtil.newHashMap();
//        map.put(application, "gateway");
        services.forEach(service ->
                gatewayProperties.getRoutes().forEach(route -> {
                    if (StrUtil.equalsIgnoreCase(service, route.getUri().getHost())) {
                        if (CollUtil.isEmpty(route.getPredicates())) {
                            return;
                        }
                        PredicateDefinition predicateDefinition = route.getPredicates().get(0);
                        predicateDefinition.getArgs().forEach((k, v) -> {
                            map.put(service, StrUtil.subBetween(v, "/", "/**"));
                        });
                    }
                })
        );
        return R.success(map);
    }

    @ResponseBody
    @Operation(summary = "查询在线服务")
    @GetMapping("/gateway/findOnlineService")
    public R<List<Option>> findOnlineService() {
        List<String> services = discoveryClient.getServices();

        List<Option> list = new ArrayList();
        services.forEach(service ->
                gatewayProperties.getRoutes().forEach(route -> {
                    if (StrUtil.equalsIgnoreCase(service, route.getUri().getHost())) {
                        if (CollUtil.isEmpty(route.getPredicates())) {
                            return;
                        }
                        PredicateDefinition predicateDefinition = route.getPredicates().get(0);
                        predicateDefinition.getArgs().forEach((k, v) -> {
                            list.add(Option.builder()
                                    .value(StrUtil.subBetween(v, "/", "/**")).text(service).label(service)
                                    .build());
                        });
                    }
                })
        );
        return R.success(list);
    }
}
