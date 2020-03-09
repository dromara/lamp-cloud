package com.github.zuihou.general.controller;

import cn.hutool.core.util.ArrayUtil;
import com.github.zuihou.authority.api.OrgApi;
import com.github.zuihou.base.BaseEnum;
import com.github.zuihou.base.R;
import com.github.zuihou.common.enums.HttpMethod;
import com.github.zuihou.file.enumeration.DataType;
import com.github.zuihou.injection.core.InjectionCore;
import com.github.zuihou.model.RemoteData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 通用 控制器
 *
 * @author zuihou
 * @date 2019/07/25
 */
@Slf4j
@RestController
@Api(value = "Common", tags = "公共Controller")
public class FileGeneralController {

    private final static Map<String, Map<String, String>> ENUM_MAP = new HashMap<>(1);

    static {
        ENUM_MAP.put(DataType.class.getSimpleName(), BaseEnum.getMap(HttpMethod.values()));
    }

    @Autowired
    private OrgApi orgApi;
    @Autowired
    private InjectionCore injectionCore;

    @ApiOperation(value = "获取当前系统指定枚举", notes = "获取当前系统指定枚举")
    @GetMapping("/enums")
    public R<Map<String, Map<String, String>>> enums(@RequestParam(value = "codes[]", required = false) String[] codes) {
        if (ArrayUtil.isEmpty(codes)) {
            return R.success(ENUM_MAP);
        }

        Map<String, Map<String, String>> map = new HashMap<>(codes.length);
        for (String code : codes) {
            if (ENUM_MAP.containsKey(code)) {
                map.put(code, ENUM_MAP.get(code));
            }
        }
        return R.success(map);
    }

    @GetMapping("/feign")
    public R<Object> feign1(@RequestParam(value = "codes[]", required = false) String[] codes) {
        Set<Serializable> orgIds = new HashSet<>();
        orgIds.add(100L);
        orgIds.add(101L);

//        MDC.put(BaseContextConstants.TENANT, BaseContextHandler.getTenant());
//        MDC.put(BaseContextConstants.JWT_KEY_USER_ID, String.valueOf(BaseContextHandler.getUserId()));

        Map<Serializable, Object> map1 = orgApi.findOrgByIds(orgIds);
//        Map<Serializable, Object> map2 = orgApi.findOrgNameByIds(orgIds);
//
//        Set<Serializable> items = new HashSet<>();
//        items.add("NATION");
//        Map<Serializable, Object> map3 = dictionaryItemApi.findDictionaryItem(items);

        return R.success(map1);
    }

    @GetMapping("/feign2")
    public R<Hahaha> feign2(@RequestParam(value = "codes[]", required = false) String[] codes) {
        Set<Serializable> orgIds = new HashSet<>();
        orgIds.add(100L);
        orgIds.add(101L);

        Hahaha haha = new Hahaha();

        haha.setNation(new RemoteData<>("mz_llz"));
        haha.setOrg(new RemoteData<>(100L));
        haha.setOrg2(new RemoteData<>(101L));
        haha.setOrg3(new RemoteData<>(102L));
        haha.setOrg4(new RemoteData<>(643775612976106881L));
        haha.setUser(new RemoteData<>(3L));
        haha.setStation(new RemoteData<>(100L));


        injectionCore.injection(haha, false);

        return R.success(haha);
    }


}
