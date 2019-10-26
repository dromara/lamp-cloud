package com.github.zuihou.authority.service.auth.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.auth.ResourceMapper;
import com.github.zuihou.authority.dto.auth.ResourceQueryDTO;
import com.github.zuihou.authority.entity.auth.Resource;
import com.github.zuihou.authority.enumeration.auth.ResourceType;
import com.github.zuihou.authority.service.auth.ResourceService;
import com.github.zuihou.base.id.CodeGenerate;
import com.github.zuihou.common.constant.CacheKey;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.update.LbuWrapper;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.utils.BizAssert;
import com.github.zuihou.utils.NumberHelper;
import com.github.zuihou.utils.StrHelper;

import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
        CacheObject cacheObject = cache.get(CacheKey.USER_RESOURCE, String.valueOf(resource.getUserId()), (key) -> {
            List<Resource> visibleResource = baseMapper.findVisibleResource(resource);
            return visibleResource.stream().mapToLong(Resource::getId).boxed().collect(Collectors.toList());
        });

        //cache 和 db 都没有时直接返回
        if (cacheObject.getValue() == null) {
            return Collections.emptyList();
        }

        List<Long> list = (List<Long>) cacheObject.getValue();

        // 将cache 中的 [resource_id ,...] 转换成 [obj ,...]
        List<Resource> resourceList = list.stream().map(((ResourceService) AopContext.currentProxy())::getByIdWithCache).collect(Collectors.toList());

        if (resource.getType() == null && resource.getMenuId() == null) {
            return resourceList;
        }

        // 根据查询条件过滤数据
        return resourceList.stream()
                .filter((re) -> resource.getType() != null ? Objects.equals(resource.getType(), re.getResourceType()) : true)
                .filter((re) -> resource.getMenuId() != null ? Objects.equals(resource.getMenuId(), re.getMenuId()) : true)
                .collect(Collectors.toList());
    }


    @Override
    public boolean updateMenuWithCache(Long menuId, String menuName) {
        //批量修改冗余数据
        super.update(Wraps.<Resource>lbU()
                .set(Resource::getMenuName, menuName)
                .eq(Resource::getMenuId, menuId));

        //清除资源缓存
        LbuWrapper<Resource> query = Wraps.<Resource>lbU().eq(Resource::getMenuId, menuId);
        List<Long> list = super.listObjs(query, (resourceId) -> NumberHelper.longValueOf0(resourceId));
        list.forEach((resourceId) -> cache.evict(CacheKey.RESOURCE, String.valueOf(resourceId)));
        return true;
    }

    @Override
    @Cacheable(value = CacheKey.RESOURCE, key = "#id")
    public Resource getByIdWithCache(Long id) {
        return super.getById(id);
    }

    @Override
    @CacheEvict(value = CacheKey.RESOURCE, key = "#id")
    public boolean removeByIdWithCache(Long id) {
        Resource resource = super.getById(id);
        BizAssert.notNull(resource);
        if (ResourceType.URI.eq(resource.getResourceType())) {
            //TODO 注意这里有没有必要
            return super.update(Wraps.<Resource>lbU().set(Resource::getMenuId, null).eq(Resource::getId, id));
        } else {
            return super.removeById(id);
        }
    }

    @Override
    @CacheEvict(value = CacheKey.RESOURCE, key = "#resource.id")
    public boolean updateWithCache(Resource resource) {
        return super.updateById(resource);
    }

    @Override
    public boolean saveWithCache(Resource resource) {
        if (ResourceType.URI.eq(resource.getResourceType())) {
            BizAssert.notNull(resource.getId(), "URI资源不能为空");
            BizAssert.notNull(resource.getMenuId(), "URI资源必须关联菜单");
        }

        resource.setCode(StrHelper.getOrDef(resource.getCode(), codeGenerate.next()));
        if (super.count(Wraps.<Resource>lbQ().eq(Resource::getCode, resource.getCode())) > 0) {
            throw BizException.validFail("编码[%s]重复", resource.getCode());
        }

        if (resource.getId() != null) {
            super.updateById(resource);
            cache.evict(CacheKey.RESOURCE, String.valueOf(resource.getId()));
        } else {
            super.save(resource);
            cache.set(CacheKey.RESOURCE, String.valueOf(resource.getId()), resource);
        }
        return true;
    }
}
