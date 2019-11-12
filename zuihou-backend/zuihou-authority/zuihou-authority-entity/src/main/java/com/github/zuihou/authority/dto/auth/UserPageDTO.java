package com.github.zuihou.authority.dto.auth;

import java.time.LocalDateTime;

import com.github.zuihou.authority.enumeration.auth.Sex;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

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
    private String account;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
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
    private String mobile;
    /**
     * 性别
     * #Sex{W:女;M:男}
     */
    @ApiModelProperty(value = "性别")
    private Sex sex;
    @ApiModelProperty(value = "启用状态 1启用 0禁用")
    private Boolean status;
    /**
     * 是否可登陆
     */
    @ApiModelProperty(value = "是否可登陆")
    private Boolean isCanLogin;
    /**
     * 照片
     */
    @ApiModelProperty(value = "照片")
    private String photo;
    /**
     * 工作描述
     * 比如：  市长、管理员、局长等等   用于登陆展示
     */
    @ApiModelProperty(value = "工作描述")
    private String workDescribe;
    /**
     * 登录次数
     * 一直累计，记录了此账号总共登录次数
     */
    @ApiModelProperty(value = "登录次数")
    private Integer loginCount;

    @ApiModelProperty(value = "开始创建时间")
    private LocalDateTime startCreateTime;
    @ApiModelProperty(value = "截止创建时间")
    private LocalDateTime endCreateTime;
}
