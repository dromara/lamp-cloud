package top.tangyh.lamp.oauth.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.base.R;
import top.tangyh.basic.interfaces.BaseEnum;
import top.tangyh.basic.utils.ClassUtils;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.common.properties.SystemProperties;
import top.tangyh.lamp.model.vo.result.Option;
import top.tangyh.lamp.oauth.service.DictService;
import top.tangyh.lamp.oauth.service.ParamService;
import top.tangyh.lamp.oauth.vo.param.CodeQueryVO;
import top.tangyh.lamp.system.vo.result.system.DefDictItemResultVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Predicate;

/**
 * 通用 控制器
 *
 * @author zuihou
 * @date 2019/07/25
 */
@Slf4j
@RestController
@Tag(name = "字典-枚举-参数-通用查询")
@RequiredArgsConstructor
public class GeneralController {
    private static final Map<String, Map<String, String>> ENUM_MAP = new HashMap<>();
    private static final Map<String, List<Option>> ENUM_LIST_MAP = new HashMap<>();
    /**
     * 过滤那些枚举
     */
    private static final Predicate<Class<?>> CLASS_FILTER = item -> item != null && item.isEnum() && item.isEnum() && MybatisEnumTypeHandler.isMpEnums(item);
    private final SystemProperties systemProperties;

    @PostConstruct
    public void init() {
        String enumPackage = systemProperties.getEnumPackage();
        if (StrUtil.isEmpty(enumPackage)) {
            log.warn("请在配置文件中配置{}.enumPackage", SystemProperties.PREFIX);
            return;
        }
        Set<Class<?>> enumClass = ClassUtils.scanPackage(enumPackage, CLASS_FILTER);

        StringJoiner enumSb = new StringJoiner(StrPool.COMMA);
        enumClass.forEach(item -> {
            Object[] enumConstants = item.getEnumConstants();
            BaseEnum[] baseEnums = Arrays.stream(enumConstants).map(i -> (BaseEnum) i).toArray(BaseEnum[]::new);

            ENUM_LIST_MAP.put(item.getSimpleName(), Option.mapOptions(baseEnums));
            ENUM_MAP.put(item.getSimpleName(), CollHelper.getMap(baseEnums));
            enumSb.add(item.getSimpleName());
        });

        log.info("扫描: {} ,共加载了{}个枚举类, 分别为: {}", enumPackage, ENUM_MAP.size(), enumSb);
    }

    private final DictService dictService;
    private final ParamService paramService;
    @Value("${test1:def}")
    private String test1;
    @Value("${test2:def}")
    private String test2;

    private static Map<String, List<Option>> mapOptionByDict(Map<String, List<DefDictItemResultVO>> map, List<CodeQueryVO> codeQueryVO) {
        if (MapUtil.isEmpty(map)) {
            return Collections.emptyMap();
        }
        Map<String, CodeQueryVO> codeMap = MapUtil.newHashMap();
        if (CollUtil.isNotEmpty(codeQueryVO)) {
            codeQueryVO.forEach(item -> codeMap.put(item.getType(), item));
        }

        Map<String, List<Option>> newMap = MapUtil.newHashMap();
        map.forEach((type, values) -> {
            CodeQueryVO codeQuery = codeMap.get(type);
            boolean extendFirst = codeQuery == null || codeQuery.getExtendFirst() == null || codeQuery.getExtendFirst();

            List<Option> options = new ArrayList<>();
            if (codeQuery != null && extendFirst && codeQuery.getExtend() != null) {
                options.add(codeQuery.getExtend());
            }
            List<Option> optionList = values.stream().filter(item -> {
                        if (codeQuery != null) {
                            List<String> excludes = codeQuery.getExcludes() == null ? Collections.emptyList() : codeQuery.getExcludes();
                            return !excludes.contains(item.getKey());
                        }
                        return false;
                    })
                    .map(item -> Option.builder().label(item.getName())
                            .text(item.getName()).value(item.getKey()).build()).toList();
            options.addAll(optionList);
            if (codeQuery != null && !extendFirst && codeQuery.getExtend() != null) {
                options.add(codeQuery.getExtend());
            }

            newMap.put(type, options);
        });
        return newMap;
    }

    @PostMapping("/anyTenant/enums/test1")
    public void test1() {
        log.info("1234={}-===_{}", test1, test2);
    }


    @PostMapping(value = "/enums/test2")
    public R<String> test2() {
        log.info("1234");
        return R.success("1234");
    }

    @Operation(summary = "获取当前系统指定枚举 List", description = "获取当前系统指定枚举（lamp-web使用）")
    @PostMapping("/anyTenant/enums/findEnumListByType")
    public R<Map<String, List<Option>>> findEnumListByType(@RequestBody List<CodeQueryVO> types) {
        if (CollUtil.isEmpty(types)) {
            return R.success(ENUM_LIST_MAP);
        }
        Map<String, CodeQueryVO> codeMap = MapUtil.newHashMap();
        if (CollUtil.isNotEmpty(types)) {
            types.forEach(item -> codeMap.put(item.getType(), item));
        }

        Map<String, List<Option>> map = new HashMap<>(CollHelper.initialCapacity(types.size()));
        for (CodeQueryVO type : types) {
            if (!ENUM_LIST_MAP.containsKey(type.getType())) {
                continue;
            }
            List<Option> cacheOptions = ENUM_LIST_MAP.get(type.getType());

            CodeQueryVO codeQuery = codeMap.get(type.getType());
            boolean extendFirst = codeQuery == null || codeQuery.getExtendFirst() == null || codeQuery.getExtendFirst();
            List<Option> options = new ArrayList<>();
            if (codeQuery != null && extendFirst && codeQuery.getExtend() != null) {
                options.add(codeQuery.getExtend());
            }

            List<Option> optionList = cacheOptions.stream().filter(item -> {
                if (codeQuery != null) {
                    List<String> excludes = codeQuery.getExcludes() == null ? Collections.emptyList() : codeQuery.getExcludes();
                    return !excludes.contains(item.getValue());
                }
                return false;
            }).toList();
            options.addAll(optionList);

            if (codeQuery != null && !extendFirst && codeQuery.getExtend() != null) {
                options.add(codeQuery.getExtend());
            }
            map.put(type.getType(), options);
        }
        return R.success(map);
    }

    @Operation(summary = "根据类型编码查询字典项,并排除指定项", description = "根据类型编码查询字典项")
    @PostMapping("/anyUser/dict/findDictMapItemListByKey")
    public R<Map<String, List<Option>>> findDictMapItemListByKey(@RequestBody List<CodeQueryVO> codeQueryVO) {
        Map<String, List<DefDictItemResultVO>> map = dictService.findDictMapItemListByKey(codeQueryVO.stream().map(CodeQueryVO::getType).toList());
        return R.success(mapOptionByDict(map, codeQueryVO));
    }

//    @GetMapping("/anyUser/parameter/value")
//    @Operation(summary = "根据key获取系统参数", description = "根据key获取系统参数")
//    public R<String> getValue(@RequestParam(value = "key") String key, @RequestParam(value = "defVal", required = false) String defVal) {
//        return R.success(parameterService.getValue(key, defVal));
//    }

    @PostMapping("/anyUser/parameter/findParamMapByKey")
    @Operation(summary = "根据key批量获取系统参数", description = "根据key批量获取系统参数")
    public R<Map<String, String>> findParams(@RequestBody List<String> keys) {
        return R.success(paramService.findParamMapByKey(keys));
    }
}

