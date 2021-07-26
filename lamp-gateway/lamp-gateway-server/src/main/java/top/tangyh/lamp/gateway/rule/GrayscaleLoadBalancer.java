package top.tangyh.lamp.gateway.rule;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * 灰度路由
 *
 * @author zuihou
 * @date 2021年07月13日08:35:17
 */
public interface GrayscaleLoadBalancer {

    /**
     * 根据serviceId 筛选可用服务
     *
     * @param serviceId 服务ID
     * @param request   请求
     * @return
     */
    ServiceInstance choose(String serviceId, ServerHttpRequest request);
}
