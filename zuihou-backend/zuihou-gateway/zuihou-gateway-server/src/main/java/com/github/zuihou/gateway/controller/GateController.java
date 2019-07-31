package com.github.zuihou.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This  Description
 *
 * @author zuihou
 * @date 2019/07/31
 */
@RestController
public class GateController {

    @GetMapping("/test")
    @ResponseBody
    public String dictionaryAndEnum(String name) {
        return "123";
    }
}
