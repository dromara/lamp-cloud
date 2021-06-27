package com.tangyh.lamp.file.dto;


import com.tangyh.lamp.file.enumeration.FileStorageType;
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
@ApiModel(value = "AttachmentUploadVO", description = "附件上传")
public class AttachmentUploadVO implements Serializable {
    @ApiModelProperty(value = "每个bizType只有一个文件")
    private Boolean single = false;

    @ApiModelProperty(value = "桶")
    private String group;

    @ApiModelProperty(value = "文件id")
    private Long attachmentId;

    @ApiModelProperty(value = "业务id")
    private String bizId;

    @ApiModelProperty(value = "业务类型")
    @NotBlank(message = "请填写业务类型")
    private String bizType;

    @ApiModelProperty(value = "存储类型")
    private FileStorageType storageType;
}
