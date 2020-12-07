package com.tangyh.lamp.oauth.granter;

import com.tangyh.basic.base.R;
import com.tangyh.basic.database.properties.DatabaseProperties;
import com.tangyh.basic.exception.BizException;
import com.tangyh.basic.jwt.TokenUtil;
import com.tangyh.basic.jwt.model.AuthInfo;
import com.tangyh.basic.utils.SpringUtils;
import com.tangyh.lamp.authority.dto.auth.LoginParamDTO;
import com.tangyh.lamp.authority.service.auth.ApplicationService;
import com.tangyh.lamp.authority.service.auth.OnlineService;
import com.tangyh.lamp.authority.service.auth.UserService;
import com.tangyh.lamp.oauth.event.LoginEvent;
import com.tangyh.lamp.oauth.event.model.LoginStatusDTO;
import com.tangyh.lamp.oauth.service.ValidateCodeService;
import com.tangyh.lamp.tenant.service.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.tangyh.lamp.oauth.granter.CaptchaTokenGranter.GRANT_TYPE;

/**
 * 验证码TokenGranter
 *
 * @author zuihou
 */
@Component(GRANT_TYPE)
@Slf4j
public class CaptchaTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "captcha";
    private final ValidateCodeService validateCodeService;

    public CaptchaTokenGranter(TokenUtil tokenUtil, UserService userService,
                               TenantService tenantService, ApplicationService applicationService,
                               DatabaseProperties databaseProperties, ValidateCodeService validateCodeService,
                               OnlineService onlineService) {
        super(tokenUtil, userService, tenantService, applicationService, databaseProperties, onlineService);
        this.validateCodeService = validateCodeService;
    }

    @Override
    public R<AuthInfo> grant(LoginParamDTO loginParam) {
        R<Boolean> check = validateCodeService.check(loginParam.getKey(), loginParam.getCode());
        if (!check.getIsSuccess()) {
            String msg = check.getMsg();
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(loginParam.getAccount(), msg)));
            throw BizException.validFail(check.getMsg());
        }

        return login(loginParam);
    }

}
