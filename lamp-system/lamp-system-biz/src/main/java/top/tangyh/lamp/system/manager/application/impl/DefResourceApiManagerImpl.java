package top.tangyh.lamp.system.manager.application.impl;

import cn.hutool.core.convert.Convert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.manager.impl.SuperCacheManagerImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.database.mybatis.conditions.query.LbQueryWrap;
import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.lamp.common.cache.tenant.application.ResourceApiCacheKeyBuilder;
import top.tangyh.lamp.common.cache.tenant.application.ResourceResourceApiCacheKeyBuilder;
import top.tangyh.lamp.system.entity.application.DefResourceApi;
import top.tangyh.lamp.system.manager.application.DefResourceApiManager;
import top.tangyh.lamp.system.mapper.application.DefResourceApiMapper;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

/**
 * 应用管理
 *
 * @author tangyh
 * @version v1.0
 * @date 2021/9/29 1:26 下午
 * @create [2021/9/29 1:26 下午 ] [tangyh] [初始创建]
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class DefResourceApiManagerImpl extends SuperCacheManagerImpl<DefResourceApiMapper, DefResourceApi> implements DefResourceApiManager {
    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new ResourceApiCacheKeyBuilder();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByResourceId(List<Long> resourceIdList) {
        LbQueryWrap<DefResourceApi> wrap = Wraps.<DefResourceApi>lbQ().select(DefResourceApi::getId).in(DefResourceApi::getResourceId, resourceIdList);
        List<Long> apiIds = listObjs(wrap, Convert::toLong);
        remove(wrap);

        CacheKey[] keys = apiIds.stream().map(ResourceApiCacheKeyBuilder::builder).toArray(CacheKey[]::new);
        cacheOps.del(keys);

        CacheKey[] resourceResourceApiKeys = resourceIdList.stream().map(ResourceResourceApiCacheKeyBuilder::builder).toArray(CacheKey[]::new);
        cacheOps.del(resourceResourceApiKeys);
    }

    @Override
    public List<DefResourceApi> findByResourceId(Long resourceId) {
        return list(Wraps.<DefResourceApi>lbQ().eq(DefResourceApi::getResourceId, resourceId));
    }

    @Override
    public List<DefResourceApi> findApiByResourceId(List<Long> resourceIdList) {

        long start = System.currentTimeMillis();
        // 新旧方法
        // 旧方法
        /* for (Long resourceId : resourceIdList) {
            CacheKey key = ResourceResourceApiCacheKeyBuilder.builder(resourceId);
            CacheResult<List<Long>> result = cacheOps.get(key, k -> super.listObjs(
                    Wraps.<DefResourceApi>lbQ().select(DefResourceApi::getId).eq(DefResourceApi::getResourceId, resourceId),
                    Convert::toLong
            ));
            apiIds.addAll(result.asList());
        }*/
        // 旧方法end

        // 新方法
        Set<Long> apiIds = findApiIdByResourceId(resourceIdList);
        // 新方法end

        long forEnd = System.currentTimeMillis();
        List<DefResourceApi> apiList = findByIds(apiIds, null);
        long end = System.currentTimeMillis();
        log.info("manager 校验api耗时： {}, {}", (forEnd - start), (end - forEnd));
        return apiList.stream().filter(Objects::nonNull).toList();
    }

    private Set<Long> findApiIdByResourceId(List<Long> resourceIdList) {
        // 版本1
        /*Set<Long> apiIds = new HashSet<>();
        List<CacheKey> cacheKeys = resourceIdList.stream().map(resourceId -> ResourceResourceApiCacheKeyBuilder.builder(resourceId)).toList();
        List<CacheResult<List<Long>>> resultList = cacheOps.find(cacheKeys);
        for (int i = 0; i < resultList.size(); i++) {
            CacheResult<List<Long>> result = resultList.get(i);
            List<Long> rraIds = result.asList();
            if (result.isNull()) {
                Long resourceId = resourceIdList.get(i);
                List<Long> rraIdList = super.listObjs(Wraps.<DefResourceApi>lbQ().select(DefResourceApi::getId).eq(DefResourceApi::getResourceId, resourceId), Convert::toLong);

                if (CollUtil.isNotEmpty(rraIdList)) {
                    CacheKey cacheKey = cacheKeys.get(i);
                    cacheOps.set(cacheKey, rraIdList);
                }
                rraIds = rraIdList;
            }
            apiIds.addAll(rraIds);
        }
        return apiIds;
        */
        // 版本1end

        // 版本2
        Function<Long, CacheKey> cacheBuilder = ResourceResourceApiCacheKeyBuilder::builder;

        // 缓存中不存在时，回调函数
        Function<Long, List<Long>> loader = resourceId -> super.listObjs(Wraps.<DefResourceApi>lbQ().select(DefResourceApi::getId).eq(DefResourceApi::getResourceId, resourceId), Convert::toLong);
        return findCollectByIds(resourceIdList, cacheBuilder, loader);
        // 版本2end
    }

    @Override
    public List<DefResourceApi> findResourceApi(List<Long> applicationIdList, Collection<String> resourceTypes) {
        return baseMapper.findResourceApi(applicationIdList, resourceTypes);
    }
}
