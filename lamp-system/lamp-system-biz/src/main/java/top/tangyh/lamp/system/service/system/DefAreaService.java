package top.tangyh.lamp.system.service.system;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.tangyh.basic.base.service.SuperService;
import top.tangyh.lamp.system.entity.system.DefArea;
import top.tangyh.lamp.system.vo.query.system.DefAreaPageQuery;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 地区表
 * </p>
 *
 * @author zuihou
 * @date 2021-10-13
 */
public interface DefAreaService extends SuperService<Long, DefArea> {
    /**
     * 查询地区的树结构
     *
     * @param pageQuery 查询条件
     * @return 树结构
     */
    List<DefArea> findTree(DefAreaPageQuery pageQuery);

    /**
     * 检测地区代码是否存在
     *
     * @param code 地区代码
     * @param id   地区id
     * @return 是否存在
     */
    Boolean check(String code, Long id);

    /**
     * 地区
     *
     * @param parentId 父id
     * @return
     */
    List<DefArea> findLazyList(Long parentId);

    /**
     * 下载地区json文件
     *
     * @param treeGrade 层级
     * @param request   请求
     * @param response  响应
     */
    void downloadJson(Integer treeGrade, HttpServletRequest request, HttpServletResponse response);
}
