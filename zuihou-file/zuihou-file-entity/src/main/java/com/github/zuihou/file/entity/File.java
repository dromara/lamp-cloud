package com.github.zuihou.file.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;
import com.github.zuihou.file.enumeration.DataType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 文件表
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
@TableName("f_file")
@ApiModel(value = "File", description = "文件表")
public class File extends Entity<Long> {

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
     */
    @ApiModelProperty(value = "原始文件名")
    @Length(max = 255, message = "原始文件名长度不能超过255")
    @TableField("submitted_file_name")
    private String submittedFileName;

    /**
     * 父目录层级关系
     */
    @ApiModelProperty(value = "父目录层级关系")
    @Length(max = 255, message = "父目录层级关系长度不能超过255")
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
     * 是否删除
     * #BooleanStatus{TRUE:1,已删除;FALSE:0,未删除}
     */
    @ApiModelProperty(value = "是否删除")
    @TableField("is_delete")
    private Boolean isDelete;

    /**
     * 父文件夹ID
     */
    @ApiModelProperty(value = "父文件夹ID")
    @TableField("folder_id")
    private Long folderId;

    /**
     * 文件访问链接
     * 需要通过nginx配置路由，才能访问
     */
    @ApiModelProperty(value = "文件访问链接")
    @Length(max = 255, message = "文件访问链接长度不能超过255")
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
     * 用于FastDFS
     */
    @ApiModelProperty(value = "FastDFS组")
    @Length(max = 255, message = "FastDFS组长度不能超过255")
    @TableField("group_")
    private String group;

    /**
     * FastDFS远程文件名
     * 用于FastDFS
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
     * 文件类型
     * 取上传文件的值
     */
    @ApiModelProperty(value = "文件类型")
    @Length(max = 255, message = "文件类型长度不能超过255")
    @TableField("context_type")
    private String contextType;

    /**
     * 唯一文件名
     */
    @ApiModelProperty(value = "唯一文件名")
    @Length(max = 255, message = "唯一文件名长度不能超过255")
    @TableField("filename")
    private String filename;

    /**
     * 文件名后缀
     * (没有.)
     */
    @ApiModelProperty(value = "文件名后缀")
    @Length(max = 64, message = "文件名后缀长度不能超过64")
    @TableField("ext")
    private String ext;

    /**
     * 文件图标
     * 用于云盘显示
     */
    @ApiModelProperty(value = "文件图标")
    @Length(max = 64, message = "文件图标长度不能超过64")
    @TableField("icon")
    private String icon;

    /**
     * 创建时年月
     * 格式：yyyy-MM 用于统计
     */
    @ApiModelProperty(value = "创建时年月")
    @Length(max = 10, message = "创建时年月长度不能超过10")
    @TableField("create_month")
    private String createMonth;

    /**
     * 创建时年周
     * yyyy-ww 用于统计
     */
    @ApiModelProperty(value = "创建时年周")
    @Length(max = 10, message = "创建时年周长度不能超过10")
    @TableField("create_week")
    private String createWeek;

    /**
     * 创建时年月日
     * 格式： yyyy-MM-dd 用于统计
     */
    @ApiModelProperty(value = "创建时年月日")
    @Length(max = 12, message = "创建时年月日长度不能超过12")
    @TableField("create_day")
    private String createDay;


    @Builder
    public File(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                DataType dataType, String submittedFileName, String treePath, Integer grade, Boolean isDelete,
                Long folderId, String url, Long size, String folderName, String group, String path,
                String relativePath, String fileMd5, String contextType, String filename, String ext, String icon,
                String createMonth, String createWeek, String createDay) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.dataType = dataType;
        this.submittedFileName = submittedFileName;
        this.treePath = treePath;
        this.grade = grade;
        this.isDelete = isDelete;
        this.folderId = folderId;
        this.url = url;
        this.size = size;
        this.folderName = folderName;
        this.group = group;
        this.path = path;
        this.relativePath = relativePath;
        this.fileMd5 = fileMd5;
        this.contextType = contextType;
        this.filename = filename;
        this.ext = ext;
        this.icon = icon;
        this.createMonth = createMonth;
        this.createWeek = createWeek;
        this.createDay = createDay;
    }

}
