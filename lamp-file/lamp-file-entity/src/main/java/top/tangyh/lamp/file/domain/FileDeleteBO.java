package top.tangyh.lamp.file.domain;

import top.tangyh.lamp.file.enumeration.FileStorageType;
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
public class FileDeleteBO {
    /**
     * 桶
     */
    private String bucket;
    /**
     * 相对路径
     */
    private String path;
    /**
     * 存储类型
     */
    private FileStorageType storageType;
}
