package com.tangyh.lamp.file.enumeration;

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
 * 附件
 * </p>
 *
 * @author zuihou
 * @date 2020-11-20
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DataType", description = "数据类型-枚举")
public enum DataType implements BaseEnum {

    /**
     * DIR="目录"
     */
    DIR("目录"),
    /**
     * IMAGE="图片"
     */
    IMAGE("图片"),
    /**
     * VIDEO="视频"
     */
    VIDEO("视频"),
    /**
     * AUDIO="音频"
     */
    AUDIO("音频"),
    /**
     * DOC="文档"
     */
    DOC("文档"),
    /**
     * OTHER="其他"
     */
    OTHER("其他"),
    ;

    @ApiModelProperty(value = "描述")
    private String desc;


    /**
     * 根据当前枚举的name匹配
     */
    public static DataType match(String val, DataType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static DataType get(String val) {
        return match(val, null);
    }

    public boolean eq(DataType val) {
        return val != null && eq(val.name());
    }

    @Override
    @ApiModelProperty(value = "编码", allowableValues = "DIR,IMAGE,VIDEO,AUDIO,DOC,OTHER", example = "DIR")
    public String getCode() {
        return this.name();
    }

}
