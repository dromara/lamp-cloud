package com.github.zuihou.admin.rest.authority.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Base菜单dto
 *
 * @author zuihou
 * @createTime 2017-12-19 11:22
 */
@Data
public abstract class BaseMenuDto {
    /**
     * 菜单名称
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "菜单名称")
    protected String name;
    /**
     * 菜单url
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "菜单url")
    protected String href;

    /**
     * 菜单打开方式
     * _self：相同框架
     * _top：整页
     * _blank：新建一个窗口
     * _parent：父窗口
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "菜单打开方式", allowableValues = "_self,_top,_blank,_parent")
    protected String targets;
    @ApiModelProperty(value = "菜单类型", required = true, allowableValues = "DIR,MENU")
    protected String type;
    /**
     * 是否启用
     * 1：启用
     * 0：禁用
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "是否启用", allowableValues = "true,false")
    protected Boolean isEnable;
    /**
     * 菜单图标
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "菜单图标")
    protected String icon;

    /**
     * 排序
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "菜单排序")
    protected Integer orderNum;

    /**
     * 描述
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "菜单描述")
    protected String description;
}
