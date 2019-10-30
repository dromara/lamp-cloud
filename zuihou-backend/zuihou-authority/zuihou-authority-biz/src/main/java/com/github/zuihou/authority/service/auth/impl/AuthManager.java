package com.github.zuihou.authority.service.auth.impl;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.zuihou.auth.server.utils.JwtTokenServerUtils;
import com.github.zuihou.auth.utils.JwtUserInfo;
import com.github.zuihou.auth.utils.Token;
import com.github.zuihou.authority.dto.auth.LoginDTO;
import com.github.zuihou.authority.dto.auth.UserDTO;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.defaults.GlobalUser;
import com.github.zuihou.authority.entity.defaults.Tenant;
import com.github.zuihou.authority.enumeration.auth.Sex;
import com.github.zuihou.authority.enumeration.defaults.TenantStatusEnum;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.defaults.GlobalUserService;
import com.github.zuihou.authority.service.defaults.TenantService;
import com.github.zuihou.authority.utils.TimeUtils;
import com.github.zuihou.base.R;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.dozer.DozerUtils;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.exception.code.ExceptionCode;
import com.github.zuihou.utils.BizAssert;
import com.github.zuihou.utils.NumberHelper;

import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.github.zuihou.utils.BizAssert.gt;
import static com.github.zuihou.utils.BizAssert.isFalse;
import static com.github.zuihou.utils.BizAssert.isTrue;
import static com.github.zuihou.utils.BizAssert.notNull;

/**
 * @author zuihou
 * @createTime 2017-12-15 13:42
 */
@Service
@Slf4j
public class AuthManager {
    @Autowired
    private JwtTokenServerUtils jwtTokenServerUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private GlobalUserService globalUserService;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private DozerUtils dozer;

    private static final String SUPER_TENANT = "admin";
    private static final String[] SUPER_ACCOUNT = new String[]{"admin", "superAdmin"};


    /**
     * 超管账号登录
     *
     * @param account
     * @param password
     * @return
     */
    public R<LoginDTO> adminLogin(String account, String password) {
        GlobalUser user = globalUserService.getOne(Wrappers.<GlobalUser>lambdaQuery()
                .eq(GlobalUser::getAccount, account).eq(GlobalUser::getTenantCode, SUPER_TENANT));
        // 密码错误
        if (user == null) {
            throw new BizException(ExceptionCode.JWT_USER_INVALID.getCode(), ExceptionCode.JWT_USER_INVALID.getMsg());
        }

        String passwordMd5 = DigestUtils.md5Hex(password);
        if (!user.getPassword().equalsIgnoreCase(passwordMd5)) {
            userService.updatePasswordErrorNumById(user.getId());
            return R.fail("用户名或密码错误!");
        }
        JwtUserInfo userInfo = new JwtUserInfo(user.getId(), user.getAccount(), user.getName(), 0L, 0L);

        Token token = jwtTokenServerUtils.generateUserToken(userInfo, null);
        log.info("token={}", token.getToken());

        UserDTO dto = dozer.map(user, UserDTO.class);
        dto.setIsDelete(false).setOrgId(0L).setStationId(0L).setPhoto("").setSex(Sex.M).setWorkDescribe("心情很美丽");
        return R.success(LoginDTO.builder().user(dto).token(token).build());
    }

    /**
     * 租户账号登录
     *
     * @param tenantCode
     * @param account
     * @param password
     * @return
     */
    public R<LoginDTO> login(String tenantCode, String account, String password) {
        // 1，检测租户是否可用
        Tenant tenant = tenantService.getByCode(tenantCode);
        notNull(tenant, "企业不存在");
        BizAssert.equals(TenantStatusEnum.NORMAL, tenant.getStatus(), "企业不可用~");
        if (tenant.getExpirationTime() != null) {
            gt(LocalDateTime.now(), tenant.getExpirationTime(), "企业服务已到期~");
        }

        // 2. 验证登录
        R<User> result = getUser(tenant, account, password);
        if (result.getIsError()) {
            return R.fail(result.getCode(), result.getMsg());
        }
        User user = result.getData();

        // 3, token
        Token token = getToken(user);
        log.info("account={}", account);
        return R.success(LoginDTO.builder().user(dozer.map(user, UserDTO.class)).token(token).build());
    }

    private Token getToken(User user) {
        JwtUserInfo userInfo = new JwtUserInfo(user.getId(), user.getAccount(), user.getName(), user.getOrgId(), user.getStationId());

        Token token = jwtTokenServerUtils.generateUserToken(userInfo, null);
        log.info("token={}", token.getToken());
        return token;
    }

    private R<User> getUser(Tenant tenant, String account, String password) {
        BaseContextHandler.setTenant(tenant.getCode());
        User user = userService.getOne(Wrappers.<User>lambdaQuery()
                .eq(User::getAccount, account));
        // 密码错误
        String passwordMd5 = DigestUtils.md5Hex(password);
        if (user == null) {
            throw new BizException(ExceptionCode.JWT_USER_INVALID.getCode(), ExceptionCode.JWT_USER_INVALID.getMsg());
        }

        if (!user.getPassword().equalsIgnoreCase(passwordMd5)) {
            userService.updatePasswordErrorNumById(user.getId());
            return R.fail("用户名或密码错误!");
        }

        // 密码过期
        if (user.getPasswordExpireTime() != null) {
            gt(LocalDateTime.now(), user.getPasswordExpireTime(), "用户密码已过期，请修改密码或者联系管理员重置!");
        }

        // 用户禁用
        isTrue(user.getStatus(), "用户被禁用，请联系管理员！");

        // 用户锁定
        Integer maxPasswordErrorNum = NumberHelper.getOrDef(tenant.getPasswordErrorNum(), 0);
        Integer passwordErrorNum = NumberHelper.getOrDef(user.getPasswordErrorNum(), 0);
        if (maxPasswordErrorNum > 0 && passwordErrorNum > maxPasswordErrorNum) {
            log.info("当前错误次数{}, 最大次数:{}", passwordErrorNum, maxPasswordErrorNum);

            LocalDateTime passwordErrorLockTime = TimeUtils.getPasswordErrorLockTime(tenant.getPasswordErrorLockTime());
            log.info("passwordErrorLockTime={}", passwordErrorLockTime);
            if (passwordErrorLockTime.isAfter(user.getPasswordErrorLastTime())) {
                return R.fail("密码连续输错次数已达到%s次,用户已被锁定~", maxPasswordErrorNum);
            }
        }

        // 错误次数清空
        userService.update(Wraps.<User>lbU().set(User::getPasswordErrorNum, 0).eq(User::getId, user.getId()));
        return R.success(user);
    }

    public JwtUserInfo validateUserToken(String token) throws BizException {
        return jwtTokenServerUtils.getUserInfo(token);
    }

    public void invalidUserToken(String token) throws BizException {

    }

}
