package com.tangyh.lamp.tenant.enumeration;

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
 * 企业
 * </p>
 *
 * @author zuihou
 * @date 2020-11-19
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "TenantConnectTypeEnum", description = "连接类型-枚举")
public enum TenantConnectTypeEnum implements BaseEnum {

    /**
     * LOCAL="本地"
     */
    LOCAL("本地"),
    /**
     * REMOTE="远程"
     */
    REMOTE("远程"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static TenantConnectTypeEnum match(String val, TenantConnectTypeEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static TenantConnectTypeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(TenantConnectTypeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "LOCAL,REMOTE", example = "LOCAL")
    public String getCode() {
        return this.name();
    }

}
