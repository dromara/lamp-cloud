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
 * 用户
 * </p>
 *
 * @author zuihou
 * @date 2021-04-01
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Sex", description = "性别-枚举")
public enum Sex implements BaseEnum {

    /**
     * M="男"
     */
    M("男"),
    /**
     * W="女"
     */
    W("女"),
    /**
     * N="未知"
     */
    N("未知"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static Sex match(String val, Sex def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static Sex get(String val) {
        return match(val, null);
    }

    public boolean eq(Sex val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "W,M,N", example = "W")
    public String getCode() {
        return this.name();
    }

}
