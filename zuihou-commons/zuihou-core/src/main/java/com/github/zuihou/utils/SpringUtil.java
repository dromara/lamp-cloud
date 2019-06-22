package com.github.zuihou.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

/**
 * @author zuihou
 * @createTime 2017-12-25 16:27
 */
public class SpringUtil {
    private static ApplicationContext applicationContext;

    private SpringUtil() {
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext ctx) {
        Assert.notNull(ctx, "SpringUtil injection ApplicationContext is null");
        SpringUtil.applicationContext = ctx;
    }

    public static Object getBean(String name) {
        Assert.hasText(name, "SpringUtil name is null or empty");
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> type) {
        Assert.hasText(name, "SpringUtil name is null or empty");
        Assert.notNull(type, "SpringUtil type is null");
        return applicationContext.getBean(name, type);
    }

    public static <T> T getBean(Class<T> type) {
        Assert.notNull(type, "SpringUtil type is null");
        return applicationContext.getBean(type);
    }

    public void destroy() {
        SpringUtil.applicationContext = null;
    }
}
