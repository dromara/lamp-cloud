package com.github.zuihou.redis;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.github.zuihou.lock.DistributedLock;
import com.github.zuihou.redis.lock.RedisDistributedLock;
import com.github.zuihou.redis.properties.CustomCacheProperties;
import com.github.zuihou.redis.template.RedisRepository;
import com.github.zuihou.redis.utils.RedisObjectSerializer;
import com.github.zuihou.utils.StrPool;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis 配置类
 *
 * @author zuihou
 * @date 2019-08-06 10:42
 */
@Slf4j
@EnableConfigurationProperties({RedisProperties.class, CustomCacheProperties.class})
@EnableCaching
public class RedisAutoConfigure {
    @Autowired
    private CustomCacheProperties cacheProperties;

    /**
     * 分布式锁
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public DistributedLock RedisDistributedLock(RedisTemplate<String, Object> redisTemplate) {
        return new RedisDistributedLock(redisTemplate);
    }


    /**
     * RedisTemplate配置
     *
     * @param factory
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        RedisObjectSerializer redisObjectSerializer = new RedisObjectSerializer();
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(redisObjectSerializer);
        redisTemplate.setValueSerializer(redisObjectSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * redis 持久库
     *
     * @param redisTemplate the redis template
     * @return the redis repository
     */
    @Bean
    @ConditionalOnMissingBean
    public RedisRepository redisRepository(RedisTemplate<String, Object> redisTemplate) {
        return new RedisRepository(redisTemplate);
    }

    @Bean(name = "cacheManager")
    @Primary
    public CacheManager cacheManager(
            RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration defConfig = getDefConf();
        defConfig.entryTtl(cacheProperties.getRedis().getTimeToLive());

        Map<String, CustomCacheProperties.Redis> configs = cacheProperties.getConfigs();
        Map<String, RedisCacheConfiguration> map = Maps.newHashMap();
        //自定义的缓存过期时间配置
        Optional.ofNullable(configs).ifPresent((config) -> {
            config.forEach((key, cache) -> {
                RedisCacheConfiguration cfg = handleRedisCacheConfiguration(cache, defConfig);
                map.put(key, cfg);
            });
        });

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defConfig)
                .withInitialCacheConfigurations(map)
                .build();

    }

    /**
     * key 的生成
     *
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(StrPool.COLON);
            sb.append(method.getName());
            for (Object obj : objects) {
                if (obj != null) {
                    sb.append(StrPool.COLON);
                    sb.append(obj.toString());
                }
            }
            return sb.toString();
        };
    }

    private RedisCacheConfiguration getDefConf() {
        RedisCacheConfiguration def = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new RedisObjectSerializer()));
        return handleRedisCacheConfiguration(cacheProperties.getRedis(), def);
    }

    private RedisCacheConfiguration handleRedisCacheConfiguration(CustomCacheProperties.Redis redisProperties, RedisCacheConfiguration config) {
        if (Objects.isNull(redisProperties)) {
            return config;
        }
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.computePrefixWith(cacheName -> redisProperties.getKeyPrefix().concat(StrPool.COLON).concat(cacheName).concat(StrPool.COLON));
        } else {
            config = config.computePrefixWith(cacheName -> cacheName.concat(StrPool.COLON));
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }

        return config;
    }
}
