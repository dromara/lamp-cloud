package com.tangyh.lamp.file.dto.chunk;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 分片检测参数
 *
 * @author zuihou
 * @date 2018/08/28
 */
@Data
@ToString
@ApiModel(value = "FileChunkCheck", description = "文件分片信息")
public class FileChunkCheckDTO {

    @ApiModelProperty(value = "文件大小")
    private Long size;
    @ApiModelProperty(value = "文件唯一名")
    private String name;
    @ApiModelProperty(value = "分片索引")
    private Integer chunkIndex;
}
