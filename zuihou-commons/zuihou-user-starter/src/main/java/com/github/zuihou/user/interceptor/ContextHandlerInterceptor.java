package com.github.zuihou.user.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.zuihou.context.BaseContextConstants;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.utils.StrHelper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 网关：
 * 获取token，并解析，然后将所有的用户、应用信息封装到请求头
 * <p>
 * 拦截器：
 * 解析请求头数据， 将用户信息、应用信息封装到BaseContextHandler
 * 考虑请求来源是否网关（ip等）
 * <p>
 * Created by zuihou on 2017/9/10.
 *
 * @author zuihou
 * @date 2019-06-20 22:22
 */
@Slf4j
public class ContextHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if (!(handler instanceof HandlerMethod)) {
                log.info("not exec!!! url={}", request.getRequestURL());
                return super.preHandle(request, response, handler);
            }
//            HandlerMethod handlerMethod = (HandlerMethod) handler;

            String userId = getHeader(request, BaseContextConstants.JWT_KEY_USER_ID);
            String account = getHeader(request, BaseContextConstants.JWT_KEY_ACCOUNT);
            String name = getHeader(request, BaseContextConstants.JWT_KEY_NAME);
            String orgId = getHeader(request, BaseContextConstants.JWT_KEY_ORG_ID);
            String stationId = getHeader(request, BaseContextConstants.JWT_KEY_STATION_ID);
            BaseContextHandler.setUserId(userId);
            BaseContextHandler.setAccount(account);
            BaseContextHandler.setName(name);
            BaseContextHandler.setOrgId(orgId);
            BaseContextHandler.setStationId(stationId);
        } catch (Exception e) {

            log.warn("解析token信息时，发生异常. ", e);
        }
        return super.preHandle(request, response, handler);
    }

    private String getHeader(HttpServletRequest request, String name) {
        String value = request.getHeader(name);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return StrHelper.decode(value);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        log.info("拦截器结束，清除上下文中的值");
        super.afterCompletion(request, response, handler, ex);
    }

}
