//package com.github.zuihou.authority.enumeration.auth;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.github.zuihou.base.BaseEnum;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//
///**
// * <p>
// * 实体注释中生成的类型枚举
// * 菜单
// * </p>
// *
// * @author zuihou
// * @date 2019-07-22
// */
//@Getter
//@AllArgsConstructor
//@ApiModel(value = "MenuType", description = "菜单类型-枚举")
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
//public enum MenuType implements BaseEnum {
//
//    /**
//     * MENU="菜单"
//     */
//    MENU("菜单"),
//    /**
//     * DIR="目录"
//     */
//    DIR("目录"),
//    ;
//
//    @ApiModelProperty(value = "描述")
//    private String desc;
//
//
//    public static MenuType match(String val, MenuType def) {
//        for (MenuType enm : MenuType.values()) {
//            if (enm.name().equalsIgnoreCase(val)) {
//                return enm;
//            }
//        }
//        return def;
//    }
//
//    public static MenuType get(String val) {
//        return match(val, null);
//    }
//
//    public boolean eq(String val) {
//        return this.name().equalsIgnoreCase(val);
//    }
//
//    public boolean eq(MenuType val) {
//        if (val == null) {
//            return false;
//        }
//        return eq(val.name());
//    }
//
//    @Override
//    @ApiModelProperty(value = "编码", allowableValues = "MENU,DIR", example = "MENU")
//    public String getCode() {
//        return this.name();
//    }
//
//}
