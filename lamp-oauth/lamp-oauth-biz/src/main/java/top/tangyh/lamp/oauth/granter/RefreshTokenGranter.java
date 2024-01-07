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
import top.tangyh.basic.jwt.model.AuthInfo;
import top.tangyh.basic.utils.StrHelper;
import top.tangyh.lamp.oauth.vo.param.LoginParamVO;
import top.tangyh.lamp.oauth.vo.result.LoginResultVO;
import top.tangyh.lamp.system.entity.tenant.DefUser;

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

    public static final String GRANT_TYPE = "REFRESH_TOKEN";

    @Override
    public R<LoginResultVO> checkParam(LoginParamVO loginParam) {
        if (StrHelper.isAnyBlank(loginParam.getRefreshToken())) {
            return R.fail("请输入刷新token");
        }

        return R.success(null);
    }

    @Override
    protected DefUser getUser(LoginParamVO loginParam) {
        String refreshTokenStr = loginParam.getRefreshToken();
        AuthInfo authInfo = tokenUtil.parseRefreshToken(refreshTokenStr);
        return defUserService.getByIdCache(authInfo.getUserId());
    }


}
