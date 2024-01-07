package top.tangyh.lamp.common.cache.common;


import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.lamp.common.cache.CacheKeyTable;

import java.time.Duration;

/**
 * 参数 KEY
 * <p>
 *
 * @author zuihou
 * @date 2020/9/20 6:45 下午
 */
public class CaptchaCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build(String key, String template) {
        return new CaptchaCacheKeyBuilder().key(key, template);
    }

    @Override
    public String getTable() {
        return CacheKeyTable.CAPTCHA;
    }

    @Override
    public String getTenant() {
        return null;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofMinutes(15);
    }
}
