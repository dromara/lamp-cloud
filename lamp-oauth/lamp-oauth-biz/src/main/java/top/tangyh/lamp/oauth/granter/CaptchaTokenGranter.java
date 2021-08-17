package top.tangyh.lamp.oauth.granter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.tangyh.basic.base.R;
import top.tangyh.basic.database.properties.DatabaseProperties;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.jwt.TokenUtil;
import top.tangyh.basic.jwt.model.AuthInfo;
import top.tangyh.basic.utils.SpringUtils;
import top.tangyh.lamp.authority.dto.auth.LoginParamDTO;
import top.tangyh.lamp.authority.service.auth.ApplicationService;
import top.tangyh.lamp.authority.service.auth.OnlineService;
import top.tangyh.lamp.authority.service.auth.UserService;
import top.tangyh.lamp.common.properties.SystemProperties;
import top.tangyh.lamp.file.service.AppendixService;
import top.tangyh.lamp.oauth.event.LoginEvent;
import top.tangyh.lamp.oauth.event.model.LoginStatusDTO;
import top.tangyh.lamp.oauth.service.ValidateCodeService;
import top.tangyh.lamp.tenant.service.TenantService;

import static top.tangyh.lamp.oauth.granter.CaptchaTokenGranter.GRANT_TYPE;

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

    public CaptchaTokenGranter(TokenUtil tokenUtil, UserService userService, AppendixService appendixService,
                               TenantService tenantService, ApplicationService applicationService,
                               DatabaseProperties databaseProperties, ValidateCodeService validateCodeService,
                               OnlineService onlineService, SystemProperties systemProperties) {
        super(tokenUtil, userService, tenantService, applicationService, databaseProperties,
                onlineService, systemProperties, appendixService);
        this.validateCodeService = validateCodeService;
    }

    @Override
    public R<AuthInfo> grant(LoginParamDTO loginParam) {
        if (systemProperties.getVerifyCaptcha()) {
            R<Boolean> check = validateCodeService.check(loginParam.getKey(), loginParam.getCode());
            if (!check.getIsSuccess()) {
                String msg = check.getMsg();
                SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(loginParam.getAccount(), msg)));
                throw BizException.validFail(check.getMsg());
            }
        }

        return login(loginParam);
    }

}
