package top.tangyh.lamp.authority.dto.auth;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Schema(description = "登录信息")
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;
    @Schema(description = "用户信息")
    private UserDTO user;
    /**
     * token
     */
    @Schema(description = "token")
    private String token;
    /**
     * 有效时间：单位：秒
     */
    @Schema(description = "有效期")
    private Long expire;

    @Schema(description = "刷新令牌")
    private String refreshToken;

    @Schema(description = "权限列表")
    private List<String> permissionsList;
}
