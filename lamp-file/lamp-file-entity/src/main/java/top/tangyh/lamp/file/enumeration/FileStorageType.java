package top.tangyh.lamp.file.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.interfaces.BaseEnum;

import java.util.stream.Stream;

/**
 * 文件 存储类型 枚举
 *
 * @author zuihou
 * @date 2019/05/06
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "文件存储类型")
public enum FileStorageType implements BaseEnum {
    /**
     * 本地
     */
    LOCAL("本地"),
    /**
     * FastDFS
     */
    FAST_DFS("FastDFS"),
    /**
     * minIO
     */
    MIN_IO("MinIO"),
    ALI_OSS("阿里云OSS"),
    QINIU_OSS("七牛云OSS"),
    HUAWEI_OSS("华为云OSS"),
    ;

    @Schema(description = "描述")
    private String desc;

    /**
     * 根据当前枚举的name匹配
     */
    public static FileStorageType match(String val, FileStorageType def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static FileStorageType get(String val) {
        return match(val, null);
    }

    @Override
    @Schema(description = "编码", allowableValues = "LOCAL,FAST_DFS,MIN_IO,ALI,QINIU", example = "LOCAL")
    public String getCode() {
        return this.name();
    }


    public boolean eq(FileStorageType type) {
        return type != null && this.name().equals(type.name());
    }
}
