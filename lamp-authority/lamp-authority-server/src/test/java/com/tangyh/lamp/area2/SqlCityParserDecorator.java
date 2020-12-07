package com.tangyh.lamp.area2;


import cn.hutool.core.collection.CollUtil;
import com.tangyh.lamp.authority.entity.common.Area;
import com.tangyh.lamp.authority.service.common.AreaService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * sql打印装饰器
 */
@Component
public class SqlCityParserDecorator {

    private AreaService areaService;

    public SqlCityParserDecorator(AreaService areaService) {
        this.areaService = areaService;
    }

    public List<Area> parseProvinces(List<Area> provinces) {

        buildSql(provinces);

        return provinces;
    }

    /**
     * *实体转sql数据
     *
     * @param provinces 省市县数据
     */
    private void buildSql(List<Area> provinces) {
        if (CollUtil.isNotEmpty(provinces)) {

            areaService.saveBatch(provinces);

            for (Area province : provinces) {
                buildCitySql(province.getChildren(), province.getId());
            }
        }
    }

    private void buildCitySql(List<Area> cities, Long parentId) {
        if (CollUtil.isNotEmpty(cities)) {

            cities.forEach(item -> item.setParentId(parentId));

            areaService.saveBatch(cities);

            for (Area city : cities) {
                buildCitySql(city.getChildren(), city.getId());
            }
        }
    }

}
