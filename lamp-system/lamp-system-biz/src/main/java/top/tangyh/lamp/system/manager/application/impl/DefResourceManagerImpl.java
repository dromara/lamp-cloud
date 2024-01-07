package top.tangyh.lamp.system.manager.application.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperCacheManagerImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.database.mybatis.conditions.query.LbQueryWrap;
import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.TreeUtil;
import top.tangyh.lamp.common.cache.tenant.application.ApplicationResourceCacheKeyBuilder;
import top.tangyh.lamp.common.cache.tenant.application.ResourceCacheKeyBuilder;
import top.tangyh.lamp.system.entity.application.DefResource;
import top.tangyh.lamp.system.manager.application.DefResourceManager;
import top.tangyh.lamp.system.mapper.application.DefResourceMapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
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
public class DefResourceManagerImpl extends SuperCacheManagerImpl<DefResourceMapper, DefResource> implements DefResourceManager {


    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new ResourceCacheKeyBuilder();
    }

    @Override
    public List<DefResource> findResourceListByApplicationId(List<Long> applicationIdList, final Collection<String> resourceTypeList) {
        if (CollUtil.isEmpty(applicationIdList)) {
            // 查询所有的菜单
            return super.list(Wraps.<DefResource>lbQ().eq(DefResource::getState, true).orderByAsc(DefResource::getSortValue));
        } else {
            // 新方法
            Set<Long> resourceIdSet = addApplicationResourceIdList(applicationIdList);
            return findByIdsAndType(resourceIdSet, resourceTypeList);
        }
    }

    private Set<Long> addApplicationResourceIdList(List<Long> applicationIdList) {
        // 版本2
        // 生成key的回调
        Function<Long, CacheKey> cacheBuilder = ApplicationResourceCacheKeyBuilder::build;

        // 缓存中不存在时，回调函数
        Function<Long, List<Long>> loader = applicationId -> {
            LbQueryWrap<DefResource> wrap = Wraps.<DefResource>lbQ().select(DefResource::getId).eq(DefResource::getApplicationId, applicationId).eq(DefResource::getState, true);
            return super.listObjs(wrap, Convert::toLong);
        };
        return findCollectByIds(applicationIdList, cacheBuilder, loader);
        // 版本2 end
    }

    @Override
    public List<DefResource> findByIdsAndType(Collection<? extends Serializable> idList, Collection<String> types) {
        List<DefResource> list = findByIds(idList, null);
        return list.stream()
                // 过滤数据状态
                .filter(Objects::nonNull).filter(DefResource::getState).filter(item -> !CollUtil.isNotEmpty(types) || (CollUtil.contains(types, item.getResourceType())))
                // 按sortValue排序，null排在最后
                .sorted(Comparator.comparing(DefResource::getSortValue, Comparator.nullsLast(Integer::compareTo))).toList();
    }

    @Override
    public List<DefResource> findByApplicationId(List<Long> applicationIds) {
        ArgumentAssert.notEmpty(applicationIds, "applicationIds 不能为空");
        return list(Wraps.<DefResource>lbQ().in(DefResource::getApplicationId, applicationIds).orderByAsc(DefResource::getSortValue));
    }

    @Override
    public List<DefResource> findChildrenByParentId(Long parentId) {
        ArgumentAssert.notNull(parentId, "parentId 不能为空");
        return list(Wraps.<DefResource>lbQ().in(DefResource::getParentId, parentId).orderByAsc(DefResource::getSortValue));
    }

    @Override
    public int deleteRoleResourceRelByResourceId(List<Long> resourceIds) {
        return baseMapper.deleteRoleResourceRelByResourceId(resourceIds);
    }

    @Override
    public List<DefResource> findAllChildrenByParentId(Long parentId) {
        ArgumentAssert.notNull(parentId, "parentId 不能为空");
        return list(Wraps.<DefResource>lbQ().like(DefResource::getTreePath, TreeUtil.buildTreePath(parentId)).orderByAsc(DefResource::getSortValue));
    }


}
