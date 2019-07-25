package com.github.zuihou.common.controller;

import java.util.HashMap;
import java.util.Map;

import com.github.zuihou.authority.enumeration.auth.MenuType;
import com.github.zuihou.authority.enumeration.auth.ResourceType;
import com.github.zuihou.authority.enumeration.auth.Sex;
import com.github.zuihou.authority.enumeration.auth.TargetType;
import com.github.zuihou.authority.enumeration.common.LogType;
import com.github.zuihou.authority.service.common.DictionaryService;
import com.github.zuihou.base.R;
import com.github.zuihou.common.enums.HttpMethod;
import com.github.zuihou.database.mybatis.auth.DataScopeType;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/enums")
    public R<Map<String, Object>> enums() {
        Map<String, Object> map = new HashMap<>(7);
        map.put(HttpMethod.class.getSimpleName(), HttpMethod.values());
        map.put(DataScopeType.class.getSimpleName(), DataScopeType.values());
        map.put(LogType.class.getSimpleName(), LogType.values());
        map.put(MenuType.class.getSimpleName(), MenuType.values());
        map.put(ResourceType.class.getSimpleName(), ResourceType.values());
        map.put(Sex.class.getSimpleName(), Sex.values());
        map.put(TargetType.class.getSimpleName(), TargetType.values());
        return R.success(map);
    }

    @GetMapping("/dictionary")
    public R<Map<String, Object>> dictionary() {
        Map<String, Object> map = new HashMap<>();

        return R.success(map);
    }

}
