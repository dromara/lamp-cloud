package com.github.zuihou.admin.entity.account.po;

import com.github.zuihou.base.entity.CommonBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Applications extends CommonBaseEntity<Long> implements Serializable {
    private Long id;

    /**
     * 应用名称
     *
     * @mbggenerated
     */
    private String appName;

    /**
     * 应用类型  system:系统管理 app:应用
     *
     * @mbggenerated
     */
    private String appType;

    /**
     * 启用状态 
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

    /**
     * 开发者ID，提供给各个应用的设别码
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * 开发者密码是校验开发者身份的密码，具有极高的安全性
     *
     * @mbggenerated
     */
    private String appSecret;

    /**
     * 应用url
     *
     * @mbggenerated
     */
    private String url;

    /**
     * 应用logo
     *
     * @mbggenerated
     */
    private String logoUrl;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String comment;

    /**
     * 排序字段
     *
     * @mbggenerated
     */
    private Integer orderNum;

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
     * 应用名称
     *
     * @mbggenerated
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 应用名称
     *
     * @mbggenerated
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * 应用类型  system:系统管理 app:应用
     *
     * @mbggenerated
     */
    public String getAppType() {
        return appType;
    }

    /**
     * 应用类型  system:系统管理 app:应用
     *
     * @mbggenerated
     */
    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    /**
     * 启用状态 
            1：启用
            0：禁用
     *
     * @mbggenerated
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 启用状态 
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
     * 开发者密码是校验开发者身份的密码，具有极高的安全性
     *
     * @mbggenerated
     */
    public String getAppSecret() {
        return appSecret;
    }

    /**
     * 开发者密码是校验开发者身份的密码，具有极高的安全性
     *
     * @mbggenerated
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    /**
     * 应用url
     *
     * @mbggenerated
     */
    public String getUrl() {
        return url;
    }

    /**
     * 应用url
     *
     * @mbggenerated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 应用logo
     *
     * @mbggenerated
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * 应用logo
     *
     * @mbggenerated
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    /**
     * 备注
     *
     * @mbggenerated
     */
    public String getComment() {
        return comment;
    }

    /**
     * 备注
     *
     * @mbggenerated
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * 排序字段
     *
     * @mbggenerated
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 排序字段
     *
     * @mbggenerated
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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