package com.github.zuihou.admin.entity.authority.po;

import com.github.zuihou.base.entity.CommonBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class AdminResources extends CommonBaseEntity<Long> implements Serializable {
    private Long id;

    /**
     * 开发者ID，提供给各个应用的设别码
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * 资源编码
     *
     * @mbggenerated
     */
    private String code;

    /**
     * 资源名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 资源类型为菜单时，对应菜单的组,  当系统只存在一组菜单时，该字段可以为空
     *
     * @mbggenerated
     */
    private String menuGroupCode;

    /**
     * 父菜单
     *
     * @mbggenerated
     */
    private Long parentId;

    /**
     * 资源类型 MENU:菜单 DIR:目录 
  BUTTON:按钮 URI:页面上的url  
 API:api
     *
     * @mbggenerated
     */
    private String type;

    /**
     * 菜单url
     *
     * @mbggenerated
     */
    private String href;

    /**
     * 资源请求方式 POST/GET/PUT/DELETE
     *
     * @mbggenerated
     */
    private String method;

    /**
     * 菜单打开方式 
  _self：相同框架 
  _top：整页 
   _blank：新建一个窗口
    _paren：t父窗口  
     *
     * @mbggenerated
     */
    private String targets;

    /**
     * 菜单图标
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * 排序
     *
     * @mbggenerated
     */
    private Integer orderNum;

    /**
     * 描述
     *
     * @mbggenerated
     */
    private String description;

    /**
     * 层级路径  顶级默认:,   [父path]父code, 
     *
     * @mbggenerated
     */
    private String path;

    /**
     * 是否启用
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
     * 资源编码
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * 资源编码
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 资源名称
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 资源名称
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 资源类型为菜单时，对应菜单的组,  当系统只存在一组菜单时，该字段可以为空
     *
     * @mbggenerated
     */
    public String getMenuGroupCode() {
        return menuGroupCode;
    }

    /**
     * 资源类型为菜单时，对应菜单的组,  当系统只存在一组菜单时，该字段可以为空
     *
     * @mbggenerated
     */
    public void setMenuGroupCode(String menuGroupCode) {
        this.menuGroupCode = menuGroupCode == null ? null : menuGroupCode.trim();
    }

    /**
     * 父菜单
     *
     * @mbggenerated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 父菜单
     *
     * @mbggenerated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 资源类型 MENU:菜单 DIR:目录 
  BUTTON:按钮 URI:页面上的url  
 API:api
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * 资源类型 MENU:菜单 DIR:目录 
  BUTTON:按钮 URI:页面上的url  
 API:api
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 菜单url
     *
     * @mbggenerated
     */
    public String getHref() {
        return href;
    }

    /**
     * 菜单url
     *
     * @mbggenerated
     */
    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    /**
     * 资源请求方式 POST/GET/PUT/DELETE
     *
     * @mbggenerated
     */
    public String getMethod() {
        return method;
    }

    /**
     * 资源请求方式 POST/GET/PUT/DELETE
     *
     * @mbggenerated
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * 菜单打开方式 
  _self：相同框架 
  _top：整页 
   _blank：新建一个窗口
    _paren：t父窗口  
     *
     * @mbggenerated
     */
    public String getTargets() {
        return targets;
    }

    /**
     * 菜单打开方式 
  _self：相同框架 
  _top：整页 
   _blank：新建一个窗口
    _paren：t父窗口  
     *
     * @mbggenerated
     */
    public void setTargets(String targets) {
        this.targets = targets == null ? null : targets.trim();
    }

    /**
     * 菜单图标
     *
     * @mbggenerated
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 菜单图标
     *
     * @mbggenerated
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
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
     * 描述
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 层级路径  顶级默认:,   [父path]父code, 
     *
     * @mbggenerated
     */
    public String getPath() {
        return path;
    }

    /**
     * 层级路径  顶级默认:,   [父path]父code, 
     *
     * @mbggenerated
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 是否启用
            1：启用
            0：禁用
     *
     * @mbggenerated
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 是否启用
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