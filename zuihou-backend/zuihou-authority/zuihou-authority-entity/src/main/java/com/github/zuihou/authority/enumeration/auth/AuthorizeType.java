package com.github.zuihou.authority.enumeration.auth;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Getter
@AllArgsConstructor
@ApiModel(value = "AuthorizeType", description = "权限类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthorizeType {

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


    public static AuthorizeType match(String val, AuthorizeType def) {
        for (AuthorizeType enm : AuthorizeType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static AuthorizeType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(AuthorizeType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @ApiModelProperty(value = "编码", allowableValues = "MENU,RESOURCE", example = "MENU")
    public String getCode() {
        return this.name();
    }

}
