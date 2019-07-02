package com.github.zuihou.authority.enumeration.core;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 菜单
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Getter
@AllArgsConstructor
@ApiModel(value = "TargetType", description = "打开方式-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TargetType {

    /**
     * SELF="_self""相同框架"
     */
    SELF("_self", "相同框架"),
    /**
     * TOP="_top""当前页"
     */
    TOP("_top", "当前页"),
    /**
     * BLANK="_blank""新建窗口"
     */
    BLANK("_blank", "新建窗口"),
    /**
     * PAREN="_parent""父窗口"
     */
    PAREN("_parent", "父窗口"),
    ;

    @ApiModelProperty(value = "描述")
    private String val;

    private String desc;


    public static TargetType match(String val, TargetType def) {
        for (TargetType enm : TargetType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static TargetType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(TargetType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @ApiModelProperty(value = "编码", allowableValues = "SELF,TOP,BLANK,PAREN", example = "SELF")
    public String getCode() {
        return this.name();
    }

}
