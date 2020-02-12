//package com.github.zuihou.utils;
//
//import java.util.function.Function;
//
//
///**
// * 数字类型 帮助类
// * <p>
// * 请使用 Convert.toXxx()
// *
// * @author zuihou
// * @date 2018/11/20
// */
//@Deprecated
//public class NumberHelper {
//
//    private static <T, R> R valueOfDef(T t, Function<T, R> function, R def) {
//        try {
//            return function.apply(t);
//        } catch (Exception e) {
//            return def;
//        }
//    }
//
//    public static Long longValueOfNil(String value) {
//        return valueOfDef(value, (val) -> Long.valueOf(val), null);
//    }
//
//    public static Long longValueOf0(String value) {
//        return valueOfDef(value, (val) -> Long.valueOf(val), 0L);
//    }
//
//    public static Long longValueOfNil(Object value) {
//        return valueOfDef(value, (val) -> Long.valueOf(val.toString()), null);
//    }
//
//    public static Long longValueOf0(Object value) {
//        return valueOfDef(value, (val) -> Long.valueOf(val.toString()), 0L);
//    }
//
//    public static Boolean boolValueOf0(Object value) {
//        return valueOfDef(value, (val) -> Boolean.valueOf(val.toString()), false);
//    }
//
//    public static Integer intValueOfNil(String value) {
//        return valueOfDef(value, (val) -> Integer.valueOf(val), null);
//    }
//
//    public static Integer intValueOf0(String value) {
//        return intValueOf(value, 0);
//    }
//
//    public static Integer intValueOf(String value, Integer def) {
//        return valueOfDef(value, (val) -> Integer.valueOf(val), def);
//    }
//
//    public static Integer intValueOfNil(Object value) {
//        return valueOfDef(value, (val) -> Integer.valueOf(val.toString()), null);
//    }
//
//    public static Integer intValueOf0(Object value) {
//        return valueOfDef(value, (val) -> Integer.valueOf(val.toString()), 0);
//    }
//
//    public static Integer getOrDef(Integer val, Integer def) {
//        return val == null ? def : val;
//    }
//
//    public static Long getOrDef(Long val, Long def) {
//        return val == null ? def : val;
//    }
//
//    public static Boolean getOrDef(Boolean val, Boolean def) {
//        return val == null ? def : val;
//    }
//
//    /**
//     * 判断object是否为基本类型
//     *
//     * @param object
//     * @return
//     */
//    public static boolean isBaseType(Object object) {
//        if (object == null) {
//            return false;
//        }
//        Class className = object.getClass();
//        if (className.equals(java.lang.Integer.class) ||
//                className.equals(java.lang.Byte.class) ||
//                className.equals(java.lang.Long.class) ||
//                className.equals(java.lang.Double.class) ||
//                className.equals(java.lang.Float.class) ||
//                className.equals(java.lang.Character.class) ||
//                className.equals(java.lang.Short.class) ||
//                className.equals(java.lang.Boolean.class) ||
//                className.equals(java.lang.String.class)
//        ) {
//            return true;
//        }
//        return false;
//    }
//}
//
//
