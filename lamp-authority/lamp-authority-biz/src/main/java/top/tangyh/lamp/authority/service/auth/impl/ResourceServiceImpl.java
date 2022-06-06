package top.tangyh.lamp.authority.service.auth.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.SuperCacheServiceImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.basic.utils.StrHelper;
import top.tangyh.lamp.authority.dao.auth.ResourceMapper;
import top.tangyh.lamp.authority.entity.auth.Resource;
import top.tangyh.lamp.authority.service.auth.ResourceService;
import top.tangyh.lamp.authority.service.auth.RoleAuthorityService;
import top.tangyh.lamp.common.cache.auth.ResourceCacheKeyBuilder;

import java.util.List;
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
