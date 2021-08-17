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

import org.springframework.stereotype.Component;
import top.tangyh.basic.base.R;
import top.tangyh.basic.context.ContextConstants;
import top.tangyh.basic.database.properties.DatabaseProperties;
import top.tangyh.basic.jwt.TokenUtil;
import top.tangyh.basic.jwt.model.AuthInfo;
import top.tangyh.basic.utils.SpringUtils;
import top.tangyh.basic.utils.StrHelper;
import top.tangyh.lamp.authority.dto.auth.LoginParamDTO;
import top.tangyh.lamp.authority.dto.auth.Online;
import top.tangyh.lamp.authority.entity.auth.User;
import top.tangyh.lamp.authority.service.auth.ApplicationService;
import top.tangyh.lamp.authority.service.auth.OnlineService;
import top.tangyh.lamp.authority.service.auth.UserService;
import top.tangyh.lamp.common.properties.SystemProperties;
import top.tangyh.lamp.file.service.AppendixService;
import top.tangyh.lamp.oauth.event.LoginEvent;
import top.tangyh.lamp.oauth.event.model.LoginStatusDTO;
import top.tangyh.lamp.tenant.service.TenantService;

import java.time.LocalDateTime;

import static top.tangyh.lamp.oauth.granter.RefreshTokenGranter.GRANT_TYPE;

/**
 * RefreshTokenGranter
 *
 * @author Dave Syer
 * @author zuihou
 * @date 2020年03月31日10:23:53
 */
@Component(GRANT_TYPE)
public class RefreshTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "refresh_token";

    public RefreshTokenGranter(TokenUtil tokenUtil, UserService userService, TenantService tenantService, AppendixService appendixService,
                               ApplicationService applicationService, DatabaseProperties databaseProperties,
                               OnlineService onlineService, SystemProperties systemProperties) {
        super(tokenUtil, userService, tenantService, applicationService,
                databaseProperties, onlineService, systemProperties, appendixService);
    }

    @Override
    public R<AuthInfo> grant(LoginParamDTO loginParam) {
        String grantType = loginParam.getGrantType();
        String refreshTokenStr = loginParam.getRefreshToken();
        if (StrHelper.isAnyBlank(grantType, refreshTokenStr) || !GRANT_TYPE.equals(grantType)) {
            return R.fail("加载用户信息失败");
        }
        // 2.检测client是否可用
        R<String[]> checkClient = checkClient();
        if (!checkClient.getIsSuccess()) {
            return R.fail(checkClient.getMsg());
        }

        AuthInfo authInfo = tokenUtil.parseRefreshToken(refreshTokenStr);

        if (!ContextConstants.REFRESH_TOKEN_KEY.equals(authInfo.getTokenType())) {
            return R.fail("refreshToken无效，无法加载用户信息");
        }

        User user = userService.getByIdCache(authInfo.getUserId());

        if (user == null || !user.getState()) {
            String msg = "您已被禁用！";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(authInfo.getUserId(), msg)));
            return R.fail(msg);
        }

        // 密码过期
        if (user.getPasswordExpireTime() != null && LocalDateTime.now().isAfter(user.getPasswordExpireTime())) {
            String msg = "用户密码已过期，请修改密码或者联系管理员重置!";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
            return R.fail(msg);
        }


        AuthInfo newAuth = createToken(user);
        Online online = getOnline(checkClient.getData()[0], newAuth);

        //成功登录事件
        LoginStatusDTO.success(user.getId(), online);
        onlineService.save(online);
        return R.success(newAuth);

    }
}
