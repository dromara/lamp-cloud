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
@ApiModel(value = "TenantStatusEnum", description = "状态-枚举")
public enum TenantStatusEnum implements BaseEnum {

    /**
     * NORMAL="正常"
     */
    NORMAL("正常"),
    /**
     * WAIT_INIT="待初始化"
     */
    WAIT_INIT("待初始化"),
    /**
     * FORBIDDEN="禁用"
     */
    FORBIDDEN("禁用"),
    /**
     * WAITING="待审核"
     */
    WAITING("待审核"),
    /**
     * REFUSE="拒绝"
     */
    REFUSE("拒绝"),
    /**
     * DELETE="已删除"
     */
    DELETE("已删除"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static TenantStatusEnum match(String val, TenantStatusEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static TenantStatusEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(TenantStatusEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "NORMAL,WAIT_INIT,FORBIDDEN,WAITING,REFUSE,DELETE", example = "NORMAL")
    public String getCode() {
        return this.name();
    }

}
