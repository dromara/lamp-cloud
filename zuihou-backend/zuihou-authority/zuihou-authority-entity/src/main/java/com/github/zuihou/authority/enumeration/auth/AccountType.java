package com.github.zuihou.authority.enumeration.auth;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 账号
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Getter
@AllArgsConstructor
@ApiModel(value = "AccountType", description = "账号类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AccountType {

    /**
     * CUSTOMER="客户"
     */
    CUSTOMER("客户"),
    /**
     * BUILT_IN="内置"
     */
    BUILT_IN("内置"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    public static AccountType match(String val, AccountType def) {
        for (AccountType enm : AccountType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static AccountType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(AccountType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @ApiModelProperty(value = "编码", allowableValues = "CUSTOMER,BUILT_IN", example = "CUSTOMER")
    public String getCode() {
        return this.name();
    }

}
