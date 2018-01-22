package com.github.zuihou.admin.rest.authority.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 资源返回数据
 *
 * @author zuihou
 * @createTime 2017-12-18 15:34
 */
@Data
@ApiModel(value = "Resource", description = "资源")
public class ResourceDto extends ResourceSaveReqDto implements Serializable {
    @ApiModelProperty(value = "id", required = true)
    private Long id;
}
