package com.github.zuihou.authority.enumeration.auth;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 资源API分配
 * 资源中需要请求的api  并且此api会被鉴权，若不需要鉴权的api就不要加入到
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Getter
@AllArgsConstructor
@ApiModel(value = "AuthorizeType", description = "授权类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthorizeType {

    /**
     * MANUAL="手动"
     */
    MANUAL("手动"),
    /**
     * AUTO="自动"
     */
    AUTO("自动"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    public static AuthorizeType match(String val, AuthorizeType def) {
        for (AuthorizeType enm : AuthorizeType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static AuthorizeType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(AuthorizeType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @ApiModelProperty(value = "编码", allowableValues = "MANUAL,AUTO", example = "MANUAL")
    public String getCode() {
        return this.name();
    }

}
