package com.tangyh.lamp.file.domain;


import lombok.Builder;
import lombok.Data;

/**
 * 文件、附件DO
 *
 * @author zuihou
 * @date 2019/05/06
 */
@Data
@Builder
public class FileDO {
    /**
     * 原始文件名
     */
    private String originalFileName;
    /**
     * 文件访问链接
     */
    private String url;
    /**
     * 文件大小
     */
    private Long size;

    private String path;
}
