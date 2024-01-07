package top.tangyh.lamp.common.cache.common;


import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.lamp.common.cache.CacheKeyModular;
import top.tangyh.lamp.common.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 参数 KEY
 * <p>
 * #c_application
 *
 * @author zuihou
 * @date 2020/9/20 6:45 下午
 */
public class TokenUserIdCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey builder(String uuid) {
        return new TokenUserIdCacheKeyBuilder().key(uuid);
    }

    public static CacheKey builder(String uuid, Long expire) {
        CacheKey cacheKey = builder(uuid);
        if (expire != null) {
            cacheKey.setExpire(Duration.ofSeconds(expire));
        }
        return cacheKey;
    }

    @Override
    public String getTable() {
        return CacheKeyTable.TOKEN_USER_ID;
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
        return CacheKeyModular.OAUTH;
    }

    @Override
    public String getField() {
        return null;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.string;
    }

    /**
     * 虽然这里配置了过期时间，但在实际使用时，业务代码从yml中读取了有效期，并覆盖了次参数，所以修改这里的有效期 不会生效！
     */
    @Override
    public Duration getExpire() {
        return Duration.ofHours(8L);
    }
}
