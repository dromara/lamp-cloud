package com.github.zuihou.utils;

import java.util.HashMap;
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

    private static final String[] KEY_WORDS = {";", "\"", "\'", "/*", "*/", "--", "exec",
            "select", "update", "delete", "insert",
            "alter", "drop", "create", "shutdown"};

    public static Map<String, String[]> getSafeParameterMap(Map<String, String[]> parameterMap) {
        Map<String, String[]> map = new HashMap<>(parameterMap.size());
        for (String key : parameterMap.keySet()) {
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
        for (String keyWord : KEY_WORDS) {
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
