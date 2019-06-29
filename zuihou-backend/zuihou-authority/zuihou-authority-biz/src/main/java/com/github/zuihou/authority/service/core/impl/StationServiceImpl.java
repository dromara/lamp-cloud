package com.github.zuihou.authority.service.core.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.core.StationMapper;
import com.github.zuihou.authority.entity.core.Station;
import com.github.zuihou.authority.service.core.StationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 *
 * </p>
 *
 * @author zuihou
 * @date 2019-06-29
 */
@Slf4j
@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements StationService {

}
