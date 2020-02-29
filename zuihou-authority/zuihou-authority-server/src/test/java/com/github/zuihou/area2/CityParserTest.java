package com.github.zuihou.area2;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.log.StaticLog;
import com.github.zuihou.authority.entity.common.Area;
import com.github.zuihou.authority.service.common.AreaService;
import com.github.zuihou.context.BaseContextHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class CityParserTest {
    private static final String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html";
    @Resource
    ICityParser cityParser;
    @Resource
    SqlCityParserDecorator sqlCityParserDecorator;
    @Autowired
    private AreaService areaService;

    @Before
    public void setTenant() {
        BaseContextHandler.setTenant("0000");
    }


    @Test
    public void init() {
        TimeInterval timer = DateUtil.timer();
        // -------这是执行过程--------------
        cityParserDecorator();
        // ---------------------------------
        long interval = timer.interval();// 花费毫秒数
        long intervalMinute = timer.intervalMinute();// 花费分钟数
        StaticLog.info("本次程序执行 花费毫秒数: {} ,   花费分钟数:{} . ", interval, intervalMinute);
    }

//    public static void main(String[] args) {
//        TimeInterval timer = DateUtil.timer();
//        // -------这是执行过程--------------
//        cityParserDecorator();
//        // ---------------------------------
//        long interval = timer.interval();// 花费毫秒数
//        long intervalMinute = timer.intervalMinute();// 花费分钟数
//        StaticLog.info("本次程序执行 花费毫秒数: {} ,   花费分钟数:{} . ", interval, intervalMinute);
//    }

    private List<Area> cityParserDecorator() {

        List<Area> list = cityParser.parseProvinces(url);


        sqlCityParserDecorator.parseProvinces(list);

        return list;
    }

}
