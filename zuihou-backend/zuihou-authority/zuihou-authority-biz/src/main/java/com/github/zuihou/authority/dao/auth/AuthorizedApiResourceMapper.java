package com.github.zuihou.authority.dao.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuihou.authority.entity.auth.AuthorizedApiResource;

import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 资源API分配
 * 资源中需要请求的api  并且此api会被鉴权，若不需要鉴权的api就不要加入到
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Repository
public interface AuthorizedApiResourceMapper extends BaseMapper<AuthorizedApiResource> {

}
