package com.github.zuihou.authority.enumeration.auth;

import com.github.zuihou.base.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @date 2019-10-20
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

    public boolean eq(AuthorizeType val) {
        return val == null ? false : eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "MENU,RESOURCE", example = "MENU")
    public String getCode() {
        return this.name();
    }

}
