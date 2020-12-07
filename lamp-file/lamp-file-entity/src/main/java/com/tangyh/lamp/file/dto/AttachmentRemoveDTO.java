package com.tangyh.lamp.file.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 删除实体
 *
 * @param
 * @author zuihou
 * @date 2019-05-12 18:49
 * @return
 */
@Data
@ApiModel(value = "AttachmentRemove", description = "附件删除")
public class AttachmentRemoveDTO implements Serializable {

    @ApiModelProperty(value = "业务类型")
    private String bizType;

    @ApiModelProperty(value = "业务id")
    private String bizId;
}
