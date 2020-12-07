package com.tangyh.lamp.file.dto.chunk;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 封建分片合并DTO
 *
 * @author zuihou
 * @date 2018/08/28
 */
@Data
@ToString
@ApiModel(value = "FileChunksMerge", description = "文件合并实体")
public class FileChunksMergeDTO {

    @ApiModelProperty(value = "文件唯一名 md5.js 生成的, 与后端生成的一致")
    private String name;
    @ApiModelProperty(value = "原始文件名")
    private String submittedFileName;

    @ApiModelProperty(value = "md5", notes = "webuploader 自带的md5算法值， 与后端生成的不一致")
    private String md5;

    @ApiModelProperty(value = "分片总数")
    private Integer chunks;
    @ApiModelProperty(value = "后缀")
    private String ext;
    @ApiModelProperty(value = "文件夹id")
    private Long folderId;

    @ApiModelProperty(value = "大小")
    private Long size;
    @ApiModelProperty(value = "类型")
    private String contextType;
}
