package com.github.zuihou.commons.utils;


import com.github.zuihou.commons.context.CommonConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *   2017/9/10.
 * @author zuihou
 */
public class StringHelper {
    private StringHelper(){
        super();
    }
    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    private static String[] splitWorker(final String str, final String separatorChars, final int max, final boolean preserveAllTokens) {
        // Performance tuned for 2.0 (JDK1.4)
        // Direct code is quicker than StringTokenizer.
        // Also, StringTokenizer uses isSpace() not isWhitespace()
        if (str == null) {
            return new String[0];
        }
        final int len = str.length();
        if (len == 0) {
            return new String[0];
        }
        final List<String> list = new ArrayList<String>();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            // Optimise 1 character case
            final char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 拆分字符串
     * eg:
     * StringHelper.split(null, *)         = null
     * StringHelper.split("", *)           = []
     * StringHelper.split("a.b.c", '.')    = ["a", "b", "c"]
     * StringHelper.split("a..b.c", '.')   = ["a", "b", "c"]
     * StringHelper.split("a:b:c", '.')    = ["a:b:c"]
     * StringHelper.split("a b c", ' ')    = ["a", "b", "c"]
     * @param str
     * @param separatorChars
     * @return
     * @see org.apache.commons.lang3.StringUtils#split
     */
    public static String[] split(final String str, final String separatorChars) {
        return splitWorker(str, separatorChars, -1, false);
    }


    /**
     * 字符串[values]按照指定分隔符[split]进行拆分，解析成Long集合
     *
     * eg:
     * values=1,2,3  split=,
     * return=[1,2,3]
     * eg:
     * values=,,,1,,2,,3,,  split=,
     * return=[1,2,3]
     * @param values 按照指定分隔符拼接的字符串
     * @param split 分隔符
     * @return
     */
    public static List<Long> strToList(String values, String split) {
        if (values == null || values.isEmpty()) {
            return new ArrayList<>();
        }
        if (split == null || split.isEmpty()) {
            split = CommonConstants.ROOT_PATH_DEF;
        }
        try {
            return Arrays.stream(split(values, split)).map((v) -> Long.valueOf(v)).collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
