package com.github.zuihou.general.controller;

import com.github.zuihou.base.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用 控制器
 *
 * @author zuihou
 * @date 2019/07/25
 */
@Slf4j
@RestController
@Api(value = "Common", tags = "公共Controller")
public class OrderGeneralController {

    @ApiOperation(value = "获取当前系统所有枚举", notes = "获取当前系统所有枚举")
    @GetMapping("/enums")
    public R<Map<String, Map<String, String>>> enums() {
        Map<String, Map<String, String>> map = new HashMap<>(1);
        return R.success(map);
    }


}
