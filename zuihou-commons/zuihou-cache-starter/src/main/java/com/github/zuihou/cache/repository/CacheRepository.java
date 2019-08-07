package com.github.zuihou.cache.repository;

import java.util.function.Function;

/**
 * 缓存操作公共接口
 *
 * @author zuihou
 * @date 2019/08/07
 */
public interface CacheRepository {
    /**
     * 5分钟
     */
    long DEF_TIMEOUT_5M = 5 * 60L;
    /**
     * 2小时
     */
    long DEF_TIMEOUT_2H = 2 * 60 * 60L;

    /**
     * 添加到带有 过期时间的  缓存
     *
     * @param key   redis主键
     * @param value 值
     * @param time  过期时间(单位秒)
     */
    void setExpire(final String key, final Object value, final long time);

    /**
     * 添加到缓存
     *
     * @param key   redis主键
     * @param value 值
     */
    void set(final String key, final Object value);

    /**
     * 根据key获取对象
     *
     * @param key redis主键
     * @return 值 不存在时，返回null
     */
    <T> T get(final String key);

    /**
     * 根据key获取对象
     * 不存在时，调用function回调获取数据，并set进入，然后返回
     *
     * @param key redis主键
     * @return 值
     */
    <T> T getOrDef(final String key, Function<String, ? extends T> function);

    /**
     * 清空所有存储的数据
     */
    void flushDb();

    /**
     * 判断指定的key 是否存在
     *
     * @param key
     * @return
     */
    boolean exists(final String key);

    /**
     * 删除指定的key
     *
     * @param keys
     * @return
     */
    long del(final String... keys);
}
