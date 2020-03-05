package com.github.zuihou.authority.service.auth.impl;

import cn.hutool.core.convert.Convert;
import com.github.zuihou.authority.dao.auth.RoleMapper;
import com.github.zuihou.authority.dto.auth.RoleSaveDTO;
import com.github.zuihou.authority.dto.auth.RoleUpdateDTO;
import com.github.zuihou.authority.entity.auth.Role;
import com.github.zuihou.authority.entity.auth.RoleAuthority;
import com.github.zuihou.authority.entity.auth.RoleOrg;
import com.github.zuihou.authority.entity.auth.UserRole;
import com.github.zuihou.authority.service.auth.RoleAuthorityService;
import com.github.zuihou.authority.service.auth.RoleOrgService;
import com.github.zuihou.authority.service.auth.RoleService;
import com.github.zuihou.authority.service.auth.UserRoleService;
import com.github.zuihou.authority.strategy.DataScopeContext;
import com.github.zuihou.base.service.SuperCacheServiceImpl;
import com.github.zuihou.common.constant.CacheKey;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.utils.BeanPlusUtil;
import com.github.zuihou.utils.CodeGenerate;
import com.github.zuihou.utils.StrHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.zuihou.common.constant.CacheKey.ROLE;

/**
 * <p>
 * 业务实现类
 * 角色
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Slf4j
@Service
@CacheConfig(cacheNames = ROLE)
public class RoleServiceImpl extends SuperCacheServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleOrgService roleOrgService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private DataScopeContext dataScopeContext;
    @Autowired
    private CodeGenerate codeGenerate;

    protected RoleService currentProxy() {
        return ((RoleService) AopContext.currentProxy());
    }

    @Override
    protected String getRegion() {
        return ROLE;
    }

    @Override
    public boolean isSuperAdmin(Long userId) {
        return userId != null && userId.equals(1L);
    }


    /**
     * 删除角色时，需要级联删除跟角色相关的一切资源：
     * 1，角色本身
     * 2，角色-组织：
     * 3，角色-权限（菜单和按钮）：
     * 4，角色-用户：角色拥有的用户
     * 5，用户-权限：
     *
     * @param ids
     * @return
     */
    @Override
    public boolean removeByIdWithCache(List<Long> ids) {
        if (ids.isEmpty()) {
            return true;
        }
        boolean removeFlag = currentProxy().removeByIds(ids);
        roleOrgService.remove(Wraps.<RoleOrg>lbQ().in(RoleOrg::getRoleId, ids));
        roleAuthorityService.remove(Wraps.<RoleAuthority>lbQ().in(RoleAuthority::getRoleId, ids));

        List<Long> userIds = userRoleService.listObjs(
                Wraps.<UserRole>lbQ().select(UserRole::getUserId).in(UserRole::getRoleId, ids),
                Convert::toLong);

        userRoleService.remove(Wraps.<UserRole>lbQ().in(UserRole::getRoleId, ids));

        ids.forEach((id) -> {
            cacheChannel.evict(CacheKey.ROLE_MENU, String.valueOf(id));
            cacheChannel.evict(CacheKey.ROLE_RESOURCE, String.valueOf(id));
            cacheChannel.evict(CacheKey.ROLE_ORG, String.valueOf(id));
        });

        if (!userIds.isEmpty()) {
            String[] userIdArray = userIds.stream().toArray(String[]::new);
            cacheChannel.evict(CacheKey.USER_ROLE, userIdArray);
            cacheChannel.evict(CacheKey.USER_RESOURCE, userIdArray);
            cacheChannel.evict(CacheKey.USER_MENU, userIdArray);
        }
        return removeFlag;
    }

    @Override
    public List<Role> findRoleByUserId(Long userId) {
        return baseMapper.findRoleByUserId(userId);
    }

    /**
     * 1，保存角色
     * 2，保存 与组织的关系
     *
     * @param data
     * @param userId 用户id
     */
    @Override
    public void saveRole(RoleSaveDTO data, Long userId) {
        Role role = BeanPlusUtil.toBean(data, Role.class);
        role.setCode(StrHelper.getOrDef(data.getCode(), codeGenerate.next()));
        role.setReadonly(false);
        currentProxy().save(role);

        saveRoleOrg(userId, role, data.getOrgList());

        cacheChannel.set(CacheKey.ROLE, String.valueOf(role.getId()), role);
    }

    @Override
//    @CacheEvict(value = CacheKey.ROLE, key = "#data.id")
    public void updateRole(RoleUpdateDTO data, Long userId) {
        Role role = BeanPlusUtil.toBean(data, Role.class);
        currentProxy().updateById(role);

        roleOrgService.remove(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getRoleId, data.getId()));
        saveRoleOrg(userId, role, data.getOrgList());

        //角色关联的组织
        cacheChannel.evict(CacheKey.ROLE_ORG, String.valueOf(data.getId()));
    }

    private void saveRoleOrg(Long userId, Role role, List<Long> orgList) {
        // 根据 数据范围类型 和 勾选的组织ID， 重新计算全量的组织ID
        List<Long> orgIds = dataScopeContext.getOrgIdsForDataScope(orgList, role.getDsType(), userId);
        if (orgIds != null && !orgIds.isEmpty()) {
            List<RoleOrg> list = orgIds.stream().map((orgId) ->
                    RoleOrg.builder()
                            .orgId(orgId).roleId(role.getId())
                            .build()
            ).collect(Collectors.toList());
            roleOrgService.saveBatch(list);
        }
    }

    @Override
    public List<Long> findUserIdByCode(String[] codes) {
        return baseMapper.findUserIdByCode(codes);
    }

    @Override
    public Boolean check(String code) {
        return super.count(Wraps.<Role>lbQ().eq(Role::getCode, code)) > 0;
    }
}
