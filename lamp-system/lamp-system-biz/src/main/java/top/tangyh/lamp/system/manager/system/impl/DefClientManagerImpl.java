package top.tangyh.lamp.system.manager.system.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperCacheManagerImpl;
import top.tangyh.basic.cache.redis2.CacheResult;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.lamp.common.cache.tenant.system.DefClientCacheKeyBuilder;
import top.tangyh.lamp.common.cache.tenant.system.DefClientSecretCacheKeyBuilder;
import top.tangyh.lamp.system.entity.system.DefClient;
import top.tangyh.lamp.system.manager.system.DefClientManager;
import top.tangyh.lamp.system.mapper.system.DefClientMapper;

/**
 * <p>
 * 通用业务实现类
 * 客户端
 * </p>
 *
 * @author zuihou
 * @date 2021-10-13
 * @create [2021-10-13] [zuihou] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefClientManagerImpl extends SuperCacheManagerImpl<DefClientMapper, DefClient> implements DefClientManager {
    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new DefClientCacheKeyBuilder();
    }

    @Override
    public DefClient getClient(String clientId, String clientSecret) {
        CacheKey key = DefClientSecretCacheKeyBuilder.builder(clientId, clientSecret);
        CacheResult<Long> result = cacheOps.get(key, k -> {
            DefClient one = getOne(Wraps.<DefClient>lbQ().eq(DefClient::getClientId, clientId).eq(DefClient::getClientSecret, clientSecret));
            return one == null ? null : one.getId();
        });
        Long id = result.asLong();
        ArgumentAssert.notNull(id, "客户端[{}]不存在", clientId);
        return getByIdCache(id);
    }
}
