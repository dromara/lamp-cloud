package com.tangyh.lamp.common.cache.common;


import com.tangyh.basic.cache.model.CacheKey;
import com.tangyh.basic.cache.model.CacheKeyBuilder;
import com.tangyh.lamp.common.cache.CacheKeyDefinition;

import java.time.Duration;
import java.time.LocalDate;

/**
 * 参数 KEY
 * {tenant}:TODAY_PV:{now} -> long
 * <p>
 *
 * @author zuihou
 * @date 2020/9/20 6:45 下午
 */
public class TodayPvCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build(LocalDate now) {
        return new TodayPvCacheKeyBuilder().key(now.toString());
    }

    @Override
    public String getPrefix() {
        return CacheKeyDefinition.TODAY_PV;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofDays(2L);
    }
}
