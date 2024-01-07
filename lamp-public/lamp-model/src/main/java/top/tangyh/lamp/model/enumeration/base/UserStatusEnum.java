package top.tangyh.lamp.model.enumeration.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * 用户状态
 *
 * @author zuihou
 * @date 2018/12/29
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "用户状态-枚举")
public enum UserStatusEnum implements BaseEnum {
    /**
     * 正常
     */
    NORMAL("0", "正常"),
    /**
     * 您尚未绑定任何任何企业
     */
    UNBIND_TENANT("1", "您尚未绑定任何任何企业"),
    /**
     * 您所在的企业被禁用
     */
    TENANT_DISABLE("2", "您所在的企业被禁用"),
    /**
     * 您被该企业禁用
     */
    EMPLOYEE_DISABLE("3", "您被该企业禁用"),
    /**
     * 您的账号被禁用
     */
    USER_DISABLE("4", "您的账号被禁用"),
    ;

    @Schema(description = "状态")
    private String code;
    @Schema(description = "描述")
    private String desc;

    public static UserStatusEnum match(String val, UserStatusEnum def) {
        return Stream.of(values()).parallel().filter((item) -> item.getCode().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static UserStatusEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(UserStatusEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码")
    public String getCode() {
        return code;
    }
}
