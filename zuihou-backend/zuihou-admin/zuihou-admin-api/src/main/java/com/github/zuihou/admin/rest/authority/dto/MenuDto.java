package com.github.zuihou.admin.rest.authority.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单返回dto
 *
 * @author zuihou
 * @createTime 2017-12-18 11:58
 */
@Data
@ApiModel(value = "Menu", description = "菜单")
public class MenuDto extends MenuSaveDto implements Serializable {

    @ApiModelProperty(value = "菜单id", required = true)
    private Long id;
}
