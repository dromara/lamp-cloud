package com.github.zuihou.center.interceptor;

import com.github.zuihou.auth.cache.TokenCache;
import com.github.zuihou.center.shiro.PrincipalUtils;
import com.github.zuihou.center.shiro.ShiroPrincipal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


public class TokenSetInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        ShiroPrincipal account = PrincipalUtils.getAccount();
        if (account != null) {
            Optional<String> optional = TokenCache.of(account.getAppId(), account.getUserName()).get();
            if (optional.isPresent()) {
                request.setAttribute("_token", optional.get());
            }
        }
    }

}
