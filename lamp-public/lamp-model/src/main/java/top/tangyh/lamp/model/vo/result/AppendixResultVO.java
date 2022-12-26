package top.tangyh.lamp.model.vo.result;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.model.enumeration.base.FileType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 业务附件
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
@Schema(description = "业务附件")
public class AppendixResultVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务id
     */
    @Schema(description = "业务id")
    private Long bizId;
    /**
     * 业务类型
     */
    @Schema(description = "业务类型")
    private String bizType;
    /**
     * 文件类型
     */
    @Schema(description = "文件类型")
    private FileType fileType;
    /**
     * 桶
     */
    @Schema(description = "桶")
    private String bucket;
    /**
     * 文件相对地址
     */
    @Schema(description = "文件相对地址")
    private String path;
    /**
     * 原始文件名
     */
    @Schema(description = "原始文件名")
    private String originalFileName;
    /**
     * 文件类型
     */
    @Schema(description = "文件类型")
    private String contentType;
    /**
     * 大小
     */
    @Schema(description = "大小")
    private Long size;

    @Schema(description = "主键")
    private Long id;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
