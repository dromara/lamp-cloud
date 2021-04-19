package com.tangyh.lamp.common.cache.common;


import com.tangyh.basic.cache.model.CacheKey;
import com.tangyh.basic.cache.model.CacheKeyBuilder;
import com.tangyh.lamp.common.cache.CacheKeyDefinition;

import java.time.Duration;
import java.time.LocalDate;

/**
 * 参数 KEY
 * {tenant}:TODAY_LOGIN_PV -> long
 * <p>
 * #c_login_log
 *
 * @author zuihou
 * @date 2020/9/20 6:45 下午
 */
public class TodayLoginPvCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build(LocalDate now) {
        return new TodayLoginPvCacheKeyBuilder().key(now.toString());
    }

    @Override
    public String getPrefix() {
        return CacheKeyDefinition.TODAY_LOGIN_PV;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofDays(2L);
    }
}
