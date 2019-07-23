package com.github.zuihou.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/07/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
public class SysRole {
    private static final long serialVersionUID = 1L;
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 功能描述
     */
    private String describe;

    /**
     * 是否启用
     */
    private Boolean isEnable;

    /**
     * 是否只读角色
     */
    private Boolean isReadonly;
}
