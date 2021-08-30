/*
 * Copyright 2006-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package top.tangyh.lamp.oauth.granter;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import top.tangyh.basic.base.R;
import top.tangyh.basic.boot.utils.WebUtils;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.database.properties.DatabaseProperties;
import top.tangyh.basic.database.properties.MultiTenantType;
import top.tangyh.basic.exception.code.ExceptionCode;
import top.tangyh.basic.jwt.TokenUtil;
import top.tangyh.basic.jwt.model.AuthInfo;
import top.tangyh.basic.jwt.model.JwtUserInfo;
import top.tangyh.basic.jwt.utils.JwtUtil;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.basic.utils.DateUtils;
import top.tangyh.basic.utils.SpringUtils;
import top.tangyh.basic.utils.StrHelper;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.authority.dto.auth.LoginParamDTO;
import top.tangyh.lamp.authority.dto.auth.Online;
import top.tangyh.lamp.authority.entity.auth.Application;
import top.tangyh.lamp.authority.entity.auth.User;
import top.tangyh.lamp.authority.service.auth.ApplicationService;
import top.tangyh.lamp.authority.service.auth.OnlineService;
import top.tangyh.lamp.authority.service.auth.UserService;
import top.tangyh.lamp.common.constant.AppendixType;
import top.tangyh.lamp.common.properties.SystemProperties;
import top.tangyh.lamp.common.vo.result.AppendixResultVO;
import top.tangyh.lamp.file.service.AppendixService;
import top.tangyh.lamp.oauth.event.LoginEvent;
import top.tangyh.lamp.oauth.event.model.LoginStatusDTO;
import top.tangyh.lamp.tenant.entity.Tenant;
import top.tangyh.lamp.tenant.enumeration.TenantStatusEnum;
import top.tangyh.lamp.tenant.service.TenantService;

import java.time.LocalDateTime;

import static top.tangyh.basic.context.ContextConstants.BASIC_HEADER_KEY;
import static top.tangyh.basic.utils.ArgumentAssert.notNull;

/**
 * 验证码TokenGranter
 *
 * @author zuihou
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractTokenGranter implements TokenGranter {
    protected final TokenUtil tokenUtil;
    protected final UserService userService;
    protected final TenantService tenantService;
    protected final ApplicationService applicationService;
    protected final DatabaseProperties databaseProperties;
    protected final OnlineService onlineService;
    protected final SystemProperties systemProperties;
    protected final AppendixService appendixService;


    /**
     * 处理登录逻辑
     *
     * @param loginParam 登录参数
     * @return 认证信息
     */
    protected R<AuthInfo> login(LoginParamDTO loginParam) {
        if (StrHelper.isAnyBlank(loginParam.getAccount(), loginParam.getPassword())) {
            return R.fail("请输入用户名或密码");
        }
        // 1，检测租户是否可用
        if (!MultiTenantType.NONE.eq(databaseProperties.getMultiTenantType())) {
            Tenant tenant = this.tenantService.getByCode(ContextUtil.getTenant());
            notNull(tenant, "企业不存在");
            ArgumentAssert.equals(TenantStatusEnum.NORMAL, tenant.getStatus(), "企业不可用~");
            if (tenant.getExpirationTime() != null) {
                ArgumentAssert.isFalse(LocalDateTime.now().isAfter(tenant.getExpirationTime()), "企业服务已到期!");
            }
        }

        // 2.检测client是否可用
        R<String[]> checkClient = checkClient();
        if (!checkClient.getIsSuccess()) {
            return R.fail(checkClient.getMsg());
        }

        // 3. 验证登录
        R<User> result = this.getUser(loginParam.getAccount(), loginParam.getPassword());
        if (!result.getIsSuccess()) {
            return R.fail(result.getCode(), result.getMsg());
        }

        // 4.生成 token
        User user = result.getData();
        AuthInfo authInfo = this.createToken(user);

        Online online = getOnline(checkClient.getData()[0], authInfo);

        //成功登录事件
        LoginStatusDTO loginStatus = LoginStatusDTO.success(user.getId(), online);
        SpringUtils.publishEvent(new LoginEvent(loginStatus));

        onlineService.save(online);
        return R.success(authInfo);
    }

    protected Online getOnline(String clientId, AuthInfo authInfo) {
        Online online = new Online();
        BeanPlusUtil.copyProperties(authInfo, online);
        online.setClientId(clientId);
        online.setExpireTime(authInfo.getExpiration());
        online.setLoginTime(LocalDateTime.now());
        return online;
    }


    /**
     * 检测 client
     */
    protected R<String[]> checkClient() {
        String basicHeader = ServletUtil.getHeader(WebUtils.request(), BASIC_HEADER_KEY, StrPool.UTF_8);
        String[] client = JwtUtil.getClient(basicHeader);
        Application application = applicationService.getByClient(client[0], client[1]);

        if (application == null) {
            return R.fail("请填写正确的客户端ID或者客户端秘钥");
        }
        if (!application.getState()) {
            return R.fail("客户端[%s]已被禁用", application.getClientId());
        }
        return R.success(client);
    }


    /**
     * 检测用户密码是否正确
     *
     * @param account  账号
     * @param password 密码
     * @return 用户信息
     */
    protected R<User> getUser(String account, String password) {
        User user = this.userService.getByAccount(account);
        // 密码错误
        if (user == null) {
            return R.fail(ExceptionCode.JWT_USER_INVALID);
        }

        // 方便开发、测试、演示环境 开发者登录别人的账号，生产环境禁用。
        if (!systemProperties.getVerifyPassword()) {
            return R.success(user);
        }

        String passwordMd5 = SecureUtil.sha256(password + user.getSalt());
        if (!passwordMd5.equalsIgnoreCase(user.getPassword())) {
            String msg = "用户名或密码错误!";
            // 密码错误事件
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.pwdError(user.getId(), msg)));
            return R.fail(msg);
        }

        // 密码过期
        if (user.getPasswordExpireTime() != null && LocalDateTime.now().isAfter(user.getPasswordExpireTime())) {
            String msg = "用户密码已过期，请修改密码或者联系管理员重置!";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
            return R.fail(msg);
        }

        if (!user.getState()) {
            String msg = "用户被禁用，请联系管理员！";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
            return R.fail(msg);
        }

        // 用户锁定
        Integer maxPasswordErrorNum = systemProperties.getMaxPasswordErrorNum();
        Integer passwordErrorNum = Convert.toInt(user.getPasswordErrorNum(), 0);
        if (maxPasswordErrorNum > 0 && passwordErrorNum >= maxPasswordErrorNum) {
            log.info("[{}][{}], 输错密码次数：{}, 最大限制次数:{}", user.getName(), user.getId(), passwordErrorNum, maxPasswordErrorNum);

            LocalDateTime passwordErrorLockTime = DateUtils.conversionDateTime(systemProperties.getPasswordErrorLockUserTime());
            log.info("passwordErrorLockTime={}", passwordErrorLockTime);
            if (passwordErrorLockTime.isAfter(user.getPasswordErrorLastTime())) {
                // 登录失败事件
                String msg = StrUtil.format("密码连续输错次数已达到{}次,用户已被锁定~", maxPasswordErrorNum);
                SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
                return R.fail(msg);
            }
        }
        return R.success(user);
    }

    /**
     * 创建用户TOKEN
     *
     * @param user 用户
     * @return token
     */
    protected AuthInfo createToken(User user) {
        JwtUserInfo userInfo = new JwtUserInfo(user.getId(), user.getAccount(), user.getName());
        AuthInfo authInfo = tokenUtil.createAuthInfo(userInfo, null);
        AppendixResultVO appendixResultVO = appendixService.getByBiz(user.getId(), AppendixType.Authority.BASE_USER_AVATAR);
        authInfo.setAvatarId(appendixResultVO != null ? appendixResultVO.getId() : null);
        authInfo.setWorkDescribe(user.getWorkDescribe());
        return authInfo;
    }


}
