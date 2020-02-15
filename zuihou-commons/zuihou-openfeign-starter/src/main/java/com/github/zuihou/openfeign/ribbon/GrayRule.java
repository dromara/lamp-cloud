package com.github.zuihou.openfeign.ribbon;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.github.zuihou.context.BaseContextHandler;
import com.google.common.base.Optional;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.zuihou.context.BaseContextConstants.GRAY_VERSION;

/**
 * 负载均衡策略：可用于灰度发布 & 访问指定服务
 * <p>
 * 若请求头中的 grayversion 若为空， 则优先从服务元数据中 没有设置 grayversion 的服务中获取，若所有服务均设置了 grayversion， 则从设置了 grayversion 的服务列表轮训
 * 若请求头中的 grayversion 若不为空， 则优先从服务元数据中 设置了 grayversion 的服务中获取，若所有服务均没有了 grayversion， 则从 所有的服务列表轮训
 */
@Slf4j
public class GrayRule extends AvailabilityFilteringRule {

    @Override
    public Server choose(Object key) {

        List<Server> serverList = this.getPredicate().getEligibleServers(this.getLoadBalancer().getAllServers(), key);
        if (CollUtil.isEmpty(serverList)) {
            return null;
        }

        String serviceVersion = BaseContextHandler.get(GRAY_VERSION);
        log.debug("======>GrayMetadataRule:  serviceVersionTL{}", serviceVersion);

        List<Server> noMetaServerList = new ArrayList<>();
        List<Server> metaServerList = new ArrayList<>();
        for (Server server : serverList) {
            Map<String, String> metadata = ((NacosServer) server).getInstance().getMetadata();
            // version策略
            String metaVersion = metadata.get(GRAY_VERSION);
            if (StrUtil.isNotEmpty(metaVersion)) {
                metaServerList.add(server);
            } else {
                noMetaServerList.add(server);
            }
        }

        if (StrUtil.isEmpty(serviceVersion)) {
            if (noMetaServerList.isEmpty()) {
                return originChoose(metaServerList, key, serviceVersion);
            }

            log.debug("====> 请求未指定服务版本，将无版本号的服务进行负载均衡");
            return originChoose(noMetaServerList, key, serviceVersion);
        }

        Map<String, List<Server>> listMap = metaServerList.stream().collect(
                Collectors.groupingBy(server -> ((NacosServer) server).getInstance().getMetadata().get(GRAY_VERSION)));
        List<Server> servers = listMap.get(serviceVersion);

        // 前端传递错误的 GRAY_VERSION
        if (CollUtil.isEmpty(servers)) {
            return originChoose((List<Server>) CollUtil.addAll(metaServerList, noMetaServerList), key, serviceVersion);
        }
        return originChoose(servers, key, serviceVersion);

    }

    private Server originChoose(List<Server> serverList, Object key, String serviceVersion) {
        if (CollUtil.isEmpty(serverList)) {
            log.error("====> 版本号:{}对应的服务列表为空，无法进行负载均衡", serviceVersion);
            return null;
        }
        //默认的轮训规则
        Optional<Server> server = getPredicate().chooseRoundRobinAfterFiltering(serverList, key);
        if (server.isPresent()) {
            return server.get();
        } else {
            return null;
        }
    }
}
