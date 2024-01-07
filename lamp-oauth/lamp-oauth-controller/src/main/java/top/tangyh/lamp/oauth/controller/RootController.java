package top.tangyh.lamp.oauth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.base.R;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.jwt.TokenHelper;
import top.tangyh.basic.jwt.model.Token;
import top.tangyh.lamp.oauth.granter.TokenGranterBuilder;
import top.tangyh.lamp.oauth.service.UserInfoService;
import top.tangyh.lamp.oauth.vo.param.LoginParamVO;
import top.tangyh.lamp.oauth.vo.param.RegisterByEmailVO;
import top.tangyh.lamp.oauth.vo.param.RegisterByMobileVO;
import top.tangyh.lamp.oauth.vo.result.LoginResultVO;
import top.tangyh.lamp.system.service.tenant.DefUserService;

/**
 * 登录页 Controller
 *
 * @author zuihou
 * @date 2020年03月31日10:10:36
 */
@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
@Tag(name = "登录-退出-注册")
public class RootController {

    private final TokenGranterBuilder tokenGranterBuilder;
    private final DefUserService defUserService;
    private final UserInfoService userInfoService;
    private final TokenHelper tokenUtil;

    /**
     * 登录接口
     * grantType 表示登录类型 可选值为：CAPTCHA,REFRESH_TOKEN,PASSWORD,MOBILE
     * <p>
     * CAPTCHA： 用户名 + 密码 + 图片验证码登录
     * key：验证码唯一字符窜
     * code：图片验证码
     * username：用户名
     * password：密码
     * <p>
     * PASSWORD：用户名 + 密码 登录
     * username：用户名
     * password：密码
     * <p>
     * MOBILE：手机号 + 短信验证码登录
     * username：手机号
     * code：短信验证码
     * <p>
     * REFRESH_TOKEN：刷新token （未实现）
     *
     * @param login login
     * @return top.tangyh.basic.base.R<top.tangyh.lamp.oauth.vo.result.LoginResultVO>
     * @author tangyh
     * @date 2022/9/29 9:33 PM
     * @create [2022/9/29 9:33 PM ] [tangyh] [初始创建]
     */
    @Operation(summary = "登录接口", description = "登录或者清空缓存时调用")
    @PostMapping(value = "/anyTenant/login")
    public R<LoginResultVO> login(@Validated @RequestBody LoginParamVO login) throws BizException {
        return tokenGranterBuilder.getGranter(login.getGrantType()).login(login);
    }

    @Operation(summary = "退出", description = "退出")
    @PostMapping(value = "/anyUser/logout")
    public R<Boolean> logout(String token) {
        return tokenGranterBuilder.getGranter().logout(token);
    }


    /**
     * 验证token
     */
    @Operation(summary = "验证token是否正确", description = "验证token")
    @GetMapping(value = "/anyTenant/verify")
    public R<Token> verify(@RequestParam(value = "token") String token) throws BizException {
        return R.success(tokenUtil.parseToken(token));
    }

    /**
     * 注册
     */
    @Operation(summary = "根据手机号注册", description = "根据手机号注册")
    @PostMapping(value = "/anyTenant/registerByMobile")
    public R<String> register(@Validated @RequestBody RegisterByMobileVO register) throws BizException {
        return R.success(userInfoService.registerByMobile(register));
    }

    @Operation(summary = "根据邮箱注册", description = "根据邮箱注册")
    @PostMapping(value = "/anyTenant/registerByEmail")
    public R<String> register(@Validated @RequestBody RegisterByEmailVO register) throws BizException {
        return R.success(userInfoService.registerByEmail(register));
    }

    @Operation(summary = "检测手机号是否存在")
    @GetMapping("/anyTenant/checkMobile")
    public R<Boolean> checkMobile(@RequestParam String mobile) {
        return R.success(defUserService.checkMobile(mobile, null));
    }


}
