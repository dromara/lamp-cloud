package com.github.zuihou.file.entity.file.po;

import com.github.zuihou.base.entity.CommonBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class ZhFolder extends CommonBaseEntity<Long> implements Serializable {
    private Long id;

    /**
     * 应用id
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * 名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 排序
     *
     * @mbggenerated
     */
    private Integer orderNum;

    /**
     * 父文件夹
     *
     * @mbggenerated
     */
    private Long parentId;

    private Date createTime;

    private String createUser;

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
     * 应用id
     *
     * @mbggenerated
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 应用id
     *
     * @mbggenerated
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * 名称
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 排序
     *
     * @mbggenerated
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 排序
     *
     * @mbggenerated
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 父文件夹
     *
     * @mbggenerated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 父文件夹
     *
     * @mbggenerated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
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