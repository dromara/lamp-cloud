package top.tangyh.lamp.common.cache.gateway;

import top.tangyh.basic.cache.model.CacheKeyBuilder;
import top.tangyh.lamp.common.cache.CacheKeyDefinition;

/**
 * 阻止列表ID KEY
 * <p>
 *
 * @author zuihou
 * @date 2020/9/20 6:45 下午
 */
public class BlockListIdCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.BLOCKLIST_ID;
    }

}
