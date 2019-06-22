package com.github.zuihou.utils;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

/**
 * @author zuihou
 * @createTime 2017-12-25 16:27
 */
@SuppressWarnings("squid:S1166")
public class SpringUtil {
    private static ApplicationContext applicationContext;
    private static ApplicationContext parentApplicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext ctx) {
        Assert.notNull(ctx, "SpringUtil injection ApplicationContext is null");
        applicationContext = ctx;
        parentApplicationContext = ctx.getParent();
    }

    public static Object getBean(String name) {
        Assert.hasText(name, "SpringUtil name is null or empty");
        try {
            return applicationContext.getBean(name);
        } catch (Exception e) {
            return parentApplicationContext.getBean(name);
        }
    }

    public static <T> T getBean(String name, Class<T> type) {
        Assert.hasText(name, "SpringUtil name is null or empty");
        Assert.notNull(type, "SpringUtil type is null");
        try {
            return applicationContext.getBean(name, type);
        } catch (Exception e) {
            return parentApplicationContext.getBean(name, type);
        }
    }

    public static <T> T getBean(Class<T> type) {
        Assert.notNull(type, "SpringUtil type is null");
        try {
            return applicationContext.getBean(type);
        } catch (Exception e) {
            return parentApplicationContext.getBean(type);
        }
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        Assert.notNull(type, "SpringUtil type is null");
        try {
            return applicationContext.getBeansOfType(type);
        } catch (Exception e) {
            return parentApplicationContext.getBeansOfType(type);
        }
    }


}
