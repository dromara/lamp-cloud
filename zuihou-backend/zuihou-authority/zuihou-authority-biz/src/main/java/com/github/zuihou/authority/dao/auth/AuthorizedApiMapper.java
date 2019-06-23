package com.github.zuihou.authority.dao.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuihou.authority.entity.auth.AuthorizedApi;

import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * API资源
 * 后端需要授权方可访问的api集合
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Repository
public interface AuthorizedApiMapper extends BaseMapper<AuthorizedApi> {

}
