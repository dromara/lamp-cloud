package com.github.zuihou.cache.repository;

import java.time.Duration;
import java.util.function.Function;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;


/**
 * 基于 Caffeine 实现的内存缓存， 主要用于开发、测试、演示环境
 * 生产环境慎用！
 *
 * @author zuihou
 * @date 2019/08/07
 */
public class CaffeineRepositoryImpl implements CacheRepository {
    /**
     * 最大数量
     */
    long DEF_MAX_SIZE = 1_000;

    /**
     * 为什么不直接用 Cache<String, Object> ？
     * 因为想针对每一个key单独设置过期时间
     */
    private final Cache<String, Cache<String, Object>> cacheMap = Caffeine.newBuilder()
            .maximumSize(DEF_MAX_SIZE)
            .build();

    @Override
    public void setExpire(String key, Object value, long time) {
        Cache<String, Object> cache = Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(time))
                .maximumSize(DEF_MAX_SIZE)
                .build();
        cache.put(key, value);
        cacheMap.put(key, cache);
    }

    @Override
    public void set(String key, Object value) {
        Cache<String, Object> cache = Caffeine.newBuilder()
                .maximumSize(DEF_MAX_SIZE)
                .build();
        cache.put(key, value);
        cacheMap.put(key, cache);
    }

    @Override
    public <T> T get(String key) {
        Cache<String, Object> ifPresent = cacheMap.getIfPresent(key);
        if (ifPresent == null) {
            return null;
        }
        return (T) ifPresent.getIfPresent(key);
    }

    @Override
    public <T> T getOrDef(String key, Function<String, ? extends T> function) {
        Cache<String, Object> cache = cacheMap.get(key, (k) -> {
            Cache<String, Object> newCache = Caffeine.newBuilder()
                    .maximumSize(DEF_MAX_SIZE)
                    .build();
            newCache.get(k, function);
            return newCache;
        });

        return (T) cache.getIfPresent(key);
    }

    @Override
    public void flushDb() {
        cacheMap.invalidateAll();
    }

    @Override
    public boolean exists(final String key) {
        Cache<String, Object> cache = cacheMap.getIfPresent(key);
        if (cache == null) {
            return false;
        }
        cache.cleanUp();
        return cache.estimatedSize() > 0;
    }

    @Override
    public long del(final String... keys) {
        for (String key : keys) {
            cacheMap.invalidate(key);
        }
        return keys.length;
    }


}
