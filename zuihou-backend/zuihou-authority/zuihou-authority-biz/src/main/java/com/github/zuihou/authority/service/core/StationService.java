package com.github.zuihou.authority.service.core;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.authority.entity.core.Station;

/**
 * <p>
 * 业务接口
 * 岗位
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
public interface StationService extends IService<Station> {
    /**
     * 按权限查询岗位的分页信息
     *
     * @param page
     * @param station
     * @return
     */
    IPage<Station> findStationPage(Page page, Station station);
}
