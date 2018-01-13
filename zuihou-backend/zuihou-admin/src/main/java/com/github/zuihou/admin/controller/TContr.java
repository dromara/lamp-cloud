package com.github.zuihou.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tyh
 * @createTime 2018-01-13 1:42
 */
@RestController
public class TContr {
    @Value("${test:null}")
    private String test;
    @Value("${test2:null}")
    private String test2;

    @RequestMapping("t")
    public String test(){
        return test + "==" + test2;
    }

}
