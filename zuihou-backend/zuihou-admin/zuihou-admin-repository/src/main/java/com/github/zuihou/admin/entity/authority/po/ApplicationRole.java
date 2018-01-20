package com.github.zuihou.admin.entity.authority.po;

import com.github.zuihou.base.entity.CommonBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class ApplicationRole extends CommonBaseEntity<Long> implements Serializable {
    private Long id;

    /**
     * 开发者ID，提供给各个应用的设别码
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * 权限Id 角色id(admin_role) 
     *
     * @mbggenerated
     */
    private Long roleId;

    /**
     * app 主键 id admin_applications表
     *
     * @mbggenerated
     */
    private Long applicationsId;

    private String createUser;

    private Date createTime;

    private Date updateTime;

    private String updateUser;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 开发者ID，提供给各个应用的设别码
     *
     * @mbggenerated
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 开发者ID，提供给各个应用的设别码
     *
     * @mbggenerated
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * 权限Id 角色id(admin_role) 
     *
     * @mbggenerated
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 权限Id 角色id(admin_role) 
     *
     * @mbggenerated
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * app 主键 id admin_applications表
     *
     * @mbggenerated
     */
    public Long getApplicationsId() {
        return applicationsId;
    }

    /**
     * app 主键 id admin_applications表
     *
     * @mbggenerated
     */
    public void setApplicationsId(Long applicationsId) {
        this.applicationsId = applicationsId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}