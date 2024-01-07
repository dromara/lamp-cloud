package top.tangyh.lamp.oauth.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.ChineseGifCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.R;
import top.tangyh.basic.cache.redis2.CacheResult;
import top.tangyh.basic.cache.repository.CacheOps;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.lamp.common.cache.common.CaptchaCacheKeyBuilder;
import top.tangyh.lamp.model.enumeration.base.MsgTemplateCodeEnum;
import top.tangyh.lamp.msg.api.MsgApi;
import top.tangyh.lamp.msg.vo.update.ExtendMsgSendVO;
import top.tangyh.lamp.oauth.granter.CaptchaTokenGranter;
import top.tangyh.lamp.oauth.properties.CaptchaProperties;
import top.tangyh.lamp.oauth.service.CaptchaService;
import top.tangyh.lamp.system.service.tenant.DefUserService;

import java.io.IOException;

import static top.tangyh.basic.exception.code.ExceptionCode.CAPTCHA_ERROR;

/**
 * 验证码服务
 *
 * @author zuihou
 */
@Service
@RequiredArgsConstructor
@Slf4j
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaServiceImpl implements CaptchaService {

    private final CacheOps cacheOps;
    private final CaptchaProperties captchaProperties;
    private final MsgApi msgApi;
    private final DefUserService defUserService;

    /**
     * 注意验证码生成需要服务器支持，若您的验证码接口显示不出来，
     * 需要在 接口 所在的服务器安装字体库！ 若是docker部署，需要在docker镜像内部安装。
     * <p>
     * centos执行（其他服务器自行百度）：
     * yum install fontconfig
     * fc-cache --force
     *
     * @param key      验证码 uuid
     * @param response HttpServletResponse
     * @throws IOException
     */
    @Override
    public void createImg(String key, HttpServletResponse response) throws IOException {
        if (StrUtil.isBlank(key)) {
            throw BizException.validFail("验证码key不能为空");
        }
        Captcha captcha = createCaptcha();

        CacheKey cacheKey = CaptchaCacheKeyBuilder.build(key, CaptchaTokenGranter.GRANT_TYPE);
        cacheOps.set(cacheKey, captcha.text().toLowerCase());

        setHeader(response);
        captcha.out(response.getOutputStream());
    }


    @Override
    public R<Boolean> sendSmsCode(String mobile, String templateCode) {
        if (MsgTemplateCodeEnum.REGISTER_SMS.eq(templateCode)) {
            // 查user表判断重复
            boolean flag = defUserService.checkMobile(mobile, null);
            ArgumentAssert.isFalse(flag, "该手机号已经被注册");
        } else if (MsgTemplateCodeEnum.MOBILE_LOGIN.eq(templateCode)) {
            //查user表判断是否存在
            boolean flag = defUserService.checkMobile(mobile, null);
            ArgumentAssert.isTrue(flag, "该手机号尚未注册，请先注册后在登陆。");
        } else if (MsgTemplateCodeEnum.MOBILE_EDIT.eq(templateCode)) {
            //查user表判断是否存在
            boolean flag = defUserService.checkMobile(mobile, null);
            ArgumentAssert.isFalse(flag, "该手机号已经被他人使用");
        }

        String code = RandomUtil.randomNumbers(4);
        CacheKey cacheKey = CaptchaCacheKeyBuilder.build(mobile, templateCode);
        // cacheKey.setExpire(Duration.ofMinutes(15));  // 可以修改有效期
        cacheOps.set(cacheKey, code);

        log.info("短信验证码 cacheKey={}, code={}", cacheKey, code);

        // 在「运营平台」-「消息模板」配置一个「模板标识」为 templateCode， 且「模板内容」中需要有 code 占位符
        // 也可以考虑给模板增加一个过期时间等参数
        ExtendMsgSendVO msgSendVO = ExtendMsgSendVO.builder().templateCode(templateCode).build();
        msgSendVO.addParam("code", code);
        msgSendVO.addRecipient(mobile);
        return msgApi.sendByTemplate(msgSendVO);
    }

    @Override
    public R<Boolean> sendEmailCode(String email, String templateCode) {
        if (MsgTemplateCodeEnum.REGISTER_EMAIL.eq(templateCode)) {
            // 查user表判断重复
            boolean flag = defUserService.checkEmail(email, null);
            ArgumentAssert.isFalse(flag, "该邮箱已经被注册");
        } else if (MsgTemplateCodeEnum.EMAIL_EDIT.eq(templateCode)) {
            //查user表判断是否存在
            boolean flag = defUserService.checkEmail(email, null);
            ArgumentAssert.isFalse(flag, "该邮箱已经被他人使用");
        }

        String code = RandomUtil.randomString(6);
        CacheKey cacheKey = CaptchaCacheKeyBuilder.build(email, templateCode);
        cacheOps.set(cacheKey, code);

        log.info("邮件验证码 cacheKey={}, code={}", cacheKey, code);

        // 在「运营平台」-「消息模板」配置一个「模板标识」为 templateCode， 且「模板内容」中需要有 code 占位符
        ExtendMsgSendVO msgSendVO = ExtendMsgSendVO.builder().templateCode(templateCode).build();
        msgSendVO.addParam("code", code);
        msgSendVO.addRecipient(email);
        return msgApi.sendByTemplate(msgSendVO);
    }

    @Override
    public R<Boolean> checkCaptcha(String key, String templateCode, String value) {
        if (StrUtil.isBlank(value)) {
            return R.fail(CAPTCHA_ERROR.build("请输入验证码"));
        }
        CacheKey cacheKey = CaptchaCacheKeyBuilder.build(key, templateCode);
        CacheResult<String> code = cacheOps.get(cacheKey);
        if (StrUtil.isEmpty(code.getValue())) {
            return R.fail(CAPTCHA_ERROR.build("验证码已过期"));
        }
        if (!StrUtil.equalsIgnoreCase(value, code.getValue())) {
            return R.fail(CAPTCHA_ERROR.build("验证码不正确"));
        }
        cacheOps.del(cacheKey);
        return R.success(true);
    }


    private Captcha createCaptcha() {

        CaptchaProperties.CaptchaType type = captchaProperties.getType();
        Captcha captcha = switch (type) {
            case GIF ->
                    new GifCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), captchaProperties.getLen());
            case SPEC ->
                    new SpecCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), captchaProperties.getLen());
            case CHINESE ->
                    new ChineseCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), captchaProperties.getLen());
            case CHINESE_GIF ->
                    new ChineseGifCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), captchaProperties.getLen());
            default ->
                    new ArithmeticCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(), captchaProperties.getLen());
        };
        captcha.setCharType(captchaProperties.getCharType());

        return captcha;
    }

    private void setHeader(HttpServletResponse response) {
        response.setContentType(captchaProperties.getType().getContentType());
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}
