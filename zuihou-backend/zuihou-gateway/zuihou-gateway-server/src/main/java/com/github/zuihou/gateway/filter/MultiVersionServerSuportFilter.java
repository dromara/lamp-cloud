package com.github.zuihou.gateway.filter;

import java.util.List;

import com.github.zuihou.utils.StrPool;
import com.netflix.zuul.context.RequestContext;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.REQUEST_URI_KEY;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_HEADER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;

/**
 * 使得网关支持同一服务的多版本化。
 *
 * @author 潘定遥
 */
@Component
@Slf4j
public class MultiVersionServerSuportFilter extends BaseFilter {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${spring.profiles.active}")
    private String active;

    public MultiVersionServerSuportFilter() {

    }

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
        RequestContext ctx = RequestContext.getCurrentContext();
        boolean flag =
                !ctx.containsKey(FilterConstants.FORWARD_TO_KEY) &&
                        !ctx.containsKey(SERVICE_ID_KEY) &&
                        !StrPool.PROD.equalsIgnoreCase(active);
        return flag;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER + 1;
    }

}

