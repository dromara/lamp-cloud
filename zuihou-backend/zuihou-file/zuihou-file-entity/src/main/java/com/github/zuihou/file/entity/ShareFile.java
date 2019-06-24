package com.github.zuihou.file.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 实体类
 * 分享文件表
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
@TableName("f_share_file")
@ApiModel(value = "ShareFile", description = "分享文件表")
public class ShareFile extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 人员ID
     * #c_auth_account
     */
    @ApiModelProperty(value = "人员ID")
    @TableField("account_id")
    private Long accountId;

    /**
     * 分享ID
     * #f_share
     */
    @ApiModelProperty(value = "分享ID")
    @TableField("share_id")
    private Long shareId;

    /**
     * 文件ID
     * #f_fil
     */
    @ApiModelProperty(value = "文件ID")
    @TableField("file_id")
    private Long fileId;


    @Builder
    public ShareFile(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                     Long accountId, Long shareId, Long fileId) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.accountId = accountId;
        this.shareId = shareId;
        this.fileId = fileId;
    }

}
