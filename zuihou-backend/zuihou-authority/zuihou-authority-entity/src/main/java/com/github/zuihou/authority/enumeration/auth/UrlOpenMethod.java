package com.github.zuihou.authority.enumeration.auth;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 实体注释中生成的类型枚举
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Getter
@AllArgsConstructor
@ApiModel(value = "UrlOpenMethod", description = "打开方式-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UrlOpenMethod {

    /**
     * SELF="相同框架"
     */
    SELF("相同框架"),
    /**
     * TOP="当前页"
     */
    TOP("当前页"),
    /**
     * BLANK="新建窗口"
     */
    BLANK("新建窗口"),
    /**
     * PAREN="父窗口"
     */
    PAREN("父窗口"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    public static UrlOpenMethod match(String val, UrlOpenMethod def) {
        for (UrlOpenMethod enm : UrlOpenMethod.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static UrlOpenMethod get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(UrlOpenMethod val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @ApiModelProperty(value = "编码", allowableValues = "SELF,TOP,BLANK,PAREN", example = "SELF")
    public String getCode() {
        return this.name();
    }

}
