package com.github.zuihou.authority.service.auth.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.auth.RoleMapper;
import com.github.zuihou.authority.dto.auth.RoleSaveDTO;
import com.github.zuihou.authority.dto.auth.RoleUpdateDTO;
import com.github.zuihou.authority.entity.auth.Role;
import com.github.zuihou.authority.entity.auth.RoleOrg;
import com.github.zuihou.authority.service.auth.RoleOrgService;
import com.github.zuihou.authority.service.auth.RoleService;
import com.github.zuihou.authority.strategy.DataScopeContext;
import com.github.zuihou.base.id.CodeGenerate;
import com.github.zuihou.common.constant.CacheKey;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.utils.BeanPlusUtil;
import com.github.zuihou.utils.StrHelper;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private CacheChannel cache;
    @Autowired
    private RoleOrgService roleOrgService;
    @Autowired
    private DataScopeContext dataScopeContext;
    @Autowired
    private CodeGenerate codeGenerate;

    @Override
    public boolean isSuperAdmin(Long userId) {
        return userId != null && userId.equals(1L);
    }

    @Override
    @Cacheable(value = CacheKey.ROLE, key = "#id")
    public Role getByIdWithCache(Long id) {
        return super.getById(id);
    }

    @Override
    public boolean removeByIdWithCache(List<Long> ids) {
        if (ids.isEmpty()) {
            return true;
        }
        super.removeByIds(ids);
        roleOrgService.remove(Wraps.<RoleOrg>lbQ().in(RoleOrg::getRoleId, ids));

        //TODO 这里还要清除 用户拥有的角色 用户拥有的菜单和资源
//        cache.evict(CacheKey.USER_ROLE, userId);
//        cache.evict(CacheKey.USER_RESOURCE, userId);
//        cache.evict(CacheKey.USER_MENU, userId);
        ids.forEach((id) -> {
            cache.evict(CacheKey.ROLE, String.valueOf(id));
            cache.evict(CacheKey.ROLE_MENU, String.valueOf(id));
            cache.evict(CacheKey.ROLE_RESOURCE, String.valueOf(id));
            cache.evict(CacheKey.ROLE_ORG, String.valueOf(id));
        });
        return true;
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
        super.save(role);

        saveRoleOrg(userId, role, data.getOrgList());

        cache.set(CacheKey.ROLE, String.valueOf(role.getId()), role);
    }

    @Override
    @CacheEvict(value = CacheKey.ROLE, key = "#data.id")
    public void updateRole(RoleUpdateDTO data, Long userId) {
        Role role = BeanPlusUtil.toBean(data, Role.class);
        super.updateById(role);

        roleOrgService.remove(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getRoleId, data.getId()));
        saveRoleOrg(userId, role, data.getOrgList());

        //角色关联的组织
        cache.evict(CacheKey.ROLE_ORG, String.valueOf(data.getId()));
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
