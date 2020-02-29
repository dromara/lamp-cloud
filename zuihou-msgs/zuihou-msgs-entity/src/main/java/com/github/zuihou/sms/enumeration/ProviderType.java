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
 * 短信供应商
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProviderType", description = "供应商类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ProviderType implements BaseEnum {

    /**
     * TENCENT="0","腾讯云短信",
     */
    ALI("OK", "阿里云短信", "\\$\\{([^\\}]+)\\}"),
    /**
     * 腾讯
     */
    TENCENT("0", "腾讯云短信", "\\{([^\\}]+)\\}"),
    /**
     * 百度
     */
    BAIDU("1000", "百度云短信", "\\$\\{([^\\}]+)\\}"),
    ;

    @ApiModelProperty(value = "描述")
    private String val;

    private String desc;

    private String regex;

    public static ProviderType match(String val, ProviderType def) {
        for (ProviderType enm : ProviderType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static ProviderType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(ProviderType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "ALI,TENCENT,BAIDU", example = "ALI")
    public String getCode() {
        return this.name();
    }

    public SendStatus getTaskStatus(String code) {
        if (this.val.equalsIgnoreCase(code)) {
            return SendStatus.SUCCESS;
        } else {
            return SendStatus.FAIL;
        }
    }
}
