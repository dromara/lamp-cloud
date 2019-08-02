package com.github.zuihou.authority.service.auth.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.auth.RoleMapper;
import com.github.zuihou.authority.dto.auth.RoleSaveDTO;
import com.github.zuihou.authority.dto.auth.RoleUpdateDTO;
import com.github.zuihou.authority.entity.auth.Role;
import com.github.zuihou.authority.entity.auth.RoleOrg;
import com.github.zuihou.authority.service.auth.RoleOrgService;
import com.github.zuihou.authority.service.auth.RoleService;
import com.github.zuihou.authority.strategy.DataScopeContext;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.dozer.DozerUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private DozerUtils dozer;
    @Autowired
    private RoleOrgService roleOrgService;
    @Autowired
    private DataScopeContext dataScopeContext;

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
        Role role = dozer.map(data, Role.class);
        super.save(role);

        saveRoleOrg(userId, role, data.getOrgList());
    }

    @Override
    public void updateRole(RoleUpdateDTO data, Long userId) {
        Role role = dozer.map(data, Role.class);
        super.updateById(role);

        roleOrgService.remove(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getOrgId, data.getId()));
        saveRoleOrg(userId, role, data.getOrgList());
    }

    private void saveRoleOrg(Long userId, Role role, List<Long> orgList) {
        // 根据 数据范围类型 和 勾选的组织ID， 重新计算全量的组织ID
        List<Long> orgIds = dataScopeContext.getOrgIdsForDataScope(orgList, role.getDsType(), userId);
        if (!orgIds.isEmpty()) {
            List<RoleOrg> list = orgList.stream().map((orgId) ->
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
}
