package com.tangyh.lamp.area2;

import com.tangyh.lamp.authority.entity.common.Area;

import java.util.List;


public interface ICityParser {

    /**
     * 解析得到省市区数据
     *
     * @param url 请求url
     * @return 城市
     */
    List<Area> parseProvinces(String url);
}
