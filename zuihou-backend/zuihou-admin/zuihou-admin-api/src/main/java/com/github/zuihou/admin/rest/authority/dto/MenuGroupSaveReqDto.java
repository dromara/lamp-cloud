package com.github.zuihou.admin.rest.authority.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zuihou
 * @createTime 2017-12-19 16:30
 */
@Data
@ApiModel(value = "MenuGroupSave", description = "菜单组")
public class MenuGroupSaveReqDto extends BaseMenuGroupDto {
    /**
     * 菜单类型编码
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "菜单组编码", required = true)
    private String code;
}
