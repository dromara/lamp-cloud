package top.tangyh.lamp.common.vo.save;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.common.enumeration.FileType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

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
@ApiModel(value = "AppendixSaveVO", description = "业务附件")
public class AppendixSaveVO implements Serializable {

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
    @Size(max = 255, message = "业务类型长度不能超过255")
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
    @Size(max = 255, message = "桶长度不能超过255")
    private String bucket;
    /**
     * 文件相对地址
     */
    @ApiModelProperty(value = "文件相对地址")
    @Size(max = 255, message = "文件相对地址长度不能超过255")
    @NotBlank(message = "请先上传文件")
    private String path;
    /**
     * 原始文件名
     */
    @ApiModelProperty(value = "原始文件名")
    @Size(max = 255, message = "原始文件名长度不能超过255")
    @NotBlank(message = "请先上传文件")
    private String originalFileName;

    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型")
    @Size(max = 255, message = "文件类型长度不能超过255")
    private String contentType;
    /**
     * 大小
     */
    @ApiModelProperty(value = "大小")
    private Long size;

    @ApiModelProperty(value = "附件id c_file表的id")
    private Long id;

}
