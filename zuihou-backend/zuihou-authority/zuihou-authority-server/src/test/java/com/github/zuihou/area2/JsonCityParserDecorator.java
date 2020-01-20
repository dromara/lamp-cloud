//package com.github.zuihou.area2;
//
//
//import java.util.List;
//
//
//import cn.hutool.core.io.FileUtil;
//import cn.hutool.core.io.file.FileWriter;
//import cn.hutool.json.JSONUtil;
//import com.github.zuihou.authority.entity.common.Area;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JsonCityParserDecorator extends CityParserDecorator{
//
//    public JsonCityParserDecorator(ICityParser cityParser) {
//        super(cityParser);
//    }
//
//    @Override
//    public List<Area> parseProvinces(String url) {
//        List<Area> provinces = super.parseProvinces(url);
//        String jsonStr = JSONUtil.toJsonStr(provinces);
//        // json数据写入到文件
//        FileWriter fileWriter = new FileWriter(FileUtil.touch(PATH + "/area.json"));
//        fileWriter.write(jsonStr);
//        return provinces;
//    }
//}
