package com.github.zuihou.file.rest.file.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FileUpdateDTO implements Serializable {
    @ApiModelProperty(value = "id", required = true)
    private Long id;
    /**
     * 文件夹id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "文件夹id")
    private Long folderId;
    /**
     * 原始文件名
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "文件原始名称")
    private String submittedFileName;

    @ApiModelProperty(value = "图标")
    private String icon;
}
