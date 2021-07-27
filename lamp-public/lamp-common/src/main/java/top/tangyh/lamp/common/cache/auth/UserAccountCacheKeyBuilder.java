package top.tangyh.lamp.common.cache.auth;

import top.tangyh.basic.cache.model.CacheKeyBuilder;
import top.tangyh.lamp.common.cache.CacheKeyDefinition;

/**
 * 系统用户 KEY
 * <p>
 * #c_user
 *
 * @author zuihou
 * @date 2020/9/20 6:45 下午
 */
public class UserAccountCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.USER_ACCOUNT;
    }
}
