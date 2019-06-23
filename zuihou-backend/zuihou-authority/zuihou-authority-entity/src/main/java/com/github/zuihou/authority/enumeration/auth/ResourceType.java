package com.github.zuihou.authority.enumeration.auth;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Getter
@AllArgsConstructor
@ApiModel(value = "ResourceType", description = "资源类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResourceType {

    /**
     * MENU="菜单"
     */
    MENU("菜单"),
    /**
     * OPT="按钮"
     */
    OPT("按钮"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    public static ResourceType match(String val, ResourceType def) {
        for (ResourceType enm : ResourceType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static ResourceType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(ResourceType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @ApiModelProperty(value = "编码", allowableValues = "MENU,OPT", example = "MENU")
    public String getCode() {
        return this.name();
    }

}
