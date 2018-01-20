package com.github.zuihou.admin.rest.authority.dto;

import com.github.zuihou.admin.rest.vo.TreeNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zuihou
 * @createTime 2017-12-20 11:37
 */
@Data
@ApiModel(value = "MenuTree", description = "菜单树")
public class MenuTreeDto extends TreeNode<MenuTreeDto> implements Serializable {
    /**
     * 资源类型为菜单时，对应菜单的组(base_menu_group),  当系统只存在一组菜单时，该字段可以为空
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "菜单组编码")
    private String menuGroupCode;
    @ApiModelProperty(value = "菜单编码", required = true)
    private String code;
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
