package com.github.zuihou.authority.service.auth;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.authority.entity.auth.User;

/**
 * <p>
 * 业务接口
 * 账号
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户id 查询数据范围
     *
     * @param userId 用户id
     * @return
     */
    Map<String, Object> getDataScopeById(Long userId);

    /**
     * 根据角色id 和 账号或名称 查询角色关联的用户
     * <p>
     * 注意，该接口只返回 id，账号，姓名，手机，性别
     *
     * @param roleId  角色id
     * @param keyword 账号或名称
     * @return
     */
    List<User> findUserByRoleId(Long roleId, String keyword);
}
