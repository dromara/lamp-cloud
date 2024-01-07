package top.tangyh.lamp.areatest;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.log.StaticLog;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import top.tangyh.lamp.system.entity.system.DefArea;

import jakarta.annotation.Resource;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
public class CityParserTest {

    @Resource
    CityParser cityParser;
    @Resource
    SqlCityParserDecorator sqlCityParserDecorator;


    /**
     * 实时爬取最新的地区数据，请执行该方法
     */
    @Test
    public void pullArea() {

        TimeInterval timer = DateUtil.timer();
        List<DefArea> list = cityParser.parseProvinces(2);
        long interval = timer.interval();// 花费毫秒数
        long intervalMinute = timer.intervalMinute();// 花费分钟数
        StaticLog.error("爬取数据 花费毫秒数: {} ,   花费分钟数:{} . ", interval, intervalMinute);

//        System.out.println(JSONObject.toJSONString(list, true));

        TimeInterval timer2 = DateUtil.timer();
        // 持久化
        sqlCityParserDecorator.batchSave(list);

        // ---------------------------------
        long interval2 = timer2.interval();// 花费毫秒数
        long intervalMinute2 = timer2.intervalMinute();// 花费分钟数
        StaticLog.error("保存数据 花费毫秒数: {} ,   花费分钟数:{} . ", interval2, intervalMinute2);
    }

}
