package com.github.zuihou.cache.repository;

import java.util.function.Function;

/**
 * This is a Description
 *
 * @author tangyh
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


    void setExpire(final String key, final Object value, final long time);

    void set(final String key, final Object value);

    <T> T get(final String key);

    <T> T getOrDef(final String key, Function<String, ? extends T> function);

    void flushDb();

    boolean exists(final String key);

    long del(final String... keys);
}
