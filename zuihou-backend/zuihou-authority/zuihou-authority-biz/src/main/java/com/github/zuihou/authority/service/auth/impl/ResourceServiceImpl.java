package com.github.zuihou.authority.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.auth.ResourceMapper;
import com.github.zuihou.authority.dto.auth.ResourceQueryDTO;
import com.github.zuihou.authority.entity.auth.Resource;
import com.github.zuihou.authority.service.auth.ResourceService;
import com.github.zuihou.base.id.CodeGenerate;
import com.github.zuihou.common.constant.CacheKey;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.utils.StrHelper;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
    @Autowired
    private CacheChannel cache;
    @Autowired
    private CodeGenerate codeGenerate;

    /**
     * 查询用户的可用资源
     * 注意：什么地方需要清除 USER_MENU 缓存
     * 给用户重新分配角色时， 角色重新分配资源/菜单时
     *
     * @param resource
     * @return
     */
    @Override
    public List<Resource> findVisibleResource(ResourceQueryDTO resource) {
        //1, 先查 cache，cache中没有就执行回调查询DB，并设置到缓存
        String userResourceKey = CacheKey.buildKey(resource.getUserId());
        CacheObject cacheObject = cache.get(CacheKey.USER_RESOURCE, userResourceKey, (key) -> {
            List<Resource> visibleResource = baseMapper.findVisibleResource(resource);
            return visibleResource.stream().mapToLong(Resource::getId).boxed().collect(Collectors.toList());
        });

        //cache 和 db 都没有时直接返回
        if (cacheObject.getValue() == null) {
            return Collections.emptyList();
        }

        List<Long> list = (List<Long>) cacheObject.getValue();
        List<Resource> resourceList = list.stream().map(((ResourceService) AopContext.currentProxy())::getByIdWithCache).collect(Collectors.toList());

        if (resource.getMenuId() == null) {
            return resourceList;
        }

        // 根据查询条件过滤数据
        return resourceList.stream()
                .filter((re) -> Objects.equals(resource.getMenuId(), re.getMenuId()))
                .collect(Collectors.toList());
    }


    @Override
    @Cacheable(value = CacheKey.RESOURCE, key = "#id")
    public Resource getByIdWithCache(Long id) {
        return super.getById(id);
    }

    @Override
    public boolean removeByIdWithCache(List<Long> ids) {
        boolean result = super.removeByIds(ids);
        if (result) {
            String[] keys = ids.stream().map(CacheKey::buildKey).toArray(String[]::new);
            cache.evict(CacheKey.RESOURCE, keys);
        }
        return result;
    }

    @Override
    public void removeByMenuIdWithCache(List<Long> menuIds) {
        List<Resource> resources = super.list(Wraps.<Resource>lbQ().in(Resource::getMenuId, menuIds));
        if (resources.isEmpty()) {
            return;
        }
        List<Long> idList = resources.stream().mapToLong(Resource::getId).boxed().collect(Collectors.toList());
        super.removeByIds(idList);

        String[] keys = idList.stream().map(CacheKey::buildKey).toArray(String[]::new);
        cache.evict(CacheKey.RESOURCE, keys);
    }

    @Override
    @CacheEvict(value = CacheKey.RESOURCE, key = "#resource.id")
    public boolean updateWithCache(Resource resource) {
        return super.updateById(resource);
    }

    @Override
    public boolean saveWithCache(Resource resource) {
        resource.setCode(StrHelper.getOrDef(resource.getCode(), codeGenerate.next()));
        if (super.count(Wraps.<Resource>lbQ().eq(Resource::getCode, resource.getCode())) > 0) {
            throw BizException.validFail("编码[%s]重复", resource.getCode());
        }

        super.save(resource);
        String resourceKey = CacheKey.buildKey(resource.getId());
        cache.set(CacheKey.RESOURCE, resourceKey, resource);
        return true;
    }

    @Override
    public List<Long> findMenuIdByResourceId(List<Long> resourceIdList) {
        return baseMapper.findMenuIdByResourceId(resourceIdList);
    }
}
