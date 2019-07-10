package com.github.zuihou.common.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zuihou
 * 用户实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true)
@Builder
public class SysUser {
    private static final long serialVersionUID = -5886012896705137070L;

    private Long id;
    /**
     * 账号
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
     * 组织ID
     * #c_core_org
     */
    private Long orgId;

    /**
     * 岗位ID
     * #c_core_station
     */
    private Long stationId;


    /**
     * 手机
     * 启用条件： LoginUser.isFull = true || LoginUser.isUser = true
     */
    private String mobile;

    /**
     * 照片
     * 启用条件： LoginUser.isFull = true || LoginUser.isUser = true
     */
    private String photo;

    /**
     * 工作描述
     * 比如：  市长、管理员、局长等等   用于登陆展示
     * 启用条件： LoginUser.isFull = true || LoginUser.isUser = true
     */
    private String workDescribe;

    /**
     * 登录次数
     * 一直累计，记录了此账号总共登录次数
     * 启用条件： LoginUser.isFull = true || LoginUser.isUser = true
     */
    private Integer loginCount;

    /**
     * 当前登录用户的角色编码
     * 启用条件： LoginUser.isFull = true || LoginUser.isRole = true
     */
    private List<SysRole> roles;
    /**
     * 当前登录用户的组织架构
     * 启用条件： LoginUser.isFull = true || LoginUser.isOrg = true
     */
    private SysOrg org;
    /**
     * 当前登录用户的 岗位
     * 启用条件： LoginUser.isFull = true || LoginUser.isStation = true
     */
    private SysStation station;

}
