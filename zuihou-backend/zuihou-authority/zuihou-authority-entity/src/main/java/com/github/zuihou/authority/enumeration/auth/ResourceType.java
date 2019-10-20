package com.github.zuihou.authority.enumeration.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.zuihou.base.BaseEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2019-10-20
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ResourceType", description = "资源类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResourceType implements BaseEnum {

    /**
     * BUTTON="按钮"
     */
    BUTTON("按钮"),
    /**
     * URI="链接"
     */
    URI("链接"),
    /**
     * COLUMN="字段"
     */
    COLUMN("字段"),
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

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "BUTTON,URI,COLUMN", example = "BUTTON")
    public String getCode() {
        return this.name();
    }

}
