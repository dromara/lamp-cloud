package com.github.zuihou.cache;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * 基于Redis的Set实现缓存
 *
 * @author zuihou
 * @createTime 2017-12-25 15:44
 */
public abstract class RedisSetCache<K, V> extends BaseRedisCache<K, V> {
    public RedisSetCache(Supplier<RedisTemplate<K, V>> redisTemplate) {
        super(redisTemplate);
    }


    protected BoundSetOperations<K, V> getBoundSetOperations() {
        return super.getRedisTemplate().boundSetOps(key());
    }

    /**
     * 获取缓存 ,默认获取第一个值
     *
     * @return
     */
    @Override
    public Optional<V> getCache() {
        V value = this.getBoundSetOperations().pop();
        return Objects.isNull(value) ? Optional.empty() : Optional.of(value);
    }


    /**
     * 设置缓存
     *
     * @param value
     */
    @Override
    public void set(V value) {
        this.sadd(value);
    }

    /**
     * 设置缓存  list默认向头部插入数据
     *
     * @param value   值
     * @param seconds 过期时间 单位:秒
     */
    @Override
    public void setex(V value, int seconds) {
        this.set(value);
        super.expire(seconds);
    }

    /**
     * 设置缓存 添加数据
     *
     * @param values 值
     */
    public void sadd(V... values) {
        this.getBoundSetOperations().add(values);
    }

    /**
     * 是否存在缓存
     *
     * @return
     */
    @Override
    public boolean exists() {
        return super.exists();
    }


    /**
     * 清除
     */
    @Override
    public void clear() {
        super.clear();
    }
}
