package com.github.zuihou.authority.enumeration.auth;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 账号
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Getter
@AllArgsConstructor
@ApiModel(value = "Sex", description = "性别-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Sex {

    /**
     * W="女"
     */
    W("女"),
    /**
     * M="男"
     */
    M("男"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    public static Sex match(String val, Sex def) {
        for (Sex enm : Sex.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static Sex get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(Sex val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @ApiModelProperty(value = "编码", allowableValues = "W,M", example = "W")
    public String getCode() {
        return this.name();
    }

}
