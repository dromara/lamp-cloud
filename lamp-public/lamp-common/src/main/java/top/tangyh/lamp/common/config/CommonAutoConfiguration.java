package top.tangyh.lamp.common.config;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.tangyh.lamp.common.aspect.LampLogAspect;
import top.tangyh.lamp.common.cache.CacheKeyModular;
import top.tangyh.lamp.common.properties.SystemProperties;

/**
 * @author tangyh
 * @version v1.0
 * @date 2021/9/5 8:04 下午
 * @create [2021/9/5 8:04 下午 ] [tangyh] [初始创建]
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnWebApplication
@EnableConfigurationProperties(SystemProperties.class)
public class CommonAutoConfiguration {
    private final SystemProperties systemProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = SystemProperties.PREFIX, name = "recordLamp", havingValue = "true", matchIfMissing = true)
    public LampLogAspect getLampLogAspect() {
        return new LampLogAspect(systemProperties);
    }

    @PostConstruct
    public void init() {
        if (StrUtil.isNotEmpty(systemProperties.getCachePrefix())) {
            CacheKeyModular.PREFIX = systemProperties.getCachePrefix();
            log.info("检查到配置文件中：{}.cachePrefix={}", SystemProperties.PREFIX, systemProperties.getCachePrefix());
        }
    }

}
