package com.github.zuihou.file.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 文件删除
 *
 * @author zuihou
 * @date 2019/05/07
 */
@Data
@Builder
public class FileDeleteDO {
    /**
     * fastDFS返回的组 用于FastDFS
     */
    private String group;
    /**
     * fastdfs 的路径
     */
    private String path;
    /**
     * 唯一文件名
     */
    private String fileName;
    /**
     * 文件在服务器的绝对路径
     */
    private String relativePath;
    private Long id;
    /**
     * 是否是云盘文件删除
     */
    private Boolean file;
}
