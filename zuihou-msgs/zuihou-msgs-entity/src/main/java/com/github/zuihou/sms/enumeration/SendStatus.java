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
 * 短信发送状态
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "SendStatus", description = "发送状态-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SendStatus implements BaseEnum {

    /**
     * WAITING="等待发送"
     */
    WAITING("等待发送"),
    /**
     * SUCCESS="发送成功"
     */
    SUCCESS("发送成功"),
    /**
     * FAIL="发送失败"
     */
    FAIL("发送失败"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    public static SendStatus match(String val, SendStatus def) {
        for (SendStatus enm : SendStatus.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static SendStatus get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(SendStatus val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "WAITING,SUCCESS,FAIL", example = "WAITING")
    public String getCode() {
        return this.name();
    }

}
