package top.tangyh.lamp.gateway.rule;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import top.tangyh.basic.context.ContextConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;
import java.util.Map;

/**
 * 基于客户端版本号灰度路由
 *
 * @author zuihou
 * @date 2021年07月13日08:35:39
 */
@Slf4j
@AllArgsConstructor
public class GrayVersionLoadBalancer implements GrayscaleLoadBalancer {
    private DiscoveryClient discoveryClient;


    /**
     * 根据serviceId 筛选可用服务
     *
     * @param serviceId 服务ID
     * @param request   当前请求
     * @return
     */
    @Override
    public ServiceInstance choose(String serviceId, ServerHttpRequest request) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);

        //注册中心无实例 抛出异常
        if (CollUtil.isEmpty(instances)) {
            log.warn("nacos中没有找到可用的示例 {}", serviceId);
            throw new NotFoundException("nacos中没有找到可用的示例 " + serviceId);
        }

        // 获取请求 garyVersion，无则随机返回可用实例
        String grayVersion = request.getHeaders().getFirst(ContextConstants.GRAY_VERSION);
        if (StrUtil.isBlank(grayVersion)) {
            return instances.get(RandomUtil.randomInt(instances.size()));
        }

        // 遍历可以实例元数据，若匹配则返回此实例
        for (ServiceInstance instance : instances) {
            Map<String, String> metadata = instance.getMetadata();
            if (grayVersion.equalsIgnoreCase(metadata.get(ContextConstants.GRAY_VERSION))) {
                log.debug("灰度 匹配成功， 参数：{} 实例：{}", grayVersion, instance);
                return instance;
            }
        }
        return instances.get(RandomUtil.randomInt(instances.size()));
    }
}
