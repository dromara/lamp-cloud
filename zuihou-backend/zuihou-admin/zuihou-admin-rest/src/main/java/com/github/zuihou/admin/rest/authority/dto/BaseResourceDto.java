package com.github.zuihou.admin.rest.authority.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Base资源数据
 *
 * @author zuihou
 * @createTime 2017-12-19 11:17
 */
@Data
public abstract class BaseResourceDto {

    /**
     * 菜单名称
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "资源名称")
    protected String name;
    /**
     * 资源类型
     * BUTTON:按钮
     * URI:页面上的url
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "资源类型", required = true, allowableValues = "BUTTON,URI")
    protected String type;
    /**
     * 菜单url
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "资源url")
    protected String href;
    /**
     * 是否启用
     * 1：启用
     * 0：禁用
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "是否启用 true:启用 false:禁用", allowableValues = "true,false")
    protected Boolean isEnable;
    /**
     * 资源请求方式 POST/GET/PUT/DELETE
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "资源请求方式", allowableValues = "POST,GET,PUT,DELETE")
    protected String method;
    /**
     * 描述
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "资源描述")
    protected String description;
}
