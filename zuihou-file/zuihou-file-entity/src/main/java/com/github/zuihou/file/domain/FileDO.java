package com.github.zuihou.file.domain;


import com.github.zuihou.file.enumeration.DataType;
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
    private String submittedFileName;
    /**
     * 数据类型 IMAGE/VIDEO/AUDIO/DOC/OTHER/DIR
     */
    private DataType dataType;
    /**
     * 文件访问链接
     */
    private String url;
    /**
     * 文件大小
     */
    private Long size;
}
