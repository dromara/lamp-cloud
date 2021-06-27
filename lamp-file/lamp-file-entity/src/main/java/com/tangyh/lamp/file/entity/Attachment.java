package com.tangyh.lamp.file.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.base.entity.Entity;
import com.tangyh.basic.model.EchoVO;
import com.tangyh.lamp.file.enumeration.FileStorageType;
import com.tangyh.lamp.file.enumeration.FileType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 附件
 * </p>
 *
 * @author zuihou
 * @since 2021-06-15
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_attachment")
@ApiModel(value = "Attachment", description = "附件")
@AllArgsConstructor
public class Attachment extends Entity<Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Map<String, Object> echoMap = new HashMap<>();
    /**
     * 业务ID
     */
    @ApiModelProperty(value = "业务ID")
    @Size(max = 255, message = "业务ID长度不能超过255")
    @TableField(value = "biz_id", condition = LIKE)
    @Excel(name = "业务ID")
    private String bizId;

    /**
     * 业务类型
     * #AttachmentType
     */
    @ApiModelProperty(value = "业务类型")
    @Size(max = 255, message = "业务类型长度不能超过255")
    @TableField(value = "biz_type", condition = LIKE)
    @Excel(name = "业务类型")
    private String bizType;

    @ApiModelProperty(value = "文件类型")
    @Size(max = 255, message = "文件类型长度不能超过255")
    @TableField(value = "file_type")
    @Excel(name = "文件类型")
    private FileType fileType;

    @ApiModelProperty(value = "存储类型")
    @Size(max = 255, message = "存储类型长度不能超过255")
    @TableField(value = "storage_type")
    @Excel(name = "存储类型")
    private FileStorageType storageType;

    /**
     * FastDFS中的组 MinIO中的bucket
     * 用于FastDFS 或 MinIO
     */
    @ApiModelProperty(value = "FastDFS中的组 MinIO中的bucket")
    @Size(max = 255, message = "FastDFS中的组 MinIO中的bucket长度不能超过255")
    @TableField(value = "group_", condition = LIKE)
    @Excel(name = "FastDFS中的组 MinIO中的bucket")
    private String group;

    /**
     * FastDFS的远程文件名 MinIO的文件路径
     * 用于FastDFS 和MinIO
     */
    @ApiModelProperty(value = "FastDFS的远程文件名 MinIO的文件路径")
    @Size(max = 255, message = "FastDFS的远程文件名 MinIO的文件路径长度不能超过255")
    @TableField(value = "path", condition = LIKE)
    @Excel(name = "FastDFS的远程文件名 MinIO的文件路径")
    private String path;

    /**
     * 文件访问链接
     * 需要通过nginx配置路由，才能访问
     */
    @ApiModelProperty(value = "文件访问链接")
    @Size(max = 500, message = "文件访问链接长度不能超过500")
    @TableField(value = "url", condition = LIKE)
    @Excel(name = "文件访问链接")
    private String url;

    /**
     * 唯一文件名
     * UUID规则
     */
    @ApiModelProperty(value = "唯一文件名")
    @Size(max = 255, message = "唯一文件名长度不能超过255")
    @TableField(value = "unique_file_name", condition = LIKE)
    @Excel(name = "唯一文件名")
    private String uniqueFileName;

    /**
     * 文件md5值
     */
    @ApiModelProperty(value = "文件md5值")
    @Size(max = 255, message = "文件md5值长度不能超过255")
    @TableField(value = "file_md5", condition = LIKE)
    @Excel(name = "文件md5值")
    private String fileMd5;

    /**
     * 原始文件名
     */
    @ApiModelProperty(value = "原始文件名")
    @Size(max = 255, message = "原始文件名长度不能超过255")
    @TableField(value = "original_file_name", condition = LIKE)
    @Excel(name = "原始文件名")
    private String originalFileName;

    /**
     * 文件原始类型
     */
    @ApiModelProperty(value = "文件原始类型")
    @Size(max = 255, message = "文件原始类型长度不能超过255")
    @TableField(value = "content_type", condition = LIKE)
    @Excel(name = "文件原始类型")
    private String contentType;

    /**
     * 后缀
     */
    @ApiModelProperty(value = "后缀")
    @Size(max = 64, message = "后缀长度不能超过64")
    @TableField(value = "ext", condition = LIKE)
    @Excel(name = "后缀")
    private String ext;

    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    @TableField(value = "size")
    @Excel(name = "文件大小")
    private Long size;

    /**
     * 组织
     * #c_org
     */
    @ApiModelProperty(value = "组织")
    @TableField(value = "org_id")
    @Excel(name = "组织")
    private Long orgId;


    @Builder
    public Attachment(Long id, LocalDateTime createTime, Long createdBy, LocalDateTime updateTime, Long updatedBy,
                      String bizId, String bizType, String group, String path, String url, FileType fileType, FileStorageType storageType,
                      String uniqueFileName, String fileMd5, String originalFileName, String contentType, String ext, Long size, Long orgId) {
        this.id = id;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.updateTime = updateTime;
        this.updatedBy = updatedBy;
        this.bizId = bizId;
        this.bizType = bizType;
        this.fileType = fileType;
        this.storageType = storageType;
        this.group = group;
        this.path = path;
        this.url = url;
        this.uniqueFileName = uniqueFileName;
        this.fileMd5 = fileMd5;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.ext = ext;
        this.size = size;
        this.orgId = orgId;
    }

}
