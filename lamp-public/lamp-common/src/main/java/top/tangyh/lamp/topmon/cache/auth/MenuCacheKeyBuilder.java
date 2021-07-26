package top.tangyh.lamp.common.cache.auth;

import top.tangyh.basic.cache.model.CacheKeyBuilder;
import top.tangyh.lamp.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 菜单 KEY
 * <p>
 * #c_menu
 *
 * @author zuihou
 * @date 2020/9/20 6:45 下午
 */
public class MenuCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.MENU;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }


}
