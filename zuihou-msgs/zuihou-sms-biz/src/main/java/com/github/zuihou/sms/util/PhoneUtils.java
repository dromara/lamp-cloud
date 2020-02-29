package com.github.zuihou.sms.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号解析工具类
 *
 * @author zuihou
 * @date 2018/12/24
 */
@Slf4j
public class PhoneUtils {
    public final static String REG_EX = "(.*?<(.*?)?>),";
    public final static String REG_EX_SIMPLE = "(.*?<(.*?)?>)";
    public final static String PHONE_SEPARATOR = "<";
    public final static String CONTACTS_SEPARATOR = ",";

    public static Set<String> getPhone(String receiverPhone) {
        return getPhone(receiverPhone, REG_EX);
    }

    /**
     * 通过正则表达式， 解析手机号
     * <p>
     * receiverPhone: 格式1： 152188699970,10086
     * receiverPhone: 格式2： 管理员<10086>,张三<10000>
     *
     * @param receiverPhone
     * @param regEx
     * @return
     */
    public static Set<String> getPhone(String receiverPhone, String regEx) {
        //判断参数类型
        if (!receiverPhone.contains(PHONE_SEPARATOR)) {
            String[] list = StringUtils.split(receiverPhone, CONTACTS_SEPARATOR);
            return new LinkedHashSet<>(Arrays.asList(list));
        }

        if (!receiverPhone.endsWith(CONTACTS_SEPARATOR)) {
            receiverPhone += CONTACTS_SEPARATOR;
        }

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(receiverPhone);
        // 查找字符串中是否有匹配正则表达式的字符/字符串
        Set<String> list = new LinkedHashSet<>();
        while (matcher.find()) {
            String key = matcher.group(2);
            log.info("phone={}", key);
            list.add(key);
        }
        return list;
    }
}
