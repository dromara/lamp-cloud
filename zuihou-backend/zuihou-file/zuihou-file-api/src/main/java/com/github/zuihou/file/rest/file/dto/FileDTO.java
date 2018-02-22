package com.github.zuihou.file.rest.file.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FileDTO implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 文件夹id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "文件夹id")
    private Long folderId;
    @ApiModelProperty(value = "文件夹名称")
    private Long folderName;

    /**
     * 链接
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "文件url")
    private String url;

    /**
     * 类型
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "文件mime类型")
    private String mime;

    /**
     * 原始文件名
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "原始文件名")
    private String submittedFileName;

    /**
     * 文件名
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "唯一文件名")
    private String filename;

    /**
     * 后缀 (没有.)
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "后缀")
    private String ext;

    /**
     * 大小
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "大小")
    private String size;

    /**
     * 图标
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "文件类型 IMAGE/VIDEO/AUDIO/DOC/OTHER/DIR", example = "IMAGE/VIDEO/AUDIO/DOC/OTHER/DIR")
    private String dataType;
}
