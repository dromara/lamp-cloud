package com.github.zuihou.authority.entity.auth;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.authority.enumeration.auth.AccountType;
import com.github.zuihou.authority.enumeration.auth.Sex;
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
 * 账号
 * </p>
 *
 * @author zuihou
 * @since 2019-06-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_auth_user")
@ApiModel(value = "User", description = "账号")
public class User extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    @Length(max = 30, message = "账号长度不能超过30")
    @TableField("account")
    private String account;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空")
    @Length(max = 20, message = "姓名长度不能超过20")
    @TableField("name")
    private String name;

    /**
     * 账号类型  
     * #AccountType{CUSTOMER:客户;BUILT_IN:内置}
     */
    @ApiModelProperty(value = "账号类型")
    @TableField("account_type")
    private AccountType accountType;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Length(max = 20, message = "手机长度不能超过20")
    @TableField("mobile")
    private String mobile;

    /**
     * 性别
     * #Sex{W:女;M:男}
     */
    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private Sex sex;

    /**
     * 是否可登陆
     */
    @ApiModelProperty(value = "是否可登陆")
    @NotNull(message = "是否可登陆不能为空")
    @TableField("is_can_login")
    private Boolean isCanLogin;

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记")
    @TableField("is_delete")
    private Boolean isDelete;

    /**
     * 照片
     */
    @ApiModelProperty(value = "照片")
    @Length(max = 255, message = "照片长度不能超过255")
    @TableField("photo")
    private String photo;

    /**
     * 工作描述
     * 比如：  市长、管理员、局长等等   用于登陆展示
     */
    @ApiModelProperty(value = "工作描述")
    @Length(max = 255, message = "工作描述长度不能超过255")
    @TableField("work_describe")
    private String workDescribe;

    /**
     * 登录次数
     * 一直累计，记录了此账号总共登录次数
     */
    @ApiModelProperty(value = "登录次数")
    @NotNull(message = "登录次数不能为空")
    @TableField("login_count")
    private Integer loginCount;

    /**
     * 输入密码错误的日期
     * 比如20190102  与error_count合力实现一天输入密码错误次数限制
     */
    @ApiModelProperty(value = "输入密码错误的日期")
    @TableField("continuation_error_day")
    private LocalDate continuationErrorDay;

    /**
     * 一天连续输错密码次数
     */
    @ApiModelProperty(value = "一天连续输错密码次数")
    @NotNull(message = "一天连续输错密码次数不能为空")
    @TableField("continuation_error_count")
    private Integer continuationErrorCount;

    /**
     * 密码过期时间
     */
    @ApiModelProperty(value = "密码过期时间")
    @TableField("password_expire_time")
    private LocalDateTime passwordExpireTime;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    @Length(max = 32, message = "密码长度不能超过32")
    @TableField("password")
    private String password;


    @Builder
    public User(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                String account, String name, AccountType accountType, String mobile, Sex sex,
                Boolean isCanLogin, Boolean isDelete, String photo, String workDescribe, Integer loginCount, LocalDate continuationErrorDay,
                Integer continuationErrorCount, LocalDateTime passwordExpireTime, String password) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.account = account;
        this.name = name;
        this.accountType = accountType;
        this.mobile = mobile;
        this.sex = sex;
        this.isCanLogin = isCanLogin;
        this.isDelete = isDelete;
        this.photo = photo;
        this.workDescribe = workDescribe;
        this.loginCount = loginCount;
        this.continuationErrorDay = continuationErrorDay;
        this.continuationErrorCount = continuationErrorCount;
        this.passwordExpireTime = passwordExpireTime;
        this.password = password;
    }

}
