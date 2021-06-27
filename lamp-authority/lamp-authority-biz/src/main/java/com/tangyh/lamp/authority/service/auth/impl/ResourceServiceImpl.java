package com.tangyh.lamp.authority.service.auth.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;

import com.tangyh.basic.base.service.SuperCacheServiceImpl;
import com.tangyh.basic.cache.model.CacheKey;
import com.tangyh.basic.cache.model.CacheKeyBuilder;
import com.tangyh.basic.database.mybatis.conditions.Wraps;
import com.tangyh.basic.exception.BizException;
import com.tangyh.basic.utils.StrHelper;
import com.tangyh.lamp.authority.dao.auth.ResourceMapper;
import com.tangyh.lamp.authority.dto.auth.ResourceQueryDTO;
import com.tangyh.lamp.authority.entity.auth.Resource;
import com.tangyh.lamp.authority.service.auth.ResourceService;
import com.tangyh.lamp.authority.service.auth.RoleAuthorityService;
import com.tangyh.lamp.common.cache.auth.ResourceCacheKeyBuilder;
import com.tangyh.lamp.common.cache.auth.UserResourceCacheKeyBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Slf4j
@Service

@RequiredArgsConstructor
public class ResourceServiceImpl extends SuperCacheServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private final RoleAuthorityService roleAuthorityService;

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new ResourceCacheKeyBuilder();
    }

    /**
     * 查询用户的可用资源
     * <p>
     * 注意：什么地方需要清除 USER_MENU 缓存
     * 给用户重新分配角色时， 角色重新分配资源/菜单时
     *
     * @param resource 资源对象
     * @return 用户的可用资源
     */
    @Override
    public List<Resource> findVisibleResource(ResourceQueryDTO resource) {
        CacheKey userResourceKey = new UserResourceCacheKeyBuilder().key(resource.getUserId());

        List<Resource> visibleResource = new ArrayList<>();
        List<Long> list = cacheOps.get(userResourceKey, k -> {
            visibleResource.addAll(baseMapper.findVisibleResource(resource));
            return visibleResource.stream().map(Resource::getId).collect(Collectors.toList());
        });

        if (!visibleResource.isEmpty()) {
            visibleResource.forEach(this::setCache);
        } else {
            visibleResource.addAll(findByIds(list, this::listByIds));
        }
        return resourceListFilterGroup(resource.getMenuId(), visibleResource);
    }

    private List<Resource> resourceListFilterGroup(Long menuId, List<Resource> visibleResource) {
        if (menuId == null) {
            return visibleResource;
        }
        return visibleResource.stream().filter(item -> Objects.equals(menuId, item.getMenuId())).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIdWithCache(List<Long> ids) {
        boolean result = this.removeByIds(ids);

        // 删除角色的权限
        roleAuthorityService.removeByAuthorityId(ids);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByMenuIdWithCache(List<Long> menuIds) {
        List<Resource> resources = super.list(Wraps.<Resource>lbQ().in(Resource::getMenuId, menuIds));
        if (resources.isEmpty()) {
            return;
        }
        List<Long> resourceIdList = resources.stream().mapToLong(Resource::getId).boxed().collect(Collectors.toList());
        this.removeByIds(resourceIdList);

        List<Long> allIds = CollUtil.newArrayList(menuIds);
        allIds.addAll(resourceIdList);
        // 删除角色的权限
        roleAuthorityService.removeByAuthorityId(allIds);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveWithCache(Resource resource) {
        resource.setCode(StrHelper.getOrDef(resource.getCode(), RandomUtil.randomString(8)));
        if (check(null, resource.getCode())) {
            throw BizException.validFail("编码[%s]重复", resource.getCode());
        }

        this.save(resource);
        return true;
    }

    @Override
    public List<Long> findMenuIdByResourceId(List<Long> resourceIdList) {
        return baseMapper.findMenuIdByResourceId(resourceIdList);
    }

    @Override
    public Boolean check(Long id, String code) {
        return super.count(Wraps.<Resource>lbQ().ne(Resource::getId, id).eq(Resource::getCode, code)) > 0;
    }
}
