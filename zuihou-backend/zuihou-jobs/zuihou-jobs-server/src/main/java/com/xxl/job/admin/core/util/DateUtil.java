//package com.xxl.job.admin.core.util;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * 时间工具类
// * @author json
// */
//public class DateUtil {
//
//    private final static String PATTERN_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
//
//    /**
//     * 根据日期字符串获取当前日期
//     * @param dateStr
//     * @return
//     */
//    public static Date getDate(String dateStr){
//        //String string = "2018-11-12 18:55:06";
//        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_HH_MM_SS);
//        Date date = null;
//        try {
//            date = sdf.parse(dateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }
//
//    /**
//     * 判断时间大小,大于当前时间返回true,否则false
//     * @param dateStr
//     * @return
//     */
//    public static boolean isMatch(String dateStr){
//        if(getDate(dateStr).compareTo(new Date()) == -1){
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 判断时间大小,大于当前时间返回true,否则false
//     * @param date
//     * @return
//     */
//    public static boolean isMatch(Date date){
//        if(date.compareTo(new Date()) == -1){
//            return false;
//        }
//        return true;
//    }
//}
