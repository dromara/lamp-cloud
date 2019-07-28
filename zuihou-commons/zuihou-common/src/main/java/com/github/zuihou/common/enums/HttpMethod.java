package com.github.zuihou.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.zuihou.base.BaseEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * HTTP方法枚举
 *
 * @author zuihou
 */
@Getter
@ApiModel(value = "HttpMethod", description = "HTTP方法-枚举")
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum HttpMethod implements BaseEnum {
    /**
     * GET:GET请求
     */
    GET("GET请求"),
    /**
     * POST:POST请求
     */
    POST("POST请求"),
    /**
     * PUT:PUT请求
     */
    PUT("PUT请求"),
    /**
     * DELETE:DELETE请求
     */
    DELETE("DELETE请求"),
    /**
     * PATCH:PATCH请求
     */
    PATCH("PATCH请求"),
    /**
     * TRACE:TRACE请求
     */
    TRACE("TRACE请求"),
    /**
     * HEAD:HEAD请求
     */
    HEAD("HEAD请求"),
    /**
     * OPTIONS:OPTIONS请求
     */
    OPTIONS("OPTIONS请求"),
    ;
    @ApiModelProperty(value = "描述")
    private String desc;

    public static HttpMethod match(String val, HttpMethod def) {
        for (HttpMethod enm : HttpMethod.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static HttpMethod get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(HttpMethod val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "GET,POST,PUT,DELETE,PATCH,TRACE,HEAD,OPTIONS", example = "GET")
    public String getCode() {
        return this.name();
    }
}
