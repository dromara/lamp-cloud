package com.github.zuihou.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;
import static com.github.zuihou.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * <p>
 * 实体类
 * token
 * </p>
 *
 * @author zuihou
 * @since 2020-04-03
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_auth_user_token")
@ApiModel(value = "UserToken", description = "token")
@AllArgsConstructor
public class UserToken extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 登录IP
     */
    @ApiModelProperty(value = "登录IP")
    @Length(max = 50, message = "登录IP长度不能超过50")
    @TableField(value = "login_ip", condition = LIKE)
    @Excel(name = "登录IP")
    private String loginIp;

    /**
     * 登录地点
     */
    @ApiModelProperty(value = "登录地点")
    @Length(max = 50, message = "登录地点长度不能超过50")
    @TableField(value = "location", condition = LIKE)
    @Excel(name = "登录地点")
    private String location;

    /**
     * 客户端Key
     */
    @ApiModelProperty(value = "客户端Key")
    @Length(max = 24, message = "客户端Key长度不能超过24")
    @TableField(value = "client_id", condition = LIKE)
    @Excel(name = "客户端Key")
    private String clientId;

    /**
     * token
     */
    @ApiModelProperty(value = "token")
    @Length(max = 65535, message = "token长度不能超过65535")
    @TableField("token")
    @Excel(name = "token")
    private String token;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Length(max = 50, message = "姓名长度不能超过50")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "姓名")
    private String name;

    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间")
    @TableField("expire_time")
    @Excel(name = "过期时间", format = DEFAULT_DATE_TIME_FORMAT, width = 20)
    private LocalDateTime expireTime;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @Length(max = 30, message = "账号长度不能超过30")
    @TableField(value = "account", condition = LIKE)
    @Excel(name = "账号")
    private String account;


    @Builder
    public UserToken(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                     String loginIp, String location, String clientId, String token, String name,
                     LocalDateTime expireTime, String account) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.loginIp = loginIp;
        this.location = location;
        this.clientId = clientId;
        this.token = token;
        this.name = name;
        this.expireTime = expireTime;
        this.account = account;
    }

}
