package com.github.zuihou.area2;

import cn.hutool.log.StaticLog;
import com.github.zuihou.authority.entity.common.Area;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 查询地区的经纬度
 *
 * @author zuihou
 * @date 2020年05月08日15:09:39
 */
@Component
public class LocationCityParserDecorator {

    public List<Area> parseProvinces(List<Area> list) {

        //有好方法的朋友可以提交PR
        StaticLog.info("查询出经纬度了. . . ");
        return list;
    }

}
