package com.tangyh.lamp.sms.enumeration;

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
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static ProviderType get(String val) {
        return match(val, null);
    }

    public boolean eq(ProviderType val) {
        return val != null && eq(val.name());
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
