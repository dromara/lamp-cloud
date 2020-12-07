package com.tangyh.lamp.authority.service.auth;

import com.tangyh.lamp.authority.entity.auth.Application;
import com.tangyh.basic.base.service.SuperCacheService;

/**
 * <p>
 * 业务接口
 * 应用
 * </p>
 *
 * @author zuihou
 * @date 2019-12-15
 */
public interface ApplicationService extends SuperCacheService<Application> {
    /**
     * 根据 clientId 和 clientSecret 查询
     *
     * @param clientId 客户端id
     * @param clientSecret 客户端密钥
     * @return 应用
     */
    Application getByClient(String clientId, String clientSecret);
}
