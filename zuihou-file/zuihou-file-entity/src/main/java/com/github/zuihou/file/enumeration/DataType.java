package com.github.zuihou.file.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.zuihou.base.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * <p>
 * 实体注释中生成的类型枚举
 * 文件回收站
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Getter
@AllArgsConstructor
@ApiModel(value = "DataType", description = "数据类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
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

    public static DataType match(String val, DataType def) {
        for (DataType enm : DataType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static DataType get(String val) {
        return match(val, null);
    }

    public boolean eq(String val) {
        return this.name().equalsIgnoreCase(val);
    }

    public boolean eq(DataType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

    @ApiModelProperty(value = "编码", allowableValues = "DIR,IMAGE,VIDEO,AUDIO,DOC,OTHER", example = "DIR")
    @Override
    public String getCode() {
        return this.name();
    }

}
