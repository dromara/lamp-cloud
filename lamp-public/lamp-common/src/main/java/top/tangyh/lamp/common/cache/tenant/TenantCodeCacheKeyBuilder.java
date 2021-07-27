package top.tangyh.lamp.common.cache.tenant;

import top.tangyh.basic.cache.model.CacheKeyBuilder;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.common.cache.CacheKeyDefinition;

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
