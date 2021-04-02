package com.tangyh.lamp.area2;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.StaticLog;
import com.tangyh.lamp.authority.entity.common.Area;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * 将国家统计局的数据封装成list
 *
 * @author zuihou
 * @date 2020年05月08日15:09:15
 */
@Component
public class CityParser implements ICityParser {

    private static final String COMMON_URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/";

    private static final Charset CHARSET = CharsetUtil.CHARSET_GBK;

    public List<Area> parseProvinces(String url) {
        return parseProvince(COMMON_URL + "index.html");
    }

    private List<Area> parseProvince(String url) {

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

                Area provinceArea = Area.builder().code(provinceCode + "0000")
                        .label(provinceName).source(url)
                        .sortValue(sort++)
                        .level("PROVINCE")
                        .fullName(provinceName)
                        .build();
                provinceArea.setChildren(parseCity(provinceName, COMMON_URL + href));

                StaticLog.info("省级数据:  {}  ", provinceArea);

                provinces.add(provinceArea);
            }
        }
        return provinces;
    }

    private List<Area> parseCity(String provinceName, String url) {
        String htmlStr = HttpUtil.get(url, CHARSET);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("citytr");

        List<Area> cities = new LinkedList<Area>();
        int sort = 1;
        for (Element tr : trs) {
            Elements links = tr.getElementsByTag("a");
            String href = links.get(0).attr("href");
            String cityCode = links.get(0).text();
//            String cityCode = links.get(0).text().substring(0, 4);
            String cityName = links.get(1).text();

            Area cityArea = Area.builder()
                    .label(cityName).code(cityCode).source(url).sortValue(sort++)
                    .level("CITY")
                    .fullName(provinceName + cityName)
                    .build();

            StaticLog.info("	市级数据:  {}  ", cityArea);
            cityArea.setChildren(parseCounty(provinceName + cityName, COMMON_URL + href));

            cities.add(cityArea);
        }
        return cities;
    }

    private List<Area> parseCounty(String fullName, String url) {
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
            String countyCode = links.get(0).text();
            String countyName = links.get(1).text();

            Area countyArea = Area.builder().code(countyCode)
                    .label(countyName)
                    .source(url)
                    .fullName(fullName + countyName)
                    .sortValue(sort++)
                    .level("COUNTY")
                    .build();

            StaticLog.info("		县级数据:  {}  ", countyArea);
//            countyArea.setChildren(parseTowntr(fullName + countyName, COMMON_URL + href.subSequence(2, 5).toString() + "/" + href));

            counties.add(countyArea);
        }
        return counties;
    }

    /**
     * 乡镇级数据
     *
     * @param url
     * @return
     */
    public List<Area> parseTowntr(String fullName, String url) {
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
            String towntrCode = links.get(0).text();
            String towntrName = links.get(1).text();

            Area towntrArea = Area.builder()
                    .label(towntrName).code(towntrCode).source(url)
                    .fullName(fullName + towntrName)
                    .level("TOWNTR")
                    .sortValue(sort++)
                    .build();

            StaticLog.info("			乡镇级数据:  {}  ", towntrArea);
            towntrArea.setChildren(parseVillagetr(fullName + towntrName, COMMON_URL + href.subSequence(2, 5).toString() + "/" + href.substring(5, 7) + "/" + href));


            counties.add(towntrArea);
        }
        return counties;
    }

    /**
     * 村庄数据
     *
     * @param url
     * @return
     */
    public List<Area> parseVillagetr(String fullName, String url) {
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

            Area villagetrArea = Area.builder().code(villagetrCode)
                    .label(villagetrName)
                    .fullName(fullName + villagetrName)
                    .sortValue(sort++)
                    .source(url).build();
            StaticLog.info("				村级数据:  {}  ", villagetrArea);

            counties.add(villagetrArea);
        }
        return counties;
    }
}
