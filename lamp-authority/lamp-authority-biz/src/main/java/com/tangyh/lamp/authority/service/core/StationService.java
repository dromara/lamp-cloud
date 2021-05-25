package com.tangyh.lamp.authority.service.core;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tangyh.basic.base.request.PageParams;
import com.tangyh.basic.base.service.SuperCacheService;
import com.tangyh.basic.model.LoadService;
import com.tangyh.lamp.authority.dto.core.StationPageQuery;
import com.tangyh.lamp.authority.entity.core.Station;

/**
 * <p>
 * 业务接口
 * 岗位
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
public interface StationService extends SuperCacheService<Station>, LoadService {
    /**
     * 按权限查询岗位的分页信息
     *
     * @param page   分页对象
     * @param params 分页参数
     * @return 分页数据
     */
    IPage<Station> findStationPage(IPage<Station> page, PageParams<StationPageQuery> params);

    /**
     * 检测名称是否存在
     *
     * @param id   id
     * @param name name
     * @return boolean
     * @author zuihou
     * @date 2021/5/23 9:37 下午
     * @create [2021/5/23 9:37 下午 ] [tangyh] [初始创建]
     */
    boolean check(Long id, String name);
}
