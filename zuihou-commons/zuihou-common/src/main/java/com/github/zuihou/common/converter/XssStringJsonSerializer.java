//package com.github.zuihou.common.converter;
//
//import java.io.IOException;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.github.zuihou.common.utils.XssUtils;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 基于xss的JsonSerializer
// * 只做序列化，不做反序列化， 数据库存的还是有问题的值， 但返回给前端是解析过的。
// *
// * @author zuihou
// * @date 2019/06/28
// */
//@Slf4j
//public class XssStringJsonSerializer extends JsonSerializer<String> {
//    @Override
//    public Class<String> handledType() {
//        return String.class;
//    }
//
//    @Override
//    public void serialize(String value, JsonGenerator jsonGenerator,
//                          SerializerProvider serializerProvider) throws IOException {
//        if (value != null) {
//            try {
//                /*String encodedValue = HtmlUtils.htmlEscape(value);*/
//                String encodedValue = XssUtils.xssClean(value, null);
//                jsonGenerator.writeString(encodedValue);
//            } catch (Exception e) {
//                log.error("序列化失败:[{}]", value, e);
//            }
//        }
//    }
//
//}
