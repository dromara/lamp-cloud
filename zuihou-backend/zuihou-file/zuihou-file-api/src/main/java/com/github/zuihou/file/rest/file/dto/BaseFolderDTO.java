package com.github.zuihou.file.rest.file.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public abstract class BaseFolderDTO {

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    protected String submittedFileName;

    /**
     * 父文件夹
     */
    @ApiModelProperty(value = "父文件夹id")
    protected Long parentId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    protected Integer orderNum;

}
