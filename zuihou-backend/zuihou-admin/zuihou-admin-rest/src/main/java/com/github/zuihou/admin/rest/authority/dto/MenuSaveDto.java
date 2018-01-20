package com.github.zuihou.admin.rest.authority.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单保存dto
 *
 * @author zuihou
 * @createTime 2017-12-19 11:23
 */
@Data
@ApiModel(value = "MenuSave", description = "菜单")
public class MenuSaveDto extends BaseMenuDto implements Serializable {

    @ApiModelProperty(value = "菜单编码", required = true)
    private String code;
    /**
     * 资源类型为菜单时，对应菜单的组(base_menu_group),  当系统只存在一组菜单时，该字段可以为空
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "菜单组编码")
    private String menuGroupCode;

    /**
     * 父菜单
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "父菜单id 根菜单为-1")
    private Long parentId;
}
