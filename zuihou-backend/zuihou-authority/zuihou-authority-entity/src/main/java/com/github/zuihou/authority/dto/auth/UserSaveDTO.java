package com.github.zuihou.authority.dto.auth;

import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.enumeration.auth.Sex;
import com.github.zuihou.model.RemoteData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 * @author zuihou
 * @since 2019-11-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserSaveDTO", description = "用户")
public class UserSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
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
     */
    @ApiModelProperty(value = "组织ID")
    private RemoteData<Long, Org> org;
    /**
     * 岗位ID
     * #c_core_station
     */
    @ApiModelProperty(value = "岗位ID")
    private RemoteData<Long, com.github.zuihou.authority.entity.core.Station> station;
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
     * 启用状态 1启用 0禁用
     */
    @ApiModelProperty(value = "启用状态 1启用 0禁用")
    private Boolean status;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @Length(max = 255, message = "头像长度不能超过255")
    private String avatar;
    /**
     * 工作描述
     * 比如：  市长、管理员、局长等等   用于登陆展示
     */
    @ApiModelProperty(value = "工作描述")
    @Length(max = 255, message = "工作描述长度不能超过255")
    private String workDescribe;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    @Length(max = 64, message = "密码长度不能超过64")
    private String password;

}
