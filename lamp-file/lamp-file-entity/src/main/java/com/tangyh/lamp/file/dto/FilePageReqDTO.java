package com.tangyh.lamp.file.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件分页列表请求参数
 *
 * @author zuihou
 * @date 2019-04-29
 */
@Data
@ApiModel(value = "FilePageReq", description = "文件分页列表请求参数")
public class FilePageReqDTO implements Serializable {
    @ApiModelProperty(value = "原始文件名")
    private String originalFileName;
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startCreateTime;
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endCreateTime;
}
