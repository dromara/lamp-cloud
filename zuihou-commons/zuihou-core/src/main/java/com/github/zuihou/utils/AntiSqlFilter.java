/*
 * Copyright (c) 2018-2022 Caratacus, (caratacus@qq.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.github.zuihou.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;


/**
 * AntiSQLFilter is a J2EE Web Application Filter that protects web components from SQL Injection hacker attacks.<br>
 * Must to be configured with web.xml descriptors.
 * <br><br>
 * Below, the filter initialization parameters to configure:
 * <br><br>
 * <b>logging</b> - a <i>true</i> value enables output to Servlet Context logging in case of a SQL Injection detection.
 * Defaults to <i>false</i>.
 * <br><br>
 * <b>behavior</b> - there are three possible behaviors in case of a SQL Injection detection:
 * <li> protect : (default) dangerous SQL keywords are 2nd character supressed /
 * dangerous SQL delimitters are blank space replaced.
 * Afterwards the request flows as expected.
 * <li> throw: a ServletException is thrown - breaking the request flow.
 * <li> forward: thre request is forwarded to a specific resource.
 * <br><br>
 * <b>forwardTo</b> - the resource to forward when forward behavior is set.<br>
 * <P>
 * http://antisqlfilter.sourceforge.net/
 * </p>
 *
 * @author rbellia
 * @version 0.1
 */
public class AntiSqlFilter {

    private static final String[] keyWords = {";", "\"", "\'", "/*", "*/", "--", "exec",
            "select", "update", "delete", "insert",
            "alter", "drop", "create", "shutdown"};

    public static Map<String, String[]> getSafeParameterMap(Map<String, String[]> parameterMap) {
        Map<String, String[]> map = new HashMap<>();
        Iterator<String> iter = parameterMap.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            String[] oldValues = parameterMap.get(key);
            map.put(key, getSafeValues(oldValues));
        }
        return map;
    }

    public static String[] getSafeValues(String[] oldValues) {
        if (ArrayUtils.isNotEmpty(oldValues)) {
            String[] newValues = new String[oldValues.length];
            for (int i = 0; i < oldValues.length; i++) {
                newValues[i] = getSafeValue(oldValues[i]);
            }
            return newValues;
        }
        return null;
    }

    public static String getSafeValue(String oldValue) {
        StringBuilder sb = new StringBuilder(oldValue);
        String lowerCase = oldValue.toLowerCase();
        for (String keyWord : keyWords) {
            int x;
            while ((x = lowerCase.indexOf(keyWord)) >= 0) {
                if (keyWord.length() == 1) {
                    sb.replace(x, x + 1, " ");
                    lowerCase = sb.toString().toLowerCase();
                    continue;
                }
                sb.deleteCharAt(x + 1);
                lowerCase = sb.toString().toLowerCase();
            }
        }
        return sb.toString();
    }

}
