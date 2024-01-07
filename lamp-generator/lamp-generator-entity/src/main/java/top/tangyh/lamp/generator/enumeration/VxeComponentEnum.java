package top.tangyh.lamp.generator.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * vxe 前端组件
 * input, textarea, select, $input, $textarea, $select, $button, $buttons, $radio, $checkbox, $switch
 *
 * @author zuihou
 * @date 2022/3/23 20:11
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "生成方式")
public enum VxeComponentEnum implements BaseEnum {
    /**
     * 输入框
     */
    INPUT("input", "原生输入框"),
    TEXTAREA("textarea", "原生文本域"),
    SELECT("select", "原生下拉框"),

    $INPUT("$input", "输入框"),
    $TEXTAREA("$textarea", "文本域"),
    $SELECT("$select", "下拉框"),
    $BUTTON("$button", "按钮"),
    $BUTTONS("$buttons", "按钮组"),
    $RADIO("$radio", "单选"),
    $CHECKBOX("$checkbox", "多选"),
    $SWITCH("$switch", "开关"),

    ;

    private String value;
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static VxeComponentEnum match(String val, VxeComponentEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static VxeComponentEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(VxeComponentEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码", example = "01")
    public String getCode() {
        return this.value;
    }


}
