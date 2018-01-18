package com.github.zuihou.cache;


import com.github.zuihou.utils.SpringUtil;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 基于Redis的List实现缓存
 *
 * @author zuihou
 * @createTime 2017-12-25 15:56
 */
public abstract class RedisListCache<K, V> {
    private final Supplier<RedisTemplate<K, V>> redisTemplate;

    public RedisListCache(Supplier<RedisTemplate<K, V>> redisTemplate) {
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

    protected abstract Supplier<List<V>> getDefaultSupplier();

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

    protected BoundListOperations<K, V> getBoundListOperations() {
        return this.getRedisTemplate().boundListOps(key());
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

    public void clear() {
        this.getRedisTemplate().delete(this.key());
    }

    public boolean exists() {
        return this.getRedisTemplate().hasKey(this.key());
    }
    /**
     * 设置缓存 list默认向头部插入数据
     *
     * @param value
     */
    public void lpush(V value) {
        this.lpush(value);
    }

    /**
     * 设置缓存  list默认向头部插入数据
     *
     * @param value   值
     * @param seconds 过期时间 单位:秒
     */
    public void lpush(V value, int seconds) {
        this.lpush(value);
        this.expire(seconds);
    }

    /**
     * 向list头部插入数据
     *
     * @param values
     * @return
     */
    public Long lpush(V... values) {
        if (values == null || values.length == 0) {
            return 0L;
        }
        return this.getBoundListOperations().leftPushAll(values);
    }

    /**
     * 向list尾部插入数据
     *
     * @param values
     * @return
     */
    public Long rpush(V... values) {
        if (values == null || values.length == 0) {
            return 0L;
        }
        return this.getBoundListOperations().rightPushAll(values);
    }

    /**
     * 从list头部获取数据并移除
     *
     * @return
     */
    public V lpop() {
        return this.getBoundListOperations().leftPop();
    }

    /**
     * 从list尾部获取数据并移除数据
     *
     * @return
     */
    public V rpop() {
        return this.getBoundListOperations().rightPop();
    }

    /**
     * 从list的长度
     *
     * @return
     */
    public Long llen() {
        return this.getBoundListOperations().size();
    }

    /**
     * 获取list集指定区间内的元素 当start为0 end为-时取所有数据
     *
     * @param start 开始值
     * @param end   结束值
     * @return
     */
    public List<V> lrange(long start, long end) {
        return this.getBoundListOperations().range(start, end);
    }

    public Optional<List<V>> get() {
        return this.get(this.getDefaultSupplier());
    }

    /**
     * list -> [0, 1, 2]
     *
     * @param supplier
     * @return
     */
    public Optional<List<V>> get(Supplier<List<V>> supplier) {
        //查所有的
        List<V> values = lrange(0, -1);
        if (values != null && !values.isEmpty()) {
            return Optional.of(values);
        }
        //因考虑并发情况下为避免同时从数据库获取数据，加锁以控制
        synchronized (RedisListCache.class) {
            values = lrange(0, -1);
            if (values != null && !values.isEmpty()) {
                return Optional.of(values);
            }

            values = supplier.get();
            if (Objects.isNull(values) || values.isEmpty()) {
                return Optional.of(Collections.emptyList());
            }
            Class<V> entityClass = (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            V[] s = (V[]) Array.newInstance(entityClass, values.size());
            this.lpush(values.toArray(s));
            return Optional.of(values);
        }
    }

}
