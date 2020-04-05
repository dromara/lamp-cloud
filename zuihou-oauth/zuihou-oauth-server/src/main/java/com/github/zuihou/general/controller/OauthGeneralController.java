package com.github.zuihou.general.controller;

import cn.hutool.core.util.ArrayUtil;
import com.github.zuihou.authority.enumeration.auth.ApplicationAppTypeEnum;
import com.github.zuihou.authority.enumeration.auth.AuthorizeType;
import com.github.zuihou.authority.enumeration.auth.Sex;
import com.github.zuihou.authority.enumeration.common.LogType;
import com.github.zuihou.authority.enumeration.defaults.TenantStatusEnum;
import com.github.zuihou.authority.enumeration.defaults.TenantTypeEnum;
import com.github.zuihou.base.BaseEnum;
import com.github.zuihou.base.R;
import com.github.zuihou.common.enums.HttpMethod;
import com.github.zuihou.database.mybatis.auth.DataScopeType;
import com.github.zuihou.file.enumeration.DataType;
import com.github.zuihou.msgs.enumeration.MsgsBizType;
import com.github.zuihou.msgs.enumeration.MsgsCenterType;
import com.github.zuihou.security.annotation.PreAuth;
import com.github.zuihou.sms.enumeration.ProviderType;
import com.github.zuihou.sms.enumeration.SendStatus;
import com.github.zuihou.sms.enumeration.SourceType;
import com.github.zuihou.sms.enumeration.TaskStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
@Api(value = "Common", tags = "通用Controller")
public class OauthGeneralController {

    private final static Map<String, Map<String, String>> ENUM_MAP = new HashMap<>(8);

    static {
        // 权限服务
        ENUM_MAP.put(HttpMethod.class.getSimpleName(), BaseEnum.getMap(HttpMethod.values()));
        ENUM_MAP.put(DataScopeType.class.getSimpleName(), BaseEnum.getMap(DataScopeType.values()));
        ENUM_MAP.put(LogType.class.getSimpleName(), BaseEnum.getMap(LogType.values()));
        ENUM_MAP.put(AuthorizeType.class.getSimpleName(), BaseEnum.getMap(AuthorizeType.values()));
        ENUM_MAP.put(Sex.class.getSimpleName(), BaseEnum.getMap(Sex.values()));
        ENUM_MAP.put(TenantTypeEnum.class.getSimpleName(), BaseEnum.getMap(TenantTypeEnum.values()));
        ENUM_MAP.put(TenantStatusEnum.class.getSimpleName(), BaseEnum.getMap(TenantStatusEnum.values()));
        ENUM_MAP.put(ApplicationAppTypeEnum.class.getSimpleName(), BaseEnum.getMap(ApplicationAppTypeEnum.values()));
        // 文件服务
        ENUM_MAP.put(DataType.class.getSimpleName(), BaseEnum.getMap(HttpMethod.values()));
        //消息服务
        ENUM_MAP.put(MsgsCenterType.class.getSimpleName(), BaseEnum.getMap(MsgsCenterType.values()));
        ENUM_MAP.put(MsgsBizType.class.getSimpleName(), BaseEnum.getMap(MsgsBizType.values()));
        ENUM_MAP.put(ProviderType.class.getSimpleName(), BaseEnum.getMap(ProviderType.values()));
        ENUM_MAP.put(SourceType.class.getSimpleName(), BaseEnum.getMap(SourceType.values()));
        ENUM_MAP.put(SendStatus.class.getSimpleName(), BaseEnum.getMap(SendStatus.values()));
        ENUM_MAP.put(TaskStatus.class.getSimpleName(), BaseEnum.getMap(TaskStatus.values()));
    }

    @Value("${zuihou.database.isNotWrite:false}")
    private Boolean isNotWrite;

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

    @GetMapping("/aaaa")
    public R<Object> test() {
        log.warn("warn");
        log.error("error");
        log.debug("debug");
        log.info("info");
        return R.success(isNotWrite);
    }

    @GetMapping("/aaa2")
    public R<Object> test22() {
        log.warn("warn");
        log.error("error");
        log.debug("debug");
        log.info("info");
        return R.success(isNotWrite);
    }

    @GetMapping("/aaa3")
    public R<Object> test3() {
        log.warn("warn");
        log.error("error");
        log.debug("debug");
        log.info("info");
        return R.success(isNotWrite);
    }

    @PreAuth("hasRole('user')")
    @GetMapping("/aaa4")
    public R<Object> test4() {
        log.warn("warn");
        log.error("error");
        log.debug("debug");
        log.info("info");
        return R.success(isNotWrite);
    }

    @PreAuth("hasRole('PT_ADMIN')")
    @GetMapping("/aaa5")
    public R<Object> test5() {
        log.warn("warn");
        log.error("error");
        log.debug("debug");
        log.info("info");
        return R.success(isNotWrite);
    }

}

