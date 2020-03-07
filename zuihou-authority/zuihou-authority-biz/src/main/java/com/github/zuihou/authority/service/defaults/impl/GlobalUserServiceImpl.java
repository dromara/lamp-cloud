package com.github.zuihou.authority.service.defaults.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.zuihou.authority.dao.defaults.GlobalUserMapper;
import com.github.zuihou.authority.dto.defaults.GlobalUserSaveDTO;
import com.github.zuihou.authority.dto.defaults.GlobalUserUpdateDTO;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.auth.UserRole;
import com.github.zuihou.authority.entity.defaults.GlobalUser;
import com.github.zuihou.authority.service.auth.UserRoleService;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.defaults.GlobalUserService;
import com.github.zuihou.base.service.SuperServiceImpl;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.utils.BeanPlusUtil;
import com.github.zuihou.utils.BizAssert;
import com.github.zuihou.utils.StrHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.zuihou.utils.BizAssert.isTrue;

/**
 * <p>
 * 业务实现类
 * 全局账号
 * </p>
 *
 * @author zuihou
 * @date 2019-10-25
 */
@Slf4j
@Service
public class GlobalUserServiceImpl extends SuperServiceImpl<GlobalUserMapper, GlobalUser> implements GlobalUserService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public Boolean check(String tenantCode, String account) {
        int globalUserCount = super.count(Wraps.<GlobalUser>lbQ()
                .eq(GlobalUser::getTenantCode, tenantCode)
                .eq(GlobalUser::getAccount, account));
        if (globalUserCount > 0) {
            return false;
        }
        BaseContextHandler.setTenant(tenantCode);
        int userCount = userService.count(Wraps.<User>lbQ()
                .eq(User::getAccount, account));
        if (userCount > 0) {
            return false;
        }
        return true;
    }

    @Override
    public GlobalUser save(GlobalUserSaveDTO data) {
        BizAssert.equals(data.getPassword(), data.getConfirmPassword(), "2次输入的密码不一致");
        BaseContextHandler.setTenant(data.getTenantCode());
        isTrue(check(data.getTenantCode(), data.getAccount()), "账号已经存在");

        String md5Password = SecureUtil.md5(data.getPassword());
        GlobalUser globalAccount = BeanPlusUtil.toBean(data, GlobalUser.class);
        // defaults 库
        globalAccount.setPassword(md5Password);
        save(globalAccount);

        // 1，保存租户用户 // 租户库
        User user = BeanPlusUtil.toBean(data, User.class);
        user.setId(globalAccount.getId());
        user.setPassword(md5Password)
                .setName(StrHelper.getOrDef(data.getName(), data.getAccount()))
                .setStatus(true);
        userService.save(user);

        userRoleService.initAdmin(user.getId());
        return globalAccount;
    }

    @Override
    public boolean removeByIds(String tenantCode, List<Long> ids) {
        boolean flag = super.removeByIds(ids);

        BaseContextHandler.setTenant(tenantCode);
        userService.removeByIds(ids);

        // 关联数据
        userRoleService.remove(Wraps.<UserRole>lbQ().in(UserRole::getUserId, ids));

        //TODO 缓存 & T人下线

        return flag;
    }

    @Override
    public GlobalUser update(GlobalUserUpdateDTO data) {
        if (StrUtil.isNotBlank(data.getPassword()) || StrUtil.isNotBlank(data.getPassword())) {
            BizAssert.equals(data.getPassword(), data.getConfirmPassword(), "2次输入的密码不一致");
        }

        GlobalUser globalUser = BeanPlusUtil.toBean(data, GlobalUser.class);
        User user = BeanPlusUtil.toBean(data, User.class);
        if (StrUtil.isNotBlank(data.getPassword())) {
            String md5Password = SecureUtil.md5(data.getPassword());
            globalUser.setPassword(md5Password);

            user.setPassword(md5Password);
        }
        updateById(globalUser);
        BaseContextHandler.setTenant(data.getTenantCode());
        userService.updateById(user);
        return globalUser;
    }
}
