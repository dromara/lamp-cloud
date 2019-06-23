package com.github.zuihou.authority.service.auth;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.authority.entity.auth.AuthorizedApiResource;

/**
 * <p>
 * 业务接口
 * 资源API分配
 * 资源中需要请求的api  并且此api会被鉴权，若不需要鉴权的api就不要加入到
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
public interface AuthorizedApiResourceService extends IService<AuthorizedApiResource> {

}
