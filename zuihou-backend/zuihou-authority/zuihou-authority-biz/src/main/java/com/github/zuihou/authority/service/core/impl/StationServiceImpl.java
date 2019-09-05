package com.github.zuihou.authority.service.core.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.core.StationMapper;
import com.github.zuihou.authority.entity.core.Station;
import com.github.zuihou.authority.service.core.StationService;
import com.github.zuihou.database.mybatis.auth.DataScope;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 岗位
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements StationService {


    @Override
    public IPage<Station> findStationPage(Page page, Station station) {
        //Wraps.lbQ(station); 这种写法值 不能和  ${ew.customSqlSegment} 一起使用
        LbqWrapper<Station> wrapper = Wraps.lbQ();

        // ${ew.customSqlSegment} 语法一定要手动eq like 等
        wrapper.like(Station::getName, station.getName())
                .like(Station::getDescribe, station.getDescribe())
                .eq(Station::getOrgId, station.getOrgId())
                .eq(Station::getSortValue, station.getSortValue())
                .eq(Station::getStatus, station.getStatus());
        wrapper.orderByDesc(Station::getCreateTime);
        return baseMapper.findStationPage(page, wrapper, new DataScope());
    }
}
