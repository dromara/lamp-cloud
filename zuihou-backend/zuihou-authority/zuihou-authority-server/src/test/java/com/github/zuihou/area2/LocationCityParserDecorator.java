package com.github.zuihou.area2;

import cn.hutool.log.StaticLog;
import com.github.zuihou.authority.entity.common.Area;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class LocationCityParserDecorator {

    public List<Area> parseProvinces(List<Area> list) {

        StaticLog.info("查询出经纬度了. . . ");
        return list;
    }

}
