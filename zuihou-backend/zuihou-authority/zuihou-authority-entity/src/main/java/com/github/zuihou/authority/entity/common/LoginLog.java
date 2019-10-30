package com.github.zuihou.authority.entity.common;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.SuperEntity;

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
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @since 2019-10-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_common_login_log")
@ApiModel(value = "LoginLog", description = "系统日志")
public class LoginLog extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 操作IP
     */
    @ApiModelProperty(value = "操作IP")
    @Length(max = 50, message = "操作IP长度不能超过50")
    @TableField(value = "request_ip", condition = LIKE)
    private String requestIp;

    /**
     * 登录用户
     */
    @ApiModelProperty(value = "登录人账号")
    @Length(max = 30, message = "登录人账号长度不能超过50")
    @TableField(value = "account", condition = LIKE)
    private String account;

    @ApiModelProperty(value = "登录人姓名")
    @Length(max = 50, message = "登录用户长度不能超过50")
    @TableField(value = "user_name", condition = LIKE)
    private String userName;
    @ApiModelProperty(value = "登录人ID")
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 登录描述
     */
    @ApiModelProperty(value = "登录描述")
    @Length(max = 255, message = "登录描述长度不能超过255")
    @TableField(value = "description", condition = LIKE)
    private String description;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @TableField("login_time")
    private LocalDateTime loginTime;

    /**
     * 浏览器请求头
     */
    @ApiModelProperty(value = "浏览器请求头")
    @TableField("ua")
    private String ua;

    /**
     * 登录地点
     */
    @ApiModelProperty(value = "登录地点")
    @Length(max = 50, message = "登录地点长度不能超过50")
    @TableField(value = "location", condition = LIKE)
    private String location;


    @Builder
    public LoginLog(Long id, LocalDateTime createTime, Long createUser,
                    String requestIp, String account, String userName, Long userId, String description, LocalDateTime loginTime, String ua, String location) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.requestIp = requestIp;
        this.account = account;
        this.userName = userName;
        this.userId = userId;
        this.description = description;
        this.loginTime = loginTime;
        this.ua = ua;
        this.location = location;
    }

}
