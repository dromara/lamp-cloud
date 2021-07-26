package top.tangyh.lamp.area2;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.log.StaticLog;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.lamp.authority.entity.common.Area;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;

import javax.annotation.Resource;
import java.util.List;

//@SpringBootTest
//@ExtendWith(SpringExtension.class)
@Slf4j
public class CityParserTest {
    private static final String URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html";
    @Resource
    ICityParser cityParser;
    @Resource
    SqlCityParserDecorator sqlCityParserDecorator;

    @BeforeEach
    public void setTenant() {
        ContextUtil.setTenant("0000");
    }

    //    @Test
    public void test() {
    }

    /**
     * 实时爬取最新的地区数据，请执行该方法
     */
    //    @Test
    public void init() {
        TimeInterval timer = DateUtil.timer();
        // -------这是执行过程--------------
        cityParserDecorator();
        // ---------------------------------
        long interval = timer.interval();// 花费毫秒数
        long intervalMinute = timer.intervalMinute();// 花费分钟数
        StaticLog.info("本次程序执行 花费毫秒数: {} ,   花费分钟数:{} . ", interval, intervalMinute);
    }

    private List<Area> cityParserDecorator() {
        /*
        获取统计局数据, 注意：目前只获取了 省市区 3级数据， 若要获取乡镇（4级）和村庄数据（5级），请将 CityParser 类的122行代码打开. （5级数据量比较多，非常非常非常非常耗时）
        CityParser 122行： countyArea.setChildren(parseTowntr(fullName + countyName, COMMON_URL + href.subSequence(2, 5).toString() + "/" + href));
        */
        List<Area> list = cityParser.parseProvinces(URL);

        // 持久化
        sqlCityParserDecorator.parseProvinces(list);
        return list;
    }

}
