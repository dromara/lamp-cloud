package top.tangyh.lamp.model.enumeration.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * 激活状态
 *
 * @author zuihou
 * @date 2018/12/29
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "激活状态-枚举")
public enum ActiveStatusEnum implements BaseEnum {
    /**
     * 未激活
     */
    NOT_ACTIVE("10", "未激活"),
    /**
     * 已激活
     */
    ACTIVATED("20", "已激活"),
    ;

    @Schema(description = "状态")
    private String code;
    @Schema(description = "描述")
    private String desc;

    public static ActiveStatusEnum match(String val, ActiveStatusEnum def) {
        return Stream.of(values()).parallel().filter((item) -> item.getCode().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static ActiveStatusEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(ActiveStatusEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码", allowableValues = "MONTH,WEEK,DAY,NUL", example = "NUL")
    public String getCode() {
        return code;
    }
}
