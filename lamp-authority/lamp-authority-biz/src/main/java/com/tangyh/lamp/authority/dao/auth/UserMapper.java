package com.tangyh.lamp.authority.dao.auth;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.tangyh.basic.base.mapper.SuperMapper;
import com.tangyh.basic.base.request.PageParams;
import com.tangyh.basic.database.mybatis.auth.DataScope;
import com.tangyh.lamp.authority.dto.auth.GlobalUserPageDTO;
import com.tangyh.lamp.authority.entity.auth.User;
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
     * @param roleId  角色id
     * @param keyword 关键字
     * @return 用户
     */
    List<User> findUserByRoleId(@Param("roleId") Long roleId, @Param("keyword") String keyword);

    /**
     * 递增 密码错误次数
     *
     * @param id 用户id
     * @return 被修改了几行数据
     */
    int incrPasswordErrorNumById(@Param("id") Long id);

    /**
     * 带数据权限的分页查询
     *
     * @param page      分页对象
     * @param wrapper   查询条件
     * @param dataScope 数据权限番位
     * @return 分页用户数据
     */
    IPage<User> findPage(IPage<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper, DataScope dataScope);

    /**
     * 重置 密码错误次数
     *
     * @param id  用户id
     * @param now 当前时间
     * @return 被修改了几行数据
     */
    int resetPassErrorNum(@Param("id") Long id, @Param("now") LocalDateTime now);

    /**
     * 查询具有超级管理员权限的用户
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return 分页数据
     */
    IPage<User> pageByRole(IPage<User> page, @Param("param") PageParams<GlobalUserPageDTO> params);
}
