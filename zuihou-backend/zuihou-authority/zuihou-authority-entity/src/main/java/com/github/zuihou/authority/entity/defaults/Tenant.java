package com.github.zuihou.authority.entity.defaults;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.authority.enumeration.defaults.TenantStatusEnum;
import com.github.zuihou.authority.enumeration.defaults.TenantTypeEnum;
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
 * 企业
 * </p>
 *
 * @author zuihou
 * @since 2019-10-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("d_tenant")
@ApiModel(value = "Tenant", description = "企业")
public class Tenant extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 企业编码
     */
    @ApiModelProperty(value = "企业编码")
    @Length(max = 20, message = "企业编码长度不能超过20")
    @TableField(value = "code", condition = LIKE)
    private String code;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    @Length(max = 255, message = "企业名称长度不能超过255")
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 类型
     * #{CREATE:创建;REGISTER:注册}
     */
    @ApiModelProperty(value = "类型")
    @TableField("type")
    private TenantTypeEnum type;

    /**
     * 状态
     * #{NORMAL:正常;FORBIDDEN:禁用;WAITING:待审核;REFUSE:拒绝}
     */
    @ApiModelProperty(value = "状态")
    @TableField("status")
    private TenantStatusEnum status;

    /**
     * 责任人
     */
    @ApiModelProperty(value = "责任人")
    @Length(max = 50, message = "责任人长度不能超过50")
    @TableField(value = "duty", condition = LIKE)
    private String duty;

    /**
     * 有效期
     * 为空表示永久
     */
    @ApiModelProperty(value = "有效期")
    @TableField("expiration_time")
    private LocalDateTime expirationTime;

    /**
     * logo地址
     */
    @ApiModelProperty(value = "logo地址")
    @Length(max = 255, message = "logo地址长度不能超过255")
    @TableField(value = "logo", condition = LIKE)
    private String logo;

    /**
     * 企业简介
     */
    @ApiModelProperty(value = "企业简介")
    @Length(max = 255, message = "企业简介长度不能超过255")
    @TableField(value = "describe_", condition = LIKE)
    private String describe;

    /**
     * 密码有效期
     * 单位：天 0表示 永久有效
     */
    @ApiModelProperty(value = "密码有效期")
    @TableField("password_expire")
    private Integer passwordExpire;

    /**
     * 是否多地登录
     */
    @ApiModelProperty(value = "是否多地登录")
    @TableField("is_multiple_login")
    private Boolean isMultipleLogin;

    /**
     * 密码输错次数
     * 密码输错锁定账号的次数
     * 单位：次
     */
    @ApiModelProperty(value = "密码输错次数")
    @TableField("passwordErrorNum")
    private Integer passwordErrorNum;

    /**
     * 账号锁定时间
     * 密码输错${passwordErrorNum}次后，锁定账号的时间
     * 单位： h | d | w | m
     * 单位： 时 | 天 | 周 | 月
     * 如：0=当天晚上24点 2h = 2小时   2d = 2天
     */
    @ApiModelProperty(value = "账号锁定时间")
    @Length(max = 50, message = "账号锁定时间长度不能超过50")
    @TableField(value = "passwordErrorLockTime", condition = LIKE)
    private String passwordErrorLockTime;


    @Builder
    public Tenant(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                  String code, String name, TenantTypeEnum type, TenantStatusEnum status, String duty,
                  LocalDateTime expirationTime, String logo, String describe, Integer passwordExpire, Boolean isMultipleLogin, Integer passwordErrorNum, String passwordErrorLockTime) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.code = code;
        this.name = name;
        this.type = type;
        this.status = status;
        this.duty = duty;
        this.expirationTime = expirationTime;
        this.logo = logo;
        this.describe = describe;
        this.passwordExpire = passwordExpire;
        this.isMultipleLogin = isMultipleLogin;
        this.passwordErrorNum = passwordErrorNum;
        this.passwordErrorLockTime = passwordErrorLockTime;
    }

}
