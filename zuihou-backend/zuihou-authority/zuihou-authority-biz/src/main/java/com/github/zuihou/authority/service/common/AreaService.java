package com.github.zuihou.authority.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.authority.entity.common.Area;

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
public interface AreaService extends IService<Area> {
    /**
     * 从缓存查
     *
     * @param id
     * @return
     */
    Area getByIdWithCache(Long id);

    /**
     * 查询 所有的数据
     *
     * @return
     */
    List<Area> findAll();

    boolean saveWithCache(Area area);

    boolean updateWithCache(Area area);

    boolean removeByIdWithCache(List<Long> ids);
}
