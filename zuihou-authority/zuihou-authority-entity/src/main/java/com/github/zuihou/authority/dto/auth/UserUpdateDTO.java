package com.github.zuihou.authority.dto.auth;

import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.enumeration.auth.Sex;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.injection.annonation.InjectionField;
import com.github.zuihou.model.RemoteData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.github.zuihou.common.constant.InjectionFieldConstants.*;

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
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserUpdateDTO", description = "用户")
public class UserUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    @Length(max = 30, message = "账号长度不能超过30")
    private String account;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空")
    @Length(max = 50, message = "姓名长度不能超过50")
    private String name;
    /**
     * 组织ID
     * #c_core_org
     *
     * @InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = Org.class) RemoteData<Long, com.github.zuihou.authority.entity.core.Org>
     */
    @ApiModelProperty(value = "组织ID")
    @InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = Org.class)
    private RemoteData<Long, Org> org;
    /**
     * 岗位ID
     * #c_core_station
     *
     * @InjectionField(api = STATION_ID_CLASS, method = STATION_ID_NAME_METHOD) RemoteData<Long, String>
     */
    @ApiModelProperty(value = "岗位ID")
    @InjectionField(api = STATION_ID_CLASS, method = STATION_ID_NAME_METHOD)
    private RemoteData<Long, String> station;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Length(max = 255, message = "邮箱长度不能超过255")
    private String email;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Length(max = 20, message = "手机长度不能超过20")
    private String mobile;
    /**
     * 性别
     * #Sex{W:女;M:男;N:未知}
     */
    @ApiModelProperty(value = "性别")
    private Sex sex;
    /**
     * 状态
     * 1启用 0禁用
     */
    @ApiModelProperty(value = "状态")
    private Boolean status;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @Length(max = 255, message = "头像长度不能超过255")
    private String avatar;
    /**
     * 民族
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>
     */
    @ApiModelProperty(value = "民族")
    @Length(max = 20, message = "民族长度不能超过20")
    @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD)
    private RemoteData<String, String> nation;
    /**
     * 学历
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>
     */
    @ApiModelProperty(value = "学历")
    @Length(max = 20, message = "学历长度不能超过20")
    @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD)
    private RemoteData<String, String> education;
    /**
     * 职位状态
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>
     */
    @ApiModelProperty(value = "职位状态")
    @Length(max = 20, message = "职位状态长度不能超过20")
    @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD)
    private RemoteData<String, String> positionStatus;
    /**
     * 工作描述
     * 比如：  市长、管理员、局长等等   用于登陆展示
     */
    @ApiModelProperty(value = "工作描述")
    @Length(max = 255, message = "工作描述长度不能超过255")
    private String workDescribe;
    /**
     * 最后一次输错密码时间
     */
    @ApiModelProperty(value = "最后一次输错密码时间")
    private LocalDateTime passwordErrorLastTime;
    /**
     * 密码错误次数
     */
    @ApiModelProperty(value = "密码错误次数")
    private Integer passwordErrorNum;
    /**
     * 密码过期时间
     */
    @ApiModelProperty(value = "密码过期时间")
    private LocalDateTime passwordExpireTime;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    @Length(max = 64, message = "密码长度不能超过64")
    private String password;
    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;
}
