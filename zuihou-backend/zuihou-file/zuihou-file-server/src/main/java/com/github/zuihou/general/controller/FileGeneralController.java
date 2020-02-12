package com.github.zuihou.general.controller;

import cn.hutool.core.util.ArrayUtil;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.BaseEnum;
import com.github.zuihou.base.R;
import com.github.zuihou.common.enums.HttpMethod;
import com.github.zuihou.file.enumeration.DataType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class FileGeneralController extends BaseController {

    private final static Map<String, Map<String, String>> ENUM_MAP = new HashMap<>(1);

    static {
        ENUM_MAP.put(DataType.class.getSimpleName(), BaseEnum.getMap(HttpMethod.values()));
    }

    @ApiOperation(value = "获取当前系统指定枚举", notes = "获取当前系统指定枚举")
    @GetMapping("/enums")
    public R<Map<String, Map<String, String>>> enums(@RequestParam(value = "codes[]", required = false) String[] codes) {
        if (ArrayUtil.isEmpty(codes)) {
            return success(ENUM_MAP);
        }

        Map<String, Map<String, String>> map = new HashMap<>(codes.length);
        for (String code : codes) {
            if (ENUM_MAP.containsKey(code)) {
                map.put(code, ENUM_MAP.get(code));
            }
        }
        return success(map);
    }
}
