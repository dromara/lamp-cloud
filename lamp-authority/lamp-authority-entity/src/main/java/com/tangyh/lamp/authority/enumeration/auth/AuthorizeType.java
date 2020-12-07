package com.tangyh.lamp.authority.enumeration.auth;

import com.tangyh.basic.base.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @date 2020-11-20
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AuthorizeType", description = "权限类型-枚举")
public enum AuthorizeType implements BaseEnum {

    /**
     * MENU="菜单"
     */
    MENU("菜单"),
    /**
     * RESOURCE="资源"
     */
    RESOURCE("资源"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static AuthorizeType match(String val, AuthorizeType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static AuthorizeType get(String val) {
        return match(val, null);
    }

    public boolean eq(AuthorizeType val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "MENU,RESOURCE", example = "MENU")
    public String getCode() {
        return this.name();
    }

}
