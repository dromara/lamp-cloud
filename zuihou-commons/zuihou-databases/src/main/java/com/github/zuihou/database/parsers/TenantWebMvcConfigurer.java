package com.github.zuihou.database.parsers;

import cn.hutool.core.util.ArrayUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 多租户配置
 *
 * @author zuihou
 * @date 2019/10/23
 */
public class TenantWebMvcConfigurer implements WebMvcConfigurer {


    private static final String[] DEF_URLS = {
            "/error",
            "/login",
            "/v2/api-docs",
            "/v2/api-docs-ext",
            "/swagger-resources/**",
            "/webjars/**",

            "/",
            "/csrf",

            "/META-INF/resources/**",
            "/resources/**",
            "/static/**",
            "/public/**",
            "classpath:/META-INF/resources/**",
            "classpath:/resources/**",
            "classpath:/static/**",
            "classpath:/public/**",

            "/cache/**",
            "/swagger-ui.html**",
            "/doc.html**"
    };

    private final String databaseName;

    private String[] exclude;
    private Integer order;

    public TenantWebMvcConfigurer(String databaseName, String[] exclude, Integer order) {
        this.databaseName = databaseName;
        this.exclude = exclude;
        this.order = order;
        if (ArrayUtil.isEmpty(this.exclude)) {
            this.exclude = DEF_URLS;
        }
        if (this.order == null) {
            this.order = 9;
        }
    }


    /**
     * 注册 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (getHandlerInterceptor() != null) {
            registry.addInterceptor(getHandlerInterceptor())
                    .addPathPatterns("/**")
                    .order(order)
                    .excludePathPatterns(exclude);
            WebMvcConfigurer.super.addInterceptors(registry);
        }
    }

    protected HandlerInterceptor getHandlerInterceptor() {
        return new TenantContextHandlerInterceptor(databaseName);
    }

}
