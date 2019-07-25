package com.github.zuihou.general.controller;

import java.util.HashMap;
import java.util.Map;

import com.github.zuihou.base.R;
import com.github.zuihou.file.enumeration.DataType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2019/07/25
 */
@Slf4j
@RestController
@Api(value = "Common", tags = "公共Controller")
public class CommonController {

    @ApiOperation(value = "获取当前系统所有枚举", notes = "获取当前系统所有枚举")
    @GetMapping("/enums")
    public R<Map<String, Object>> enums() {
        Map<String, Object> map = new HashMap<>(1);
        map.put(DataType.class.getSimpleName(), DataType.values());
        return R.success(map);
    }

    @ApiOperation(value = "获取当前系统所有数据字典", notes = "获取当前系统所有数据字典")
    @GetMapping("/dictionary")
    public R<Map<String, Object>> dictionary() {
        Map<String, Object> map = new HashMap<>();

        return R.success(map);
    }
}
