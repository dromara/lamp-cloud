package com.github.zuihou.authority.service.auth.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.zuihou.auth.server.utils.JwtTokenServerUtils;
import com.github.zuihou.auth.utils.JwtUserInfo;
import com.github.zuihou.auth.utils.Token;
import com.github.zuihou.authority.dto.auth.LoginDTO;
import com.github.zuihou.authority.dto.auth.ResourceQueryDTO;
import com.github.zuihou.authority.dto.auth.UserDTO;
import com.github.zuihou.authority.entity.auth.Resource;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.defaults.GlobalUser;
import com.github.zuihou.authority.entity.defaults.Tenant;
import com.github.zuihou.authority.enumeration.auth.Sex;
import com.github.zuihou.authority.enumeration.defaults.TenantStatusEnum;
import com.github.zuihou.authority.service.auth.ResourceService;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.defaults.GlobalUserService;
import com.github.zuihou.authority.service.defaults.TenantService;
import com.github.zuihou.authority.utils.TimeUtils;
import com.github.zuihou.base.R;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.database.properties.DatabaseProperties;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.exception.code.ExceptionCode;
import com.github.zuihou.model.RemoteData;
import com.github.zuihou.utils.BeanPlusUtil;
import com.github.zuihou.utils.BizAssert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.zuihou.utils.BizAssert.*;

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
    private ResourceService resourceService;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private DatabaseProperties databaseProperties;

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
        GlobalUser user = this.globalUserService.getOne(Wrappers.<GlobalUser>lambdaQuery()
                .eq(GlobalUser::getAccount, account).eq(GlobalUser::getTenantCode, SUPER_TENANT));
        // 密码错误
        if (user == null) {
            throw new BizException(ExceptionCode.JWT_USER_INVALID.getCode(), ExceptionCode.JWT_USER_INVALID.getMsg());
        }

        String passwordMd5 = DigestUtils.md5Hex(password);
        if (!user.getPassword().equalsIgnoreCase(passwordMd5)) {
            this.userService.updatePasswordErrorNumById(user.getId());
            return R.fail("用户名或密码错误!");
        }
        JwtUserInfo userInfo = new JwtUserInfo(user.getId(), user.getAccount(), user.getName(), 0L, 0L);

        Token token = this.jwtTokenServerUtils.generateUserToken(userInfo, null);
        log.info("token={}", token.getToken());

        UserDTO dto = BeanPlusUtil.toBean(user, UserDTO.class);
        dto.setStatus(true).setOrg(new RemoteData<>(0L)).setStation(new RemoteData<>(0L)).setAvatar("").setSex(Sex.M).setWorkDescribe("心情很美丽");
        return R.success(LoginDTO.builder().user(dto).token(token).build());
    }

    /**
     * 多租户模式登陆
     *
     * @param tenantCode
     * @param account
     * @param password
     * @return
     */
    private R<LoginDTO> tenantLogin(String tenantCode, String account, String password) {
        // 1，检测租户是否可用
        Tenant tenant = this.tenantService.getByCode(tenantCode);
        notNull(tenant, "企业不存在");
        BizAssert.equals(TenantStatusEnum.NORMAL, tenant.getStatus(), "企业不可用~");
        if (tenant.getExpirationTime() != null) {
            gt(LocalDateTime.now(), tenant.getExpirationTime(), "企业服务已到期~");
        }
        BaseContextHandler.setTenant(tenant.getCode());

        // 2. 验证登录
        R<User> result = this.getUser(tenant, account, password);
        if (result.getIsError()) {
            return R.fail(result.getCode(), result.getMsg());
        }
        User user = result.getData();

        // 3, token
        Token token = this.getToken(user);

        List<Resource> resourceList = this.resourceService.findVisibleResource(ResourceQueryDTO.builder().userId(user.getId()).build());
        List<String> permissionsList = resourceList.stream().map(Resource::getCode).collect(Collectors.toList());

        log.info("account={}", account);
        return R.success(LoginDTO.builder().user(BeanPlusUtil.toBean(user, UserDTO.class)).permissionsList(permissionsList).token(token).build());
    }

    /**
     * 非多租户模式登陆
     *
     * @param account
     * @param password
     * @return
     */
    private R<LoginDTO> simpleLogin(String account, String password) {
        // 2. 验证登录
        R<User> result = this.getUser(new Tenant(), account, password);
        if (result.getIsError()) {
            return R.fail(result.getCode(), result.getMsg());
        }
        User user = result.getData();

        // 3, token
        Token token = this.getToken(user);

        List<Resource> resourceList = this.resourceService.findVisibleResource(ResourceQueryDTO.builder().userId(user.getId()).build());
        List<String> permissionsList = resourceList.stream().map(Resource::getCode).collect(Collectors.toList());

        log.info("account={}", account);
        return R.success(LoginDTO.builder().user(BeanPlusUtil.toBean(user, UserDTO.class)).permissionsList(permissionsList).token(token).build());
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
        if (this.databaseProperties.getIsMultiTenant()) {
            return this.tenantLogin(tenantCode, account, password);
        } else {
            return this.simpleLogin(account, password);
        }
    }

    private Token getToken(User user) {
        Long orgId = RemoteData.getKey(user.getOrg());
        Long stationId = RemoteData.getKey(user.getStation());

        JwtUserInfo userInfo = new JwtUserInfo(user.getId(), user.getAccount(), user.getName(), orgId, stationId);

        Token token = this.jwtTokenServerUtils.generateUserToken(userInfo, null);
        log.info("token={}", token.getToken());
        return token;
    }

    private R<User> getUser(Tenant tenant, String account, String password) {
        User user = this.userService.getOne(Wrappers.<User>lambdaQuery()
                .eq(User::getAccount, account));
        // 密码错误
        String passwordMd5 = DigestUtils.md5Hex(password);
        if (user == null) {
//            throw new BizException(ExceptionCode.JWT_USER_INVALID.getCode(), ExceptionCode.JWT_USER_INVALID.getMsg());
            return R.fail(ExceptionCode.JWT_USER_INVALID);
        }


        if (!user.getPassword().equalsIgnoreCase(passwordMd5)) {
            this.userService.updatePasswordErrorNumById(user.getId());
            return R.fail("用户名或密码错误!");
        }

        // 密码过期
        if (user.getPasswordExpireTime() != null) {
            gt(LocalDateTime.now(), user.getPasswordExpireTime(), "用户密码已过期，请修改密码或者联系管理员重置!");
        }

        // 用户禁用
        isTrue(user.getStatus(), "用户被禁用，请联系管理员！");

        // 用户锁定
        Integer maxPasswordErrorNum = Convert.toInt(tenant.getPasswordErrorNum(), 0);
        Integer passwordErrorNum = Convert.toInt(user.getPasswordErrorNum(), 0);
        if (maxPasswordErrorNum > 0 && passwordErrorNum > maxPasswordErrorNum) {
            log.info("当前错误次数{}, 最大次数:{}", passwordErrorNum, maxPasswordErrorNum);

            LocalDateTime passwordErrorLockTime = TimeUtils.getPasswordErrorLockTime(tenant.getPasswordErrorLockTime());
            log.info("passwordErrorLockTime={}", passwordErrorLockTime);
            if (passwordErrorLockTime.isAfter(user.getPasswordErrorLastTime())) {
                return R.fail("密码连续输错次数已达到%s次,用户已被锁定~", maxPasswordErrorNum);
            }
        }

        // 错误次数清空
//        userService.update(Wraps.<User>lbU().set(User::getPasswordErrorNum, 0).eq(User::getId, user.getId()));
        this.userService.resetPassErrorNum(user.getId());
        return R.success(user);
    }

    public JwtUserInfo validateUserToken(String token) throws BizException {
        return this.jwtTokenServerUtils.getUserInfo(token);
    }

    public void invalidUserToken(String token) throws BizException {

    }

}
