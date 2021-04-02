package com.tangyh.lamp.authority.entity.common;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.base.entity.SuperEntity;
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

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 登录日志
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
@TableName("c_login_log")
@ApiModel(value = "LoginLog", description = "登录日志")
@AllArgsConstructor
public class LoginLog extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 登录IP
     */
    @ApiModelProperty(value = "登录IP")
    @Size(max = 50, message = "登录IP长度不能超过50")
    @TableField(value = "request_ip", condition = LIKE)
    @Excel(name = "登录IP")
    private String requestIp;

    /**
     * 登录人ID
     */
    @ApiModelProperty(value = "登录人ID")
    @TableField("user_id")
    @Excel(name = "登录人ID")
    private Long userId;

    /**
     * 登录人姓名
     */
    @ApiModelProperty(value = "登录人姓名")
    @Size(max = 50, message = "登录人姓名长度不能超过50")
    @TableField(value = "user_name", condition = LIKE)
    @Excel(name = "登录人姓名")
    private String userName;

    /**
     * 登录人账号
     */
    @ApiModelProperty(value = "登录人账号")
    @Size(max = 30, message = "登录人账号长度不能超过30")
    @TableField(value = "account", condition = LIKE)
    @Excel(name = "登录人账号")
    private String account;

    /**
     * 登录描述
     */
    @ApiModelProperty(value = "登录描述")
    @Size(max = 255, message = "登录描述长度不能超过255")
    @TableField(value = "description", condition = LIKE)
    @Excel(name = "登录描述")
    private String description;

    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间")
    @Size(max = 10, message = "登录时间长度不能超过10")
    @TableField(value = "login_date", condition = LIKE)
    @Excel(name = "登录时间")
    private String loginDate;

    /**
     * 浏览器请求头
     */
    @ApiModelProperty(value = "浏览器请求头")
    @Size(max = 500, message = "浏览器请求头长度不能超过500")
    @TableField(value = "ua", condition = LIKE)
    @Excel(name = "浏览器请求头")
    private String ua;

    /**
     * 浏览器名称
     */
    @ApiModelProperty(value = "浏览器名称")
    @Size(max = 255, message = "浏览器名称长度不能超过255")
    @TableField(value = "browser", condition = LIKE)
    @Excel(name = "浏览器名称")
    private String browser;

    /**
     * 浏览器版本
     */
    @ApiModelProperty(value = "浏览器版本")
    @Size(max = 255, message = "浏览器版本长度不能超过255")
    @TableField(value = "browser_version", condition = LIKE)
    @Excel(name = "浏览器版本")
    private String browserVersion;

    /**
     * 操作系统
     */
    @ApiModelProperty(value = "操作系统")
    @Size(max = 255, message = "操作系统长度不能超过255")
    @TableField(value = "operating_system", condition = LIKE)
    @Excel(name = "操作系统")
    private String operatingSystem;

    /**
     * 登录地点
     */
    @ApiModelProperty(value = "登录地点")
    @Size(max = 50, message = "登录地点长度不能超过50")
    @TableField(value = "location", condition = LIKE)
    @Excel(name = "登录地点")
    private String location;


    @Builder
    public LoginLog(Long id, LocalDateTime createTime, Long createdBy,
                    String requestIp, Long userId, String userName, String account, String description,
                    String loginDate, String ua, String browser, String browserVersion, String operatingSystem, String location) {
        this.id = id;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.requestIp = requestIp;
        this.userId = userId;
        this.userName = userName;
        this.account = account;
        this.description = description;
        this.loginDate = loginDate;
        this.ua = ua;
        this.browser = browser;
        this.browserVersion = browserVersion;
        this.operatingSystem = operatingSystem;
        this.location = location;
    }

}
