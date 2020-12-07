package com.tangyh.lamp.file.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.base.entity.Entity;
import com.tangyh.lamp.file.enumeration.DataType;
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

import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 附件
 * </p>
 *
 * @author zuihou
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_attachment")
@ApiModel(value = "Attachment", description = "附件")
@AllArgsConstructor
public class Attachment extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 业务ID
     */
    @ApiModelProperty(value = "业务ID")
    @Length(max = 64, message = "业务ID长度不能超过64")
    @TableField(value = "biz_id", condition = LIKE)
    @Excel(name = "业务ID")
    private String bizId;

    /**
     * 业务类型
     * #AttachmentType
     */
    @ApiModelProperty(value = "业务类型")
    @Length(max = 255, message = "业务类型长度不能超过255")
    @TableField(value = "biz_type", condition = LIKE)
    @Excel(name = "业务类型")
    private String bizType;

    /**
     * 数据类型
     * #DataType{DIR:目录;IMAGE:图片;VIDEO:视频;AUDIO:音频;DOC:文档;OTHER:其他}
     */
    @ApiModelProperty(value = "数据类型")
    @TableField("data_type")
    @Excel(name = "数据类型", replace = {"目录_DIR", "图片_IMAGE", "视频_VIDEO", "音频_AUDIO", "文档_DOC", "其他_OTHER", "_null"})
    private DataType dataType;

    /**
     * 原始文件名
     */
    @ApiModelProperty(value = "原始文件名")
    @Length(max = 255, message = "原始文件名长度不能超过255")
    @TableField(value = "submitted_file_name", condition = LIKE)
    @Excel(name = "原始文件名")
    private String submittedFileName;

    /**
     * FastDFS返回的组
     * 用于FastDFS
     */
    @ApiModelProperty(value = "FastDFS返回的组")
    @Length(max = 255, message = "FastDFS返回的组长度不能超过255")
    @TableField(value = "group_", condition = LIKE)
    @Excel(name = "FastDFS返回的组")
    private String group;

    /**
     * FastDFS的远程文件名
     * 用于FastDFS
     */
    @ApiModelProperty(value = "FastDFS的远程文件名")
    @Length(max = 255, message = "FastDFS的远程文件名长度不能超过255")
    @TableField(value = "path", condition = LIKE)
    @Excel(name = "FastDFS的远程文件名")
    private String path;

    /**
     * 文件相对路径
     */
    @ApiModelProperty(value = "文件相对路径")
    @Length(max = 255, message = "文件相对路径长度不能超过255")
    @TableField(value = "relative_path", condition = LIKE)
    @Excel(name = "文件相对路径")
    private String relativePath;

    /**
     * 文件访问链接
     * 需要通过nginx配置路由，才能访问
     */
    @ApiModelProperty(value = "文件访问链接")
    @Length(max = 255, message = "文件访问链接长度不能超过255")
    @TableField(value = "url", condition = LIKE)
    @Excel(name = "文件访问链接")
    private String url;

    /**
     * 文件md5值
     */
    @ApiModelProperty(value = "文件md5值")
    @Length(max = 255, message = "文件md5值长度不能超过255")
    @TableField(value = "file_md5", condition = LIKE)
    @Excel(name = "文件md5值")
    private String fileMd5;

    /**
     * 文件上传类型
     * 取上传文件的值
     */
    @ApiModelProperty(value = "文件上传类型")
    @Length(max = 255, message = "文件上传类型长度不能超过255")
    @TableField(value = "context_type", condition = LIKE)
    @Excel(name = "文件上传类型")
    private String contextType;

    /**
     * 唯一文件名
     */
    @ApiModelProperty(value = "唯一文件名")
    @Length(max = 255, message = "唯一文件名长度不能超过255")
    @TableField(value = "filename", condition = LIKE)
    @Excel(name = "唯一文件名")
    private String filename;

    /**
     * 后缀
     * (没有.)
     */
    @ApiModelProperty(value = "后缀")
    @Length(max = 64, message = "后缀长度不能超过64")
    @TableField(value = "ext", condition = LIKE)
    @Excel(name = "后缀")
    private String ext;

    /**
     * 大小
     */
    @ApiModelProperty(value = "大小")
    @TableField("size")
    @Excel(name = "大小")
    private Long size;

    /**
     * 组织ID
     * #c_core_org
     */
    @ApiModelProperty(value = "组织ID")
    @TableField("org_id")
    @Excel(name = "组织ID")
    private Long orgId;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    @Length(max = 64, message = "图标长度不能超过64")
    @TableField(value = "icon", condition = LIKE)
    @Excel(name = "图标")
    private String icon;

    /**
     * 创建年月
     * 格式：yyyy-MM 用于统计
     */
    @ApiModelProperty(value = "创建年月")
    @Length(max = 7, message = "创建年月长度不能超过7")
    @TableField(value = "create_month", condition = LIKE)
    @Excel(name = "创建年月")
    private String createMonth;

    /**
     * 创建时处于当年的第几周
     * 格式：yyyy-ww 用于统计
     */
    @ApiModelProperty(value = "创建时处于当年的第几周")
    @Length(max = 7, message = "创建时处于当年的第几周长度不能超过7")
    @TableField(value = "create_week", condition = LIKE)
    @Excel(name = "创建时处于当年的第几周")
    private String createWeek;

    /**
     * 创建年月日
     * 格式： yyyy-MM-dd 用于统计
     */
    @ApiModelProperty(value = "创建年月日")
    @Length(max = 10, message = "创建年月日长度不能超过10")
    @TableField(value = "create_day", condition = LIKE)
    @Excel(name = "创建年月日")
    private String createDay;


    @Builder
    public Attachment(Long id, LocalDateTime createTime, Long createdBy, LocalDateTime updateTime, Long updatedBy,
                      String bizId, String bizType, DataType dataType, String submittedFileName, String group,
                      String path, String relativePath, String url, String fileMd5, String contextType, String filename,
                      String ext, Long size, Long orgId, String icon, String createMonth, String createWeek, String createDay) {
        this.id = id;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.updateTime = updateTime;
        this.updatedBy = updatedBy;
        this.bizId = bizId;
        this.bizType = bizType;
        this.dataType = dataType;
        this.submittedFileName = submittedFileName;
        this.group = group;
        this.path = path;
        this.relativePath = relativePath;
        this.url = url;
        this.fileMd5 = fileMd5;
        this.contextType = contextType;
        this.filename = filename;
        this.ext = ext;
        this.size = size;
        this.orgId = orgId;
        this.icon = icon;
        this.createMonth = createMonth;
        this.createWeek = createWeek;
        this.createDay = createDay;
    }

}
