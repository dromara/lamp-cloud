package com.github.zuihou.file.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 文件 按时间线统计数量和大小的 DTO
 *
 * @author zuihou
 * @date 2019/05/08
 */
@Data
@Builder
@ApiModel(value = "FileStatisticsAll", description = "统计所有类型文件的数量和大小")
public class FileStatisticsAllDTO {
    private List<String> dateList;
    private List<Long> sizeList;
    private List<Integer> numList;

    private List<Integer> dirNumList;

    private List<Long> imgSizeList;
    private List<Integer> imgNumList;

    private List<Long> videoSizeList;
    private List<Integer> videoNumList;

    private List<Long> audioSizeList;
    private List<Integer> audioNumList;

    private List<Long> docSizeList;
    private List<Integer> docNumList;

    private List<Long> otherSizeList;
    private List<Integer> otherNumList;
}
