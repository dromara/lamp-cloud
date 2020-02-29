package com.github.zuihou.zuul.filter.pre;

import cn.hutool.core.util.StrUtil;
import com.github.zuihou.filter.BaseFilter;
import com.github.zuihou.utils.StrPool;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * 使得网关支持同一服务的多版本化。
 * 诞生背景：
 * 本地开发环境，每次开发测试都需要启动整个项目，对于开发环境的电脑配置有很大的要求,
 * 如果能想要调试那个服务，就只启动那个服务，跟测试环境共用一套eureka，并且能将每个请求准确路由到自己的服务就完美了。
 * <p>
 * 使用场景:
 * 用户zuihou，在启动 zuihou-authority-server 时， 将改成 spring.application.name: zuihou-authority-server-zuihou ，
 * 通过在请求每个请求头中增加 serviceSuffix=zuihou , 请求就能通过网关路由到 zuihou 的机器上
 * <p>
 * 该过滤器请勿使用在正式环境
 *
 * @author zuihou
 */
@Component
@Slf4j
public class MultiVersionServerSupportFilter extends BaseFilter {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public Object run() {
        Route route = route();

        RequestContext ctx = RequestContext.getCurrentContext();
        final String requestURI = URL_PATH_HELPER.getPathWithinApplication(ctx.getRequest());
        String version = ctx.getRequest().getHeader("serviceSuffix");

        if (StrUtil.isEmpty(version)) {
            version = ctx.getRequest().getParameter("serviceSuffix");
        }

        StringBuilder serviceId = new StringBuilder(route.getLocation());
        if (StrUtil.isNotEmpty(version)) {
            serviceId.append("-");
            serviceId.append(version);
        }
        String serviceIdStr = serviceId.toString();
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceIdStr);
        log.debug("serviceIdStr={}, size={}", serviceIdStr, instances.size());
        if (!instances.isEmpty()) {
            ctx.put(REQUEST_URI_KEY, requestURI.substring(requestURI.indexOf('/', 1)));
            ctx.set(SERVICE_ID_KEY, serviceIdStr);
            ctx.setRouteHost(null);
            ctx.addOriginResponseHeader(SERVICE_ID_HEADER, serviceIdStr);

        }
        return null;
    }


    @Override
    public boolean shouldFilter() {
        return !StrPool.PROD.equalsIgnoreCase(profiles);
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER + 2;
    }

}

