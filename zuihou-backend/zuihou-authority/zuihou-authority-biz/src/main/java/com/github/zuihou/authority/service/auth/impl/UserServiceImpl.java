package com.github.zuihou.authority.service.auth.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.auth.UserMapper;
import com.github.zuihou.authority.entity.auth.Role;
import com.github.zuihou.authority.entity.auth.RoleOrg;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.service.auth.RoleOrgService;
import com.github.zuihou.authority.service.auth.RoleService;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.database.mybatis.auth.DataScopeType;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 账号
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleOrgService roleOrgService;
    @Autowired
    private OrgService orgService;

    @Override
    public List<User> findUserByRoleId(Long roleId, String keyword) {
        return baseMapper.findUserByRoleId(roleId, keyword);
    }

    @Override
    public Map<String, Object> getDataScopeById(Long userId) {
        Map<String, Object> map = new HashMap<>(2);
        List<Long> orgIds = new ArrayList<>();
        DataScopeType dsType = DataScopeType.SELF;

        List<Role> list = roleService.findRoleByUserId(userId);
        Optional<Role> min = list.stream().min(Comparator.comparingInt(Role::getDsType));

        if (min.isPresent()) {
            Role role = min.get();
            dsType = DataScopeType.get(role.getDsType());

            if (DataScopeType.CUSTOMIZE.eq(dsType)) {
                LbqWrapper<RoleOrg> wrapper = Wraps.<RoleOrg>lbQ().select(RoleOrg::getOrgId).eq(RoleOrg::getRoleId, role.getId());
                List<RoleOrg> roleOrgList = roleOrgService.list(wrapper);

                orgIds = roleOrgList.stream().mapToLong(RoleOrg::getOrgId).boxed().collect(Collectors.toList());
            } else if (DataScopeType.THIS_LEVEL.eq(dsType)) {
                User user = super.getById(userId);
                if (user != null) {
                    orgIds.add(user.getOrgId());
                }
            } else if (DataScopeType.THIS_LEVEL_CHILDREN.eq(dsType)) {
                User user = super.getById(userId);
                if (user != null) {
                    List<Org> orgList = orgService.findChildren(Arrays.asList(user.getOrgId()));
                    orgIds = orgList.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList());
                }
            }
        }
        map.put("dsType", dsType.getVal());
        map.put("orgIds", orgIds);
        return map;
    }
}
