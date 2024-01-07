package top.tangyh.lamp.system.manager.application;

import top.tangyh.basic.base.manager.SuperCacheManager;
import top.tangyh.basic.interfaces.echo.LoadService;
import top.tangyh.lamp.system.entity.application.DefApplication;
import top.tangyh.lamp.system.vo.result.application.DefApplicationResultVO;

import java.util.List;

/**
 * 应用管理
 *
 * @author tangyh
 * @version v1.0
 * @date 2021/9/29 1:26 下午
 * @create [2021/9/29 1:26 下午 ] [tangyh] [初始创建]
 */
public interface DefApplicationManager extends SuperCacheManager<DefApplication>, LoadService {

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
     * @param name 应用名
     * @return
     */
    List<DefApplicationResultVO> findRecommendApplication(String name);

    /**
     * 查询公共应用
     *
     * @return
     */
    List<DefApplication> findGeneral();

}
