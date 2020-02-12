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
 * 用户
 * </p>
 *
 * @author zuihou
 * @date 2020-02-12
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Sex", description = "性别-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Sex implements BaseEnum {

    /**
     * W="女"
     */
    W("女"),
    /**
     * M="男"
     */
    M("男"),
    /**
     * N="未知"
     */
    N("未知"),
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

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "W,M,N", example = "W")
    public String getCode() {
        return this.name();
    }

}
