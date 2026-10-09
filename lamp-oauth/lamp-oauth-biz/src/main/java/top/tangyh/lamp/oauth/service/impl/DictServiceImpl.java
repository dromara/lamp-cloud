package top.tangyh.lamp.oauth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.EnumUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.interfaces.BaseEnum;
import top.tangyh.basic.utils.ClassUtils;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.common.properties.SystemProperties;
import top.tangyh.lamp.model.vo.result.Option;
import top.tangyh.lamp.oauth.service.DictService;
import top.tangyh.lamp.system.entity.system.DefDict;
import top.tangyh.lamp.system.manager.system.DefDictManager;
import top.tangyh.lamp.system.vo.result.system.DefDictItemResultVO;
import top.tangyh.lamp.system.vo.result.system.DefDictResultVO;

import java.io.Serializable;
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
 * @author zuihou
 * @date 2021/10/7 13:27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DictServiceImpl implements DictService {
    private static final Map<String, Map<String, String>> ENUM_MAP = new HashMap<>();
    private static final Map<String, List<Option>> ENUM_LIST_MAP = new HashMap<>();
    private static final Map<Option, List<Option>> TEMP_ENUM_LIST_MAP = new HashMap<>();
    /**
     * 过滤那些枚举
     */
    private static final Predicate<Class<?>> CLASS_FILTER = item -> item != null && item.isEnum() && EnumUtils.isMpEnums(item);
    private final DefDictManager defDictManager;
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

            Option option = new Option();
            option.setValue(item.getSimpleName());

            // 2. 获取Schema注解的title属性
            Schema schemaAnnotation = item.getAnnotation(Schema.class);
            if (schemaAnnotation != null) {
                String description = schemaAnnotation.description();
                String title = schemaAnnotation.title();
                option.setLabel(description);
                if (StrUtil.isNotEmpty(title)) {
                    option.setValue(title);
                }
            } else {
                // 3. 获取类注释的首行内容
                System.err.println(item.getSimpleName() + "类上没有@Schema注解");
                option.setLabel(item.getSimpleName());
            }

            TEMP_ENUM_LIST_MAP.put(option, Option.mapOptions(baseEnums));
            enumSb.add(item.getSimpleName());
        });

        log.info("扫描: {} ,共加载了{}个枚举类, 分别为: {}", enumPackage, TEMP_ENUM_LIST_MAP.size(), enumSb);
    }

    /**
     * 查找本服务中，所有的枚举类
     * @return 枚举数据
     */
    public List<DefDictResultVO> findAll() {
        if (CollUtil.isEmpty(TEMP_ENUM_LIST_MAP)) {
            return Collections.emptyList();
        }
        Map<Option, List<Option>> map = new HashMap<>(TEMP_ENUM_LIST_MAP);

        // 将枚举的value值，转为字典key
        List<String> dictKeyList = new ArrayList<>();
        map.forEach((key, value) -> dictKeyList.add(key.getValue()));

        // 查询数据库库中的所有字典和字典项
        List<DefDict> existsList = defDictManager.list(Wraps.<DefDict>lbQ().in(DefDict::getKey, dictKeyList));
        List<DefDictResultVO> existsDictList = BeanUtil.copyToList(existsList, DefDictResultVO.class);
        existsDictList.forEach(dict -> {
            List<DefDict> itemList = defDictManager.list(Wraps.<DefDict>lbQ().eq(DefDict::getParentId, dict.getId()));
            List<DefDictItemResultVO> sysDictItemList = BeanUtil.copyToList(itemList, DefDictItemResultVO.class);
            dict.setItemList(sysDictItemList);
        });

        // 已存在的字典
        Map<String, DefDictResultVO> existingDictMap = new HashMap<>();
        // 已存在的字典项
        Map<String, DefDictItemResultVO> existingDictItemMap = new HashMap<>();
        for (DefDictResultVO sysDict : existsDictList) {
            existingDictMap.put(sysDict.getKey(), sysDict);
            List<DefDictItemResultVO> sysDictItemList = sysDict.getItemList();
            if (CollUtil.isNotEmpty(sysDictItemList)) {
                for (DefDictItemResultVO item : sysDictItemList) {
                    existingDictItemMap.put(sysDict.getKey() + item.getKey(), item);
                }
            }
        }

        List<DefDictResultVO> list = new ArrayList<>();
        map.forEach((key, value) -> {
            boolean exist = existingDictMap.containsKey(key.getValue());
            DefDictResultVO vo = new DefDictResultVO();
            vo.setKey(key.getValue());
            vo.setName(key.getLabel());
            vo.setDataType(key.getRemark());
            vo.setExist(exist);
            List<DefDictItemResultVO> itemList = new ArrayList<>();
            value.forEach(option -> {
                boolean itemExist = existingDictItemMap.containsKey(key.getValue() + option.getValue());
                DefDictItemResultVO item = new DefDictItemResultVO();
                item.setKey(option.getValue());
                item.setName(option.getLabel());
                item.setExist(itemExist);
                itemList.add(item);
            });
            vo.setItemList(itemList);
            list.add(vo);
        });
        return list;
    }


    @Override
    public void syncEnumToDict() {
        defDictManager.syncEnumToDict(TEMP_ENUM_LIST_MAP);
    }

    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> dictKeys) {
        if (CollUtil.isEmpty(dictKeys)) {
            return Collections.emptyMap();
        }

        String locale = ContextUtil.getLocale();

        // 查询不在base的字典
        Map<Serializable, DefDict> defMap = defDictManager.findByIds(dictKeys);

        HashMap<Serializable, Object> map = MapUtil.newHashMap();
        // 顺序不能乱，一定是base的覆盖def的
        execI18n(defMap, locale, map);

        return map;
    }

    private static void execI18n(Map<Serializable, DefDict> defMap, String locale, HashMap<Serializable, Object> map) {
        defMap.forEach((key, value) -> {
            String name = value.getName();
            if (StrUtil.isNotEmpty(locale)) {
                String i18nJson = value.getI18nJson();
                try {
                    JSONObject i18n = JSONUtil.parseObj(i18nJson);
                    String i18nValue = i18n.getStr(locale);
                    if (StrUtil.isNotEmpty(i18nValue)) {
                        name = i18nValue;
                    }
                } catch (Exception e) {

                }
            }
            map.put(key, name);
        });
    }

    @Override
    public Map<String, List<DefDictItemResultVO>> findDictItemByType(List<String> dictKeys) {
        if (CollUtil.isEmpty(dictKeys)) {
            return Collections.emptyMap();
        }

        // 查询不在base的字典
        Map<String, List<DefDictItemResultVO>> defMap = defDictManager.findDictMapItemListByKey(dictKeys);

        Map<String, List<DefDictItemResultVO>> map = MapUtil.newHashMap();
        map.putAll(defMap);
        return map;
    }
}
