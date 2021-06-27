package com.tangyh.lamp.oauth.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.tangyh.basic.base.BaseEnum;
import com.tangyh.basic.base.R;
import com.tangyh.basic.database.mybatis.auth.DataScopeType;
import com.tangyh.basic.utils.CollHelper;
import com.tangyh.lamp.authority.entity.common.Dictionary;
import com.tangyh.lamp.authority.enumeration.auth.ApplicationAppTypeEnum;
import com.tangyh.lamp.authority.enumeration.auth.AuthorizeType;
import com.tangyh.lamp.authority.enumeration.auth.Sex;
import com.tangyh.lamp.authority.enumeration.common.LogType;
import com.tangyh.lamp.authority.service.common.DictionaryService;
import com.tangyh.lamp.authority.service.common.ParameterService;
import com.tangyh.lamp.common.enums.HttpMethod;
import com.tangyh.lamp.file.enumeration.FileType;
import com.tangyh.lamp.msg.enumeration.MsgBizType;
import com.tangyh.lamp.msg.enumeration.MsgType;
import com.tangyh.lamp.oauth.controller.model.Option;
import com.tangyh.lamp.sms.enumeration.ProviderType;
import com.tangyh.lamp.sms.enumeration.SendStatus;
import com.tangyh.lamp.sms.enumeration.SourceType;
import com.tangyh.lamp.sms.enumeration.TaskStatus;
import com.tangyh.lamp.tenant.enumeration.TenantConnectTypeEnum;
import com.tangyh.lamp.tenant.enumeration.TenantStatusEnum;
import com.tangyh.lamp.tenant.enumeration.TenantTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 通用 控制器
 *
 * @author zuihou
 * @date 2019/07/25
 */
@Slf4j
@RestController
@Api(value = "Common", tags = "通用Controller")
@AllArgsConstructor
public class OauthGeneralController {
    private final DictionaryService dictionaryService;
    private final ParameterService parameterService;

    private static final Map<String, Map<String, String>> ENUM_MAP = new HashMap<>(21);
    private static final Map<String, List<Option>> ENUM_LIST_MAP = new HashMap<>(21);

    static {
        // 权限服务
        ENUM_LIST_MAP.put(HttpMethod.class.getSimpleName(), mapOptions(HttpMethod.values()));
        ENUM_LIST_MAP.put(DataScopeType.class.getSimpleName(), mapOptions(DataScopeType.values()));
        ENUM_LIST_MAP.put(LogType.class.getSimpleName(), mapOptions(LogType.values()));
        ENUM_LIST_MAP.put(AuthorizeType.class.getSimpleName(), mapOptions(AuthorizeType.values()));
        ENUM_LIST_MAP.put(Sex.class.getSimpleName(), mapOptions(Sex.values()));
        ENUM_LIST_MAP.put(TenantTypeEnum.class.getSimpleName(), mapOptions(TenantTypeEnum.values()));
        ENUM_LIST_MAP.put(TenantStatusEnum.class.getSimpleName(), mapOptions(TenantStatusEnum.values()));
        ENUM_LIST_MAP.put(ApplicationAppTypeEnum.class.getSimpleName(), mapOptions(ApplicationAppTypeEnum.values()));
        // 租户服务
        ENUM_LIST_MAP.put(TenantConnectTypeEnum.class.getSimpleName(), mapOptions(TenantConnectTypeEnum.values()));
        // 文件服务
        ENUM_LIST_MAP.put(FileType.class.getSimpleName(), mapOptions(HttpMethod.values()));
        //消息服务
        ENUM_LIST_MAP.put(MsgType.class.getSimpleName(), mapOptions(MsgType.values()));
        ENUM_LIST_MAP.put(MsgBizType.class.getSimpleName(), mapOptions(MsgBizType.values()));
        ENUM_LIST_MAP.put(ProviderType.class.getSimpleName(), mapOptions(ProviderType.values()));
        ENUM_LIST_MAP.put(SourceType.class.getSimpleName(), mapOptions(SourceType.values()));
        ENUM_LIST_MAP.put(SendStatus.class.getSimpleName(), mapOptions(SendStatus.values()));
        ENUM_LIST_MAP.put(TaskStatus.class.getSimpleName(), mapOptions(TaskStatus.values()));

        // 权限服务
        ENUM_MAP.put(HttpMethod.class.getSimpleName(), CollHelper.getMap(HttpMethod.values()));
        ENUM_MAP.put(DataScopeType.class.getSimpleName(), CollHelper.getMap(DataScopeType.values()));
        ENUM_MAP.put(LogType.class.getSimpleName(), CollHelper.getMap(LogType.values()));
        ENUM_MAP.put(AuthorizeType.class.getSimpleName(), CollHelper.getMap(AuthorizeType.values()));
        ENUM_MAP.put(Sex.class.getSimpleName(), CollHelper.getMap(Sex.values()));
        ENUM_MAP.put(TenantTypeEnum.class.getSimpleName(), CollHelper.getMap(TenantTypeEnum.values()));
        ENUM_MAP.put(TenantStatusEnum.class.getSimpleName(), CollHelper.getMap(TenantStatusEnum.values()));
        ENUM_MAP.put(ApplicationAppTypeEnum.class.getSimpleName(), CollHelper.getMap(ApplicationAppTypeEnum.values()));
        // 租户服务
        ENUM_MAP.put(TenantConnectTypeEnum.class.getSimpleName(), CollHelper.getMap(TenantConnectTypeEnum.values()));
        // 文件服务
        ENUM_MAP.put(FileType.class.getSimpleName(), CollHelper.getMap(HttpMethod.values()));
        //消息服务
        ENUM_MAP.put(MsgType.class.getSimpleName(), CollHelper.getMap(MsgType.values()));
        ENUM_MAP.put(MsgBizType.class.getSimpleName(), CollHelper.getMap(MsgBizType.values()));
        ENUM_MAP.put(ProviderType.class.getSimpleName(), CollHelper.getMap(ProviderType.values()));
        ENUM_MAP.put(SourceType.class.getSimpleName(), CollHelper.getMap(SourceType.values()));
        ENUM_MAP.put(SendStatus.class.getSimpleName(), CollHelper.getMap(SendStatus.values()));
        ENUM_MAP.put(TaskStatus.class.getSimpleName(), CollHelper.getMap(TaskStatus.values()));
    }

    private static List<Option> mapOptions(BaseEnum[] values) {
        return Arrays.stream(values).map(item -> Option.builder().label(item.getDesc())
                .text(item.getDesc()).value(item.getCode()).build()).collect(Collectors.toList());
    }

    @ApiOperation(value = "获取当前系统指定枚举 Map", notes = "获取当前系统指定枚举")
    @PostMapping("/enums")
    public R<Map<String, Map<String, String>>> enums(@RequestBody String[] codes) {
        if (ArrayUtil.isEmpty(codes)) {
            return R.success(ENUM_MAP);
        }
        Map<String, Map<String, String>> map = new HashMap<>(CollHelper.initialCapacity(codes.length));

        for (String code : codes) {
            if (ENUM_MAP.containsKey(code)) {
                map.put(code, ENUM_MAP.get(code));
            }
        }
        return R.success(map);
    }

    @ApiOperation(value = "获取当前系统指定枚举 List", notes = "获取当前系统指定枚举")
    @PostMapping("/enumLists")
    public R<Map<String, List<Option>>> enumLists(@RequestBody String[] codes) {
        if (ArrayUtil.isEmpty(codes)) {
            return R.success(ENUM_LIST_MAP);
        }
        Map<String, List<Option>> map = new HashMap<>(CollHelper.initialCapacity(codes.length));

        for (String code : codes) {
            if (ENUM_MAP.containsKey(code)) {
                map.put(code, ENUM_LIST_MAP.get(code));
            }
        }
        return R.success(map);
    }

    @ApiOperation(value = "根据类型编码查询字典项", notes = "根据类型编码查询字典项")
    @PostMapping("/dictionary/codes")
    public R<Map<String, List<Dictionary>>> list(@RequestBody String[] types) {
        return R.success(this.dictionaryService.listByTypes(types));
    }

    private static Map<String, List<Option>> mapOptionByDict(Map<String, List<Dictionary>> map) {
        if (CollUtil.isEmpty(map)) {
            return Collections.emptyMap();
        }
        Map<String, List<Option>> newMap = new HashMap<>();
        map.forEach((key, values) -> {
            newMap.put(key, values.stream().map(item -> Option.builder().label(item.getName())
                    .text(item.getName()).value(item.getCode()).build()).collect(Collectors.toList()));
        });
        return newMap;
    }

    @ApiOperation(value = "根据类型编码查询字典项", notes = "根据类型编码查询字典项")
    @PostMapping("/dictionary/codeList")
    public R<Map<String, List<Option>>> codeList(@RequestBody String[] types) {
        return R.success(mapOptionByDict(dictionaryService.listByTypes(types)));
    }


    @GetMapping("/parameter/value")
    @ApiOperation(value = "根据key获取系统参数", notes = "根据key获取系统参数")
    public R<String> getValue(@RequestParam(value = "key") String key, @RequestParam(value = "defVal", required = false) String defVal) {
        return R.success(parameterService.getValue(key, defVal));
    }

    @PostMapping("/parameter/findParams")
    @ApiOperation(value = "根据key批量获取系统参数", notes = "根据key批量获取系统参数")
    public R<Map<String, String>> findParams(@RequestBody List<String> keys) {
        return R.success(parameterService.findParams(keys));
    }
}

