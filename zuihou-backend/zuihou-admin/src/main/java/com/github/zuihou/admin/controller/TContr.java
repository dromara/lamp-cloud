package com.github.zuihou.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tyh
 * @createTime 2018-01-13 1:42
 */
//加了这个注解，刷新才能生效
@RefreshScope
@RestController
public class TContr {
    @Value("${test:null}")
    private String test;
    @Value("${hello:null}")
    private String hello;

    @RequestMapping("t")
    public String test(){
        return test + "==" + hello;
    }

}
