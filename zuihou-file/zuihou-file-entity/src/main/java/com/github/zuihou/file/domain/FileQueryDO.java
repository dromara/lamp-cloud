package com.github.zuihou.file.domain;


import com.github.zuihou.file.entity.File;
import lombok.Data;

/**
 * 文件查询 DO
 *
 * @author zuihou
 * @date 2019/05/07
 */
@Data
public class FileQueryDO extends File {
    private File parent;

}
