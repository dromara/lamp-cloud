package com.github.zuihou.authority.dao.auth;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuihou.authority.entity.auth.Role;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 角色
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 查询用户拥有的角色
     *
     * @param userId
     * @return
     */
    List<Role> findRoleByUserId(@Param("userId") Long userId);

    /**
     * 根据角色编码查询用户ID
     *
     * @param codes 角色编码
     * @return
     */
    List<Long> findUserIdByCode(@Param("codes") String[] codes);
}
