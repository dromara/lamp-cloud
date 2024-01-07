package top.tangyh.lamp.oauth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.response.IgnoreResponseBodyAdvice;
import top.tangyh.basic.base.R;
import top.tangyh.basic.exception.BizException;
import top.tangyh.lamp.oauth.granter.CaptchaTokenGranter;
import top.tangyh.lamp.oauth.service.CaptchaService;

import java.io.IOException;

import static top.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_STRING;

/**
 * 验证码服务
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/9/29 5:37 PM
 * @create [2022/9/29 5:37 PM ] [tangyh] [初始创建]
 */
@Slf4j
@RestController
@RequestMapping("/anyTenant")
@AllArgsConstructor
@Tag(name = "验证码")
public class CaptchaController {

    private final CaptchaService captchaService;

    /**
     * 验证验证码
     *
     * @param key  验证码唯一uuid key
     * @param code 验证码
     */
    @Operation(summary = "验证验证码是否正确", description = "验证验证码")
    @GetMapping(value = "/checkCaptcha")
    public R<Boolean> checkCaptcha(@RequestParam(value = "key") String key, @RequestParam(value = "code") String code,
                                   @RequestParam(value = "templateCode", required = false, defaultValue = CaptchaTokenGranter.GRANT_TYPE)
                                   String templateCode) throws BizException {
        return this.captchaService.checkCaptcha(key, templateCode, code);
    }

    @Operation(summary = "获取图片验证码", description = "获取图片验证码")


    @Parameters({
            @Parameter(name = "key", description = "唯一字符串: 前端随机生成一个唯一字符串用于生成验证码，并将key传给后台用于验证",
                    schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
    })
    @GetMapping(value = "/captcha", produces = "image/png")
    @IgnoreResponseBodyAdvice
    public void captcha(@RequestParam(value = "key") String key, HttpServletResponse response) throws IOException {
        this.captchaService.createImg(key, response);
    }

    @Operation(summary = "发送短信验证码", description = "发送短信验证码")
    @Parameters({
            @Parameter(name = "mobile", description = "手机号", schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
            @Parameter(name = "templateCode", description = "模板编码: 在「运营平台」-「消息模板」-「模板标识」配置一个短信模板", schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
    })
    @GetMapping(value = "/sendSmsCode")
    public R<Boolean> sendSmsCode(@RequestParam(value = "mobile") String mobile,
                                  @RequestParam(value = "templateCode") String templateCode) {
        return captchaService.sendSmsCode(mobile, templateCode);
    }
    @Parameters({
            @Parameter(name = "email", description = "邮箱", schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
            @Parameter(name = "templateCode", description = "模板编码: 在「运营平台」-「消息模板」-「模板标识」配置一个邮件模板", schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
    })
    @Operation(summary = "发送邮箱验证码", description = "发送邮箱验证码")
    @GetMapping(value = "/sendEmailCode")
    public R<Boolean> sendEmailCode(@RequestParam(value = "email") String email,
                                    @RequestParam(value = "templateCode") String templateCode) {
        return captchaService.sendEmailCode(email, templateCode);
    }

}
