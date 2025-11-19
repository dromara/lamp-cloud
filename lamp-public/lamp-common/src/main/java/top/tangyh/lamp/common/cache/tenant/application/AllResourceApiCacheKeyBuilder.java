package top.tangyh.lamp.common.cache.tenant.application;

import top.tangyh.basic.base.entity.SuperEntity;
import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.lamp.common.cache.CacheKeyModular;
import top.tangyh.lamp.common.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 系统的所有资源接口 KEY
 * [服务模块名:]业务类型[:业务字段][:value类型] -> obj
 * system:all_dra:obj-> {}
 * <p>
 * #def_resource
 *
 * @author zuihou
 * @since 2020/9/20 6:45 下午
 */
public class AllResourceApiCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey builder() {
        return new AllResourceApiCacheKeyBuilder().key();
    }

    

    

    @Override
    public String getModular() {
        return CacheKeyModular.SYSTEM;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.System.ALL_RESOURCE_API;
    }

    @Override
    public String getField() {
        return SuperEntity.ID_FIELD;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.obj;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
