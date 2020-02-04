package com.github.zuihou.injection.annonation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动注入数据返回值注入 注解
 *
 * @author zuihou
 * @create 2020年01月19日09:08:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface InjectionResult {
}
