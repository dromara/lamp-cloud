package top.tangyh.lamp.common.cache.common;


import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.lamp.common.cache.CacheKeyTable;

/**
 * 参数 KEY
 * {tenant}:TOTAL_LOGIN_IV -> long
 * <p>
 * #c_login_log
 *
 * @author zuihou
 * @date 2020/9/20 6:45 下午
 */
public class TotalLoginIvCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build() {
        return new TodayLoginIvCacheKeyBuilder().key();
    }

    @Override
    public String getTable() {
        return CacheKeyTable.TOTAL_LOGIN_IV;
    }
}
