package top.tangyh.lamp.authority.dto.auth;

/**
 * 用户角色DTO
 *
 * @author zuihou
 * @date 2019/07/28
 */

import top.tangyh.lamp.authority.entity.auth.User;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description="用户角色DTO")
public class UserRoleDTO implements Serializable {
    @Schema(description="用户id")
    private List<Long> idList;
    @Schema(description="用户信息")
    private List<User> userList;
}
