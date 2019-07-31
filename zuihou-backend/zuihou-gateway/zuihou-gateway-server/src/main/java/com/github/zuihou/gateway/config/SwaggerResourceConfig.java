//
//package com.github.zuihou.gateway.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.github.zuihou.swagger2.SwaggerProperties;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.gateway.config.GatewayProperties;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.support.NameUtils;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//
///***
// * 资源配置
// * @since:swagger-bootstrap-ui 1.0
// * @author zuihou
// * @date 2019-07-25 22:13
// */
//@Component
//@Primary
//@Slf4j
//public class SwaggerResourceConfig implements SwaggerResourcesProvider {
//    @Autowired
//    private SwaggerProperties swaggerProperties;
//
//    @LoadBalanced
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    @Autowired
//    private GatewayProperties gatewayProperties;
//
//    @Autowired
//    RouteLocator routeLocator;
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Value("${server.servlet.context-path:/api}")
//    private String contextPath;
//
//    @Override
//    public List<SwaggerResource> get() {
//        String url = "/swagger-resources";
//        //获取所有router
//        List<SwaggerResource> resources = new ArrayList<>();
//
//        List<String> routes = new ArrayList<>();
//        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
//        gatewayProperties.getRoutes().stream()
//                .filter(routeDefinition -> routes.contains(routeDefinition.getId()))
//                .forEach(route -> {
//                    route.getPredicates().stream()
//                            .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
//                            .forEach(predicateDefinition ->
//                                    resources.add(
//                                            swaggerResource(route.getId(),
//                                                    contextPath+
//                                                    predicateDefinition.getArgs()
//                                                            .get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("**", "v2/api-docs"))
//                                    )
//                            );
//                });
//
//        if (swaggerProperties != null) {
//            resources.add(swaggerResource(swaggerProperties.getTitle(), "/v2/api-docs?group=" + swaggerProperties.getTitle()));
//        }
//        return resources;
//    }
//
//    private SwaggerResource swaggerResource(String name, String location) {
//        log.info("name:{},location:{}", name, location);
//        SwaggerResource swaggerResource = new SwaggerResource();
//        swaggerResource.setName(name);
//        swaggerResource.setLocation(location);
//        swaggerResource.setSwaggerVersion("2.0");
//        return swaggerResource;
//    }
//}
