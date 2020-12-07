package com.tangyh.lamp.oauth.controller;

import cn.hutool.core.util.ArrayUtil;
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
import com.tangyh.lamp.file.enumeration.DataType;
import com.tangyh.lamp.msg.enumeration.MsgBizType;
import com.tangyh.lamp.msg.enumeration.MsgType;
import com.tangyh.lamp.sms.enumeration.ProviderType;
import com.tangyh.lamp.sms.enumeration.SendStatus;
import com.tangyh.lamp.sms.enumeration.SourceType;
import com.tangyh.lamp.sms.enumeration.TaskStatus;
import com.tangyh.lamp.tenant.enumeration.TenantStatusEnum;
import com.tangyh.lamp.tenant.enumeration.TenantTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    static {
        // 权限服务
        ENUM_MAP.put(HttpMethod.class.getSimpleName(), CollHelper.getMap(HttpMethod.values()));
        ENUM_MAP.put(DataScopeType.class.getSimpleName(), CollHelper.getMap(DataScopeType.values()));
        ENUM_MAP.put(LogType.class.getSimpleName(), CollHelper.getMap(LogType.values()));
        ENUM_MAP.put(AuthorizeType.class.getSimpleName(), CollHelper.getMap(AuthorizeType.values()));
        ENUM_MAP.put(Sex.class.getSimpleName(), CollHelper.getMap(Sex.values()));
        ENUM_MAP.put(TenantTypeEnum.class.getSimpleName(), CollHelper.getMap(TenantTypeEnum.values()));
        ENUM_MAP.put(TenantStatusEnum.class.getSimpleName(), CollHelper.getMap(TenantStatusEnum.values()));
        ENUM_MAP.put(ApplicationAppTypeEnum.class.getSimpleName(), CollHelper.getMap(ApplicationAppTypeEnum.values()));
        // 文件服务
        ENUM_MAP.put(DataType.class.getSimpleName(), CollHelper.getMap(HttpMethod.values()));
        //消息服务
        ENUM_MAP.put(MsgType.class.getSimpleName(), CollHelper.getMap(MsgType.values()));
        ENUM_MAP.put(MsgBizType.class.getSimpleName(), CollHelper.getMap(MsgBizType.values()));
        ENUM_MAP.put(ProviderType.class.getSimpleName(), CollHelper.getMap(ProviderType.values()));
        ENUM_MAP.put(SourceType.class.getSimpleName(), CollHelper.getMap(SourceType.values()));
        ENUM_MAP.put(SendStatus.class.getSimpleName(), CollHelper.getMap(SendStatus.values()));
        ENUM_MAP.put(TaskStatus.class.getSimpleName(), CollHelper.getMap(TaskStatus.values()));
    }

    @ApiOperation(value = "获取当前系统指定枚举", notes = "获取当前系统指定枚举")
    @GetMapping("/enums")
    public R<Map<String, Map<String, String>>> enums(@RequestParam(value = "codes[]", required = false) String[] codes) {
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


    @ApiOperation(value = "根据类型编码查询字典项", notes = "根据类型编码查询字典项")
    @GetMapping("/dictionary/codes")
    public R<Map<String, List<Dictionary>>> list(@RequestParam("codes[]") String[] types) {
        return R.success(this.dictionaryService.listByTypes(types));
    }

    @GetMapping("/parameter/value")
    public R<String> getValue(@RequestParam(value = "key") String key, @RequestParam(value = "defVal") String defVal) {
        return R.success(parameterService.getValue(key, defVal));
    }
}

