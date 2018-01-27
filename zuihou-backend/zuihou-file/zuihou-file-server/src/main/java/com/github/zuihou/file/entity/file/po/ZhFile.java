package com.github.zuihou.file.entity.file.po;

import com.github.zuihou.base.entity.CommonBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class ZhFile extends CommonBaseEntity<Long> implements Serializable {
    private Long id;

    /**
     * 应用id
     *
     * @mbggenerated
     */
    private String appId;

    /**
     * 文件夹id
     *
     * @mbggenerated
     */
    private Long folderId;

    /**
     * 文件类型 pan:云盘  接口:api
     *
     * @mbggenerated
     */
    private String type;

    /**
     * 对象id
     *
     * @mbggenerated
     */
    private String objectId;

    /**
     * 命名空间
     *
     * @mbggenerated
     */
    private String namespace;

    /**
     * 文件的唯一key
     *
     * @mbggenerated
     */
    private String uKey;

    /**
     * 链接
     *
     * @mbggenerated
     */
    private String url;

    /**
     * 文件在服务器的绝对路径
文件的完整路径=absolutePath + filename
     *
     * @mbggenerated
     */
    private String absolutePath;

    /**
     * 文件在服务器的相对路径 \r\n 
文件的完整路径=absolutePath + filename 
 \r\n linux eg: 2015/01/01 \r\n
 win eg: 2015\01\01
     *
     * @mbggenerated
     */
    private String relativePath;

    /**
     * 文件名
     *
     * @mbggenerated
     */
    private String filename;

    /**
     * 类型
     *
     * @mbggenerated
     */
    private String mime;

    /**
     * 原始文件名
     *
     * @mbggenerated
     */
    private String submittedFileName;

    /**
     * 后缀 (没有.)
     *
     * @mbggenerated
     */
    private String ext;

    /**
     * 大小
     *
     * @mbggenerated
     */
    private String size;

    /**
     * 图标
     *
     * @mbggenerated
     */
    private String icon;

    private Date createTime;

    private String createName;

    private Date updateTime;

    private String updateName;

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
     * 文件夹id
     *
     * @mbggenerated
     */
    public Long getFolderId() {
        return folderId;
    }

    /**
     * 文件夹id
     *
     * @mbggenerated
     */
    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    /**
     * 文件类型 pan:云盘  接口:api
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * 文件类型 pan:云盘  接口:api
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 对象id
     *
     * @mbggenerated
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 对象id
     *
     * @mbggenerated
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 命名空间
     *
     * @mbggenerated
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * 命名空间
     *
     * @mbggenerated
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace == null ? null : namespace.trim();
    }

    /**
     * 文件的唯一key
     *
     * @mbggenerated
     */
    public String getuKey() {
        return uKey;
    }

    /**
     * 文件的唯一key
     *
     * @mbggenerated
     */
    public void setuKey(String uKey) {
        this.uKey = uKey == null ? null : uKey.trim();
    }

    /**
     * 链接
     *
     * @mbggenerated
     */
    public String getUrl() {
        return url;
    }

    /**
     * 链接
     *
     * @mbggenerated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 文件在服务器的绝对路径
文件的完整路径=absolutePath + filename
     *
     * @mbggenerated
     */
    public String getAbsolutePath() {
        return absolutePath;
    }

    /**
     * 文件在服务器的绝对路径
文件的完整路径=absolutePath + filename
     *
     * @mbggenerated
     */
    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath == null ? null : absolutePath.trim();
    }

    /**
     * 文件在服务器的相对路径 \r\n 
文件的完整路径=absolutePath + filename 
 \r\n linux eg: 2015/01/01 \r\n
 win eg: 2015\01\01
     *
     * @mbggenerated
     */
    public String getRelativePath() {
        return relativePath;
    }

    /**
     * 文件在服务器的相对路径 \r\n 
文件的完整路径=absolutePath + filename 
 \r\n linux eg: 2015/01/01 \r\n
 win eg: 2015\01\01
     *
     * @mbggenerated
     */
    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath == null ? null : relativePath.trim();
    }

    /**
     * 文件名
     *
     * @mbggenerated
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 文件名
     *
     * @mbggenerated
     */
    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    /**
     * 类型
     *
     * @mbggenerated
     */
    public String getMime() {
        return mime;
    }

    /**
     * 类型
     *
     * @mbggenerated
     */
    public void setMime(String mime) {
        this.mime = mime == null ? null : mime.trim();
    }

    /**
     * 原始文件名
     *
     * @mbggenerated
     */
    public String getSubmittedFileName() {
        return submittedFileName;
    }

    /**
     * 原始文件名
     *
     * @mbggenerated
     */
    public void setSubmittedFileName(String submittedFileName) {
        this.submittedFileName = submittedFileName == null ? null : submittedFileName.trim();
    }

    /**
     * 后缀 (没有.)
     *
     * @mbggenerated
     */
    public String getExt() {
        return ext;
    }

    /**
     * 后缀 (没有.)
     *
     * @mbggenerated
     */
    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    /**
     * 大小
     *
     * @mbggenerated
     */
    public String getSize() {
        return size;
    }

    /**
     * 大小
     *
     * @mbggenerated
     */
    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    /**
     * 图标
     *
     * @mbggenerated
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 图标
     *
     * @mbggenerated
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }
}