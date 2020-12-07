package com.tangyh.lamp.authority.dto.auth;

/**
 * 用户角色DTO
 *
 * @author zuihou
 * @date 2019/07/28
 */

import com.tangyh.lamp.authority.entity.auth.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserRoleDTO", description = "用户角色DTO")
public class UserRoleDTO implements Serializable {
    @ApiModelProperty(value = "用户id")
    private List<Long> idList;
    @ApiModelProperty(value = "用户信息")
    private List<User> userList;
}
