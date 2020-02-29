package com.github.zuihou.sms.enumeration;

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
 * 发送任务
 * 所有的短息发送调用，都视为是一次短信任务，任务表只保存数据和执行状态等信息，
 * 具体的发送状态查看发送状态（#sms_send_status）表
 * </p>
 *
 * @author zuihou
 * @date 2019-11-22
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SourceType", description = "来源类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SourceType implements BaseEnum {

    /**
     * APP="应用"
     */
    APP("应用"),
    /**
     * SERVICE="服务"
     */
    SERVICE("服务"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    public static SourceType match(String val, SourceType def) {
        for (SourceType enm : SourceType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static SourceType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(SourceType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "APP,SERVICE", example = "APP")
    public String getCode() {
        return this.name();
    }

}
