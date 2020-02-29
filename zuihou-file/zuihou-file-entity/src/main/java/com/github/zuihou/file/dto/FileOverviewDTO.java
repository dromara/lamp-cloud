package com.github.zuihou.file.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 文件统计 概览DTO
 *
 * @author zuihou
 * @date 2019/05/08
 */
@Data
@ApiModel(value = "FileOverview", description = "云盘首页概览")
@Builder
public class FileOverviewDTO {

    @ApiModelProperty(value = "所有文件数量")
    private Integer allFileNum;

    @ApiModelProperty(value = "所有文件大小")
    private Long allFileSize;

    @ApiModelProperty(value = "文件夹数量")
    private Integer dirNum;

    @ApiModelProperty(value = "图片文件数量")
    private Integer imgNum;

    @ApiModelProperty(value = "文档文件数量")
    private Integer docNum;

    @ApiModelProperty(value = "视频文件数量")
    private Integer videoNum;

    @ApiModelProperty(value = "视音频文件数量")
    private Integer audioNum;

    @ApiModelProperty(value = "其他文件数量")
    private Integer otherNum;

    public static FileOverviewDTOBuilder myBuilder() {
        FileOverviewDTOBuilder builder = FileOverviewDTO.builder();
        return builder.allFileSize(0L).allFileNum(0).dirNum(0).imgNum(0).docNum(0).videoNum(0).
                audioNum(0).otherNum(0);
    }
}
