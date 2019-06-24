package com.github.zuihou.file.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;
import com.github.zuihou.file.enumeration.DataType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 实体类
 * 文件回收站
 * </p>
 *
 * @author zuihou
 * @since 2019-06-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("f_recycle")
@ApiModel(value = "Recycle", description = "文件回收站")
public class Recycle extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 数据类型
     * #DataType{DIR:目录;IMAGE:图片;VIDEO:视频;AUDIO:音频;DOC:文档;OTHER:其他}
     */
    @ApiModelProperty(value = "数据类型")
    @TableField("data_type")
    private DataType dataType;

    /**
     * 原始文件名
     * 很长的换行的注释
     * 很长的换行的注释
     */
    @ApiModelProperty(value = "原始文件名")
    @Length(max = 255, message = "原始文件名长度不能超过255")
    @TableField("submitted_file_name")
    private String submittedFileName;

    /**
     * 树形结构
     * 用,拼接所有父类的id
     */
    @ApiModelProperty(value = "树形结构")
    @Length(max = 255, message = "树形结构长度不能超过255")
    @TableField("tree_path")
    private String treePath;

    /**
     * 层级等级
     * 从1开始计算
     */
    @ApiModelProperty(value = "层级等级")
    @TableField("grade")
    private Integer grade;

    /**
     * 文件夹id
     */
    @ApiModelProperty(value = "文件夹id")
    @TableField("folder_id")
    private Long folderId;

    /**
     * 链接
     */
    @ApiModelProperty(value = "链接")
    @Length(max = 255, message = "链接长度不能超过255")
    @TableField("url")
    private String url;

    /**
     * 文件大小
     * 单位字节
     */
    @ApiModelProperty(value = "文件大小")
    @TableField("size")
    private Long size;

    /**
     * 父文件夹名称
     */
    @ApiModelProperty(value = "父文件夹名称")
    @Length(max = 255, message = "父文件夹名称长度不能超过255")
    @TableField("folder_name")
    private String folderName;

    /**
     * FastDFS组
     */
    @ApiModelProperty(value = "FastDFS组")
    @Length(max = 255, message = "FastDFS组长度不能超过255")
    @TableField("group_")
    private String group;

    /**
     * FastDFS远程文件名
     */
    @ApiModelProperty(value = "FastDFS远程文件名")
    @Length(max = 255, message = "FastDFS远程文件名长度不能超过255")
    @TableField("path")
    private String path;

    /**
     * 文件的相对路径
     */
    @ApiModelProperty(value = "文件的相对路径 ")
    @Length(max = 255, message = "文件的相对路径 长度不能超过255")
    @TableField("relative_path")
    private String relativePath;

    /**
     * md5值
     */
    @ApiModelProperty(value = "md5值")
    @Length(max = 255, message = "md5值长度不能超过255")
    @TableField("file_md5")
    private String fileMd5;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @Length(max = 255, message = "类型长度不能超过255")
    @TableField("mime")
    private String mime;

    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型")
    @Length(max = 255, message = "文件类型长度不能超过255")
    @TableField("context_type")
    private String contextType;

    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名")
    @Length(max = 255, message = "文件名长度不能超过255")
    @TableField("filename")
    private String filename;

    /**
     * 后缀
     * (没有.)
     */
    @ApiModelProperty(value = "后缀")
    @Length(max = 64, message = "后缀长度不能超过64")
    @TableField("ext")
    private String ext;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    @Length(max = 64, message = "图标长度不能超过64")
    @TableField("icon")
    private String icon;


    @Builder
    public Recycle(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                   DataType dataType, String submittedFileName, String treePath, Integer grade, Long folderId,
                   String url, Long size, String folderName, String group, String path, String relativePath,
                   String fileMd5, String mime, String contextType, String filename, String ext, String icon) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.dataType = dataType;
        this.submittedFileName = submittedFileName;
        this.treePath = treePath;
        this.grade = grade;
        this.folderId = folderId;
        this.url = url;
        this.size = size;
        this.folderName = folderName;
        this.group = group;
        this.path = path;
        this.relativePath = relativePath;
        this.fileMd5 = fileMd5;
        this.mime = mime;
        this.contextType = contextType;
        this.filename = filename;
        this.ext = ext;
        this.icon = icon;
    }

}
