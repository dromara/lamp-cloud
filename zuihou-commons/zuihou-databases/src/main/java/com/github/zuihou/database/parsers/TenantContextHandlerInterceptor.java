package com.github.zuihou.database.parsers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.zuihou.context.BaseContextConstants;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.utils.StrHelper;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 租户信息解析器
 *
 * @author tyh
 * @date 2019-06-20 22:22
 */
@Slf4j
@AllArgsConstructor
public class TenantContextHandlerInterceptor extends HandlerInterceptorAdapter {

    private String databaseName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            log.info("not exec!!! url={}", request.getRequestURL());
            return super.preHandle(request, response, handler);
        }
        BaseContextHandler.setDatabase(databaseName);
        BaseContextHandler.setTenant(getHeader(request, BaseContextConstants.TENANT));
        return super.preHandle(request, response, handler);
    }

    private String getHeader(HttpServletRequest request, String name) {
        String value = request.getHeader(name);
        if (StrUtil.isEmpty(value)) {
            value = request.getParameter(name);
        }
        if (StrUtil.isEmpty(value)) {
            return StrUtil.EMPTY;
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
