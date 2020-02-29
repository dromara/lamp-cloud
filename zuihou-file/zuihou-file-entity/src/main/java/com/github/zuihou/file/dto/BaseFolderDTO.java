package com.github.zuihou.file.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 文件夹基础DTO
 *
 * @author zuihou
 * @date 2019-04-29
 */
@Data
public abstract class BaseFolderDTO {

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "文件名称不能为空")
    protected String submittedFileName;

    /**
     * 父文件夹
     */
    @ApiModelProperty(value = "父文件夹id")
    protected Long folderId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    protected Integer orderNum;

}
