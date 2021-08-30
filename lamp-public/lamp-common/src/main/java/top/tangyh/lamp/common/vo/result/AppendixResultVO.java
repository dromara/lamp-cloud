package top.tangyh.lamp.common.vo.result;

import top.tangyh.lamp.common.enumeration.FileType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ApiModel(value = "AppendixResultVO", description = "业务附件")
public class AppendixResultVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务id
     */
    @ApiModelProperty(value = "业务id")
    private Long bizId;
    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型")
    private String bizType;
    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型")
    private FileType fileType;
    /**
     * 桶
     */
    @ApiModelProperty(value = "桶")
    private String bucket;
    /**
     * 文件相对地址
     */
    @ApiModelProperty(value = "文件相对地址")
    private String path;
    /**
     * 原始文件名
     */
    @ApiModelProperty(value = "原始文件名")
    private String originalFileName;
    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型")
    private String contentType;
    /**
     * 大小
     */
    @ApiModelProperty(value = "大小")
    private Long size;

    @ApiModelProperty("主键")
    private Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
