package com.github.zuihou.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import com.github.zuihou.base.Result;
import com.github.zuihou.common.adapter.IgnoreTokenConfig;
import com.github.zuihou.utils.JSONUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.web.util.UrlPathHelper;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2018/11/23
 */
@Slf4j
public abstract class BaseFilter extends ZuulFilter {
    /**
     * 为zuul设置一个公共的前缀
     */
    @Value("${server.servlet.context-path}")
    protected String zuulPrefix;
    @Value("${spring.profiles.active:dev}")
    protected String profiles;
    protected UrlPathHelper URL_PATH_HELPER = new UrlPathHelper();
    @Autowired
    protected RouteLocator routeLocator;

    protected boolean isDev() {
        return !"prod".equalsIgnoreCase(profiles);
    }

    /**
     * 获取route
     *
     * @return
     */
    protected Route route() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String requestURI = URL_PATH_HELPER.getPathWithinApplication(request);
        return routeLocator.getMatchingRoute(requestURI);
    }

    private String getUri() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();
        uri = StringUtils.substring(uri, zuulPrefix.length());
        uri = StringUtils.substring(uri, uri.indexOf("/", 1));
        return uri;
    }

    /**
     * 忽略应用级token
     *
     * @return
     */
    protected boolean isIgnoreToken() {
        return IgnoreTokenConfig.isIgnoreToken(getUri());
    }

    protected String getTokenFromRequest(String headerName, HttpServletRequest request) {
        String token = request.getHeader(headerName);
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(headerName);
        }
        return token;
    }


    /**
     * 网关抛异常
     *
     * @param body
     * @param code
     */
    protected void setFailedRequest(String body, int code) {
        log.debug("Reporting error ({}): {}", code, body);
        RequestContext ctx = RequestContext.getCurrentContext();
        // 返回错误码
        ctx.setResponseStatusCode(code);
        ctx.addZuulResponseHeader("Content-Type", "application/json;charset=UTF-8");
        if (ctx.getResponseBody() == null) {
            // 返回错误内容
            ctx.setResponseBody(body);
            // 过滤该请求，不对其进行路由
            ctx.setSendZuulResponse(false);
        }
    }

    protected void errorResponse(String errMsg, int errCode, int httpStatusCode) {
        Result tokenError = Result.fail(errCode, errMsg);
        setFailedRequest(JSONUtils.toJsonString(tokenError), httpStatusCode);
    }
}
