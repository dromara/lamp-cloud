package top.tangyh.lamp.model.enumeration.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/7/28 8:09 AM
 * @create [2022/7/28 8:09 AM ] [tangyh] [初始创建]
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "消息模板类型-枚举")
public enum MsgTemplateCodeEnum implements BaseEnum {
    // 您的验证码为：${code}，请勿将验证码泄露给他人
    REGISTER_SMS("REGISTER_SMS", "注册短信"),
    // 您的验证码为：${code}，请勿将验证码泄露给他人
    REGISTER_EMAIL("REGISTER_EMAIL", "注册邮件验证码"),
    // 您的验证码为：${code}，请勿将验证码泄露给他人
    MOBILE_LOGIN("MOBILE_LOGIN", "手机登录短信"),
    MOBILE_EDIT("MOBILE_EDIT", "修改手机号"),
    EMAIL_EDIT("EMAIL_EDIT", "修改邮箱"),
    ;
    String value;
    String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static MsgTemplateCodeEnum match(String val, MsgTemplateCodeEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static MsgTemplateCodeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(MsgTemplateCodeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码", allowableValues = "IMAGE,VIDEO,AUDIO,DOC,OTHER", example = "IMAGE")
    public String getCode() {
        return this.value;
    }
}
