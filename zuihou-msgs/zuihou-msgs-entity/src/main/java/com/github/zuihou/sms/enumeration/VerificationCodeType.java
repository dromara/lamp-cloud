package com.github.zuihou.sms.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.zuihou.base.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 验证码类型
 *
 * @author zuihou
 * @date 2019/08/06
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "VerificationCodeType", description = "验证码类型")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum VerificationCodeType implements BaseEnum {
    /**
     * 用户注册
     */
    REGISTER_USER("用户注册"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    public static VerificationCodeType match(String val, VerificationCodeType def) {
        for (VerificationCodeType enm : VerificationCodeType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static VerificationCodeType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(VerificationCodeType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "REGISTER_USER", example = "REGISTER_USER")
    public String getCode() {
        return this.name();
    }
}
