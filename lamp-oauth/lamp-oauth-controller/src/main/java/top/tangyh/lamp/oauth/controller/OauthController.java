package top.tangyh.lamp.oauth.controller;

import top.tangyh.basic.annotation.base.IgnoreResponseBodyAdvice;
import top.tangyh.basic.base.R;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.jwt.TokenUtil;
import top.tangyh.basic.jwt.model.AuthInfo;
import top.tangyh.lamp.authority.dto.auth.LoginParamDTO;
import top.tangyh.lamp.authority.service.auth.OnlineService;
import top.tangyh.lamp.oauth.granter.TokenGranterBuilder;
import top.tangyh.lamp.oauth.service.ValidateCodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证Controller
 *
 * @author zuihou
 * @date 2020年03月31日10:10:36
 */
@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
@Tag(name = "登录接口")
public class OauthController {

    private final ValidateCodeService validateCodeService;
    private final TokenGranterBuilder tokenGranterBuilder;
    private final TokenUtil tokenUtil;
    private final OnlineService onlineService;

    /**
     * 租户登录 lamp-ui 系统
     */
    @Operation(summary = "登录接口", description = "登录或者清空缓存时调用")
    @PostMapping(value = "/noToken/login")
    public R<AuthInfo> login(@Validated @RequestBody LoginParamDTO login) throws BizException {
        return tokenGranterBuilder.getGranter(login.getGrantType()).grant(login);
    }

    @Operation(summary = "退出", description = "退出")
    @PostMapping(value = "/noToken/logout")
    public R<Boolean> logout(String token, Long userId, String clientId) {
        return R.success(onlineService.clear(token, userId, clientId));
    }

    /**
     * 验证验证码
     *
     * @param key  验证码唯一uuid key
     * @param code 验证码
     */
    @Operation(summary = "验证验证码", description = "验证验证码")
    @GetMapping(value = "/anno/check")
    public R<Boolean> check(@RequestParam(value = "key") String key, @RequestParam(value = "code") String code) throws BizException {
        return this.validateCodeService.check(key, code);
    }

    @Operation(summary = "验证码", description = "验证码")
    @GetMapping(value = "/anno/captcha", produces = "image/png")
    @IgnoreResponseBodyAdvice
    public void captcha(@RequestParam(value = "key") String key, HttpServletResponse response) throws IOException {
        this.validateCodeService.create(key, response);
    }

    /**
     * 验证token
     */
    @Operation(summary = "验证token", description = "验证token")
    @GetMapping(value = "/anno/verify")
    public R<AuthInfo> verify(@RequestParam(value = "token") String token) throws BizException {
        return R.success(tokenUtil.getAuthInfo(token));
    }


}
