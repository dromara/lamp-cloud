package top.tangyh.lamp.file.dto.chunk;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * 文件分片上传实体
 *
 * @author zuihou
 * @date 2018/08/29
 */
@Data
@Schema(description = "文件分片上传实体")
@ToString
public class FileUploadDTO {
    @Schema(title = "md5", description = "webuploader 自带的md5算法值， 与后端生成的不一致")
    private String md5;
    @Schema(description = "大小")
    private Long size;
    @Schema(description = "文件唯一名 md5.js生成的, 与后端生成的一致")
    private String name;
    @Schema(description = "分片总数")
    private Integer chunks;
    @Schema(description = "当前分片")
    private Integer chunk;
    @Schema(description = "最后更新时间")
    private String lastModifiedDate;
    @Schema(description = "类型")
    private String type;
    @Schema(description = "后缀")
    private String ext;
    @Schema(description = "文件夹id")
    private Long folderId;
}
