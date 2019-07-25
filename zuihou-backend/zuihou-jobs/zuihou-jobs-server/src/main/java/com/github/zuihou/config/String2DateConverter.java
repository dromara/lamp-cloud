//package com.github.zuihou.config;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.Set;
//
//import org.springframework.core.convert.converter.Converter;
//
///**
// * 字符串转Date 类型转换器
// * @author zuihou
// * @date 2019-07-24 17:23
// */
//public class String2DateConverter implements Converter<String, Date> {
//
//    protected static final Map<String, String> FORMATS = new LinkedHashMap(6);
//
//    static {
//        FORMATS.put("yyyy", "^\\d{4}");
//        FORMATS.put("yyyy-MM", "^\\d{4}-\\d{1,2}$");
//        FORMATS.put("yyyy-MM-dd", "^\\d{4}-\\d{1,2}-\\d{1,2}$");
//        FORMATS.put("yyyy-MM-dd HH", "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}");
//        FORMATS.put("yyyy-MM-dd HH:mm", "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$");
//        FORMATS.put("yyyy-MM-dd HH:mm:ss", "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$");
//        FORMATS.put("yyyy/MM", "^\\d{4}/\\d{1,2}$");
//        FORMATS.put("yyyy/MM/dd", "^\\d{4}/\\d{1,2}/\\d{1,2}$");
//        FORMATS.put("yyyy/MM/dd HH", "^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}");
//        FORMATS.put("yyyy/MM/dd HH:mm", "^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}$");
//        FORMATS.put("yyyy/MM/dd HH:mm:ss", "^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$");
//    }
//
//    /**
//     * 格式化日期
//     *
//     * @param dateStr String 字符型日期
//     * @param format  String 格式
//     * @return Date 日期
//     */
//    protected static Date parseDate(String dateStr, String format) {
//        Date date = null;
//        try {
//            DateFormat dateFormat = new SimpleDateFormat(format);
//            //严格模式
//            dateFormat.setLenient(false);
//            date = dateFormat.parse(dateStr);
//        } catch (Exception e) {
//
//        }
//        return date;
//    }
//
//    @Override
//    public Date convert(String source) {
//        if (source == null || source.isEmpty()) {
//            return null;
//        }
//        source = source.trim();
//        Set<Map.Entry<String, String>> entries = FORMATS.entrySet();
//        for (Map.Entry<String, String> entry : entries) {
//            if (source.matches(entry.getValue())) {
//                return parseDate(source, entry.getKey());
//            }
//        }
//        throw new IllegalArgumentException("无效的日期参数格式:'" + source + "'");
//    }
//
//
//}
