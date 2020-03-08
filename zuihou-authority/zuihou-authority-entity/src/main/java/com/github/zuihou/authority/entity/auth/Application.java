package com.github.zuihou.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.authority.enumeration.auth.ApplicationAppTypeEnum;
import com.github.zuihou.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 应用
 * </p>
 *
 * @author zuihou
 * @since 2019-12-25
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
     * AppKey
     */
    @ApiModelProperty(value = "AppKey")
    @Length(max = 24, message = "AppKey长度不能超过100")
    @TableField(value = "app_key", condition = LIKE)
    @Excel(name = "AppKey")
    private String appKey;

    /**
     * AppSecret
     */
    @ApiModelProperty(value = "AppSecret")
    @Length(max = 32, message = "AppSecret长度不能超过255")
    @TableField(value = "app_secret", condition = LIKE)
    private String appSecret;

    /**
     * 官网
     */
    @ApiModelProperty(value = "官网")
    @Length(max = 100, message = "官网长度不能超过100")
    @TableField(value = "website", condition = LIKE)
    private String website;

    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称")
    @NotEmpty(message = "应用名称不能为空")
    @Length(max = 255, message = "应用名称长度不能超过255")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "应用名称", width = 20)
    private String name;

    /**
     * 应用图标
     */
    @ApiModelProperty(value = "应用图标")
    @Length(max = 255, message = "应用图标长度不能超过255")
    @TableField(value = "icon", condition = LIKE)
    private String icon;

    /**
     * 类型
     * #{SERVER:服务应用;APP:手机应用;PC:PC网页应用;WAP:手机网页应用}
     */
    @ApiModelProperty(value = "类型")
    @TableField("app_type")
    @Excel(name = "应用名称", replace = {"服务应用_SERVER", "手机应用_APP", "手机网页应用_WAP", "PC网页应用_PC", "_null"})
    private ApplicationAppTypeEnum appType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Length(max = 200, message = "备注长度不能超过200")
    @TableField(value = "describe_", condition = LIKE)
    @Excel(name = "备注", width = 20)
    private String describe;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("status")
    @Excel(name = "状态", replace = {"启用_true", "禁用_false", "_null"})
    private Boolean status;


    @Builder
    public Application(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                       String appKey, String appSecret, String website, String name, String icon,
                       ApplicationAppTypeEnum appType, String describe, Boolean status) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.website = website;
        this.name = name;
        this.icon = icon;
        this.appType = appType;
        this.describe = describe;
        this.status = status;
    }

}
