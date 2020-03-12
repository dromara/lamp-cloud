package com.github.zuihou.authority.service.auth.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dao.auth.UserMapper;
import com.github.zuihou.authority.dao.defaults.TenantMapper;
import com.github.zuihou.authority.dto.auth.UserUpdatePasswordDTO;
import com.github.zuihou.authority.entity.auth.Role;
import com.github.zuihou.authority.entity.auth.RoleOrg;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.auth.UserRole;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.entity.core.Station;
import com.github.zuihou.authority.entity.defaults.Tenant;
import com.github.zuihou.authority.service.auth.RoleOrgService;
import com.github.zuihou.authority.service.auth.RoleService;
import com.github.zuihou.authority.service.auth.UserRoleService;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.authority.service.core.StationService;
import com.github.zuihou.base.service.SuperCacheServiceImpl;
import com.github.zuihou.common.constant.BizConstant;
import com.github.zuihou.common.constant.CacheKey;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.database.mybatis.auth.DataScope;
import com.github.zuihou.database.mybatis.auth.DataScopeType;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.injection.annonation.InjectionResult;
import com.github.zuihou.model.RemoteData;
import com.github.zuihou.user.feign.UserQuery;
import com.github.zuihou.user.model.SysOrg;
import com.github.zuihou.user.model.SysRole;
import com.github.zuihou.user.model.SysStation;
import com.github.zuihou.user.model.SysUser;
import com.github.zuihou.utils.BeanPlusUtil;
import com.github.zuihou.utils.BizAssert;
import com.github.zuihou.utils.MapHelper;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
@CacheConfig(cacheNames = CacheKey.USER)
public class UserServiceImpl extends SuperCacheServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private StationService stationService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleOrgService roleOrgService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private TenantMapper tenantMapper;

    @Override
    protected String getRegion() {
        return CacheKey.USER;
    }


    protected UserService currentProxy() {
        return ((UserService) AopContext.currentProxy());
    }

    @Override
    @InjectionResult
    public IPage<User> findPage(IPage<User> page, LbqWrapper<User> wrapper) {
        return baseMapper.findPage(page, wrapper, new DataScope());
    }

    @Override
    public int resetPassErrorNum(Long id) {
        return baseMapper.resetPassErrorNum(id);
    }

    @Override
    public Boolean updatePassword(UserUpdatePasswordDTO data) {
        BizAssert.equals(data.getConfirmPassword(), data.getPassword(), "密码与确认密码不一致");

        User user = getById(data.getId());
        BizAssert.notNull(user, "用户不存在");
        String oldPassword = SecureUtil.md5(data.getOldPassword());
        BizAssert.equals(user.getPassword(), oldPassword, "旧密码错误");

        User build = User.builder().password(data.getPassword()).id(data.getId()).build();
        currentProxy().updateById(build);
        return true;
    }

    @Override
    public User getByAccount(String account) {
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
                User user = currentProxy().getByIdCache(userId);
                if (user != null) {
                    Long orgId = RemoteData.getKey(user.getOrg());
                    orgIds.add(orgId);
                }
            } else if (DataScopeType.THIS_LEVEL_CHILDREN.eq(dsType)) {
                User user = currentProxy().getByIdCache(userId);
                if (user != null) {
                    Long orgId = RemoteData.getKey(user.getOrg());
                    List<Org> orgList = orgService.findChildren(Arrays.asList(orgId));
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
    @CacheEvict(key = "#id")
    public void updatePasswordErrorNumById(Long id) {
        baseMapper.incrPasswordErrorNumById(id);
    }

    @Override
    public void updateLoginTime(String account) {
        User user = getByAccount(account);
        if (user == null) {
            return;
        }

        baseMapper.updateLastLoginTime(account, LocalDateTime.now());

        String key = key(user.getId());
        cacheChannel.evict(getRegion(), key);
    }

    @Override
    public User saveUser(User user) {
        Tenant tenant = tenantMapper.getByCode(BaseContextHandler.getTenant());
        BizAssert.notNull(tenant, "租户不存在，请联系管理员");

        // 永不过期
        if (tenant.getPasswordExpire() == null || tenant.getPasswordExpire() <= 0) {
            user.setPasswordExpireTime(null);
        } else {
            user.setPasswordExpireTime(LocalDateTime.now().plusDays(tenant.getPasswordExpire()));
        }

        user.setPassword(SecureUtil.md5(user.getPassword()));
        user.setPasswordErrorNum(0);
        super.save(user);
        return user;
    }

    @Override
    public boolean reset(List<Long> ids) {
        if (ids.isEmpty()) {
            return true;
        }
        Tenant tenant = tenantMapper.getByCode(BaseContextHandler.getTenant());
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
        String[] keys = ids.stream().map((id) -> key(id)).toArray(String[]::new);
        cacheChannel.evict(getRegion(), keys);

        return true;
    }

    @Override
    public User updateUser(User user) {
        Tenant tenant = tenantMapper.getByCode(BaseContextHandler.getTenant());
        BizAssert.notNull(tenant, "租户不存在，请联系管理员");
        // 永不过期
        if (tenant.getPasswordExpire() == null || tenant.getPasswordExpire() <= 0) {
            user.setPasswordExpireTime(null);
        } else {
            user.setPasswordExpireTime(LocalDateTime.now().plusDays(tenant.getPasswordExpire()));
        }

        if (StrUtil.isNotEmpty(user.getPassword())) {
            user.setPassword(SecureUtil.md5(user.getPassword()));
        }
        currentProxy().updateById(user);
        return user;
    }

    @Override
    public boolean remove(List<Long> ids) {
        userRoleService.remove(Wraps.<UserRole>lbQ()
                .in(UserRole::getUserId, ids)
        );
        return currentProxy().removeByIds(ids);
    }

    @Override
    public Map<Serializable, Object> findUserByIds(Set<Serializable> ids) {
        List<User> list = findUser(ids);

        //key 是 用户id
        ImmutableMap<Serializable, Object> typeMap = MapHelper.uniqueIndex(list, User::getId, (user) -> user);
        return typeMap;
    }

    private List<User> findUser(Set<Serializable> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> idList = ids.stream().mapToLong(Convert::toLong).boxed().collect(Collectors.toList());


        List<User> list = null;
        if (idList.size() > 100) {
            LbqWrapper<User> query = Wraps.<User>lbQ()
                    .in(User::getId, idList)
                    .eq(User::getStatus, true);
            list = super.list(query);

            if (!list.isEmpty()) {
                list.forEach(user -> {
                    String menuKey = key(user.getId());
                    cacheChannel.set(getRegion(), menuKey, user);
                });
            }

        } else {
            list = idList.stream().map(currentProxy()::getByIdCache)
                    .filter(Objects::nonNull).collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public Map<Serializable, Object> findUserNameByIds(Set<Serializable> ids) {
        List<User> list = findUser(ids);

        //key 是 用户id
        ImmutableMap<Serializable, Object> typeMap = MapHelper.uniqueIndex(list, User::getId, User::getName);
        return typeMap;
    }

    @Override
    public SysUser getSysUserById(Long id, UserQuery query) {
        User user = currentProxy().getByIdCache(id);
        if (user == null) {
            return null;
        }
        SysUser sysUser = BeanUtil.toBean(user, SysUser.class);

        sysUser.setOrgId(RemoteData.getKey(user.getOrg()));
        sysUser.setStationId(RemoteData.getKey(user.getOrg()));

        if (query.getFull() || query.getOrg()) {
            sysUser.setOrg(BeanUtil.toBean(orgService.getById(user.getOrg()), SysOrg.class));
        }

        if (query.getFull() || query.getStation()) {
            Station station = stationService.getById(user.getStation());
            if (station != null) {
                SysStation sysStation = BeanUtil.toBean(station, SysStation.class);
                sysStation.setOrgId(RemoteData.getKey(station.getOrg()));
                sysUser.setStation(sysStation);
            }
        }

        if (query.getFull() || query.getRoles()) {
            List<Role> list = roleService.findRoleByUserId(id);
            sysUser.setRoles(BeanPlusUtil.toBeanList(list, SysRole.class));
        }

        return sysUser;
    }

    @Override
    public List<Long> findAllUserId() {
        return super.list().stream().mapToLong(User::getId).boxed().collect(Collectors.toList());
    }
}
