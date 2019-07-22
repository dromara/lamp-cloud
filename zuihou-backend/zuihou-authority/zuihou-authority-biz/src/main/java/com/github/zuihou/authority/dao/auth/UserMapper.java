package com.github.zuihou.authority.dao.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zuihou.authority.dto.auth.UserPageDTO;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.database.mybatis.auth.DataScope;

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
     * 分页查询用户信息（含角色）
     *
     * @param page      分页
     * @param userDTO   查询参数
     * @param dataScope
     * @return list
     */
    IPage<User> findUserPage(Page page, @Param("query") UserPageDTO userDTO, DataScope dataScope);

}
