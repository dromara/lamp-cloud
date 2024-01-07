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

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.tangyh.basic.base.R;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.utils.SpringUtils;
import top.tangyh.basic.utils.StrHelper;
import top.tangyh.lamp.model.enumeration.base.MsgTemplateCodeEnum;
import top.tangyh.lamp.oauth.event.LoginEvent;
import top.tangyh.lamp.oauth.event.model.LoginStatusDTO;
import top.tangyh.lamp.oauth.service.CaptchaService;
import top.tangyh.lamp.oauth.vo.param.LoginParamVO;
import top.tangyh.lamp.oauth.vo.result.LoginResultVO;
import top.tangyh.lamp.system.entity.tenant.DefUser;
import top.tangyh.lamp.system.enumeration.system.LoginStatusEnum;

import static top.tangyh.lamp.oauth.granter.MobileTokenGranter.GRANT_TYPE;


/**
 * 手机号登录获取token
 *
 * @author Dave Syer
 * @author zuihou
 * @date 2020年03月31日10:22:55
 */
@Component(GRANT_TYPE)
@RequiredArgsConstructor
public class MobileTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "MOBILE";
    private final CaptchaService captchaService;

    @Override
    public R<LoginResultVO> checkParam(LoginParamVO loginParam) {
        String mobile = loginParam.getMobile();
        String code = loginParam.getCode();
        if (StrHelper.isAnyBlank(mobile, code)) {
            return R.fail("请输入手机号或验证码");
        }

        return R.success(null);
    }

    @Override
    protected R<LoginResultVO> checkCaptcha(LoginParamVO loginParam) {
        if (systemProperties.getVerifyCaptcha()) {
            R<Boolean> check = captchaService.checkCaptcha(loginParam.getMobile(), MsgTemplateCodeEnum.MOBILE_LOGIN.getCode(), loginParam.getCode());
            if (!check.getIsSuccess()) {
                String msg = check.getMsg();
                SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.smsCodeError(loginParam.getMobile(), LoginStatusEnum.SMS_CODE_ERROR, msg)));
                throw BizException.validFail(check.getMsg());
            }
        }
        return R.success(null);
    }

    @Override
    protected DefUser getUser(LoginParamVO loginParam) {
        return defUserService.getUserByMobile(loginParam.getMobile());
    }

}
