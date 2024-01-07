package top.tangyh.lamp.base.enumeration.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 操作日志
 * </p>
 *
 * @author zuihou
 * @date 2021-11-08
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "日志类型-枚举")
public enum LogType implements BaseEnum {

    /**
     * OPT="操作类型"
     */
    OPT("正常"),
    /**
     * EX="异常类型"
     */
    EX("异常"),
    ;

    @Schema(description = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static LogType match(String val, LogType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static LogType get(String val) {
        return match(val, null);
    }

    public boolean eq(LogType val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码", allowableValues = "OPT,EX", example = "OPT")
    public String getCode() {
        return this.name();
    }

}
