package com.github.zuihou.context;

import java.util.HashMap;
import java.util.Map;

import com.github.zuihou.utils.NumberHelper;
import com.github.zuihou.utils.StringHelper;


/**
 * 获取当前域中的 用户id appid 用户昵称
 * 注意： appid 通过token解析，  用户id 和 用户昵称必须在前端 通过请求头的方法传入。 否则这里无法获取
 *
 * @author zuihou
 * @createTime 2017-12-13 16:52
 */
public class BaseContextHandler {
    public static final ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, String> map = getLocalMap();
        map.put(key, String.valueOf(value));
    }

    private static Map<String, String> getLocalMap() {
        Map<String, String> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return map;
    }

    public static String get(String key) {
        Map<String, String> map = getLocalMap();
        return map.getOrDefault(key, "");
    }

    /**
     * 账号id
     *
     * @return
     */
    public static Long getUserId() {
        Object value = get(BaseContextConstants.JWT_KEY_USER_ID);
        return NumberHelper.longValueOfNil(value);
    }

    /**
     * 账号id
     *
     * @param userId
     */
    public static void setUserId(Long userId) {
        set(BaseContextConstants.JWT_KEY_USER_ID, userId);
    }

    public static void setUserId(String userId) {
        setUserId(NumberHelper.longValueOf0(userId));
    }

    /**
     * 账号表中的name
     *
     * @return
     */
    public static String getAccount() {
        Object value = get(BaseContextConstants.JWT_KEY_ACCOUNT);
        return returnObjectValue(value);
    }

    /**
     * 账号表中的name
     *
     * @param name
     */
    public static void setAccount(String name) {
        set(BaseContextConstants.JWT_KEY_ACCOUNT, name);
    }

    /**
     * 账号类型
     *
     * @return
     */
    public static String getAccountType() {
        Object value = get(BaseContextConstants.JWT_KEY_ACCOUNT_TYPE);
        return returnObjectValue(value);
    }

    /**
     * 账号类型
     *
     * @param accountType
     */
    public static void setAccountType(String accountType) {
        set(BaseContextConstants.JWT_KEY_ACCOUNT_TYPE, accountType);
    }


    /**
     * 登录的账号
     *
     * @return
     */
    public static String getNickName() {
        Object value = get(BaseContextConstants.JWT_KEY_NICK_NAME);
        return returnObjectValue(value);
    }

    /**
     * 登录的账号
     *
     * @param account
     */
    public static void setNickName(String account) {
        set(BaseContextConstants.JWT_KEY_NICK_NAME, account);
    }

    /**
     * 获取用户token
     *
     * @return
     */
    public static String getToken() {
        Object value = get(BaseContextConstants.TOKEN_NAME);
        return StringHelper.getObjectValue(value);
    }

    public static void setToken(String token) {
        set(BaseContextConstants.TOKEN_NAME, token);
    }

    public static String getOrgId() {
        Object value = get(BaseContextConstants.JWT_KEY_ORG_ID);
        return StringHelper.getObjectValue(value);
    }

    public static void setOrgId(String val) {
        set(BaseContextConstants.JWT_KEY_ORG_ID, val);
    }


    public static String getDepartmentId() {
        Object value = get(BaseContextConstants.JWT_KEY_DEPARTMENT_ID);
        return StringHelper.getObjectValue(value);
    }

    public static void setDepartmentId(String val) {
        set(BaseContextConstants.JWT_KEY_DEPARTMENT_ID, val);
    }


    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        if (threadLocal != null) {
            threadLocal.remove();
        }
    }

}
