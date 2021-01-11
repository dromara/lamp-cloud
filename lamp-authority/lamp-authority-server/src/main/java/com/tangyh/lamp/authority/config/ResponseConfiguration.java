package com.tangyh.lamp.authority.config;

import com.tangyh.basic.boot.handler.AbstractGlobalResponseBodyAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;

/**
 * 全局统一返回值 包装器
 *
 * @author zuihou
 * @date 2020/12/30 2:48 下午
 */
@Configuration
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RestControllerAdvice(basePackages = {"com.tangyh.lamp"})
public class ResponseConfiguration extends AbstractGlobalResponseBodyAdvice {
}
