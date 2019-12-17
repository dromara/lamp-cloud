package com.github.zuihou.authority.entity.auth;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

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

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 应用
 * </p>
 *
 * @author zuihou
 * @since 2019-12-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_auth_application")
@ApiModel(value = "Application", description = "应用")
public class Application extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * AppId
     */
    @ApiModelProperty(value = "AppId")
    @Length(max = 100, message = "AppId长度不能超过100")
    @TableField(value = "app_key", condition = LIKE)
    private String appKey;

    /**
     * AppSecret
     */
    @ApiModelProperty(value = "AppSecret")
    @Length(max = 255, message = "AppSecret长度不能超过255")
    @TableField(value = "app_secret", condition = LIKE)
    private String appSecret;

    /**
     * 首页地址
     */
    @ApiModelProperty(value = "首页地址")
    @Length(max = 100, message = "首页地址长度不能超过100")
    @TableField(value = "index_url", condition = LIKE)
    private String indexUrl;

    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称")
    @Length(max = 20, message = "应用名称长度不能超过20")
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 应用logo
     */
    @ApiModelProperty(value = "应用logo")
    @Length(max = 255, message = "应用logo长度不能超过255")
    @TableField(value = "logo_url", condition = LIKE)
    private String logoUrl;

    /**
     * 功能描述
     */
    @ApiModelProperty(value = "功能描述")
    @Length(max = 200, message = "功能描述长度不能超过200")
    @TableField(value = "describe_", condition = LIKE)
    private String describe;

    /**
     * 应用编码
     * 必须唯一
     */
    @ApiModelProperty(value = "应用编码")
    @NotEmpty(message = "应用编码不能为空")
    @Length(max = 20, message = "应用编码长度不能超过20")
    @TableField(value = "code", condition = LIKE)
    private String code;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    @TableField("sort_value")
    private Integer sortValue;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    @TableField("is_enable")
    private Boolean isEnable;


    @Builder
    public Application(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                       String appKey, String appSecret, String indexUrl, String name, String logoUrl,
                       String describe, String code, Integer sortValue, Boolean isEnable) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.indexUrl = indexUrl;
        this.name = name;
        this.logoUrl = logoUrl;
        this.describe = describe;
        this.code = code;
        this.sortValue = sortValue;
        this.isEnable = isEnable;
    }

}
