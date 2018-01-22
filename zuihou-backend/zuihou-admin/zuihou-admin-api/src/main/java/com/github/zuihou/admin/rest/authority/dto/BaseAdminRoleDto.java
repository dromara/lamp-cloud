package com.github.zuihou.admin.rest.authority.dto;

import lombok.Data;

/**
 * @author zuihou
 * @createTime 2018-01-02 15:51
 */
@Data
public abstract class BaseAdminRoleDto {

    /**
     * 角色名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 是否启用
     1：启用
     0：禁用
     *
     * @mbggenerated
     */
    private Boolean isEnable;

    private String description;
}
