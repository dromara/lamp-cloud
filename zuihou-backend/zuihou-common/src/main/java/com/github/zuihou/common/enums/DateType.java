package com.github.zuihou.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 日期类型
 *
 * @author zuihou
 * @date 2018/12/29
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "DateType", description = "日期类型-枚举")
public enum DateType {
    /**
     * 一个月
     */
    MONTH(30, "一月"),
    /**
     * 一周
     */
    WEEK(7, "一周"),
    /**
     * 一天
     */
    DAY(1, "一天"),
    /**
     * 无限
     */
    NUL(0, "不限");

    @ApiModelProperty(value = "天")
    private int day;
    @ApiModelProperty(value = "描述")
    private String desc;

    public static DateType match(String val, DateType def) {
        for (DateType enm : DateType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static DateType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(DateType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @ApiModelProperty(value = "编码", allowableValues = "MONTH,WEEK,DAY,NUL", example = "NUL")
    public String getCode() {
        return this.name();
    }
}
