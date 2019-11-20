package com.github.zuihou.openfeign.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.zuihou.context.BaseContextConstants;
import com.github.zuihou.context.BaseContextHandler;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * feign client 拦截器， 实现将 feign 调用方的 请求头封装到 被调用方的请求头
 *
 * @author zuihou
 * @date 2019-07-25 11:23
 */
@Slf4j
public class FeignAddHeaderRequestInterceptor implements RequestInterceptor {

    private static final List<String> HEADER_NAME_LIST = Arrays.asList(
            BaseContextConstants.TENANT, BaseContextConstants.JWT_KEY_USER_ID,
            BaseContextConstants.JWT_KEY_ACCOUNT, BaseContextConstants.JWT_KEY_NAME,
            BaseContextConstants.JWT_KEY_STATION_ID, BaseContextConstants.JWT_KEY_ORG_ID
    );

    public FeignAddHeaderRequestInterceptor() {
        super();
    }

    @Override
    public void apply(RequestTemplate template) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
//            log.warn("当前服务未配置OpenFeignAutoConfiguration类 或 当前调用不属于HTTP调用(如记录操作日志接口)， 故而无法在远程调用时获取请求头中的参数.");

            HEADER_NAME_LIST.forEach((headerName) -> template.header(headerName, BaseContextHandler.get(headerName)));
            return;
        }

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        if (request == null) {
            log.warn("当前服务未配置OpenFeignAutoConfiguration类 或 当前调用不属于HTTP调用(如记录操作日志接口)， 故而无法在远程调用时获取请求头中的参数.");
            return;
        }
        HEADER_NAME_LIST.forEach((headerName) -> template.header(headerName, request.getHeader(headerName)));

    }
}
