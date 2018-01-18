package com.github.zuihou.cache;


import com.github.zuihou.utils.SpringUtil;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author zuihou
 * @createTime 2017-12-25 14:27
 */
public abstract class BaseRedisCache<K, V> implements Cache<V> {
    private final Supplier<RedisTemplate<K, V>> redisTemplate;

    public BaseRedisCache(Supplier<RedisTemplate<K, V>> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 具体业务缓存key
     * 规则：
     * [开发环境=${spring.redis.key-prefix}]:[业务系统=${spring.application.name}]:[具体业务缓存key]
     *
     * @return
     */
    protected abstract K key();

    /**
     * 只从缓存中获取，缓存中不存在返回空
     *
     * @return
     */
    public abstract Optional<V> getCache();

    protected abstract Supplier<V> getDefaultSupplier() ;

    protected RedisTemplate<K, V> getRedisTemplate() {
        return redisTemplate.get();
    }

    /**
     * 用于生成key的工具方法
     * 规则：
     * [开发环境=${spring.redis.key-prefix}]:[业务系统=${spring.application.name}]:[具体业务缓存key]
     *
     * @param values 入参只需要包含 具体业务缓存key
     * @return
     */
    protected String keyGenerator(CharSequence... values) {
        KeyGenerator bean = SpringUtil.getBean("cacheKeyGenerator", KeyGenerator.class);
        if (bean != null) {
            return new StringJoiner(":")
                    .add(bean.keyPrefix())
                    .add(bean.bizModular())
                    .add(String.join(":", values))
                    .toString();
        } else {
            return String.join(":", values);
        }
    }


    /**
     * 先从缓存中获取，缓存不存在再从supplier获取，最后返回空
     *
     * @return
     */
    @Override
    public Optional<V> get() {
        return this.get(this.getDefaultSupplier());
    }

    @Override
    public boolean exists() {
        return this.getRedisTemplate().hasKey(this.key());
    }

    @Override
    public void clear() {
        this.getRedisTemplate().delete(this.key());
    }

    /**
     * 设置过期时间
     *
     * @param timeout 过期时间 单位：秒
     * @return
     */
    public Boolean expire(int timeout) {
        return this.getRedisTemplate().expire(this.key(), timeout, TimeUnit.SECONDS);
    }

    /**
     * @param supplier
     * @return
     */
    @Override
    public Optional<V> get(Supplier<V> supplier) {
        Optional<V> value = this.getCache();
        if (value.isPresent()) {
            return value;
        }
        //因考虑并发情况下为避免同时从数据库获取数据，加锁以控制
        synchronized (BaseRedisCache.class) {
            value = this.getCache();
            if (value.isPresent()) {
                return value;
            }
            V obj = supplier.get();
            if (Objects.isNull(obj)) {
                return Optional.empty();
            }
            this.set(obj);
            return Optional.of(obj);
        }
    }
}
