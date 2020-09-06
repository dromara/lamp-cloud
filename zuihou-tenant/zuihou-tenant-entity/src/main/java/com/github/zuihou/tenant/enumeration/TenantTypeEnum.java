package com.github.zuihou.tenant.enumeration;

import com.github.zuihou.base.BaseEnum;
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
 * @date 2019-10-25
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "TenantTypeEnum", description = "类型-枚举")
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
        return Stream.of(values()).parallel().filter((item) -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static TenantTypeEnum get(String val) {
        return match(val, null);
    }


    public boolean eq(TenantTypeEnum val) {
        return val == null ? false : eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "CREATE,REGISTER", example = "CREATE")
    public String getCode() {
        return this.name();
    }

}
