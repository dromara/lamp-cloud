package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;

import com.github.zuihou.auth.utils.Token;
import com.github.zuihou.authority.entity.auth.User;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

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
    private User user;
    private Token token;
}
