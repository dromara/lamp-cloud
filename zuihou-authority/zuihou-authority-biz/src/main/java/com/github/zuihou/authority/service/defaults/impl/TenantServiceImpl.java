package com.github.zuihou.authority.service.defaults.impl;

import cn.hutool.crypto.SecureUtil;
import com.github.zuihou.authority.dao.defaults.TenantMapper;
import com.github.zuihou.authority.dto.defaults.TenantSaveDTO;
import com.github.zuihou.authority.dto.defaults.TenantSaveInitDTO;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.defaults.GlobalUser;
import com.github.zuihou.authority.entity.defaults.Tenant;
import com.github.zuihou.authority.enumeration.defaults.TenantStatusEnum;
import com.github.zuihou.authority.enumeration.defaults.TenantTypeEnum;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.defaults.GlobalUserService;
import com.github.zuihou.authority.service.defaults.InitSystemContext;
import com.github.zuihou.authority.service.defaults.TenantService;
import com.github.zuihou.base.service.SuperCacheServiceImpl;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.utils.BeanPlusUtil;
import com.github.zuihou.utils.BizAssert;
import com.github.zuihou.utils.StrHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import static com.github.zuihou.common.constant.CacheKey.TENANT;
import static com.github.zuihou.utils.BizAssert.isFalse;

/**
 * <p>
 * 业务实现类
 * 企业
 * </p>
 *
 * @author zuihou
 * @date 2019-10-24
 */
@Slf4j
@Service
@CacheConfig(cacheNames = TENANT)
public class TenantServiceImpl extends SuperCacheServiceImpl<TenantMapper, Tenant> implements TenantService {

    @Override
    protected String getRegion() {
        return TENANT;
    }

    protected TenantService currentProxy() {
        return ((TenantService) AopContext.currentProxy());
    }

    @Autowired
    private GlobalUserService globalUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private InitSystemContext initSystemContext;

    @Override
    public Tenant getByCode(String tenant) {
        return super.getOne(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenant));
    }

    @Override
    public Tenant saveInit(TenantSaveInitDTO data) {
        BizAssert.equals(data.getPassword(), data.getConfirmPassword(), "2次输入的密码不一致");
        // defaults 库
        isFalse(check(data.getCode()), "编码重复，请重新输入");
        // 租户库
        BaseContextHandler.setTenant(data.getCode());
        isFalse(userService.check(data.getAccount()), "账号已经存在");

        // 1， 保存租户 (默认库)
        Tenant tenant = BeanPlusUtil.toBean(data, Tenant.class);
        tenant.setStatus(TenantStatusEnum.NORMAL);
        tenant.setType(TenantTypeEnum.CREATE);
        // defaults 库
        currentProxy().save(tenant);

        // 2， 保存全局数据(默认库)
        GlobalUser globalAccount = BeanPlusUtil.toBean(data, GlobalUser.class);
        globalAccount.setTenantCode(tenant.getCode());
        // defaults 库
        globalUserService.save(globalAccount);

        // 3, 初始化库，表, 数据  考虑异步完成 // 租户库
        initSystemContext.init(tenant.getCode());

        // 4，保存租户用户 // 租户库
        User user = BeanPlusUtil.toBean(data, User.class);
        user.setId(globalAccount.getId());
        user.setPassword(SecureUtil.md5(data.getPassword()));
//            user.setPasswordExpireTime(LocalDateTime.now().plusDays(authorityServerProperties.getPasswordExpire()));
        user.setName(StrHelper.getOrDef(data.getName(), data.getAccount()));
        userService.save(user);

        // 5， 新建用户授权 // 租户库

        return tenant;
    }

    @Override
    public Tenant save(TenantSaveDTO data) {
        // defaults 库
        isFalse(check(data.getCode()), "编码重复，请重新输入");

        // 1， 保存租户 (默认库)
        Tenant tenant = BeanPlusUtil.toBean(data, Tenant.class);
        tenant.setStatus(TenantStatusEnum.NORMAL);
        tenant.setType(TenantTypeEnum.CREATE);
        // defaults 库
        currentProxy().save(tenant);

        // 3, 初始化库，表, 数据  考虑异步完成 // 租户库
        initSystemContext.init(tenant.getCode());
        return tenant;
    }

    @Override
    public boolean check(String tenantCode) {
        return super.count(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenantCode)) > 0;
    }
}
