package com.tangyh.lamp.file.domain;

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
//    /**
//     * 唯一文件名
//     */
//    private String uniqueFileName;

//    private Long id;
}
