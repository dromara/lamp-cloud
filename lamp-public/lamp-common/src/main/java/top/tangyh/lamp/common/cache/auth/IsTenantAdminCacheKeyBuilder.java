package top.tangyh.lamp.common.cache.auth;


import top.tangyh.basic.base.entity.SuperEntity;
import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.lamp.common.cache.CacheKeyModular;
import top.tangyh.lamp.common.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 员工 是否系统管理员
 * <p>
 * 完整key: ${companyId}:is_sys_admin:${employeeId} -> "1" or "0"
 * <p>
 *
 * @author zuihou
 * @date 2021/12/20 6:45 下午
 */
public class IsTenantAdminCacheKeyBuilder implements CacheKeyBuilder {

    public static CacheKey builder(Long employeeId) {
        return new IsTenantAdminCacheKeyBuilder().key(employeeId);
    }

    @Override
    public String getPrefix() {
        return CacheKeyModular.PREFIX;
    }

    @Override
    public String getTenant() {
        return null;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.COMMON;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.System.TENANT;
    }

    @Override
    public String getField() {
        return SuperEntity.ID_FIELD;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.string;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
