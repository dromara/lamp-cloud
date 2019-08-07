package com.github.zuihou.cache.properties;

import java.time.Duration;
import java.util.Map;

import lombok.Data;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 主要用来设置通过 @Cacheable 注解标注的方法的缓存策略
 *
 * @author zuihou
 * @date 2019/08/06
 */
@Data
@ConfigurationProperties(prefix = "zuihou.cache")
public class CustomCacheProperties {

    /**
     * 目前只支持 redis 和 CAFFEINE ！
     * CAFFEINE 只用于项目的开发环境或者演示环境使用， 生产环境请用redis！！！
     */
    private CacheType type = CacheType.REDIS;

    /**
     * 全局配置
     */
    private Cache def = new Cache();

    /**
     * 针对某几个具体的key特殊配置
     *
     * 改属性只对 redis 有效！！！
     * configs的key需要配置成@Cacheable注解的value
     */
    private Map<String, Cache> configs;

    @Data
    public static class Cache {

        /**
         * key 的过期时间
         * 默认1天过期
         * eg:
         * timeToLive: 1d
         */
        private Duration timeToLive = Duration.ofDays(1);

        /**
         * 是否允许缓存null值
         */
        private boolean cacheNullValues = true;

        /**
         * key 的前缀
         * 最后的key格式： keyPrefix + @Cacheable.value + @Cacheable.key
         *
         * 使用场景： 开发/测试环境 或者 演示/生产 环境，为了节省资源，往往会共用一个redis，即可以根据key前缀来区分(当然，也能用切换 database 来实现)
         */
        private String keyPrefix;

        /**
         * 写入redis时，是否使用key前缀
         */
        private boolean useKeyPrefix = true;

        /**
         * Caffeine 的最大缓存个数
         */
        private int maxSize = 1000;


    }

}
