package com.github.zuihou.admin.rest.authority.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zuihou
 * @createTime 2018-01-02 15:53
 */
@Data
public class AdminRoleSaveDto   extends BaseAdminRoleDto implements Serializable {
    /**
     * 角色编码
     *
     * @mbggenerated
     */
    private String code;
}
