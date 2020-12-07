package com.tangyh.lamp.authority.enumeration.auth;

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
 * 应用
 * </p>
 *
 * @author zuihou
 * @date 2020-11-20
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ApplicationAppTypeEnum", description = "类型-枚举")
public enum ApplicationAppTypeEnum implements BaseEnum {

    /**
     * SERVER="服务应用"
     */
    SERVER("服务应用"),
    /**
     * APP="手机应用"
     */
    APP("手机应用"),
    /**
     * PC="PC网页应用"
     */
    PC("PC网页应用"),
    /**
     * WAP="手机网页应用"
     */
    WAP("手机网页应用"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static ApplicationAppTypeEnum match(String val, ApplicationAppTypeEnum def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static ApplicationAppTypeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(ApplicationAppTypeEnum val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "SERVER,APP,PC,WAP", example = "SERVER")
    public String getCode() {
        return this.name();
    }

}
