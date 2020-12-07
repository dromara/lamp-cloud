package com.tangyh.lamp.demo.enumeration;

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
 * 商品
 * </p>
 *
 * @author zuihou
 * @date 2020-12-01
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProductType", description = "商品类型-枚举")
public enum ProductType implements BaseEnum {

    /**
     * ORDINARY="普通"
     */
    ORDINARY("普通"),
    /**
     * GIFT="赠品"
     */
    GIFT("赠品"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static ProductType match(String val, ProductType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static ProductType get(String val) {
        return match(val, null);
    }

    public boolean eq(ProductType val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "ORDINARY,GIFT", example = "ORDINARY")
    public String getCode() {
        return this.name();
    }

}
