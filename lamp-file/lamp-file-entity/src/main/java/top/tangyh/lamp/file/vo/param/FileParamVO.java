package top.tangyh.lamp.file.vo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.file.enumeration.FileStorageType;
import top.tangyh.lamp.model.enumeration.base.FileType;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 增量文件上传日志
 * </p>
 *
 * @author tangyh
 * @date 2021-06-30
 * @create [2021-06-30] [tangyh] [初始创建]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description="增量文件上传日志")
public class FileParamVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务类型
     */
    @Schema(description="业务类型")
    private String bizType;
    /**
     * 文件类型
     */
    @Schema(description="文件类型")
    private FileType fileType;
    /**
     * 存储类型
     * LOCAL FAST_DFS MIN_IO ALI
     */
    @Schema(description="存储类型")
    private FileStorageType storageType;
    /**
     * 桶
     */
    @Schema(description="桶")
    private String bucket;
    /**
     * 文件相对地址
     */
    @Schema(description="文件相对地址")
    private String path;
    /**
     * 文件访问地址
     */
    @Schema(description="文件访问地址")
    private String url;
    /**
     * 唯一文件名
     */
    @Schema(description="唯一文件名")
    private String uniqueFileName;
    /**
     * 文件md5
     */
    @Schema(description="文件md5")
    private String fileMd5;
    /**
     * 原始文件名
     */
    @Schema(description="原始文件名")
    private String originalFileName;
    /**
     * 文件类型
     */
    @Schema(description="文件类型")
    private String contentType;
    /**
     * 后缀
     */
    @Schema(description="后缀")
    private String suffix;
    /**
     * 大小
     */
    @Schema(description="大小")
    private Long size;

    @Schema(description = "主键")
    private Long id;

}
