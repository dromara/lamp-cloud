package top.tangyh.lamp.oauth.granter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.tangyh.basic.base.R;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.utils.SpringUtils;
import top.tangyh.basic.utils.StrHelper;
import top.tangyh.lamp.oauth.event.LoginEvent;
import top.tangyh.lamp.oauth.event.model.LoginStatusDTO;
import top.tangyh.lamp.oauth.service.CaptchaService;
import top.tangyh.lamp.oauth.vo.param.LoginParamVO;
import top.tangyh.lamp.oauth.vo.result.LoginResultVO;
import top.tangyh.lamp.system.enumeration.system.LoginStatusEnum;

import static top.tangyh.lamp.oauth.granter.CaptchaTokenGranter.GRANT_TYPE;

/**
 * 验证码TokenGranter
 *
 * @author zuihou
 */
@Component(GRANT_TYPE)
@Slf4j
@RequiredArgsConstructor
public class CaptchaTokenGranter extends PasswordTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "CAPTCHA";
    private final CaptchaService captchaService;

    @Override
    protected R<LoginResultVO> checkCaptcha(LoginParamVO loginParam) {
        if (systemProperties.getVerifyCaptcha()) {
            R<Boolean> check = captchaService.checkCaptcha(loginParam.getKey(), GRANT_TYPE, loginParam.getCode());
            if (!check.getIsSuccess()) {
                String msg = check.getMsg();
                SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(loginParam.getUsername(), LoginStatusEnum.CAPTCHA_ERROR, msg)));
                throw BizException.validFail(check.getMsg());
            }
        }
        return R.success(null);
    }

    @Override
    public R<LoginResultVO> checkParam(LoginParamVO loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        if (StrHelper.isAnyBlank(username, password)) {
            return R.fail("请输入用户名或密码");
        }
        if (StrHelper.isAnyBlank(loginParam.getCode(), loginParam.getKey())) {
            return R.fail("请输入验证码");
        }

        return R.success(null);
    }

}
