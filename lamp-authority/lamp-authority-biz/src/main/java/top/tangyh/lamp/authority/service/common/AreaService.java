package top.tangyh.lamp.authority.service.common;

import top.tangyh.lamp.authority.entity.common.Area;
import top.tangyh.basic.base.service.SuperCacheService;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 地区表
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
public interface AreaService extends SuperCacheService<Area> {

    /**
     * 递归删除
     *
     * @param ids 地区id
     * @return 是否成功
     */
    boolean recursively(List<Long> ids);
}
