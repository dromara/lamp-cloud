package com.github.zuihou.authority.dto.auth;

import com.github.zuihou.auth.utils.Token;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 登录返回信息
 *
 * @author zuihou
 * @date 2019/06/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "LoginDTO", description = "登录信息")
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;
    @ApiModelProperty(value = "用户信息")
    private UserDTO user;
    @ApiModelProperty(value = "token")
    private Token token;
    @ApiModelProperty(value = "权限列表")
    private List<String> permissionsList;
}
