package com.github.zuihou.user.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户查询对象
 *
 * @author zuihou
 * @date 2019-07-10 11:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserQuery {
    /**
     * 是否查询SysUser对象所有信息，true则通过rpc接口查询
     */
    private Boolean full;

    /**
     * 是否只查询角色信息，true则通过rpc接口查询
     */
    private Boolean roles;

    /**
     * 是否只查询组织信息，true则通过rpc接口查询
     */
    private Boolean org;

    /**
     * 是否只查询岗位信息，true则通过rpc接口查询
     */
    private Boolean station;
}
