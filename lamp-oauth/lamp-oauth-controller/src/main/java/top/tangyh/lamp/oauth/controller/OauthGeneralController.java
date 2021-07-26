package top.tangyh.lamp.oauth.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import top.tangyh.basic.base.BaseEnum;
import top.tangyh.basic.base.R;
import top.tangyh.basic.utils.ClassUtils;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.authority.entity.common.Dictionary;
import top.tangyh.lamp.authority.service.common.DictionaryService;
import top.tangyh.lamp.authority.service.common.ParameterService;
import top.tangyh.lamp.oauth.controller.model.Option;
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
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Predicate;
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

    private static final Map<String, Map<String, String>> ENUM_MAP = new HashMap<>();
    private static final Map<String, List<Option>> ENUM_LIST_MAP = new HashMap<>();
    /**
     * 过滤那些枚举
     */
    private static final Predicate<Class<?>> CLASS_FILTER = item -> item != null && item.isEnum() && item.isEnum() && MybatisEnumTypeHandler.isMpEnums(item);

    /**
     * 需要让前端查询的枚举类所在的包
     */
    private static final String ENUMS_PACKAGE = "top.tangyh";

    static {
        Set<Class<?>> enumClass = ClassUtils.scanPackage(ENUMS_PACKAGE, CLASS_FILTER);

        StringJoiner enumSb = new StringJoiner(StrPool.COMMA);
        enumClass.forEach(item -> {
            Object[] enumConstants = item.getEnumConstants();
            BaseEnum[] baseEnums = Arrays.stream(enumConstants).map(i -> (BaseEnum) i).toArray(BaseEnum[]::new);

            ENUM_LIST_MAP.put(item.getSimpleName(), Option.mapOptions(baseEnums));
            ENUM_MAP.put(item.getSimpleName(), CollHelper.getMap(baseEnums));
            enumSb.add(item.getSimpleName());
        });

        log.info("扫描: {} ,共加载了{}个枚举类, 分别为: {}", ENUMS_PACKAGE, ENUM_MAP.size(), enumSb);
    }

    private static Map<String, List<Option>> mapOptionByDict(Map<String, List<Dictionary>> map) {
        if (MapUtil.isEmpty(map)) {
            return Collections.emptyMap();
        }
        Map<String, List<Option>> newMap = new HashMap<>();
        map.forEach((key, values) ->
                newMap.put(key, values.stream().map(item -> Option.builder().label(item.getName())
                        .text(item.getName()).value(item.getCode()).build()).collect(Collectors.toList()))
        );
        return newMap;
    }

    @ApiOperation(value = "获取当前系统指定枚举 Map", notes = "获取当前系统指定枚举（lamp-web使用）")
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

    @ApiOperation(value = "根据类型编码查询字典项", notes = "根据类型编码查询字典项")
    @PostMapping("/dictionary/codes")
    public R<Map<String, List<Dictionary>>> list(@RequestBody String[] types) {
        return R.success(this.dictionaryService.listByTypes(types));
    }

    @ApiOperation(value = "获取当前系统指定枚举 List", notes = "获取当前系统指定枚举（lamp-web使用）")
    @PostMapping("/enumList")
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

