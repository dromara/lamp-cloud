package net.oschina.j2cache.autoconfigure;

import java.util.List;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;
import net.oschina.j2cache.cache.support.J2CacheCacheManger;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 开启对spring cache支持的配置入口
 *
 * @author zhangsaizz
 */
@Configuration
@ConditionalOnClass(J2Cache.class)
@EnableConfigurationProperties({J2CacheConfig.class, CacheProperties.class})
@ConditionalOnProperty(name = "j2cache.open-spring-cache", havingValue = "true")
@EnableCaching
public class J2CacheSpringCacheAutoConfiguration {

    private final CacheProperties cacheProperties;

    private final J2CacheConfig j2CacheConfig;

    J2CacheSpringCacheAutoConfiguration(CacheProperties cacheProperties, J2CacheConfig j2CacheConfig) {
        this.cacheProperties = cacheProperties;
        this.j2CacheConfig = j2CacheConfig;
    }

    @Bean
    @ConditionalOnBean(CacheChannel.class)
    public J2CacheCacheManger cacheManager(CacheChannel cacheChannel) {
        List<String> cacheNames = cacheProperties.getCacheNames();
        J2CacheCacheManger cacheCacheManger = new J2CacheCacheManger(cacheChannel);
        cacheCacheManger.setAllowNullValues(j2CacheConfig.isAllowNullValues());
        cacheCacheManger.setCacheNames(cacheNames);
        return cacheCacheManger;
    }


}
