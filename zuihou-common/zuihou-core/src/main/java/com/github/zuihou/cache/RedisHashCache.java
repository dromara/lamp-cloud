package com.github.zuihou.cache;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * 基于Redis的Hash实现缓存
 *
 * @author zuihou
 * @createTime 2017-12-25 15:21
 */
public abstract class RedisHashCache<K, F, V> extends BaseRedisCache<K, V> {

    public RedisHashCache(Supplier<RedisTemplate<K, V>> redisTemplate) {
        super(redisTemplate);
    }

    /**
     * 缓存field
     *
     * @return
     */
    protected abstract F field();

    protected String filedGenerator(CharSequence... values) {
        return String.join(":", values);
    }


    protected BoundHashOperations<K, F, V> getBoundHashOperations() {
        return super.getRedisTemplate().boundHashOps(key());
    }

    /**
     * 获取
     *
     * @return
     */
    @Override
    public Optional<V> getCache() {
        V value = this.getBoundHashOperations().get(this.field());
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
        this.getBoundHashOperations().put(this.field(), value);
    }


    /**
     * 设置缓存
     *
     * @param value   值
     * @param seconds 过期时间 单位:秒
     */
    @Override
    public void setex(V value, int seconds) {
        // 基于hash，暂不支持缓存自动过期
        this.set(value);
    }

    /**
     * 是否存在
     *
     * @return
     */
    @Override
    public boolean exists() {
        return this.getBoundHashOperations().hasKey(this.field());
    }

    /**
     * 根据缓存中的key 清除
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
        return this.getBoundHashOperations().increment(this.field(), value);
    }

    /**
     * 清除指定 key field的缓存值
     *
     * @return
     */
    public void hdel() {
        this.getBoundHashOperations().delete(this.field());
    }
}
