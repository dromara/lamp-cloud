package top.tangyh.lamp.generator.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * web pro 前端组件
 *
 * @author zuihou
 * @date 2022/3/23 20:11
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "生成方式")
public enum ComponentEnum implements BaseEnum {
    /**
     * 输入框
     */
    PLUS_INPUT("Input", "输入框"),
    /**
     * 输入框组
     */
    PLUS_INPUT_GROUP("InputGroup", "输入框组"),
    /**
     * InputPassword
     */
    PLUS_INPUT_PASSWORD("InputPassword", "密码框"),
    /**
     * InputSearch
     */
    PLUS_INPUT_SEARCH("InputSearch", "搜索框"),
    /**
     * InputTextArea
     */
    PLUS_INPUT_TEXT_AREA("InputTextArea", "文本域"),
    /**
     * InputNumber
     */
    PLUS_INPUT_NUMBER("InputNumber", "数字框"),
    /**
     * AutoComplete
     */
    PLUS_AUTO_COMPLETE("AutoComplete", "自动完成"),
    /**
     * Select
     */
    PLUS_SELECT("Select", "下拉框"),
    /**
     * ApiSelect
     */
    PLUS_API_SELECT("ApiSelect", "Api下拉框"),
    /**
     * ApiTree
     */
    PLUS_API_TREE("ApiTree", "Api树"),
    /**
     * TreeSelect
     */
    PLUS_TREE_SELECT("TreeSelect", "树选择"),
    /**
     * ApiTreeSelect
     */
    PLUS_API_TREE_SELECT("ApiTreeSelect", "Api树选择"),
    /**
     * RadioButtonGroup
     */
    PLUS_RADIO_BUTTON_GROUP("RadioButtonGroup", "单选按钮"),
    /**
     * ApiRadioGroup
     */
    PLUS_API_RADIO_GROUP("ApiRadioGroup", "Api单选框"),
    /**
     * RadioGroup
     */
    PLUS_RADIO_GROUP("RadioGroup", "单选框"),
    /**
     * Switch
     */
    PLUS_SWITCH("Switch", "开关"),
    /**
     * Checkbox
     */
    PLUS_CHECKBOX("Checkbox", "复选框"),
    /**
     * CheckboxGroup
     */
    PLUS_CHECKBOX_GROUP("CheckboxGroup", "复选组"),
    /**
     * Cascader
     */
    PLUS_CASCADER("Cascader", "级联选择"),
    /**
     * Slider
     */
    PLUS_SLIDER("Slider", "滑动输入条"),
    /**
     * Rate
     */
    PLUS_RATE("Rate", "评分"),
    /**
     * Transfer
     */
    PLUS_TRANSFER("Transfer", "穿梭框"),
    /**
     * DatePicker
     */

    PLUS_DATE_PICKER("DatePicker", "日期选择"),
    /**
     * MonthPicker
     */
    PLUS_MONTH_PICKER("MonthPicker", "月份选择"),
    /**
     * RangePicker
     */
    PLUS_RANGE_PICKER("RangePicker", "日期范围选择"),
    /**
     * WeekPicker
     */
    PLUS_WEEK_PICKER("WeekPicker", "周选择"),
    /**
     * TimePicker
     */
    PLUS_TIME_PICKER("TimePicker", "时间选择"),
    /**
     * StrengthMeter
     */
    PLUS_STRENGTH_METER("StrengthMeter", "密码强度"),
    /**
     * IconPicker
     */
    PLUS_ICON_PICKER("IconPicker", "图标选择"),
    /**
     * InputCountDown
     */
    PLUS_INPUT_COUNT_DOWN("InputCountDown", "倒计时"),
    /**
     * Upload
     */
    PLUS_UPLOAD("Upload", "文件上传"),
    /**
     * CropperAvatar
     */
    PLUS_CROPPER_AVATAR("CropperAvatar", "头像上传"),
    /**
     * Divider
     */
    PLUS_DIVIDER("Divider", "分隔线"),
    ;

    private String value;
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static ComponentEnum match(String val, ComponentEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static ComponentEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(ComponentEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "编码", example = "01")
    public String getCode() {
        return this.value;
    }


}
