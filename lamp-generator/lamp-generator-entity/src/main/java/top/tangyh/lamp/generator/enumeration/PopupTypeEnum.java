package top.tangyh.lamp.generator.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * <p>
 * 弹窗方式
 * </p>
 *
 * @author zuihou
 * @date 2021-11-08
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "弹窗方式")
public enum PopupTypeEnum implements BaseEnum {

    /**
     * 单表
     */
    MODAL("01", "对话框"),
    /**
     * 树结构
     */
    DRAWER("02", "抽屉"),
    JUMP("03", "跳转"),
    ;

    private String value;
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static PopupTypeEnum match(String val, PopupTypeEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static PopupTypeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(PopupTypeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码", example = "01")
    public String getCode() {
        return this.name();
    }


}
