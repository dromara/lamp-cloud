package com.github.zuihou.injection.annonation;


import com.github.zuihou.injection.facade.DefaultRemoteResultParser;
import com.github.zuihou.injection.facade.RemoteResultParser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 远程数据 返回值
 *
 * @author zuihou
 * @create 2020年01月19日09:08:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface InjectionResult {
    @Deprecated
    Class<? extends RemoteResultParser> resultParser() default DefaultRemoteResultParser.class;
}
