package com.github.zuihou.authority.enumeration.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.zuihou.base.BaseEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-07-28
 */
@Getter
@AllArgsConstructor
@ApiModel(value = "LogType", description = "日志类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
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


    public static LogType match(String val, LogType def) {
        for (LogType enm : LogType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static LogType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(LogType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "OPT,EX", example = "OPT")
    public String getCode() {
        return this.name();
    }

}
