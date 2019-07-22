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
 * @date 2019-07-22
 */
@Getter
@AllArgsConstructor
@ApiModel(value = "ResourceType", description = "资源类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResourceType {

    /**
     * BUTTON="按钮"
     */
    BUTTON("按钮"),
    /**
     * URI="链接"
     */
    URI("链接"),
    /**
     * COLUMN="数据列"
     */
    COLUMN("数据列"),
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

    @ApiModelProperty(value = "编码", allowableValues = "BUTTON,URI,COLUMN", example = "BUTTON")
    public String getCode() {
        return this.name();
    }

}
