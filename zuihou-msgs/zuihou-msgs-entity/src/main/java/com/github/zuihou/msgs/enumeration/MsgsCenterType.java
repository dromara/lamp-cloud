package com.github.zuihou.msgs.enumeration;

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
 * 消息中心表
 * </p>
 *
 * @author zuihou
 * @date 2019-12-21
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MsgsCenterType", description = "消息类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MsgsCenterType implements BaseEnum {

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


    public static MsgsCenterType match(String val, MsgsCenterType def) {
        for (MsgsCenterType enm : MsgsCenterType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static MsgsCenterType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return name().equalsIgnoreCase(val);
    }

    public boolean eq(MsgsCenterType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "WAIT,NOTIFY,PUBLICITY,WARN", example = "WAIT")
    public String getCode() {
        return name();
    }

}
