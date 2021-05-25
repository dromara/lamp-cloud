package com.tangyh.lamp.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.annotation.echo.Echo;
import com.tangyh.basic.base.entity.Entity;
import com.tangyh.basic.model.EchoVO;
import com.tangyh.lamp.authority.entity.core.Org;
import com.tangyh.lamp.authority.enumeration.auth.Sex;
import com.tangyh.lamp.common.constant.DictionaryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;
import static com.tangyh.lamp.common.constant.EchoConstants.DICTIONARY_ITEM_CLASS;
import static com.tangyh.lamp.common.constant.EchoConstants.FIND_BY_IDS;
import static com.tangyh.lamp.common.constant.EchoConstants.FIND_NAME_BY_IDS;
import static com.tangyh.lamp.common.constant.EchoConstants.ORG_ID_CLASS;
import static com.tangyh.lamp.common.constant.EchoConstants.STATION_ID_CLASS;

/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 * @author zuihou
 * @since 2021-04-01
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_user")
@ApiModel(value = "User", description = "用户")
@AllArgsConstructor
public class User extends Entity<Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Map<String, Object> echoMap = new HashMap<>();
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    @Size(max = 30, message = "账号长度不能超过30")
    @TableField(value = "account", condition = LIKE)
    @Excel(name = "账号")
    private String account;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空")
    @Size(max = 50, message = "姓名长度不能超过50")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "姓名")
    private String name;

    /**
     * 组织
     * #c_org
     *
     * @Echo(api = ORG_ID_CLASS, method = FIND_BY_IDS, beanClass = Org.class)
     */
    @ApiModelProperty(value = "组织")
    @TableField("org_id")
    @Echo(api = ORG_ID_CLASS, method = FIND_BY_IDS, beanClass = Org.class)
    @Excel(name = "组织")
    private Long orgId;

    /**
     * 岗位
     * #c_station
     *
     * @Echo(api = STATION_ID_CLASS, method = FIND_NAME_BY_IDS)
     */
    @ApiModelProperty(value = "岗位")
    @TableField("station_id")
    @Echo(api = STATION_ID_CLASS, method = FIND_NAME_BY_IDS)
    @Excel(name = "岗位")
    private Long stationId;

    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    @NotNull(message = "内置不能为空")
    @TableField("readonly")
    private Boolean readonly;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Size(max = 255, message = "邮箱长度不能超过255")
    @TableField(value = "email", condition = LIKE)
    @Excel(name = "邮箱")
    private String email;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Size(max = 20, message = "手机长度不能超过20")
    @TableField(value = "mobile", condition = LIKE)
    @Excel(name = "手机")
    private String mobile;

    /**
     * 性别
     * #Sex{W:女;M:男;N:未知}
     */
    @ApiModelProperty(value = "性别")
    @TableField("sex")
    @Excel(name = "性别", replace = {"女_W", "男_M", "未知_N", "_null"})
    private Sex sex;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    @Excel(name = "状态", replace = {"启用_true", "禁用_false", "_null"})
    private Boolean state;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @Size(max = 255, message = "头像长度不能超过255")
    @TableField(value = "avatar", condition = LIKE)
    @Excel(name = "头像")
    private String avatar;

    /**
     * 民族
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.NATION)
     */
    @ApiModelProperty(value = "民族")
    @Size(max = 2, message = "民族长度不能超过2")
    @TableField(value = "nation", condition = LIKE)
    @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.NATION)
    @Excel(name = "民族")
    private String nation;

    /**
     * 学历
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.EDUCATION)
     */
    @ApiModelProperty(value = "学历")
    @Size(max = 2, message = "学历长度不能超过2")
    @TableField(value = "education", condition = LIKE)
    @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.EDUCATION)
    @Excel(name = "学历")
    private String education;

    /**
     * 职位状态
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.POSITION_STATUS)
     */
    @ApiModelProperty(value = "职位状态")
    @Size(max = 2, message = "职位状态长度不能超过2")
    @TableField(value = "position_status", condition = LIKE)
    @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.POSITION_STATUS)
    @Excel(name = "职位状态")
    private String positionStatus;

    /**
     * 工作描述
     */
    @ApiModelProperty(value = "工作描述")
    @Size(max = 255, message = "工作描述长度不能超过255")
    @TableField(value = "work_describe", condition = LIKE)
    @Excel(name = "工作描述")
    private String workDescribe;

    /**
     * 最后一次输错密码时间
     */
    @ApiModelProperty(value = "最后一次输错密码时间")
    @TableField("password_error_last_time")
    private LocalDateTime passwordErrorLastTime;

    /**
     * 密码错误次数
     */
    @ApiModelProperty(value = "密码错误次数")
    @TableField("password_error_num")
    private Integer passwordErrorNum;

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
    @Size(max = 64, message = "密码长度不能超过64")
    @TableField(value = "password", condition = LIKE)
    private String password;

    /**
     * 密码盐
     */
    @ApiModelProperty(value = "密码盐")
    @NotEmpty(message = "密码盐不能为空")
    @Size(max = 20, message = "密码盐长度不能超过20")
    @TableField(value = "salt", condition = LIKE)
    private String salt;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;


    @Builder
    public User(Long id, Long createdBy, LocalDateTime createTime, Long updatedBy, LocalDateTime updateTime,
                String account, String name, Long orgId, Long stationId, Boolean readonly,
                String email, String mobile, Sex sex, Boolean state, String avatar, String nation,
                String education, String positionStatus, String workDescribe, LocalDateTime passwordErrorLastTime, Integer passwordErrorNum, LocalDateTime passwordExpireTime,
                String password, String salt, LocalDateTime lastLoginTime) {
        this.id = id;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.updatedBy = updatedBy;
        this.updateTime = updateTime;
        this.account = account;
        this.name = name;
        this.orgId = orgId;
        this.stationId = stationId;
        this.readonly = readonly;
        this.email = email;
        this.mobile = mobile;
        this.sex = sex;
        this.state = state;
        this.avatar = avatar;
        this.nation = nation;
        this.education = education;
        this.positionStatus = positionStatus;
        this.workDescribe = workDescribe;
        this.passwordErrorLastTime = passwordErrorLastTime;
        this.passwordErrorNum = passwordErrorNum;
        this.passwordExpireTime = passwordExpireTime;
        this.password = password;
        this.salt = salt;
        this.lastLoginTime = lastLoginTime;
    }

}
