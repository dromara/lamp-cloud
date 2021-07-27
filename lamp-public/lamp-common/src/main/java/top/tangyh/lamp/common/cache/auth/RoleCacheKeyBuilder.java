package top.tangyh.lamp.common.cache.auth;

import top.tangyh.basic.cache.model.CacheKeyBuilder;
import top.tangyh.lamp.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 系统用户 KEY
 * <p>
 * #c_role
 *
 * @author zuihou
 * @date 2020/9/20 6:45 下午
 */
public class RoleCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.ROLE;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
