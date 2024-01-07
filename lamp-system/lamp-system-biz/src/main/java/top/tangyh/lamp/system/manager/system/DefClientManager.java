package top.tangyh.lamp.system.manager.system;

import top.tangyh.basic.base.manager.SuperCacheManager;
import top.tangyh.lamp.system.entity.system.DefClient;

/**
 * <p>
 * 通用业务接口
 * 客户端
 * </p>
 *
 * @author zuihou
 * @date 2021-10-13
 */
public interface DefClientManager extends SuperCacheManager<DefClient> {
    /**
     * 根据 客户端id 和 客户端秘钥查询应用
     *
     * @param clientId
     * @param clientSecret
     * @return
     */
    DefClient getClient(String clientId, String clientSecret);
}
