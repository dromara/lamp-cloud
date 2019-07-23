package com.github.zuihou.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

import lombok.extern.slf4j.Slf4j;

/**
 * 字符串帮助类
 *
 * @author zuihou
 * @date 2019-07-16 22:09
 */
@Slf4j
public class StringHelper {
    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String encode(String value) {
        try {
            return URLEncoder.encode(value, "utf-8");
        } catch (Exception e) {
            return "";
        }
    }

    public static String decode(String value) {
        try {
            return URLDecoder.decode(value, "utf-8");
        } catch (Exception e) {
            return "";
        }
    }

    public static String getOrDef(String val, String def) {
        BizAssert.assertNotEmpty(val);
        return val == null ? def : val;
    }
}
