package top.tangyh.lamp.authority.service.auth.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.SuperServiceImpl;
import top.tangyh.basic.cache.model.CacheKey;
import top.tangyh.basic.cache.repository.CacheOps;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.lamp.authority.dao.auth.ResourceMapper;
import top.tangyh.lamp.authority.dao.auth.RoleAuthorityMapper;
import top.tangyh.lamp.authority.dto.auth.RoleAuthoritySaveDTO;
import top.tangyh.lamp.authority.dto.auth.UserRoleSaveDTO;
import top.tangyh.lamp.authority.entity.auth.RoleAuthority;
import top.tangyh.lamp.authority.entity.auth.UserRole;
import top.tangyh.lamp.authority.enumeration.auth.AuthorizeType;
import top.tangyh.lamp.authority.service.auth.RoleAuthorityService;
import top.tangyh.lamp.authority.service.auth.UserRoleService;
import top.tangyh.lamp.common.cache.auth.RoleMenuCacheKeyBuilder;
import top.tangyh.lamp.common.cache.auth.RoleResourceCacheKeyBuilder;
import top.tangyh.lamp.common.cache.auth.UserMenuCacheKeyBuilder;
import top.tangyh.lamp.common.cache.auth.UserResourceCacheKeyBuilder;
import top.tangyh.lamp.common.cache.auth.UserRoleCacheKeyBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Slf4j
@Service

@RequiredArgsConstructor
public class RoleAuthorityServiceImpl extends SuperServiceImpl<RoleAuthorityMapper, RoleAuthority> implements RoleAuthorityService {

    private final UserRoleService userRoleService;
    private final ResourceMapper resourceMapper;
    private final CacheOps cacheOps;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUserRole(UserRoleSaveDTO userRole) {
        List<UserRole> oldUserRoleList = userRoleService.list(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, userRole.getRoleId()));
        userRoleService.remove(Wraps.<UserRole>lbQ().eq(UserRole::getRoleId, userRole.getRoleId()));

        Set<Long> delIdList = new HashSet<>();
        if (CollUtil.isNotEmpty(userRole.getUserIdList())) {
            List<UserRole> list = userRole.getUserIdList()
                    .stream()
                    .map(userId -> UserRole.builder()
                            .userId(userId)
                            .roleId(userRole.getRoleId())
                            .build())
                    .collect(Collectors.toList());
            userRoleService.saveBatch(list);
            delIdList.addAll(userRole.getUserIdList());
        }

        /* 角色A -> u1 u2
            修改成
            角色A -> u3 u2
            所以， 应该清除 u1、u2、u3 的角色、菜单、资源
            */

        if (!oldUserRoleList.isEmpty()) {
            delIdList.addAll(oldUserRoleList.stream().map(UserRole::getUserId).collect(Collectors.toSet()));
        }
        delUserAuthority(delIdList);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRoleAuthority(RoleAuthoritySaveDTO dto) {
        ArgumentAssert.notNull(dto.getRoleId(), "请选择角色");

        //删除角色和资源的关联
        super.remove(Wraps.<RoleAuthority>lbQ().eq(RoleAuthority::getRoleId, dto.getRoleId()));

        List<RoleAuthority> list = new ArrayList<>();
        if (dto.getResourceIdList() != null && !dto.getResourceIdList().isEmpty()) {
            List<Long> menuIdList = resourceMapper.findMenuIdByResourceId(dto.getResourceIdList());
            if (dto.getMenuIdList() == null || dto.getMenuIdList().isEmpty()) {
                dto.setMenuIdList(menuIdList);
            } else {
                dto.getMenuIdList().addAll(menuIdList);
            }

            //保存授予的资源resourceMapper
            List<RoleAuthority> resourceList = new HashSet<>(dto.getResourceIdList())
                    .stream()
                    .map(resourceId -> RoleAuthority.builder()
                            .authorityType(AuthorizeType.RESOURCE)
                            .authorityId(resourceId)
                            .roleId(dto.getRoleId())
                            .build())
                    .collect(Collectors.toList());
            list.addAll(resourceList);
        }
        if (dto.getMenuIdList() != null && !dto.getMenuIdList().isEmpty()) {
            //保存授予的菜单
            List<RoleAuthority> menuList = new HashSet<>(dto.getMenuIdList())
                    .stream()
                    .map(menuId -> RoleAuthority.builder()
                            .authorityType(AuthorizeType.MENU)
                            .authorityId(menuId)
                            .roleId(dto.getRoleId())
                            .build())
                    .collect(Collectors.toList());
            list.addAll(menuList);
        }
        super.saveBatch(list);

        // 角色下的所有用户
        List<Long> userIdList = userRoleService.listObjs(
                Wraps.<UserRole>lbQ().select(UserRole::getUserId).eq(UserRole::getRoleId, dto.getRoleId()),
                Convert::toLong);

        delUserAuthority(userIdList);
        cacheOps.del(new RoleResourceCacheKeyBuilder().key(dto.getRoleId()));
        cacheOps.del(new RoleMenuCacheKeyBuilder().key(dto.getRoleId()));
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByAuthorityId(List<Long> ids) {
        List<Long> roleIds = listObjs(
                Wraps.<RoleAuthority>lbQ().select(RoleAuthority::getRoleId).in(RoleAuthority::getAuthorityId, ids),
                Convert::toLong);

        if (!roleIds.isEmpty()) {
            remove(Wraps.<RoleAuthority>lbQ().in(RoleAuthority::getAuthorityId, ids));

            List<Long> userIdList = userRoleService.listObjs(
                    Wraps.<UserRole>lbQ().select(UserRole::getUserId).in(UserRole::getRoleId, roleIds),
                    Convert::toLong);

            delUserAuthority(userIdList);
            cacheOps.del(roleIds.stream().map(new RoleResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
            cacheOps.del(roleIds.stream().map(new RoleMenuCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        }
        return true;
    }

    private void delUserAuthority(Collection<Long> collections) {
        cacheOps.del(collections.stream().map(new UserResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        cacheOps.del(collections.stream().map(new UserMenuCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        cacheOps.del(collections.stream().map(new UserRoleCacheKeyBuilder()::key).toArray(CacheKey[]::new));
    }
}
