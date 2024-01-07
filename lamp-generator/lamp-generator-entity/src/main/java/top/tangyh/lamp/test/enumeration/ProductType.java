package top.tangyh.lamp.test.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 测试树结构
 * </p>
 *
 * @author zuihou
 * @date 2022-04-20 00:28:30
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "商品类型-枚举")
public enum ProductType implements BaseEnum {

    /**
     * ORDINARY
     */
    ORDINARY("普通"),
    /**
     * GIFT
     */
    GIFT("赠品"),
    ;

    @Schema(description = "描述")
    private String desc;

    /**
     * 根据当前枚举的name匹配
     */
    public static ProductType match(String val, ProductType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static ProductType get(String val) {
        return match(val, null);
    }

    public boolean eq(ProductType val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "name", allowableValues = "ORDINARY,GIFT", example = "ORDINARY")
    public String getCode() {
        return this.name();
    }

    @Override
    @Schema(description = "数据库中的值")
    public String getValue() {
        return this.name();
    }

}
