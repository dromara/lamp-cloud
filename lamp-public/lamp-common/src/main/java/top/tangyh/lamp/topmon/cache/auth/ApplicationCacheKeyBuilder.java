package top.tangyh.lamp.common.cache.auth;

import top.tangyh.basic.cache.model.CacheKeyBuilder;
import top.tangyh.lamp.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 应用 KEY
 * <p>
 * #c_auth_application
 *
 * @author zuihou
 * @date 2020/9/20 6:45 下午
 */
public class ApplicationCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.APPLICATION;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}
