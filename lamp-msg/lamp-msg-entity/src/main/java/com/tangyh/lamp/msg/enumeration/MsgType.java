package com.tangyh.lamp.msg.enumeration;

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
 * 消息表
 * </p>
 *
 * @author zuihou
 * @date 2020-11-21
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MsgType", description = "消息类型-枚举")
public enum MsgType implements BaseEnum {

    /**
     * WAIT="待办"
     */
    WAIT("待办"),
    /**
     * NOTIFY="通知"
     */
    NOTIFY("通知"),
    /**
     * PUBLICITY="公告"
     */
    PUBLICITY("公告"),
    /**
     * WARN="预警"
     */
    WARN("预警"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static MsgType match(String val, MsgType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static MsgType get(String val) {
        return match(val, null);
    }

    public boolean eq(MsgType val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "WAIT,NOTIFY,PUBLICITY,WARN", example = "WAIT")
    public String getCode() {
        return this.name();
    }

}
