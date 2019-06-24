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
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 实体类
 * 分享目录表
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
@TableName("f_share")
@ApiModel(value = "Share", description = "分享目录表")
public class Share extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 分享名称
     */
    @ApiModelProperty(value = "分享名称")
    @Length(max = 255, message = "分享名称长度不能超过255")
    @TableField("name")
    private String name;

    /**
     * 分享密码
     */
    @ApiModelProperty(value = "分享密码")
    @Length(max = 32, message = "分享密码长度不能超过32")
    @TableField("password")
    private String password;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    @Length(max = 64, message = "图标长度不能超过64")
    @TableField("icon")
    private String icon;

    /**
     * 链接
     */
    @ApiModelProperty(value = "链接")
    @Length(max = 255, message = "链接长度不能超过255")
    @TableField("url")
    private String url;

    /**
     * 分享过期时间
     */
    @ApiModelProperty(value = "分享过期时间")
    @TableField("expire_time")
    private LocalDateTime expireTime;

    /**
     * 点赞次数
     */
    @ApiModelProperty(value = "点赞次数")
    @TableField("thumbs_up")
    private Integer thumbsUp;

    /**
     * 下载次数
     */
    @ApiModelProperty(value = "下载次数")
    @TableField("download_count")
    private Integer downloadCount;

    /**
     * 保存次数
     */
    @ApiModelProperty(value = "保存次数")
    @TableField("save_count")
    private Integer saveCount;

    /**
     * 浏览次数
     */
    @ApiModelProperty(value = "浏览次数")
    @TableField("view_count")
    private Integer viewCount;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    @Length(max = 255, message = "创建人名称长度不能超过255")
    @TableField("create_user_name")
    private String createUserName;


    @Builder
    public Share(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                 String name, String password, String icon, String url, LocalDateTime expireTime,
                 Integer thumbsUp, Integer downloadCount, Integer saveCount, Integer viewCount, String createUserName) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.name = name;
        this.password = password;
        this.icon = icon;
        this.url = url;
        this.expireTime = expireTime;
        this.thumbsUp = thumbsUp;
        this.downloadCount = downloadCount;
        this.saveCount = saveCount;
        this.viewCount = viewCount;
        this.createUserName = createUserName;
    }

}
