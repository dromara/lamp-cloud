/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.tangyh.lamp.oauth.granter;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.tangyh.basic.base.R;
import top.tangyh.basic.exception.code.ExceptionCode;
import top.tangyh.basic.utils.DateUtils;
import top.tangyh.basic.utils.SpringUtils;
import top.tangyh.basic.utils.StrHelper;
import top.tangyh.basic.utils.ValidatorUtil;
import top.tangyh.lamp.base.service.user.BaseOrgService;
import top.tangyh.lamp.oauth.event.LoginEvent;
import top.tangyh.lamp.oauth.event.model.LoginStatusDTO;
import top.tangyh.lamp.oauth.vo.param.LoginParamVO;
import top.tangyh.lamp.oauth.vo.result.LoginResultVO;
import top.tangyh.lamp.system.entity.tenant.DefUser;
import top.tangyh.lamp.system.enumeration.system.LoginStatusEnum;

import java.time.LocalDateTime;

import static top.tangyh.lamp.oauth.granter.PasswordTokenGranter.GRANT_TYPE;

/**
 * 账号密码登录获取token
 *
 * @author Dave Syer
 * @author zuihou
 * @date 2020年03月31日10:22:55
 */
@Component(GRANT_TYPE)
@Slf4j
public class PasswordTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "PASSWORD";

    @Autowired
    protected BaseOrgService baseOrgService;

    @Override
    public R<LoginResultVO> checkParam(LoginParamVO loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        if (StrHelper.isAnyBlank(username, password)) {
            return R.fail("请输入用户名或密码");
        }
        return R.success(null);
    }

    @Override
    protected DefUser getUser(LoginParamVO loginParam) {
        String username = loginParam.getUsername();
        boolean emailLogin = ValidatorUtil.isEmail(username);
        if (emailLogin) {
            return defUserService.getUserByEmail(username);
        }
        boolean idCardLogin = ValidatorUtil.isIdCard(username);
        if (idCardLogin) {
            return defUserService.getUserByIdCard(username);
        }
        boolean mobileLogin = ValidatorUtil.isMobile(username);
        if (mobileLogin) {
            return defUserService.getUserByMobile(username);
        }
        return defUserService.getUserByUsername(username);
    }

    /**
     * 检测用户密码是否正确
     *
     * @param user       用户信息
     * @param loginParam 登录参数
     * @return 用户信息
     */
    @Override
    protected R<LoginResultVO> checkUserPassword(LoginParamVO loginParam, DefUser user) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        // 密码错误
        if (user == null) {
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(username, LoginStatusEnum.USER_ERROR, "用户不存在！")));
            return R.fail(ExceptionCode.JWT_USER_INVALID);
        }

        // 方便开发、测试、演示环境 开发者登录别人的账号，生产环境禁用。
        if (!systemProperties.getVerifyPassword()) {
            return R.success(null);
        }

        // 密码过期
        if (user.getPasswordExpireTime() != null && LocalDateTime.now().isAfter(user.getPasswordExpireTime())) {
            String msg = "用户密码已过期，请修改密码或者联系管理员重置!";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), LoginStatusEnum.USER_ERROR, msg)));
            return R.fail(msg);
        }

        // 用户锁定
        Integer passwordErrorNum = Convert.toInt(user.getPasswordErrorNum(), 0);
        Integer maxPasswordErrorNum = systemProperties.getMaxPasswordErrorNum();
        if (maxPasswordErrorNum > 0 && passwordErrorNum >= maxPasswordErrorNum) {
            log.info("[{}][{}], 输错密码次数：{}, 最大限制次数:{}", user.getNickName(), user.getId(), passwordErrorNum, maxPasswordErrorNum);

            /*
             * (最后一次输错密码的时间 + 锁定时间) 与 (当前时间) 比较
             * (最后一次输错密码的时间 + 锁定时间) > (当前时间) 表示未解锁
             * (最后一次输错密码的时间 + 锁定时间) < (当前时间) 表示自动解锁，并重置错误次数和最后一次错误时间
             */
            LocalDateTime passwordErrorLockExpireTime = DateUtils.conversionDateTime(user.getPasswordErrorLastTime(), systemProperties.getPasswordErrorLockUserTime());
            log.info("密码最后一次输错后，解锁时间: {}", passwordErrorLockExpireTime);
            // passwordErrorLockTime(锁定到期时间) > 当前时间
            if (passwordErrorLockExpireTime.isAfter(LocalDateTime.now())) {
                // 登录失败事件
                String msg = StrUtil.format("密码连续输错次数已超过最大限制：{}次,用户将被锁定至: {}", maxPasswordErrorNum, DateUtils.format(passwordErrorLockExpireTime));
                SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), LoginStatusEnum.USER_ERROR, msg)));
                return R.fail(msg);
            }
        }

        String passwordMd5 = SecureUtil.sha256(password + user.getSalt());
        if (!passwordMd5.equalsIgnoreCase(user.getPassword())) {
            String msg = StrUtil.format("用户名或密码错误{}次，连续输错{}次您将被锁定！", (user.getPasswordErrorNum() + 1), maxPasswordErrorNum);
            // 密码错误事件
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), LoginStatusEnum.PASSWORD_ERROR, msg)));
            return R.fail(msg);
        }
        return R.success(null);
    }

}
