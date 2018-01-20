package com.github.zuihou.admin.rest.authority.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zuihou
 * @createTime 2018-01-02 15:54
 */
@Data
public class AdminRoleUpdateDto   extends BaseAdminRoleDto implements Serializable {
    private Long id;

}
