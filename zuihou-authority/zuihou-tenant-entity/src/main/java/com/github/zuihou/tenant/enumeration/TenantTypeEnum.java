package com.github.zuihou.tenant.enumeration;

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
@ApiModel(value = "TenantTypeEnum", description = "类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TenantTypeEnum implements BaseEnum {

    /**
     * CREATE="创建"
     */
    CREATE("创建"),
    /**
     * REGISTER="注册"
     */
    REGISTER("注册"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    public static TenantTypeEnum match(String val, TenantTypeEnum def) {
        for (TenantTypeEnum enm : TenantTypeEnum.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static TenantTypeEnum get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(TenantTypeEnum val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "CREATE,REGISTER", example = "CREATE")
    public String getCode() {
        return this.name();
    }

}
