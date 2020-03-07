package com.github.zuihou.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.enumeration.auth.Sex;
import com.github.zuihou.base.entity.Entity;
import com.github.zuihou.injection.annonation.InjectionField;
import com.github.zuihou.model.RemoteData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;
import static com.github.zuihou.common.constant.InjectionFieldConstants.*;
import static com.github.zuihou.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 * @author zuihou
 * @since 2020-02-14
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_auth_user")
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
     * #c_core_org
     *
     * @InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = Org.class) RemoteData<Long, com.github.zuihou.authority.entity.core.Org>
     */
    @ApiModelProperty(value = "组织ID")
    @TableField("org_id")
    @InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = Org.class)
    @Excel(name = "组织")
    @ExcelEntity(name = "")
    private RemoteData<Long, Org> org;

    /**
     * 岗位ID
     * #c_core_station
     *
     * @InjectionField(api = STATION_ID_CLASS, method = STATION_ID_NAME_METHOD) RemoteData<Long, String>
     */
    @ApiModelProperty(value = "岗位ID")
    @TableField("station_id")
    @InjectionField(api = STATION_ID_CLASS, method = STATION_ID_NAME_METHOD)
    @ExcelEntity(name = "station")
    @Excel(name = "岗位")
    private RemoteData<Long, String> station;

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
    @Excel(name = "性别", replace = {"男_M", "女_W", "未知_N", "_null"})
    private Sex sex;

    /**
     * 状态
     * 1启用 0禁用
     */
    @ApiModelProperty(value = "状态")
    @TableField("status")
    @Excel(name = "状态", replace = {"启用_true", "禁用_false", "_null"})
    private Boolean status;

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
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>
     */
    @ApiModelProperty(value = "民族")
    @Length(max = 20, message = "民族长度不能超过20")
    @TableField(value = "nation", condition = LIKE)
    @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD)
    @Excel(name = "民族")
    @ExcelEntity(name = "")
    private RemoteData<String, String> nation;

    /**
     * 学历
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>
     */
    @ApiModelProperty(value = "学历")
    @Length(max = 20, message = "学历长度不能超过20")
    @TableField(value = "education", condition = LIKE)
    @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD)
    @Excel(name = "学历")
    @ExcelEntity(name = "")
    private RemoteData<String, String> education;

    /**
     * 职位状态
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>
     */
    @ApiModelProperty(value = "职位状态")
    @Length(max = 20, message = "职位状态长度不能超过20")
    @TableField(value = "position_status", condition = LIKE)
    @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD)
    @Excel(name = "职位状态")
    @ExcelEntity(name = "")
    private RemoteData<String, String> positionStatus;

    /**
     * 工作描述
     * 比如：  市长、管理员、局长等等   用于登陆展示
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
    @Length(max = 64, message = "密码长度不能超过64")
    @TableField(value = "password", condition = LIKE)
    private String password;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    @TableField("last_login_time")
    @Excel(name = "最后登录时间", format = DEFAULT_DATE_TIME_FORMAT, width = 20)
    private LocalDateTime lastLoginTime;


    @Builder
    public User(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                String account, String name, RemoteData<Long, Org> orgId, RemoteData<Long, String> stationId, String email,
                String mobile, Sex sex, Boolean status, String avatar, RemoteData<String, String> nation, RemoteData<String, String> education,
                RemoteData<String, String> positionStatus, String workDescribe, LocalDateTime passwordErrorLastTime, Integer passwordErrorNum, LocalDateTime passwordExpireTime, String password, LocalDateTime lastLoginTime) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.account = account;
        this.name = name;
        this.org = orgId;
        this.station = stationId;
        this.email = email;
        this.mobile = mobile;
        this.sex = sex;
        this.status = status;
        this.avatar = avatar;
        this.nation = nation;
        this.education = education;
        this.positionStatus = positionStatus;
        this.workDescribe = workDescribe;
        this.passwordErrorLastTime = passwordErrorLastTime;
        this.passwordErrorNum = passwordErrorNum;
        this.passwordExpireTime = passwordExpireTime;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
    }

}
