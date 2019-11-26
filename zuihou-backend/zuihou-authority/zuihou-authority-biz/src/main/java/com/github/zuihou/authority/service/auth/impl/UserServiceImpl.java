package com.github.zuihou.authority.service.auth.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.auth.UserMapper;
import com.github.zuihou.authority.entity.auth.Role;
import com.github.zuihou.authority.entity.auth.RoleOrg;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.auth.UserRole;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.entity.defaults.Tenant;
import com.github.zuihou.authority.service.auth.RoleOrgService;
import com.github.zuihou.authority.service.auth.RoleService;
import com.github.zuihou.authority.service.auth.UserRoleService;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.authority.service.defaults.TenantService;
import com.github.zuihou.common.constant.BizConstant;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.database.mybatis.auth.DataScope;
import com.github.zuihou.database.mybatis.auth.DataScopeType;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.utils.BizAssert;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
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
    private UserRoleService userRoleService;
    @Autowired
    private RoleOrgService roleOrgService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private TenantService tenantService;

    @Override
    public IPage<User> findPage(IPage<User> page, LbqWrapper<User> wrapper) {
        return baseMapper.findPage(page, wrapper, new DataScope());
    }

    @Override
    public User getByAccount(String account) {
        //TODO 缓存
        return super.getOne(Wraps.<User>lbQ().eq(User::getAccount, account));
    }

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
        Optional<Role> min = list.stream().min(Comparator.comparingInt((item) -> item.getDsType().getVal()));

        if (min.isPresent()) {
            Role role = min.get();
            dsType = role.getDsType();

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

    @Override
    public boolean check(String account) {
        return super.count(Wraps.<User>lbQ().eq(User::getAccount, account)) > 0;
    }

    @Override
    public void updatePasswordErrorNumById(Long id) {
        baseMapper.incrPasswordErrorNumById(id);
    }

    @Override
    public void updateLoginTime(String account) {
        baseMapper.update(User.builder().lastLoginTime(LocalDateTime.now()).build(), Wraps.<User>lbQ().eq(User::getAccount, account));
    }

    @Override
    public User saveUser(User user) {
        Tenant tenant = tenantService.getByCode(BaseContextHandler.getTenant());
        BizAssert.notNull(tenant, "租户不存在，请联系管理员");

        // 永不过期
        if (tenant.getPasswordExpire() == null || tenant.getPasswordExpire() <= 0) {
            user.setPasswordExpireTime(null);
        } else {
            user.setPasswordExpireTime(LocalDateTime.now().plusDays(tenant.getPasswordExpire()));
        }

        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setPasswordErrorNum(0);
        super.save(user);
        return user;
    }

    @Override
    public boolean reset(List<Long> ids) {
        Tenant tenant = tenantService.getByCode(BaseContextHandler.getTenant());
        BizAssert.notNull(tenant, "租户不存在，请联系管理员");

        LocalDateTime passwordExpireTime = null;
        if (tenant.getPasswordExpire() != null && tenant.getPasswordExpire() > 0) {
            passwordExpireTime = LocalDateTime.now().plusDays(tenant.getPasswordExpire());
        }

        String defPassword = BizConstant.DEF_PASSWORD;
        super.update(Wraps.<User>lbU()
                .set(User::getPassword, defPassword)
                .set(User::getPasswordErrorNum, 0L)
                .set(User::getPasswordErrorLastTime, null)
                .set(User::getPasswordExpireTime, passwordExpireTime)
                .in(User::getId, ids)
        );
        return true;
    }

    @Override
    public User updateUser(User user) {
        Tenant tenant = tenantService.getByCode(BaseContextHandler.getTenant());
        BizAssert.notNull(tenant, "租户不存在，请联系管理员");
        // 永不过期
        if (tenant.getPasswordExpire() == null || tenant.getPasswordExpire() <= 0) {
            user.setPasswordExpireTime(null);
        } else {
            user.setPasswordExpireTime(LocalDateTime.now().plusDays(tenant.getPasswordExpire()));
        }
        super.updateById(user);
        return user;
    }

    @Override
    public boolean remove(List<Long> ids) {
        userRoleService.remove(Wraps.<UserRole>lbQ()
                .in(UserRole::getUserId, ids)
        );
        return super.removeByIds(ids);
    }
}
