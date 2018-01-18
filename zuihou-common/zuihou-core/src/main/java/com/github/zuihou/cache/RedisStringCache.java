package com.github.zuihou.cache;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 基于Redis的 str 实现缓存
 *
 * @author zuihou
 * @createTime 2017-12-25 14:44
 */
public abstract class RedisStringCache<K, V> extends BaseRedisCache<K, V> {

    public RedisStringCache(Supplier<RedisTemplate<K, V>> redisTemplate) {
        super(redisTemplate);
    }

    protected BoundValueOperations<K, V> getBoundValueOperations() {
        return super.getRedisTemplate().boundValueOps(key());
    }

    /**
     * 获取缓存
     *
     * @return
     */
    @Override
    public Optional<V> getCache() {
        V value = this.getBoundValueOperations().get();
        if (value != null) {
            return Optional.of(value);
        }
        return Optional.empty();
    }


    /**
     * 设置缓存
     *
     * @param value
     */
    @Override
    public void set(V value) {
        this.getBoundValueOperations().set(value);
    }

    /**
     * 设置缓存
     *
     * @param value   值
     * @param seconds 过期时间 单位:秒
     */
    @Override
    public void setex(V value, int seconds) {
        this.getBoundValueOperations().set(value, seconds, TimeUnit.SECONDS);
    }


    /**
     * 设置缓存
     *
     * @param value 值
     * @return 如果指定的Key不存在，则返回True, 否则返回False
     */
    public boolean setnx(V value) {
        return this.getBoundValueOperations().setIfAbsent(value);
    }




    /**
     * 是否存在缓存
     *
     * @return
     */
    @Override
    public boolean exists() {
        return super.getRedisTemplate().hasKey(this.key());
    }


    /**
     * 清除
     */
    @Override
    public void clear() {
        super.clear();
    }

    /**
     * 累加传负数为累减
     *
     * @param value
     * @return
     */
    public Long incrBy(long value) {
        return this.getBoundValueOperations().increment(value);
    }
}
