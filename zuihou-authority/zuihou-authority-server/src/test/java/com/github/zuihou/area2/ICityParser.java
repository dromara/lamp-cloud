package com.github.zuihou.area2;

import com.github.zuihou.authority.entity.common.Area;

import java.util.List;


public interface ICityParser {

    String PATH = "/Users/tangyh/githubspace/zuihou-admin-cloud";

    /**
     * *解析得到省市区数据
     *
     * @param url 请求url
     * @return 城市
     */
    List<Area> parseProvinces(String url);
}
