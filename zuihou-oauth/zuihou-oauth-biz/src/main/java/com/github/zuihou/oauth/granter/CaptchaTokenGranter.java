package com.github.zuihou.oauth.granter;

import com.github.zuihou.authority.dto.auth.LoginParamDTO;
import com.github.zuihou.authority.event.LoginEvent;
import com.github.zuihou.authority.event.model.LoginStatusDTO;
import com.github.zuihou.base.R;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.jwt.model.AuthInfo;
import com.github.zuihou.oauth.service.ValidateCodeService;
import com.github.zuihou.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.github.zuihou.oauth.granter.CaptchaTokenGranter.GRANT_TYPE;

/**
 * 验证码TokenGranter
 *
 * @author Chill
 */
@Component(GRANT_TYPE)
@Slf4j
public class CaptchaTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "captcha";
    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    public R<AuthInfo> grant(LoginParamDTO loginParam) {
        R<Boolean> check = validateCodeService.check(loginParam.getKey(), loginParam.getCode());
        if (check.getIsError()) {
            String msg = check.getMsg();
            BaseContextHandler.setTenant(loginParam.getTenant());
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(loginParam.getAccount(), msg)));
            throw BizException.validFail(check.getMsg());
        }

        return login(loginParam);
    }

}
