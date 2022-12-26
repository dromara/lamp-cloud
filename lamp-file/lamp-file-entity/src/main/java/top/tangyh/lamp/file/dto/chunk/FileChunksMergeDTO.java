package top.tangyh.lamp.file.dto.chunk;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * 封建分片合并DTO
 *
 * @author zuihou
 * @date 2018/08/28
 */
@Data
@ToString
@Schema(description = "文件合并实体")
public class FileChunksMergeDTO {

    @Schema(description = "文件唯一名 md5.js 生成的, 与后端生成的一致")
    private String name;
    @Schema(description = "原始文件名")
    private String submittedFileName;

    @Schema(title = "md5", description = "webuploader 自带的md5算法值， 与后端生成的不一致")
    private String md5;

    @Schema(description = "分片总数")
    private Integer chunks;
    @Schema(description = "后缀")
    private String ext;
    @Schema(description = "文件夹id")
    private Long folderId;

    @Schema(description = "大小")
    private Long size;
    @Schema(description = "类型")
    private String contextType;
}
