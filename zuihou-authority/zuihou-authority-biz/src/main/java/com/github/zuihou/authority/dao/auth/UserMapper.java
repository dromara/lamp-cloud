package com.github.zuihou.authority.dao.auth;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.base.mapper.SuperMapper;
import com.github.zuihou.database.mybatis.auth.DataScope;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

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
public interface UserMapper extends SuperMapper<User> {

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

    /**
     * 带数据权限的分页查询
     *
     * @param page
     * @param wrapper
     * @param dataScope
     * @return
     */
    IPage<User> findPage(IPage<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper, DataScope dataScope);

    /**
     * 重置 密码错误次数
     *
     * @param id
     * @return
     */
    int resetPassErrorNum(@Param("id") Long id, @Param("now") LocalDateTime now);

//    /**
//     * 修改用户最后登录时间
//     *
//     * @param id 用户id
//     * @param now 当前时间
//     * @return
//     */
//    int updateLastLoginTime(@Param("id") Long id, @Param("now") LocalDateTime now);

}
