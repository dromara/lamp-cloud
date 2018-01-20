package com.github.zuihou.commons.context;

import com.github.zuihou.commons.utils.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zuihou
 * @createTime 2017-12-13 16:52
 */
public class BaseContextHandler {
    private static final Logger log = LoggerFactory.getLogger(BaseContextHandler.class);
    private static final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    private BaseContextHandler() {
        super();
    }

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return map.get(key);
    }


    public static Long getAdminId() {
        Object value = get(CommonConstants.CONTEXT_KEY_ADMIN_ID);
        if (value != null && !"".equals(value)) {
            try {
                return Long.valueOf(value.toString());
            } catch (NumberFormatException e) {
                log.error("get adminId error:", e);
                return -1L;
            }
        }
        return -1L;
    }

    public static String getUserName() {
        Object value = get(CommonConstants.CONTEXT_KEY_USERNAME);
        return returnObjectValue(value);
    }

    public static String getName() {
        Object value = get(CommonConstants.CONTEXT_KEY_NAME);
        return StringHelper.getObjectValue(value);
    }

    public static String getAppId() {
        Object value = get(CommonConstants.CONTEXT_KEY_APP_ID);
        return StringHelper.getObjectValue(value);
    }

    public static String getToken() {
        Object value = get(CommonConstants.CONTEXT_KEY_APP_TOKEN);
        return StringHelper.getObjectValue(value);
    }

    public static void setToken(String token) {
        set(CommonConstants.CONTEXT_KEY_APP_TOKEN, token);
    }

    public static void setName(String name) {
        set(CommonConstants.CONTEXT_KEY_NAME, name);
    }

    public static void setAdminId(Long adminId) {
        set(CommonConstants.CONTEXT_KEY_ADMIN_ID, adminId);
    }

    public static void setUserName(String userName) {
        set(CommonConstants.CONTEXT_KEY_USERNAME, userName);
    }

    public static void setAppId(String appId) {
        set(CommonConstants.CONTEXT_KEY_APP_ID, appId);
    }

    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
