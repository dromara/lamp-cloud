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
import top.tangyh.basic.database.properties.DatabaseProperties;
import top.tangyh.basic.jwt.TokenUtil;
import top.tangyh.basic.jwt.model.AuthInfo;
import top.tangyh.lamp.authority.dto.auth.LoginParamDTO;
import top.tangyh.lamp.authority.service.auth.ApplicationService;
import top.tangyh.lamp.authority.service.auth.OnlineService;
import top.tangyh.lamp.authority.service.auth.UserService;
import top.tangyh.lamp.common.properties.SystemProperties;
import top.tangyh.lamp.file.service.AppendixService;
import top.tangyh.lamp.tenant.service.TenantService;

import static top.tangyh.lamp.oauth.granter.PasswordTokenGranter.GRANT_TYPE;

/**
 * 账号密码登录获取token
 *
 * @author Dave Syer
 * @author zuihou
 * @date 2020年03月31日10:22:55
 */
@Component(GRANT_TYPE)
public class PasswordTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "password";

    public PasswordTokenGranter(TokenUtil tokenUtil, UserService userService, TenantService tenantService, AppendixService appendixService,
                                ApplicationService applicationService, DatabaseProperties databaseProperties,
                                OnlineService onlineService, SystemProperties systemProperties) {
        super(tokenUtil, userService, tenantService, applicationService, databaseProperties, onlineService, systemProperties, appendixService);
    }

    @Override
    public R<AuthInfo> grant(LoginParamDTO tokenParameter) {
        return login(tokenParameter);
    }
}
