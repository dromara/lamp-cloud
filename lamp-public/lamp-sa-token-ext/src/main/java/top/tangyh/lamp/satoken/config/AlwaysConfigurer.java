package top.tangyh.lamp.satoken.config;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.tangyh.lamp.common.properties.SystemProperties;
import top.tangyh.lamp.satoken.interceptor.NotAllowWriteInterceptor;

/**
 * 永远执行的配置
 *
 * @author zuihou
 * @date 2018/8/25
 */
@RequiredArgsConstructor
public class AlwaysConfigurer implements WebMvcConfigurer {
    private final SystemProperties systemProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new NotAllowWriteInterceptor(systemProperties))
                .addPathPatterns("/**")
                .order(Integer.MAX_VALUE);
    }
}
