package top.tangyh.lamp.msg.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * [01-实现类 02-脚本]
 *
 * @author zuihou
 * @date 2022/7/10 0010 15:00
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "接口执行模式-枚举")
public enum InterfaceExecModeEnum implements BaseEnum {
    /**
     * 实现类
     */
    IMPL_CLASS("01", "实现类"),
    /**
     * 脚本
     */
    SCRIPT("02", "脚本"),
    ;
    private String value;
    private String desc;

    /**
     * 根据当前枚举的name匹配
     */
    public static InterfaceExecModeEnum match(String val, InterfaceExecModeEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static InterfaceExecModeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(InterfaceExecModeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "name", allowableValues = "01,02", example = "01")
    public String getCode() {
        return this.value;
    }

    @Override
    @Schema(description = "数据库中存储的值")
    public String getValue() {
        return this.value;
    }
}
