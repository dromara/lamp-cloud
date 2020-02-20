package com.github.zuihou.j2cache;

import cn.hutool.core.collection.CollUtil;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * 覆盖 SpringCache 相关配置
 *
 * @author zuihou
 * @date 2019-10-18 09:06
 */
public class MyCacheConfig extends CachingConfigurerSupport {
    private final CacheManager cacheManager;

    public MyCacheConfig(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * 解决注解：Cacheable 没有指定key时，会将key生成为 ${value}:SimpleKey []
     * eg： @Cacheable(value = "zuihou") ->  zuihou:SimpleKey []
     *
     * @return
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
//            StringBuilder sb = new StringBuilder();
//            sb.append(target.getClass().getName());
//            sb.append(StrPool.COLON);
//            sb.append(method.getName());
//            for (Object obj : objects) {
//                if (obj != null) {
//                    sb.append(StrPool.COLON);
//                    sb.append(obj.toString());
//                }
//            }
//            return sb.toString();
            return "";
        };
    }

    @Override
    public CacheResolver cacheResolver() {
        return (context) -> {
            Collection<String> cacheNames = null;
            CacheConfig annotation = context.getTarget().getClass().getAnnotation(CacheConfig.class);
            if (annotation != null) {
                cacheNames = CollUtil.toList(annotation.cacheNames());
            }
            if (cacheNames == null || cacheNames.isEmpty()) {
                cacheNames = context.getOperation().getCacheNames();
            }
            if (cacheNames == null || cacheNames.isEmpty()) {
                return Collections.emptyList();
            }
            Collection<Cache> result = new ArrayList<>(cacheNames.size());
            for (String cacheName : cacheNames) {
                Cache cache = cacheManager.getCache(cacheName);
                if (cache == null) {
                    throw new IllegalArgumentException("Cannot find cache named '" +
                            cacheName + "' for " + context.getOperation());
                }
                result.add(cache);
            }
            return result;
        };
    }
}
