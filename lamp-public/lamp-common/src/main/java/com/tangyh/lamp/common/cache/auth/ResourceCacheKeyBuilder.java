package com.tangyh.lamp.common.cache.auth;

import com.tangyh.basic.cache.model.CacheKeyBuilder;
import com.tangyh.lamp.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 资源 KEY
 * <p>
 * #c_resource
 *
 * @author zuihou
 * @date 2020/9/20 6:45 下午
 */
public class ResourceCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.RESOURCE;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }


}
