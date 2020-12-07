package com.tangyh.lamp.authority.enumeration.common;

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
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2020-11-20
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "LogType", description = "日志类型-枚举")
public enum LogType implements BaseEnum {

    /**
     * OPT="操作类型"
     */
    OPT("操作类型"),
    /**
     * EX="异常类型"
     */
    EX("异常类型"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static LogType match(String val, LogType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static LogType get(String val) {
        return match(val, null);
    }

    public boolean eq(LogType val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "OPT,EX", example = "OPT")
    public String getCode() {
        return this.name();
    }

}
