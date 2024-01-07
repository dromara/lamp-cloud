package top.tangyh.lamp.common.cache.tenant.base;


import top.tangyh.basic.base.entity.SuperEntity;
import top.tangyh.basic.model.cache.CacheHashKey;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.lamp.common.cache.CacheKeyModular;
import top.tangyh.lamp.common.cache.CacheKeyTable;

import java.io.Serializable;

/**
 * 参数 KEY
 * <p>
 * key: lc:def_parameter:tenant:id:string:{id}
 * value: obj
 *
 * <p>
 * #def_parameter
 *
 * @author zuihou
 * @date 2020/9/20 6:45 下午
 */
public class DictParameterKeyBuilder implements CacheKeyBuilder {
    public static CacheHashKey builder(Serializable id) {
        return new DictParameterKeyBuilder().hashKey(id);
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
    public String getTable() {
        return CacheKeyTable.System.DEF_PARAMETER;
    }

    @Override
    public String getModular() {
        return CacheKeyModular.SYSTEM;
    }

    @Override
    public String getField() {
        return SuperEntity.ID_FIELD;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.string;
    }

}
