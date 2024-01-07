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
@Schema(description = "代码模板")
public enum TemplateEnum implements BaseEnum {

    /**
     * 后端
     */
    BACKEND("01", "后端"),
    /**
     * 前端
     */
    WEB_PLUS("02", "前端"),
    ;

    private String value;
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static TemplateEnum match(String val, TemplateEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static TemplateEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(TemplateEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码")
    public String getCode() {
        return this.name();
    }


}
