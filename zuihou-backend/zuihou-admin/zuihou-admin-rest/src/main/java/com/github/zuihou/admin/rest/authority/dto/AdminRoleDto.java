package com.github.zuihou.admin.rest.authority.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zuihou
 * @createTime 2018-01-02 15:51
 */
@Data
public class AdminRoleDto  extends BaseAdminRoleDto implements Serializable {
    private Long id;

    /**
     * 角色编码
     *
     * @mbggenerated
     */
    private String code;
}
