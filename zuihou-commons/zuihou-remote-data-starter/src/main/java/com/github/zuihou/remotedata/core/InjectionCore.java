package com.github.zuihou.remotedata.core;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.model.RemoteData;
import com.github.zuihou.remotedata.annonation.RemoteField;
import com.github.zuihou.remotedata.annonation.RemoteResult;
import com.github.zuihou.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.BeansException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 遍历obj中的所有字段，
 * 将标注了Sys注解的字段值设置为： type类型的fieldName字段的值。 取值从value中查
 *
 * @author tangyh
 * @date 2019/11/13
 */

@Slf4j
public class InjectionCore {

    private static int MAX_DEPTH = 2;

    /**
     * 手动注入
     *
     * @param obj 需要注入的对象、集合、IPage
     * @throws Exception
     */
    public void injection(Object obj) throws Exception {
        // key 为远程查询的对象
        // value 为 待查询的数据
        Map<RemoteFieldPo, Map<Serializable, Object>> typeMap = new HashMap();
        //解析待查询的数据
        long parseStart = System.currentTimeMillis();
        parse(obj, typeMap, 1, MAX_DEPTH);
        long parseEnd = System.currentTimeMillis();

        log.info("解析耗时={} ms", (parseEnd - parseStart));
        if (typeMap.isEmpty()) {
            return;
        }

        // 批量查询
        for (Map.Entry<RemoteFieldPo, Map<Serializable, Object>> entries : typeMap.entrySet()) {
            RemoteFieldPo type = entries.getKey();
            Map<Serializable, Object> valueMap = entries.getValue();
            Set<Serializable> keys = valueMap.keySet();
            try {
                Object bean = SpringUtils.getBean(type.getApi());
                log.info("建议在方法： [{}.{}]，上加入缓存，加速查询", type.getApi(), type.getMethod());
                Map<Serializable, Object> value = ReflectUtil.invoke(bean, type.getMethod(), keys);
                typeMap.put(type, value);
            } catch (UtilException | BeansException e) {
                log.error("远程调用方法 [{}.{}] 失败， 请确保系统存在该方法", type.getApi(), type.getMethod(), e);
            }
        }

        long injectionStart = System.currentTimeMillis();
        log.info("批量查询耗时={} ms", (injectionStart - parseEnd));

        // 注入
        injection(obj, typeMap, 1, MAX_DEPTH);
        long injectionEnd = System.currentTimeMillis();

        log.info("注入耗时={} ms", (injectionEnd - injectionStart));
    }

    /**
     * aop方式加工
     *
     * @param pjp
     * @param anno
     * @return
     * @throws Throwable
     */
    public Object injection(ProceedingJoinPoint pjp, RemoteResult anno) throws Throwable {
        Object proceed = pjp.proceed();
        try {
//            MethodSignature signature = (MethodSignature) pjp.getSignature();
////            Method method = signature.getMethod();
////            if (method.getGenericReturnType() instanceof ParameterizedType) {
////                ParameterizedType parameterizedType = (ParameterizedType) method.getGenericReturnType();
////                Type rawType = parameterizedType.getRawType();
////                List<?> result = null;
////                // 获取当前方法的返回值
////                Type[] types = parameterizedType.getActualTypeArguments();
////                Class clazz = ((Class) types[0]);
////                // 非list直接返回
////                if (anno.resultParser().equals(DefaultRemoteResultParser.class) && ((Class) rawType).isAssignableFrom(List.class)) {
////                    result = (List<?>) proceed;
////                    injection(result);
////                    return result;
////                } else {
////                    RemoteResultParser bean = SpringUtils.getBean(anno.resultParser());
////                    result = bean.parser(proceed);
////                    injection(result);
////                    return proceed;
////                }
////            } else {
////                injection(proceed);
////                return proceed;
////            }
            injection(proceed);
            return proceed;
        } catch (Exception e) {
            log.error("某属性数据聚合失败", e);
            return proceed;
        }
    }

    /**
     * 判断是否为基本类型
     *
     * @param obj
     * @param field 字段
     * @return
     */
    private boolean isNotBaseType(Object obj, Field field) {
        String typeName = field.getType().getName();
        if ("java.lang.Integer".equals(typeName) ||
                "java.lang.Byte".equals(typeName) ||
                "java.lang.Long".equals(typeName) ||
                "java.lang.Double".equals(typeName) ||
                "java.lang.Float".equals(typeName) ||
                "java.lang.Character".equals(typeName) ||
                "java.lang.Short".equals(typeName) ||
                "java.lang.Boolean".equals(typeName) ||
                "java.lang.String".equals(typeName) ||
                "com.github.zuihou.model.RemoteData".equals(typeName)
        ) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 1，遍历字段，解析出数据
     * 2，遍历字段，设值
     *
     * @param obj
     * @throws Exception
     */
    private void parse(Object obj, Map<RemoteFieldPo, Map<Serializable, Object>> typeMap, int depth, int maxDepth) throws Exception {
        if (obj == null) {
            return;
        }
        if (depth > maxDepth) {
            log.info("出现循环依赖，对多执行 {} 次， 已执行 {} 次，已为您跳出循环", depth, maxDepth);
            return;
        }
        if (typeMap == null) {
            typeMap = new HashMap();
        }

        if (obj instanceof IPage) {
            List records = ((IPage) obj).getRecords();
            parseList(records, typeMap, depth, maxDepth);
            return;
        }
        if (obj instanceof Collection) {
            parseList((Collection) obj, typeMap, depth, maxDepth);
            return;
        }

        //解析方法上的注解，计算出obj对象中所有需要查询的数据
        Field[] fields = ReflectUtil.getFields(obj.getClass());

        for (Field field : fields) {
            //是否使用MyAnno注解
            RemoteField anno = field.getDeclaredAnnotation(RemoteField.class);
            if (anno == null) {
                continue;
            }
            field.setAccessible(true);

            if (isNotBaseType(obj, field)) {
                parse(field.get(obj), typeMap, depth + 1, anno.depth());
                continue;
            }

            String api = anno.api();
            if (StrUtil.isEmpty(api)) {
                log.warn("忽略注入字段: {}.{}", field.getType(), field.getName());
                continue;
            }

            String key = anno.key();
            RemoteFieldPo type = new RemoteFieldPo(anno);
            Map<Serializable, Object> valueMap;
            if (typeMap.get(type) == null) {
                valueMap = new HashMap();
            } else {
                valueMap = typeMap.get(type);
            }

            Serializable queryKey;
            if (StrUtil.isNotEmpty(key)) {
                queryKey = key;
            } else {
                try {
                    Object curField = ReflectUtil.getFieldValue(obj, field);
                    if (curField instanceof RemoteData) {
                        RemoteData remoteData = (RemoteData) curField;
                        queryKey = (Serializable) remoteData.getKey();
                    } else {
                        queryKey = (Serializable) curField;
                    }
                } catch (Exception e) {
                    log.warn("类型装换失败忽略注入字段: {}.{}", field.getType(), field.getName());
                    continue;
                }
            }

            if (ObjectUtil.isNotEmpty(queryKey)) {
                valueMap.put(queryKey, null);
            }

            typeMap.put(type, valueMap);
        }
    }

    /**
     * 解析list
     *
     * @param list
     * @param typeMap
     * @throws Exception
     */
    public void parseList(Collection list, Map typeMap, int depth, int maxDepth) throws Exception {
        for (Object item : list) {
            parse(item, typeMap, depth, maxDepth);
        }
    }

    /**
     * 注入
     *
     * @param obj
     * @param typeMap
     * @param depth
     * @param maxDepth
     * @throws Exception
     */
    private void injection(Object obj, Map<RemoteFieldPo, Map<Serializable, Object>> typeMap, int depth, int maxDepth) throws Exception {
        if (obj == null) {
            return;
        }
        if (depth > maxDepth) {
            log.info("出现循环依赖，对多执行 {} 次， 已执行 {} 次，已为您跳出循环", depth, maxDepth);
            return;
        }
        if (typeMap == null || typeMap.isEmpty()) {
            return;
        }

        if (obj instanceof IPage) {
            List records = ((IPage) obj).getRecords();
            injectionList((Collection) records, typeMap);
            return;
        }
        if (obj instanceof Collection) {
            injectionList((Collection) obj, typeMap);
            return;
        }

        //解析方法上的注解，计算出obj对象中所有需要查询的数据
        Field[] fields = ReflectUtil.getFields(obj.getClass());
        for (Field field : fields) {
            //是否使用MyAnno注解
            RemoteField anno = field.getDeclaredAnnotation(RemoteField.class);
            if (anno == null) {
                continue;
            }
            field.setAccessible(true);
            //类型
            if (isNotBaseType(obj, field)) {
                injection(field.get(obj), typeMap, depth + 1, anno.depth());
                continue;
            }

            String api = anno.api();
            if (StrUtil.isEmpty(api)) {
                log.warn("忽略注入字段: {}.{}", field.getType(), field.getName());
                continue;
            }

            String key = anno.key();
            RemoteFieldPo type = new RemoteFieldPo(anno);
            Serializable queryKey = null;
            if (StrUtil.isNotEmpty(key)) {
                queryKey = key;
            } else {
                try {
                    Object curField = ReflectUtil.getFieldValue(obj, field);
                    if (curField instanceof RemoteData) {
                        RemoteData remoteData = (RemoteData) curField;
                        queryKey = (Serializable) remoteData.getKey();
                    } else {
                        queryKey = (Serializable) curField;
                    }
                } catch (Exception e) {
                    log.warn("类型装换失败忽略注入字段: {}.{}", field.getType(), field.getName());
                    continue;
                }
            }

            Map<Serializable, Object> valueMap = typeMap.get(type);

            if (valueMap == null || valueMap.isEmpty()) {
                continue;
            }

            Object newVal = valueMap.get(queryKey);
            Object curField = ReflectUtil.getFieldValue(obj, field);
            if (curField == null) {
                log.debug("字段[{}]为空,跳过", field.getName());
                continue;
            }

            if (curField instanceof RemoteData) {
                RemoteData remoteData = (RemoteData) curField;

                remoteData.setData(newVal);
            } else {
                ReflectUtil.setFieldValue(obj, field, newVal);
            }
        }
    }

    private void injectionList(Collection list, Map typeMap) throws Exception {
        for (Object item : list) {
            injection(item, typeMap, 1, MAX_DEPTH);
        }
    }

}
