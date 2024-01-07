package top.tangyh.lamp.system.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.Entity;

import java.time.LocalDateTime;

import static top.tangyh.lamp.model.constant.Condition.LIKE;

/**
 * <p>
 * 实体类
 * 登录日志
 * </p>
 *
 * @author zuihou
 * @since 2021-11-12
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("def_login_log")
@AllArgsConstructor
public class DefLoginLog extends Entity<Long> {

    private static final long serialVersionUID = 1L;


    /**
     * 登录员工
     */
    @TableField(value = "employee_id")
    private Long employeeId;

    /**
     * 登录用户
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 登录IP
     */
    @TableField(value = "request_ip", condition = LIKE)
    private String requestIp;

    /**
     * 登录人姓名
     */
    @TableField(value = "nick_name", condition = LIKE)
    private String nickName;

    /**
     * 登录人账号
     */
    @TableField(value = "username", condition = LIKE)
    private String username;

    /**
     * 登录描述
     */
    @TableField(value = "description", condition = LIKE)
    private String description;

    /**
     * 登录时间
     */
    @TableField(value = "login_date", condition = LIKE)
    private String loginDate;

    /**
     * 浏览器请求头
     */
    @TableField(value = "ua", condition = LIKE)
    private String ua;

    /**
     * 浏览器名称
     */
    @TableField(value = "browser", condition = LIKE)
    private String browser;

    /**
     * 浏览器版本
     */
    @TableField(value = "browser_version", condition = LIKE)
    private String browserVersion;

    /**
     * 操作系统
     */
    @TableField(value = "operating_system", condition = LIKE)
    private String operatingSystem;

    /**
     * 登录地点
     */
    @TableField(value = "location", condition = LIKE)
    private String location;
    /**
     * '登录状态;[01-登录成功 02-验证码错误 03-密码错误 04-账号锁定 05-切换租户 06-短信验证码错误]
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.LOGIN_STATUS)
     */
    @TableField(value = "status", condition = LIKE)
    private String status;


    @Builder
    public DefLoginLog(Long id, LocalDateTime createdTime, Long createdBy, LocalDateTime updatedTime, Long updatedBy,
                       Long employeeId, Long userId, String requestIp, String nickName,
                       String username, String description, String loginDate, String ua, String browser, String browserVersion,
                       String operatingSystem, String location, String status) {
        this.id = id;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
        this.updatedBy = updatedBy;
        this.employeeId = employeeId;
        this.userId = userId;
        this.requestIp = requestIp;
        this.nickName = nickName;
        this.username = username;
        this.description = description;
        this.loginDate = loginDate;
        this.ua = ua;
        this.browser = browser;
        this.browserVersion = browserVersion;
        this.operatingSystem = operatingSystem;
        this.location = location;
        this.status = status;
    }

}
