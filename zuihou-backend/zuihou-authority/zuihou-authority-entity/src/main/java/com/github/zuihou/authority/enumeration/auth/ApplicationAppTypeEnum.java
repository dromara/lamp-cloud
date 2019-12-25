package com.github.zuihou.authority.enumeration.auth;

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
 * 应用
 * </p>
 *
 * @author zuihou
 * @date 2019-12-25
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ApplicationAppTypeEnum", description = "类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
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


    public static ApplicationAppTypeEnum match(String val, ApplicationAppTypeEnum def) {
        for (ApplicationAppTypeEnum enm : ApplicationAppTypeEnum.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static ApplicationAppTypeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return name().equalsIgnoreCase(val);
    }

    public boolean eq(ApplicationAppTypeEnum val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "SERVER,APP,PC,WAP", example = "SERVER")
    public String getCode() {
        return name();
    }

}
