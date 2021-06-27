//package com.tangyh.lamp.sms.enumeration;
//
//import com.tangyh.basic.base.BaseEnum;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.util.stream.Stream;
//
///**
// * 验证码类型
// *
// * @author zuihou
// * @date 2019/08/06
// */
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@ApiModel(value = "TemplateCodeType", description = "短信模板类型")
//public enum TemplateCodeType implements BaseEnum {
//    /**
//     * 通用短信
//     */
//    COMMON_SMS("通用短信"),
//    /**
//     * 注册短信
//     */
//    TFD_REG("注册短信"),
//    /**
//     * 闪购网
//     */
//    SGW_REG("闪购网"),
//    ;
//
//    @ApiModelProperty(value = "描述")
//    private String desc;
//
//
//    public static TemplateCodeType match(String val, TemplateCodeType def) {
//        return Stream.of(values()).parallel().filter((item) -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
//    }
//
//    public static TemplateCodeType get(String val) {
//        return match(val, null);
//    }
//
//    public boolean eq(TemplateCodeType val) {
//        return val != null && eq(val.name());
//    }
//
//    @Override
//    @ApiModelProperty(value = "编码", allowableValues = "LAMP_COMMON,TFD_REG,SGW_REG", example = "LAMP_COMMON")
//    public String getCode() {
//        return this.name();
//    }
//}
