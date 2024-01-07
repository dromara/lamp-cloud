package top.tangyh.lamp.oauth.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 角色
 * </p>
 *
 * @author zuihou
 * @date 2021-10-21
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "授权类型-枚举")
public enum GrantType implements BaseEnum {
    /**
     * 验证码登录
     */
    CAPTCHA,
    /**
     * 账号(身份证,邮箱,用户名)密码登录
     */
    PASSWORD,
    /**
     * 手机登录
     */
    MOBILE,
    /**
     * 自动刷新token
     */
    REFRESH_TOKEN,
    ;

    @Schema(description = "描述")
    private int val;

    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static GrantType match(String val, GrantType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static GrantType get(String val) {
        return match(val, null);
    }

    public boolean eq(GrantType val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码")
    public String getCode() {
        return this.name();
    }

    @Override
    public String getDesc() {
        return this.name();
    }


}
