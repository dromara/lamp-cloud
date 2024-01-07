package top.tangyh.lamp.areatest;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.baidu.fsg.uid.UidGenerator;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.tangyh.lamp.common.constant.DefValConstants;
import top.tangyh.lamp.system.entity.system.DefArea;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static top.tangyh.basic.utils.TreeUtil.getTreePath;
import static top.tangyh.lamp.common.constant.DefValConstants.TREE_PATH_SPLIT;

/**
 * 将国家统计局的数据封装成list
 *
 * @author zuihou
 * @date 2020年05月08日15:09:15
 */
@Component
@Slf4j
public class CityParserImpl implements CityParser {

    //    private static final String COMMON_URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/";
    private static final String COMMON_URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/";
    private static final String PROVINCE = "20";
    private static final String CITY = "30";
    private static final String COUNTY = "40";
    private static final String TOWNTR = "50";
    private static final String VLILAGERT = "60";
    private static final String SOURCE = "10";
    private static final Charset CHARSET = CharsetUtil.CHARSET_GBK;
    //    private static final Charset CHARSET = CharsetUtil.CHARSET_UTF_8;
    @Autowired
    private UidGenerator uidGenerator;


    public List<DefArea> parseProvinces(int level) {
        if (level < 0) {
            return Collections.emptyList();
        }
        return parseProvince(COMMON_URL + "index.html", level);

//        DefArea parent = new DefArea();
//        parent.setTreePath("/162118161972331434/");
//        parent.setId(162118161972331435L);
//        parent.setFullName("市辖区");
//        return parseCounty(parent, COMMON_URL + "50/5001.html", 4);
//        parent.setTreePath("/test/");
//        parent.setId(1213L);
//        parent.setFullName("test");
//        return parseCounty(parent, COMMON_URL + "41/4115.html", 4);

//        parent.setTreePath("/162118144792461765/");
//        parent.setId(162118733202981458L);
//        parent.setFullName("儋州市");
//        return parseTowntr(parent, COMMON_URL + "46/4604.html", 4);
//        parent.setTreePath("/");
//        parent.setId(162483375926411677L);
//        parent.setFullName("新疆维吾尔自治区");
//        parent.setTreePath("/");
//        return parseCity(parent, COMMON_URL + "65.html", 4);
//        parent.setId(162483375926411676L);
//        parent.setTreePath("/");
//        parent.setFullName("宁夏回族自治区");
//        return parseCity(parent, COMMON_URL + "64.html", 4);
//        parent.setId(162118733202981457L);
//        parent.setFullName("青海省");
//        parent.setTreePath("/");
//        return parseCity(parent, COMMON_URL + "64.html", 2);
    }

    private List<DefArea> parseProvince(String url, int level) {

        HttpRequest get = HttpUtil.createGet(url);
        byte[] bytes = get.charset(CHARSET).execute().bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CHARSET, false);
        Document document = Jsoup.parse(htmlStr);

        // 获取 class='provincetr' 的元素
        Elements elements = document.getElementsByClass("provincetr");
        List<DefArea> list = new LinkedList<>();
        int sort = 1;
        for (Element element : elements) {
            // 获取 elements 下属性是 href 的元素
            Elements links = element.getElementsByAttribute("href");
            for (Element link : links) {
                String name = link.text();
                String href = link.attr("href");
                String code = href.substring(0, 2);

                DefArea area = DefArea.builder()
                        .id(uidGenerator.getUid())
                        .code(code + "0000000000").name(name)
                        .source(SOURCE).sortValue(sort++)
                        .level(PROVINCE).fullName(name)
                        .treeGrade(0).treePath(TREE_PATH_SPLIT)
                        .parentId(DefValConstants.PARENT_ID)
                        .build();
                if (level > 0) {
                    area.setChildren(parseCity(area, COMMON_URL + href, level));
                }

                log.debug("省级数据:  {}  ", area);

                list.add(area);
            }
        }
        return list;
    }

    private List<DefArea> parseCity(DefArea parent, String url, int level) {
        String parentName = parent.getFullName();
        Long parentId = parent.getId();
        String parentTreePath = parent.getTreePath();
        HttpRequest get = HttpUtil.createGet(url);
        byte[] bytes = get.charset(CHARSET).execute().bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CHARSET, false);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("citytr");

        List<DefArea> cities = new LinkedList<>();
        int sort = 1;
        for (Element tr : trs) {
            Elements links = tr.getElementsByTag("a");
            String href = links.get(0).attr("href");
            String code = links.get(0).text();
//            String cityCode = links.get(0).text().substring(0, 4);
            String name = links.get(1).text();

            DefArea area = DefArea.builder()
                    .id(uidGenerator.getUid())
                    .code(code).name(name).source(SOURCE).sortValue(sort++)
                    .level(CITY).fullName(parentName + name)
                    .treeGrade(1).treePath(getTreePath(parentTreePath, parentId))
                    .parentId(parentId)
                    .build();
            log.debug("	市级数据:  {}  ", area);
            if (level > 1) {
                area.setChildren(parseCounty(area, COMMON_URL + href, level));
            }
            cities.add(area);
        }
        return cities;
    }


    private List<DefArea> parseCounty(DefArea parent, String url, int level) {
        String parentName = parent.getFullName();
        Long parentId = parent.getId();
        String parentTreePath = parent.getTreePath();
        HttpRequest get = HttpUtil.createGet(url);
        byte[] bytes = get.charset(CHARSET).execute().bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CHARSET, false);

        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("countytr");

        if (trs.isEmpty()) {
            return parseTowntr(parent, url, level, 2);
        }

        List<DefArea> counties = new LinkedList<>();
        int sort = 1;
        for (Element tr : trs) {
            Elements links = tr.getElementsByTag("a");
            if (links == null || links.size() != 2) {
                continue;
            }
            String href = links.get(0).attr("href");
            String code = links.get(0).text();
            String name = links.get(1).text();

            DefArea area = DefArea.builder()
                    .id(uidGenerator.getUid())
                    .code(code).name(name).source(SOURCE).sortValue(sort++)
                    .level(COUNTY).fullName(parentName + name)
                    .treeGrade(2).treePath(getTreePath(parentTreePath, parentId))
                    .parentId(parentId)
                    .build();

            log.debug("		县级数据:  {}  ", area);
            if (level > 2) {
                area.setChildren(parseTowntr(area, COMMON_URL + href.substring(2, 5) + "/" + href, level, 3));
            }
            counties.add(area);
        }
        return counties;
    }

    /**
     * 乡镇级数据
     *
     * @param url
     * @return
     */
    public List<DefArea> parseTowntr(DefArea parent, String url, int level, int treeGrade) {
        String parentName = parent.getFullName();
        Long parentId = parent.getId();
        String parentTreePath = parent.getTreePath();
        HttpRequest get = HttpUtil.createGet(url);
        byte[] bytes = get.charset(CHARSET).execute().bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CHARSET, false);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("towntr");

        List<DefArea> counties = new LinkedList<>();
        int sort = 1;
        for (Element tr : trs) {
            Elements links = tr.getElementsByTag("a");
            if (links == null || links.size() != 2) {
                continue;
            }
            String href = links.get(0).attr("href");
            String code = links.get(0).text();
            String name = links.get(1).text();

            DefArea area = DefArea.builder()
                    .id(uidGenerator.getUid())
                    .code(code).name(name).source(SOURCE).sortValue(sort++)
                    .level(TOWNTR).fullName(parentName + name)
                    .treeGrade(treeGrade).treePath(getTreePath(parentTreePath, parentId))
                    .parentId(parentId)
                    .build();
            log.debug("			乡镇级数据:  {}  ", area);
            if (level > 3) {
                area.setChildren(parseVillagetr(area,
                        COMMON_URL + href.substring(2, 5) + "/" + href.substring(5, 7) + "/" + href, level, treeGrade + 1));
            }

            counties.add(area);
        }
        return counties;
    }

    /**
     * 村庄数据
     *
     * @param url
     * @return
     */
    public List<DefArea> parseVillagetr(DefArea parent, String url, int level, int treeGrade) {
        String parentName = parent.getFullName();
        Long parentId = parent.getId();
        String parentTreePath = parent.getTreePath();
        log.info("url={}", url);
        HttpRequest get = HttpUtil.createGet(url);
        byte[] bytes = get.charset(CHARSET).execute().bodyBytes();
        String htmlStr = HttpUtil.getString(bytes, CHARSET, false);
        Document document = Jsoup.parse(htmlStr);
        Elements trs = document.getElementsByClass("villagetr");

        List<DefArea> counties = new LinkedList<>();
        int sort = 1;
        for (Element tr : trs) {
            Elements tds = tr.getElementsByTag("td");
            if (tds == null || tds.size() != 3) {
                continue;
            }
            String code = tds.get(0).text();
            String divisionCode = tds.get(1).text();
            String name = tds.get(2).text();

            DefArea area = DefArea.builder()
                    .id(uidGenerator.getUid()).divisionCode(divisionCode)
                    .code(code).name(name).source(SOURCE).sortValue(sort++)
                    .level(VLILAGERT).fullName(parentName + name)
                    .treeGrade(treeGrade).treePath(getTreePath(parentTreePath, parentId))
                    .parentId(parentId)
                    .build();

            log.debug("				村级数据:  {}  ", area);

            counties.add(area);
        }
        return counties;
    }
}
