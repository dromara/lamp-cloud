package com.github.zuihou.cache;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * 缓存对象
 *
 * @author zuihou
 * @createTime 2017-12-25 14:24
 */
public interface Cache<V> {

    /**
     * 获取缓存
     * @return
     */
    Optional<V> get();

    void set(V value);

    /**
     * 设置缓存
     *
     * @param value   值
     * @param seconds 过期时间 单位:秒
     */
    void setex(V value, int seconds);

    /**
     * 判断是否存在
     * @return
     */
    boolean exists();

    /**
     * 清除缓存
     */
    void clear();

    /**
     * 默认的获取缓存方法。
     * 先判断缓存中是否存在，不存在就根据supplier 提供的方法进行获取
     * @param supplier
     * @return
     */
    default Optional<V> get(Supplier<V> supplier) {
        Optional<V> value = get();
        if (value.isPresent()) {
            return value;
        }

        V obj = supplier.get();
        this.set(obj);
        return Optional.of(obj);
    }

}

