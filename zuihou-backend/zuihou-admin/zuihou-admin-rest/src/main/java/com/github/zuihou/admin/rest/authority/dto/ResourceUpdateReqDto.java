package com.github.zuihou.admin.rest.authority.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 资源修改数据
 *
 * @author zuihou
 * @createTime 2017-12-19 11:27
 */
@Data
@ApiModel(value = "ResourceUpdate", description = "资源")
public class ResourceUpdateReqDto extends BaseResourceDto implements Serializable {
    @ApiModelProperty(value = "id", required = true)
    private Long id;
    /**
     * 资源类型
     * BUTTON:按钮
     * URI:页面上的url
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "资源类型", allowableValues = "BUTTON,URI")
    private String type;
}
