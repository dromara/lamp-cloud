package top.tangyh.lamp.model.vo.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户查询对象
 *
 * @author zuihou
 * @date 2019-07-10 11:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@Builder
public class UserQuery {
    private Long userId;
    private Long employeeId;

    /**
     * 是否查询SysUser对象所有信息，true则通过rpc接口查询
     */
    @Builder.Default
    private Boolean full = false;

    /**
     * 是否 查询角色信息，true则通过rpc接口查询
     */
    @Builder.Default
    private Boolean roles = false;

    /**
     * 是否 查询所属的所有组织信息，true则通过rpc接口查询
     */
    @Builder.Default
    private Boolean org = false;
    /**
     * 是否 查询当前所属的组织信息，true则通过rpc接口查询
     */
    @Builder.Default
    private Boolean currentOrg = false;

    /**
     * 是否 查询员工信息，true则通过rpc接口查询
     */
    @Builder.Default
    private Boolean employee = false;

    /**
     * 是否 查询用户信息，true则通过rpc接口查询
     */
    @Builder.Default
    private Boolean user = true;

    /**
     * 是否 查询岗位信息，true则通过rpc接口查询
     */
    @Builder.Default
    private Boolean position = false;

    /**
     * 是否 查询资源信息，true则通过rpc接口查询
     */
    @Builder.Default
    private Boolean resource = false;

    public static UserQuery buildFull(Long userId, Long employeeId) {
        return new UserQuery().setUserId(userId).setEmployeeId(employeeId).setFull(true);
    }

    public static UserQuery buildRoles(Long userId, Long employeeId) {
        return new UserQuery().setUserId(userId).setEmployeeId(employeeId).setRoles(true);
    }

    public static UserQuery buildOrg(Long userId, Long employeeId) {
        return new UserQuery().setUserId(userId).setEmployeeId(employeeId).setOrg(true);
    }

    public static UserQuery buildPosition(Long userId, Long employeeId) {
        return new UserQuery().setUserId(userId).setEmployeeId(employeeId).setPosition(true);
    }

    public static UserQuery buildResource(Long userId, Long employeeId) {
        return new UserQuery().setUserId(userId).setEmployeeId(employeeId).setResource(true);
    }

    public static UserQuery buildEmployee(Long userId, Long employeeId) {
        return new UserQuery().setUserId(userId).setEmployeeId(employeeId).setEmployee(true);
    }

    public static UserQuery buildUser(Long userId, Long employeeId) {
        return new UserQuery().setUserId(userId).setEmployeeId(employeeId).setUser(true);
    }
}
