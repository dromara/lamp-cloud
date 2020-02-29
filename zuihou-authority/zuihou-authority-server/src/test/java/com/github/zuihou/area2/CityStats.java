package com.github.zuihou.area2;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import com.github.zuihou.authority.entity.common.Area;
import com.github.zuihou.model.RemoteData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

public class CityStats {


    private static final String COMMON_URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/";

    //    private static final Charset CHARSET = CharsetUtil.CHARSET_UTF_8;
    private static final Charset CHARSET = CharsetUtil.CHARSET_GBK;

    private CityStats() {
    }

    public static void parseProvince(String url) {

        String htmlStr = HttpUtil.get(url, CHARSET);

        Document document = Jsoup.parse(htmlStr);

        // 获取 class='provincetr' 的元素
        Elements elements = document.getElementsByClass("provincetr");
        List<Area> provinces = new LinkedList<Area>();
        int sort = 1;
        for (Element element : elements) {
            // 获取 elements 下属性是 href 的元素
            Elements links = element.getElementsByAttribute("href");
            for (Element link : links) {
                String provinceName = link.text();
                String href = link.attr("href");
                String provinceCode = href.substring(0, 2);

                StaticLog.info("provinceName: {} , provinceCode: {} .", provinceName, provinceCode);

                Area provinceArea = Area.builder().code(provinceCode).label(provinceName).source(url)
                        .sortValue(sort++).fullName(provinceName).level(new RemoteData<>("PROVINCE"))
                        .build();

                StaticLog.info("省级数据:  {}  ", provinceArea);

                parseCity(COMMON_URL + href, provinceArea);
                provinces.add(provinceArea);
            }
        }
        StaticLog.info(JSONUtil.toJsonPrettyStr(provinces));
    }

    public static void parseCity(String url, Area provinceArea) {
        String htmlStr = HttpUtil.get(url, CHARSET);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("citytr");
        List<Area> cities = new LinkedList<Area>();
        int sort = 1;
        for (Element tr : trs) {
            Elements links = tr.getElementsByTag("a");
            String href = links.get(0).attr("href");
            String cityCode = links.get(0).text().substring(0, 4);
            String cityName = links.get(1).text();

            Area cityArea = Area.builder().label(cityName).code(cityCode).source(url)
                    .sortValue(sort++).level(new RemoteData<>("CITY")).fullName(provinceArea.getFullName() + cityName)
                    .build();

            StaticLog.info("	市级数据:  {}  ", cityArea);

            parseCounty(COMMON_URL + href, cityArea);
            cities.add(cityArea);
        }
        provinceArea.setChildren(cities);
    }

    public static void parseCounty(String url, Area cityArea) {
        String htmlStr = HttpUtil.get(url, CHARSET);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("countytr");
        List<Area> counties = new LinkedList<Area>();
        int sort = 1;
        for (Element tr : trs) {
            Elements links = tr.getElementsByTag("a");
            if (links == null || links.size() != 2) {
                continue;
            }
            String href = links.get(0).attr("href");
            String countyCode = links.get(0).text().substring(0, 6);
            String countyName = links.get(1).text();

            Area countyArea = Area.builder().label(countyName).code(countyCode).source(url)
                    .sortValue(sort++).level(new RemoteData<>("COUNTY")).fullName(cityArea.getFullName() + countyName)
                    .build();

            StaticLog.info("		县级数据:  {}  ", countyArea);

            parseTowntr(COMMON_URL + href.subSequence(2, 5).toString() + "/" + href, countyArea);
            counties.add(cityArea);
        }
        cityArea.setChildren(counties);
    }

    public static void parseTowntr(String url, Area countyArea) {
        String htmlStr = HttpUtil.get(url, CHARSET);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("towntr");

        List<Area> counties = new LinkedList<Area>();
        int sort = 1;
        for (Element tr : trs) {
            Elements links = tr.getElementsByTag("a");
            if (links == null || links.size() != 2) {
                continue;
            }
            String href = links.get(0).attr("href");
            String towntrCode = links.get(0).text().substring(0, 9);
            String towntrName = links.get(1).text();

            Area towntrArea = Area.builder().label(towntrName).code(towntrCode).source(url)
                    .sortValue(sort++).level(new RemoteData<>("TOWNTR")).fullName(countyArea.getFullName() + towntrName)
                    .build();

            StaticLog.info("		乡镇级数据:  {}  ", towntrArea);

            parseVillagetr(COMMON_URL + href.subSequence(2, 5).toString() + "/" + href.substring(5, 7) + "/" + href,
                    countyArea);

            counties.add(towntrArea);
        }
        countyArea.setChildren(counties);
    }

    public static void parseVillagetr(String url, Area countyArea) {
        String htmlStr = HttpUtil.get(url, CHARSET);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("villagetr");

        List<Area> counties = new LinkedList<Area>();
        int sort = 1;
        for (Element tr : trs) {
            Elements tds = tr.getElementsByTag("td");
            if (tds == null || tds.size() != 3) {
                continue;
            }
            String villagetrCode = tds.get(0).text();
            String villagetrName = tds.get(2).text();

            Area villagetrArea = Area.builder().code(villagetrCode).label(villagetrName).source(url)
                    .sortValue(sort++).level(new RemoteData<>("VILLAGETR")).fullName(countyArea.getFullName() + villagetrName)
                    .build();
            StaticLog.info("		村级数据:  {}  ", villagetrArea);

            counties.add(villagetrArea);

        }
        countyArea.setChildren(counties);
    }

    public static void main(String[] args) {
        /**
         * # 查看省份数据 http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html
         *
         * # 查看 内蒙古 市级数据 http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/15.html
         *
         * # 查看 内蒙古 区级数据
         * http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/15/1509.html
         *
         * # 查看 内蒙古 街道级数据
         * http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/15/09/150902.html
         *
         * # 查看 内蒙古 社区居委会级数据
         * http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/15/09/02/150902003.html
         *
         * *我们发现这个是有规律的，15是内蒙古的区划代码，而1509是乌兰察布市的区划代码，
         * *前面的http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/这一大串都是一样的，我们就叫commonUrl。
         * *规律就是：
         *
         * # 获取省的数据 commonUrl + index.html
         *
         * # 获取市级数据 commonUrl + 对应省级区划代码.html
         *
         * # 获取县区级数据 commonUrl + 对应省级区划代码 + / + 对应市级区划代码.html
         */

//        String provinceUrl = COMMON_URL + "index.html";
//        CityStats.parseProvince(provinceUrl);

//        String cityUrl = COMMON_URL + "15.html";
//        CityStats.parseCity(cityUrl, new Area());

        String countyUrl = COMMON_URL + "42/4201.html";
        CityStats.parseCounty(countyUrl, new Area());

//        String towntrUrl = COMMON_URL + "15/09/150981.html";
//        CityStats.parseTowntr(towntrUrl, new Area());
    }
}
