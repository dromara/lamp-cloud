package com.github.zuihou.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 字符串帮助类
 *
 * @author zuihou
 * @date 2019-07-16 22:09
 */
@Slf4j
@Deprecated
public class StrHelper {
    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 请使用 URLUtil.encode
     * @param value
     * @return
     */
    public static String encode(String value) {
        try {
            return URLEncoder.encode(value, "utf-8");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 请使用 URLUtil.decode
     * @param value
     * @return
     */
    public static String decode(String value) {
        try {
            return URLDecoder.decode(value, "utf-8");
        } catch (Exception e) {
            return "";
        }
    }

    public static String getOrDef(String val, String def) {
        return StrUtil.isEmpty(val) ? def : val;
    }
}
