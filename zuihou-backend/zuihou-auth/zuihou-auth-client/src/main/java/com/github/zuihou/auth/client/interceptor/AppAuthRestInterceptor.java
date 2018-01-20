package com.github.zuihou.auth.client.interceptor;


import com.github.zuihou.auth.client.annotation.IgnoreAppToken;
import com.github.zuihou.auth.client.config.AppAuthConfig;
import com.github.zuihou.auth.client.config.ServiceAuthConfig;
import com.github.zuihou.auth.client.jwt.AppAuthUtil;
import com.github.zuihou.auth.client.jwt.ServiceAuthUtil;
import com.github.zuihou.auth.common.jwt.IJWTInfo;
import com.github.zuihou.commons.context.BaseContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证第三方外部请求
 * Created by zuihou on 2017/9/10.
 *
 * @author zuihou
 */
public class AppAuthRestInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(AppAuthRestInterceptor.class);
    @Autowired
    private AppAuthUtil appAuthUtil;

    @Autowired
    private AppAuthConfig appAuthConfig;

    @Autowired
    private ServiceAuthUtil serviceAuthUtil;

    @Autowired
    private ServiceAuthConfig serviceAuthConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行用户拦截
        IgnoreAppToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreAppToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreAppToken.class);
        }
        if (annotation != null) {
            return super.preHandle(request, response, handler);
        }
        String isAdmin = request.getHeader(serviceAuthConfig.getAdminHeader());
        isAdmin = isAdmin == null || isAdmin.isEmpty() ?
                request.getParameter(serviceAuthConfig.getAdminHeader()) : isAdmin;
        String token = null;
        IJWTInfo infoFromToken = null;
        if ("true".equals(isAdmin)) {
            token = request.getHeader(serviceAuthConfig.getTokenHeader());
            infoFromToken = serviceAuthUtil.getInfoFromToken(token);
        } else {
            token = request.getHeader(appAuthConfig.getTokenHeader());
            infoFromToken = appAuthUtil.getInfoFromToken(token);
        }
        BaseContextHandler.setUserName(infoFromToken.getUserName());
        BaseContextHandler.setName(infoFromToken.getName());
        BaseContextHandler.setAdminId(infoFromToken.getAdminId());
        BaseContextHandler.setAppId(infoFromToken.getAppId());
        BaseContextHandler.setToken(token);
        log.info("token={} \\r\\n appId={}, userName={}", token, infoFromToken.getAppId(), infoFromToken.getUserName());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
