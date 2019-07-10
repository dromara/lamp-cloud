package com.github.zuihou.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.zuihou.common.config.LoginArgResolverConfig;

import org.springframework.context.annotation.Import;

/**
 * 在启动类上添加该注解来----开启自动登录用户对象注入
 * Token转化SysUser
 *
 * @author zlt
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(LoginArgResolverConfig.class)
public @interface EnableLoginArgResolver {
}
