package com.github.zuihou.admin.entity.account.po;

import com.github.zuihou.base.entity.CommonBaseEntity;

import java.io.Serializable;
import java.util.Date;

public class Admin extends CommonBaseEntity<Long> implements Serializable {
    private Long id;

    /**
     * 开发者ID，提供给各个应用的设别码
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * 帐号类型： 0:超级管理 1:普通管理
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * 帐号
     *
     * @mbggenerated
     */
    private String username;

    /**
     * 登录密码
     *
     * @mbggenerated
     */
    private String password;

    /**
     * 昵称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 邮箱
     *
     * @mbggenerated
     */
    private String email;

    /**
     * 手机
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * 头像
     *
     * @mbggenerated
     */
    private String logoUrl;

    /**
     * 是否禁用 
            1：启用
            0：禁用
     *
     * @mbggenerated
     */
    private Boolean isEnable;

    /**
     * 是否删除 
            1：已删除
            0：未删除
     *
     * @mbggenerated
     */
    private Boolean isDelete;

    private String description;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

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
     * 帐号类型： 0:超级管理 1:普通管理
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * 帐号类型： 0:超级管理 1:普通管理
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 帐号
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * 帐号
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 登录密码
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * 登录密码
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 昵称
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 昵称
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 邮箱
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 手机
     *
     * @mbggenerated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机
     *
     * @mbggenerated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 头像
     *
     * @mbggenerated
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * 头像
     *
     * @mbggenerated
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    /**
     * 是否禁用 
            1：启用
            0：禁用
     *
     * @mbggenerated
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 是否禁用 
            1：启用
            0：禁用
     *
     * @mbggenerated
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 是否删除 
            1：已删除
            0：未删除
     *
     * @mbggenerated
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除 
            1：已删除
            0：未删除
     *
     * @mbggenerated
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}