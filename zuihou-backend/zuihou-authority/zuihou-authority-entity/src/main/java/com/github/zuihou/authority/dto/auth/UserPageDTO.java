package com.github.zuihou.authority.dto.auth;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.zuihou.authority.enumeration.auth.Sex;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * 用户分页DTO
 *
 * @author zuihou
 * @date 2019/07/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserPageDTO", description = "用户分页对象")
public class UserPageDTO {
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
    private Long orgId;
    /**
     * 岗位ID
     * #c_core_station
     */
    @ApiModelProperty(value = "岗位ID")
    private Long stationId;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Length(max = 20, message = "手机长度不能超过20")
    private String mobile;
    /**
     * 性别
     * #Sex{W:女;M:男}
     */
    @ApiModelProperty(value = "性别")
    private Sex sex;
    /**
     * 是否可登陆
     */
    @ApiModelProperty(value = "是否可登陆")
    @NotNull(message = "是否可登陆不能为空")
    private Boolean isCanLogin;
    /**
     * 照片
     */
    @ApiModelProperty(value = "照片")
    @Length(max = 255, message = "照片长度不能超过255")
    private String photo;
    /**
     * 工作描述
     * 比如：  市长、管理员、局长等等   用于登陆展示
     */
    @ApiModelProperty(value = "工作描述")
    @Length(max = 255, message = "工作描述长度不能超过255")
    private String workDescribe;
    /**
     * 登录次数
     * 一直累计，记录了此账号总共登录次数
     */
    @ApiModelProperty(value = "登录次数")
    @NotNull(message = "登录次数不能为空")
    private Integer loginCount;

}
