package com.github.zuihou.authority.dao.auth;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuihou.authority.entity.auth.User;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 账号
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据角色id，查询已关联用户
     *
     * @param roleId
     * @param keyword
     * @return
     */
    List<User> findUserByRoleId(@Param("roleId") Long roleId, @Param("keyword") String keyword);

    /**
     * 递增 密码错误次数
     *
     * @param id
     * @return
     */
    int incrPasswordErrorNumById(@Param("id") Long id);

}
