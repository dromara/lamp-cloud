package com.github.zuihou.common.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.github.zuihou.base.R;
import com.github.zuihou.file.enumeration.DataType;

import io.swagger.annotations.Api;
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

    @GetMapping("/enums")
    public R<Map<String, Object>> enums() {
        Map<String, Object> map = new HashMap<>();
        map.put(DataType.class.getSimpleName(), DataType.values());
        return R.success(map);
    }

    @GetMapping("/dictionaries")
    public R<Map<String, Object>> dictionaries() {
        return R.success(Collections.emptyMap());
    }

}
