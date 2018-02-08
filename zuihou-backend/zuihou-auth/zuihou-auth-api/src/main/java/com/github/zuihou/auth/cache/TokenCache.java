package com.github.zuihou.auth.cache;

import com.github.zuihou.auth.api.AuthTokenApi;
import com.github.zuihou.cache.RedisHashCache;
import com.github.zuihou.utils.SpringUtil;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.function.Supplier;

public class TokenCache extends RedisHashCache<String, String, String> {
    /**
     * 业务key
     */
    private static final String CACHE_RESOURCE_COLLECTION = "app:token";
    private static final RedisTemplate<String, String> REDIS_TEMPLATE = SpringUtil.getBean("redisTemplate", RedisTemplate.class);

    private final AuthTokenApi authTokenApi = SpringUtil.getBean(AuthTokenApi.class);
    private final String appId;
    private final String account;

    private TokenCache(String appId, String account) {
        super(() -> REDIS_TEMPLATE);
        this.account = account;
        this.appId = appId;
    }

    @Override
    protected String key() {
        return keyGenerator(CACHE_RESOURCE_COLLECTION, appId);
    }

    @Override
    protected String field() {
        return String.valueOf(account);
    }

    @Override
    protected Supplier<String> getDefaultSupplier() {
        return () -> authTokenApi.login(account).getData().getToken();
    }

    public static TokenCache of(String appId, String account) {
        return new TokenCache(appId, account);
    }
}
