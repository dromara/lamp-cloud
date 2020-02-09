package com.github.zuihou.authority.service.core.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.core.StationMapper;
import com.github.zuihou.authority.dto.core.StationPageDTO;
import com.github.zuihou.authority.entity.core.Station;
import com.github.zuihou.authority.service.core.StationService;
import com.github.zuihou.database.mybatis.auth.DataScope;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.injection.annonation.InjectionResult;
import com.github.zuihou.model.RemoteData;
import com.github.zuihou.utils.MapHelper;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    // 启用属性自动注入
    @InjectionResult
    public IPage<Station> findStationPage(IPage page, StationPageDTO data) {
        Station station = BeanUtil.toBean(data, Station.class);

        if (data != null && data.getOrgId() != null) {
            station.setOrg(new RemoteData<>(data.getOrgId()));
        }

        //Wraps.lbQ(station); 这种写法值 不能和  ${ew.customSqlSegment} 一起使用
        LbqWrapper<Station> wrapper = Wraps.lbQ();

        // ${ew.customSqlSegment} 语法一定要手动eq like 等
        wrapper.like(Station::getName, station.getName())
                .like(Station::getDescribe, station.getDescribe())
                .eq(station.getOrg() != null && ObjectUtil.isNotEmpty(station.getOrg().getKey()), Station::getOrg, station.getOrg())
                .eq(Station::getStatus, station.getStatus())
                .geHeader(Station::getCreateTime, data.getStartCreateTime())
                .leFooter(Station::getCreateTime, data.getEndCreateTime())
                .orderByDesc(Station::getCreateTime)
        ;
        wrapper.orderByDesc(Station::getId);
        return baseMapper.findStationPage(page, wrapper, new DataScope());
    }

    /**
     * TODO 这里应该做缓存
     *
     * @param ids
     * @return
     */
    @Override
    public Map<Serializable, Object> findStationByIds(Set<Serializable> ids) {
        LbqWrapper<Station> query = Wraps.<Station>lbQ()
                .in(Station::getId, ids)
                .eq(Station::getStatus, true);
        List<Station> list = super.list(query);

        //key 是 组织id， value 是org 对象
        ImmutableMap<Serializable, Object> typeMap = MapHelper.uniqueIndex(list, Station::getId, (station) -> station);
        return typeMap;
    }

    /**
     * TODO 这里应该做缓存
     *
     * @param ids
     * @return
     */
    @Override
    public Map<Serializable, Object> findStationNameByIds(Set<Serializable> ids) {
        LbqWrapper<Station> query = Wraps.<Station>lbQ()
                .in(Station::getId, ids)
                .eq(Station::getStatus, true);
        List<Station> list = super.list(query);

        //key 是 组织id， value 是org 对象
        ImmutableMap<Serializable, Object> typeMap = MapHelper.uniqueIndex(list, Station::getId, Station::getName);
        return typeMap;
    }

    /**
     * 测试类
     *
     * @param id
     * @return
     */
    @InjectionResult
    public Station getById2(Serializable id) {
        return baseMapper.selectById(id);
    }
}
