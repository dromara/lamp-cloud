package com.tangyh.lamp.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.annotation.injection.InjectionField;
import com.tangyh.basic.base.entity.Entity;
import com.tangyh.basic.model.RemoteData;
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
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;
import static com.tangyh.basic.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;
import static com.tangyh.lamp.common.constant.InjectionFieldConstants.DICTIONARY_ITEM_CLASS;
import static com.tangyh.lamp.common.constant.InjectionFieldConstants.DICTIONARY_ITEM_METHOD;
import static com.tangyh.lamp.common.constant.InjectionFieldConstants.ORG_ID_CLASS;
import static com.tangyh.lamp.common.constant.InjectionFieldConstants.ORG_ID_METHOD;
import static com.tangyh.lamp.common.constant.InjectionFieldConstants.STATION_ID_CLASS;
import static com.tangyh.lamp.common.constant.InjectionFieldConstants.STATION_ID_NAME_METHOD;

/**
 * <p>
 * 实体类
 * 用户
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
@TableName("c_user")
@ApiModel(value = "User", description = "用户")
@AllArgsConstructor
public class User extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    @Length(max = 30, message = "账号长度不能超过30")
    @TableField(value = "account", condition = LIKE)
    @Excel(name = "账号")
    private String account;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空")
    @Length(max = 50, message = "姓名长度不能超过50")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "姓名")
    private String name;

    /**
     * 组织ID
     * #c_org
     *
     * @InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = Org.class) RemoteData<Long, com.tangyh.lamp.authority.entity.core.Org>
     */
    @ApiModelProperty(value = "组织ID")
    @TableField("org_id")
    @InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = Org.class)
    @ExcelEntity(name = "")
    @Excel(name = "组织ID")
    private RemoteData<Long, Org> org;

    /**
     * 岗位ID
     * #c_station
     *
     * @InjectionField(api = STATION_ID_CLASS, method = STATION_ID_NAME_METHOD) RemoteData<Long, String>
     */
    @ApiModelProperty(value = "岗位ID")
    @TableField("station_id")
    @InjectionField(api = STATION_ID_CLASS, method = STATION_ID_NAME_METHOD)
    @ExcelEntity(name = "")
    @Excel(name = "岗位ID")
    private RemoteData<Long, String> station;

    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    @NotNull(message = "内置不能为空")
    @TableField("readonly")
    @Excel(name = "内置", replace = {"是_true", "否_false", "_null"})
    private Boolean readonly;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Length(max = 255, message = "邮箱长度不能超过255")
    @TableField(value = "email", condition = LIKE)
    @Excel(name = "邮箱")
    private String email;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Length(max = 20, message = "手机长度不能超过20")
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
    @Excel(name = "状态", replace = {"是_true", "否_false", "_null"})
    private Boolean state;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @Length(max = 255, message = "头像长度不能超过255")
    @TableField(value = "avatar", condition = LIKE)
    @Excel(name = "头像")
    private String avatar;

    /**
     * 民族
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.NATION) RemoteData<String, String>
     */
    @ApiModelProperty(value = "民族")
    @Length(max = 2, message = "民族长度不能超过2")
    @TableField(value = "nation", condition = LIKE)
    @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.NATION)
    @ExcelEntity(name = "")
    @Excel(name = "民族")
    private RemoteData<String, String> nation;

    /**
     * 学历
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.EDUCATION) RemoteData<String, String>
     */
    @ApiModelProperty(value = "学历")
    @Length(max = 2, message = "学历长度不能超过2")
    @TableField(value = "education", condition = LIKE)
    @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.EDUCATION)
    @ExcelEntity(name = "")
    @Excel(name = "学历")
    private RemoteData<String, String> education;

    /**
     * 职位状态
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.POSITION_STATUS) RemoteData<String, String>
     */
    @ApiModelProperty(value = "职位状态")
    @Length(max = 2, message = "职位状态长度不能超过2")
    @TableField(value = "position_status", condition = LIKE)
    @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.POSITION_STATUS)
    @ExcelEntity(name = "")
    @Excel(name = "职位状态")
    private RemoteData<String, String> positionStatus;

    /**
     * 工作描述
     */
    @ApiModelProperty(value = "工作描述")
    @Length(max = 255, message = "工作描述长度不能超过255")
    @TableField(value = "work_describe", condition = LIKE)
    @Excel(name = "工作描述")
    private String workDescribe;

    /**
     * 最后一次输错密码时间
     */
    @ApiModelProperty(value = "最后一次输错密码时间")
    @TableField(value = "password_error_last_time", updateStrategy = FieldStrategy.IGNORED)
    @Excel(name = "最后一次输错密码时间", format = DEFAULT_DATE_TIME_FORMAT, width = 20)
    private LocalDateTime passwordErrorLastTime;

    /**
     * 密码错误次数
     */
    @ApiModelProperty(value = "密码错误次数")
    @TableField("password_error_num")
    @Excel(name = "密码错误次数")
    private Integer passwordErrorNum;

    /**
     * 密码过期时间
     */
    @ApiModelProperty(value = "密码过期时间")
    @TableField("password_expire_time")
    @Excel(name = "密码过期时间", format = DEFAULT_DATE_TIME_FORMAT, width = 20)
    private LocalDateTime passwordExpireTime;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    @Length(max = 64, message = "密码长度不能超过64")
    @TableField(value = "password", condition = LIKE)
    private String password;

    @ApiModelProperty(value = "盐")
    @NotEmpty(message = "盐不能为空")
    @Length(max = 20, message = "盐长度不能超过20")
    @TableField(value = "salt", condition = LIKE)
    @Excel(name = "盐")
    private String salt;


    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    @TableField("last_login_time")
    @Excel(name = "最后登录时间", format = DEFAULT_DATE_TIME_FORMAT, width = 20)
    private LocalDateTime lastLoginTime;


    @Builder
    public User(Long id, Long createdBy, LocalDateTime createTime, Long updatedBy, LocalDateTime updateTime,
                String account, String name, RemoteData<Long, Org> orgId, RemoteData<Long, String> stationId, Boolean readonly,
                String email, String mobile, Sex sex, Boolean state, String avatar, RemoteData<String, String> nation,
                RemoteData<String, String> education, RemoteData<String, String> positionStatus, String workDescribe, LocalDateTime passwordErrorLastTime, Integer passwordErrorNum, LocalDateTime passwordExpireTime,
                String password, String salt, LocalDateTime lastLoginTime) {
        this.id = id;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.updatedBy = updatedBy;
        this.updateTime = updateTime;
        this.account = account;
        this.name = name;
        this.org = orgId;
        this.station = stationId;
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
