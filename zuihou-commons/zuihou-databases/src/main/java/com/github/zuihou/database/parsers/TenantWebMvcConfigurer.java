package com.github.zuihou.database.parsers;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${zuihou.mysql.biz-database:zuihou_default}")
    private String databaseName;

    /**
     * 注册 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (getHandlerInterceptor() != null) {
            String[] commonPathPatterns = getExcludeCommonPathPatterns();
            registry.addInterceptor(getHandlerInterceptor())
                    .addPathPatterns("/**")
                    .order(9)
                    .excludePathPatterns(commonPathPatterns);
            WebMvcConfigurer.super.addInterceptors(registry);
        }
    }

    protected HandlerInterceptor getHandlerInterceptor() {
        return new TenantContextHandlerInterceptor(databaseName);
    }

    /**
     * auth-client 中的拦截器需要排除拦截的地址
     *
     * @return
     */
    protected String[] getExcludeCommonPathPatterns() {
        String[] urls = {
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
        return urls;
    }
}
