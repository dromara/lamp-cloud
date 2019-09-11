package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.zuihou.authority.enumeration.auth.Sex;

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

/**
 * 用户
 *
 * @author zuihou
 * @date 2019/09/11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserDTO", description = "用户")
public class UserDTO implements Serializable {

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    @Length(max = 30, message = "账号长度不能超过30")
    @TableField("account")
    private String account;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空")
    @Length(max = 50, message = "姓名长度不能超过50")
    @TableField("name")
    private String name;

    /**
     * 组织ID
     * #c_core_org
     */
    @ApiModelProperty(value = "组织ID")
    @TableField("org_id")
    private Long orgId;

    /**
     * 岗位ID
     * #c_core_station
     */
    @ApiModelProperty(value = "岗位ID")
    @TableField("station_id")
    private Long stationId;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Length(max = 20, message = "手机长度不能超过20")
    @TableField("mobile")
    private String mobile;

    /**
     * 性别
     * #Sex{W:女;M:男}
     */
    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private Sex sex;

    /**
     * 是否可登陆
     */
    @ApiModelProperty(value = "是否可登陆")
    @NotNull(message = "是否可登陆不能为空")
    @TableField("is_can_login")
    private Boolean isCanLogin;

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记")
    @TableField("is_delete")
    private Boolean isDelete;

    /**
     * 照片
     */
    @ApiModelProperty(value = "照片")
    @Length(max = 255, message = "照片长度不能超过255")
    @TableField("photo")
    private String photo;

    /**
     * 工作描述
     * 比如：  市长、管理员、局长等等   用于登陆展示
     */
    @ApiModelProperty(value = "工作描述")
    @Length(max = 255, message = "工作描述长度不能超过255")
    @TableField("work_describe")
    private String workDescribe;

}
