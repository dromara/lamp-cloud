package com.tangyh.lamp.common.enums;

import com.tangyh.basic.base.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * 日期类型
 *
 * @author zuihou
 * @date 2018/12/29
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ApiModel(value = "DateType", description = "日期类型-枚举")
public enum DateType implements BaseEnum {
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
        return Stream.of(values()).parallel().filter((item) -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static DateType get(String val) {
        return match(val, null);
    }

    public boolean eq(DateType val) {
        return val == null ? false : eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "MONTH,WEEK,DAY,NUL", example = "NUL")
    public String getCode() {
        return this.name();
    }
}
