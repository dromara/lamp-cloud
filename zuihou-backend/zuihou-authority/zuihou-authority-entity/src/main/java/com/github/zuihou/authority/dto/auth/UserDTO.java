package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;

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

    @ApiModelProperty(value = "ID")
    private Long id;

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

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记")
    private Boolean isDelete;

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

}
