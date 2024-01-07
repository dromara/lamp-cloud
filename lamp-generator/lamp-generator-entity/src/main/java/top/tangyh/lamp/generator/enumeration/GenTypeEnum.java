package top.tangyh.lamp.generator.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 模板类型
 * </p>
 *
 * @author zuihou
 * @date 2021-11-08
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "生成方式")
public enum GenTypeEnum implements BaseEnum {

    /**
     * 直接生成
     */
    GEN("01", "直接生成"),
    /**
     * 打包下载
     */
    ZIP("02", "打包下载"),
    ;

    private String value;
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static GenTypeEnum match(String val, GenTypeEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static GenTypeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(GenTypeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码", allowableValues = "GEN,ZIP", example = "01")
    public String getCode() {
        return this.name();
    }


}
