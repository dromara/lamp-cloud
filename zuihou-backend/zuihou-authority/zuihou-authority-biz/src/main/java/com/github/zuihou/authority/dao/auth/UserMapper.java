package com.github.zuihou.authority.dao.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuihou.authority.entity.auth.User;

import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 账号
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
