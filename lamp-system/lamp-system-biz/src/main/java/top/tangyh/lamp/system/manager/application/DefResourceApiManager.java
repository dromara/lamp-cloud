package top.tangyh.lamp.system.manager.application;

import top.tangyh.basic.base.manager.SuperCacheManager;
import top.tangyh.lamp.system.entity.application.DefResourceApi;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 通用业务层
 * 资源API
 * </p>
 *
 * @author tangyh
 * @version v1.0
 * @date 2021/9/29 1:26 下午
 * @create [2021/9/29 1:26 下午 ] [tangyh] [初始创建]
 */
public interface DefResourceApiManager extends SuperCacheManager<DefResourceApi> {
    /**
     * 根据资源id 删除资源的接口
     *
     * @param resourceIdList 资源id
     * @author tangyh
     * @date 2021/9/17 9:42 下午
     * @create [2021/9/17 9:42 下午 ] [tangyh] [初始创建]
     */
    void removeByResourceId(List<Long> resourceIdList);

    /**
     * 根据资源id查询资源接口
     *
     * @param resourceId 资源id
     * @return java.util.List<top.tangyh.lamp.tenant.vo.result.tenant.DefResourceApiResultVO>
     * @author tangyh
     * @date 2021/9/20 6:49 下午
     * @create [2021/9/20 6:49 下午 ] [tangyh] [初始创建]
     */
    List<DefResourceApi> findByResourceId(Long resourceId);

    /**
     * 根据资源id查询资源 api id
     *
     * @param resourceIdList
     * @return
     */
    List<DefResourceApi> findApiByResourceId(List<Long> resourceIdList);

    /**
     * 查询指定租户下指定应用和指定资源类型的 接口
     *
     * @param applicationIdList 应用ID
     * @param resourceTypes     资源类型
     * @return java.util.List<top.tangyh.lamp.system.entity.application.DefResourceApi>
     * @author tangyh
     * @date 2023/5/19 3:26 PM
     * @create [2023/5/19 3:26 PM ] [tangyh] [初始创建]
     */
    List<DefResourceApi> findResourceApi(List<Long> applicationIdList,
                                         Collection<String> resourceTypes);
}
