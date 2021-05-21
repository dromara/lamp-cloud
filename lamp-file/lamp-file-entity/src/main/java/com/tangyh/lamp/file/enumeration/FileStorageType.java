package com.tangyh.lamp.file.enumeration;

/**
 * 文件 存储类型 枚举
 *
 * @author zuihou
 * @date 2019/05/06
 */
public enum FileStorageType {
    /**
     * 本地
     */
    LOCAL,
    /**
     * FastDFS
     */
    FAST_DFS,
    /**
     * minIO
     */
    MIN_IO,
    ALI,
    QINIU,
    ;

    public boolean eq(FileStorageType type) {
        return type != null && this.name().equals(type.name());
    }
}
