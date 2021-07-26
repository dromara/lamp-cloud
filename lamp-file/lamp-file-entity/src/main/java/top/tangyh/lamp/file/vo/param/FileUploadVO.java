package top.tangyh.lamp.file.vo.param;


import top.tangyh.lamp.file.enumeration.FileStorageType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 附件上传
 *
 * @author zuihou
 * @date 2019-05-12 18:49
 */
@Data
@ApiModel(value = "FileUploadVO", description = "附件上传")
public class FileUploadVO implements Serializable {

    @ApiModelProperty(value = "业务类型")
    @NotBlank(message = "请填写业务类型")
    private String bizType;

    @ApiModelProperty(value = "桶")
    private String bucket;

    @ApiModelProperty(value = "存储类型")
    private FileStorageType storageType;
}
