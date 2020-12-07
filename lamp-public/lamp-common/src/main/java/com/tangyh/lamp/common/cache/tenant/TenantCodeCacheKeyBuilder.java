package com.tangyh.lamp.common.cache.tenant;

import com.tangyh.basic.cache.model.CacheKeyBuilder;
import com.tangyh.basic.utils.StrPool;
import com.tangyh.lamp.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 系统用户 KEY
 * <p>
 * #c_tenant
 *
 * @author zuihou
 * @date 2020/9/20 6:45 下午
 */
public class TenantCodeCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getTenant() {
        return StrPool.EMPTY;
    }

    @Override
    public String getPrefix() {
        return CacheKeyDefinition.TENANT_CODE;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
