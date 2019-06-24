package com.github.zuihou.file.enumeration;

/**
 * This is a Description
 *
 * @author tangyh
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
    FASTDFS,
    ;

    public boolean eq(FileStorageType type) {
        for (FileStorageType t : FileStorageType.values()) {
            return t.name().equals(type);
        }
        return false;
    }
}
