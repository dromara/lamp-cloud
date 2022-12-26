package top.tangyh.lamp.model.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 用户
 * </p>
 *
 * @author zuihou
 * @date 2021-04-01
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "性别-枚举")
public enum Sex implements BaseEnum {

    /**
     * M="男"
     */
    M("男"),
    /**
     * W="女"
     */
    W("女"),
    /**
     * N="未知"
     */
    N("未知"),
    ;

    @Schema(description = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static Sex match(String val, Sex def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static Sex get(String val) {
        return match(val, null);
    }

    public boolean eq(Sex val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码", allowableValues = "W,M,N", example = "W")
    public String getCode() {
        return this.name();
    }

}
