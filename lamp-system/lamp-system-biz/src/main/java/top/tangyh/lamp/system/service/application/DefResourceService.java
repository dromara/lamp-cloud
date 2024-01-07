package top.tangyh.lamp.system.service.application;

import top.tangyh.basic.base.service.SuperCacheService;
import top.tangyh.lamp.system.entity.application.DefResource;
import top.tangyh.lamp.system.entity.application.DefResourceApi;
import top.tangyh.lamp.system.vo.result.application.DefResourceResultVO;
import top.tangyh.lamp.system.vo.save.application.DefResourceSaveVO;
import top.tangyh.lamp.system.vo.update.application.DefResourceUpdateVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2021-09-13
 */
public interface DefResourceService extends SuperCacheService<Long, DefResource> {
    /**
     * 查找租户拥有的资源
     *
     * @param applicationIdList 应用ID
     * @param resourceTypes     资源类型
     * @return java.util.List<top.tangyh.lamp.system.entity.application.DefResource>
     * @author tangyh
     * @date 2022/10/22 12:13 PM
     * @create [2022/10/22 12:13 PM ] [tangyh] [初始创建]
     */
    List<DefResource> findResourceListByApplicationId(List<Long> applicationIdList, Collection<String> resourceTypes);

    /**
     * 根据资源ID和资源类型 查找资源
     *
     * @param idList 资源ID
     * @param types  资源类型
     * @return java.util.List<top.tangyh.lamp.model.entity.system.SysResource>
     * @author tangyh
     * @date 2022/10/24 8:50 AM
     * @create [2022/10/24 8:50 AM ] [tangyh] [初始创建]
     */
    List<DefResource> findByIdsAndType(Collection<? extends Serializable> idList, Collection<String> types);

    /**
     * 检测资源编码是否可用
     *
     * @param id   资源id
     * @param code 资源编码
     * @return java.lang.Boolean
     * @author tangyh
     * @date 2021/6/5 9:33 上午
     * @create [2021/6/5 9:33 上午 ] [tangyh] [初始创建]
     */
    Boolean check(Long id, String code);

    /**
     * 根据ID删除资源，并清理相关缓存
     *
     * @param ids id
     * @return 是否成功
     */
    boolean removeByIdWithCache(List<Long> ids);

    /**
     * 根据资源ID删除角色资源 关联关系
     *
     * @param resourceIds resourceIds
     * @author tangyh
     * @date 2022/4/12 12:57 PM
     * @create [2022/4/12 12:57 PM ] [tangyh] [初始创建]
     */
    void deleteRoleResourceRelByResourceId(List<Long> resourceIds);

    /**
     * 保存
     *
     * @param resource 资源
     * @return 是否成功
     */
    DefResource saveWithCache(DefResourceSaveVO resource);


    /**
     * 修改资源并淘汰相关缓存
     *
     * @param data 资源数据
     * @return java.lang.Boolean
     * @author tangyh
     * @date 2021/9/14 8:49 下午
     * @create [2021/9/14 8:49 下午 ] [tangyh] [初始创建]
     */
    DefResource updateWithCacheById(DefResourceUpdateVO data);

    /**
     * 将节点「id」 移动到 「parentId」下， 并重新生成「id」下所有tree_path
     *
     * @param id       需要移动的节点ID
     * @param parentId 待移动的节点ID 为空时，表示移动到根节点
     * @author tangyh
     * @date 2022/10/13 5:08 PM
     * @create [2022/10/13 5:08 PM ] [tangyh] [初始创建]
     */
    void moveResource(Long id, Long parentId);

    /**
     * 检测资源 path 是否重复
     *
     * @param id            主键
     * @param applicationId 应用ID
     * @param path          菜单或视图 地址栏地址
     * @return java.lang.Boolean
     * @author tangyh
     * @date 2021/9/18 2:50 下午
     * @create [2021/9/18 2:50 下午 ] [tangyh] [初始创建]
     */
    Boolean checkPath(Long id, Long applicationId, String path);

    /**
     * 检测资源 名称 是否重复
     *
     * @param id            主键
     * @param applicationId 应用ID
     * @param name          菜单或视图 名称
     * @return java.lang.Boolean
     * @author tangyh
     * @date 2021/9/18 2:50 下午
     * @create [2021/9/18 2:50 下午 ] [tangyh] [初始创建]
     */
    Boolean checkName(Long id, Long applicationId, String name);

    /**
     * 根据id查询资源和资源接口
     *
     * @param id id
     * @return top.tangyh.lamp.tenant.vo.result.tenant.DefResourceResultVO
     * @author tangyh
     * @date 2021/9/20 6:47 下午
     * @create [2021/9/20 6:47 下午 ] [tangyh] [初始创建]
     */
    DefResourceResultVO getResourceById(Long id);

    /**
     * 根据 资源id 查询 资源api
     *
     * @param resourceIdList
     * @return
     */
    List<DefResourceApi> findApiByResourceId(List<Long> resourceIdList);


    /**
     * 查找所有可用的资源
     *
     * @return java.util.Map<java.lang.Long, java.util.Collection < java.lang.Long>>
     * @author tangyh
     * @date 2022/10/17 7:29 PM
     * @create [2022/10/17 7:29 PM ] [tangyh] [初始创建]
     */
    Map<Long, Collection<Long>> findResource();

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
    List<DefResourceApi> findResourceApi(List<Long> applicationIdList, Collection<String> resourceTypes);
}
