package com.github.zuihou.authority.enumeration.defaults;

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
 * 企业
 * </p>
 *
 * @author zuihou
 * @date 2019-10-25
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "TenantStatusEnum", description = "状态-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TenantStatusEnum implements BaseEnum {

    /**
     * NORMAL="正常"
     */
    NORMAL("正常"),
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
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    public static TenantStatusEnum match(String val, TenantStatusEnum def) {
        for (TenantStatusEnum enm : TenantStatusEnum.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static TenantStatusEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(TenantStatusEnum val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "NORMAL,FORBIDDEN,WAITING,REFUSE", example = "NORMAL")
    public String getCode() {
        return this.name();
    }

}
