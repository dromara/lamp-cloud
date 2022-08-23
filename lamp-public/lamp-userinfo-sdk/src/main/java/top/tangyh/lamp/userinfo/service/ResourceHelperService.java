package top.tangyh.lamp.userinfo.service;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.tangyh.basic.cache.repository.CacheOps;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.lamp.common.cache.auth.ResourceCacheKeyBuilder;
import top.tangyh.lamp.common.cache.auth.UserResourceCacheKeyBuilder;
import top.tangyh.lamp.model.entity.base.SysResource;
import top.tangyh.lamp.model.vo.query.ResourceQueryDTO;
import top.tangyh.lamp.userinfo.dao.ResourceHelperMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/4/24 2:48 PM
 * @create [2022/4/24 2:48 PM ] [tangyh] [初始创建]
 */
@Component
@RequiredArgsConstructor
public class ResourceHelperService {
    private final ResourceHelperMapper resourceHelperMapper;
    private final CacheOps cacheOps;

    protected void setCache(SysResource model) {
        Object id = model.getId();
        if (id != null) {
            CacheKey key = new ResourceCacheKeyBuilder().key(id);
            cacheOps.set(key, model);
        }
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
    public List<SysResource> findVisibleResource(ResourceQueryDTO resource) {
        ContextUtil.setDatabaseBase();
        CacheKey userResourceKey = new UserResourceCacheKeyBuilder().key(resource.getUserId());

        List<SysResource> visibleResource = new ArrayList<>();
        List<Long> list = cacheOps.get(userResourceKey, k -> {
            visibleResource.addAll(resourceHelperMapper.findVisibleResource(resource));
            return visibleResource.stream().map(SysResource::getId).collect(Collectors.toList());
        });

        if (visibleResource.isEmpty() && CollUtil.isNotEmpty(list)) {
            visibleResource.addAll(resourceHelperMapper.selectBatchIds(list));
        }
        return resourceListFilterGroup(resource.getMenuId(), visibleResource);
    }

    private List<SysResource> resourceListFilterGroup(Long menuId, List<SysResource> visibleResource) {
        if (menuId == null) {
            return visibleResource;
        }
        return visibleResource.stream().filter(item -> Objects.equals(menuId, item.getMenuId())).collect(Collectors.toList());
    }
}
