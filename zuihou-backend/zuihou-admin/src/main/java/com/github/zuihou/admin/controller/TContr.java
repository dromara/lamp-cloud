package com.github.zuihou.admin.controller;

import com.github.zuihou.admin.ser.Ser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class TContr {
    @Value("${test:null}")
    private String test;
    @Value("${hello:null}")
    private String hello;
    @Autowired
    Ser ser;

    @RequestMapping("t")
    public String test(){
        log.error("info---------------");
        log.warn("warn---------------");
        log.info("info---------------");
        log.debug("debug---------------");
        log.trace("trace---------------");
        ser.test();
        return test + "==" + hello;
    }

}
