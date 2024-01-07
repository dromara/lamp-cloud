package top.tangyh.lamp.system.service.application;

import top.tangyh.basic.base.service.SuperCacheService;
import top.tangyh.lamp.system.entity.application.DefApplication;
import top.tangyh.lamp.system.vo.result.application.ApplicationResourceResultVO;
import top.tangyh.lamp.system.vo.result.application.DefApplicationResultVO;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 应用
 * </p>
 *
 * @author zuihou
 * @date 2021-09-15
 */
public interface DefApplicationService extends SuperCacheService<Long, DefApplication> {

    /**
     * id!=null：排除此id，检测name是否重复
     * id==null: 直接检测name是否重复
     *
     * @param id   应用id
     * @param name 应用名
     * @return java.lang.Boolean
     * @author tangyh
     * @date 2021/9/15 6:46 下午
     * @create [2021/9/15 6:46 下午 ] [tangyh] [初始创建]
     */
    Boolean check(Long id, String name);

    /**
     * 查询全部应用资源列表
     *
     * @return java.util.List<top.tangyh.lamp.tenant.vo.result.tenant.ApplicationResourceResultVO>
     * @author tangyh
     * @date 2021/9/27 11:39 下午
     * @create [2021/9/27 11:39 下午 ] [tangyh] [初始创建]
     */
    List<ApplicationResourceResultVO> findApplicationResourceList();

    /**
     * 查询可用的应用资源列表
     *
     * @return java.util.List<top.tangyh.lamp.tenant.vo.result.tenant.ApplicationResourceResultVO>
     * @author tangyh
     * @date 2021/9/27 11:39 下午
     * @create [2021/9/27 11:39 下午 ] [tangyh] [初始创建]
     */
    List<ApplicationResourceResultVO> findAvailableApplicationResourceList();

    /**
     * 查询我的应用
     *
     * @param name 应用名
     * @return
     */
    List<DefApplicationResultVO> findMyApplication(String name);

    /**
     * 查询推荐应用
     *
     * @param name     应用名
     * @return
     */
    List<DefApplicationResultVO> findRecommendApplication(String name);

    /**
     * 查询可用的应用数据权限列表
     *
     * @return java.util.List<top.tangyh.lamp.tenant.vo.result.tenant.ApplicationResourceResultVO>
     * @author tangyh
     * @date 2021/9/27 11:39 下午
     * @create [2021/9/27 11:39 下午 ] [tangyh] [初始创建]
     */
    List<ApplicationResourceResultVO> findAvailableApplicationDataScopeList();

    /**
     * 修改默认应用
     *
     * @param applicationId 应用Id
     * @param userId        用户ID
     * @return
     */
    Boolean updateDefApp(Long applicationId, Long userId);

    /**
     * 查询默认应用
     *
     * @param userId
     * @return
     */
    DefApplication getDefApp(Long userId);

}
