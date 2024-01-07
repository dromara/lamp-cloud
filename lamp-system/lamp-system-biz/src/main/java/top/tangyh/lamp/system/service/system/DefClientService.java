package top.tangyh.lamp.system.service.system;

import top.tangyh.basic.base.service.SuperCacheService;
import top.tangyh.lamp.system.entity.system.DefClient;

/**
 * <p>
 * 业务接口
 * 客户端
 * </p>
 *
 * @author zuihou
 * @date 2021-10-13
 */
public interface DefClientService extends SuperCacheService<Long, DefClient> {

    /**
     * 根据 客户端id 和 客户端秘钥查询应用
     *
     * @param clientId
     * @param clientSecret
     * @return
     */
    DefClient getClient(String clientId, String clientSecret);
}
