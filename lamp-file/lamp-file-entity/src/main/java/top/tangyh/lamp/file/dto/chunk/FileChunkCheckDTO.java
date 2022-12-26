package top.tangyh.lamp.file.dto.chunk;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * 分片检测参数
 *
 * @author zuihou
 * @date 2018/08/28
 */
@Data
@ToString
@Schema(description = "文件分片信息")
public class FileChunkCheckDTO {

    @Schema(description = "文件大小")
    private Long size;
    @Schema(description = "文件唯一名")
    private String name;
    @Schema(description = "分片索引")
    private Integer chunkIndex;
}
