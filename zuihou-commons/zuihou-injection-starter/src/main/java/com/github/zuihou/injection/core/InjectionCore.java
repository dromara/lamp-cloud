package com.github.zuihou.injection.core;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.injection.annonation.InjectionField;
import com.github.zuihou.injection.annonation.InjectionResult;
import com.github.zuihou.injection.configuration.InjectionProperties;
import com.github.zuihou.model.RemoteData;
import com.github.zuihou.utils.SpringUtils;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 字典数据注入工具类
 * 1. 通过反射将obj的字段上标记了@InjectionFiled注解的字段解析出来
 * 2. 依次查询待注入的数据
 * 3. 将查询出来结果注入到obj的 @InjectionFiled注解的字段中
 *
 * @author zuihou
 * @date 2019/11/13
 */
@Slf4j
public class InjectionCore {

    private static int MAX_DEPTH = 2;


    private final InjectionProperties ips;
    private ListeningExecutorService backgroundRefreshPools;
    private LoadingCache<InjectionFieldExtPo, Map<Serializable, Object>> caches;

    public InjectionCore(InjectionProperties ips) {
        this.ips = ips;
        InjectionProperties.GuavaCache guavaCache = ips.getGuavaCache();
        if (guavaCache.getEnabled()) {
            this.backgroundRefreshPools = MoreExecutors.listeningDecorator(
                    new ThreadPoolExecutor(guavaCache.getRefreshThreadPoolSize(), guavaCache.getRefreshThreadPoolSize(),
                            0L, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<Runnable>())
            );
            this.caches = CacheBuilder.newBuilder()
                    .maximumSize(guavaCache.getMaximumSize())
                    .refreshAfterWrite(guavaCache.getRefreshWriteTime(), TimeUnit.MINUTES)
                    .build(new CacheLoader<InjectionFieldExtPo, Map<Serializable, Object>>() {
                        @Override
                        public Map<Serializable, Object> load(InjectionFieldExtPo type) throws Exception {
                            log.info("首次读取缓存: " + type);
                            return loadMap(type);
                        }

                        // 自动刷新缓存，防止脏数据
                        @Override
                        public ListenableFuture<Map<Serializable, Object>> reload(final InjectionFieldExtPo key, Map<Serializable, Object> oldValue) throws Exception {
                            return backgroundRefreshPools.submit(() -> {
                                BaseContextHandler.setTenant(key.getTenant());
                                BaseContextHandler.setDatabase(key.getDatabase());
                                return load(key);
                            });
                        }
                    });
        }
    }

    private Map<Serializable, Object> loadMap(InjectionFieldExtPo type) {
        Object bean = null;
        if (StrUtil.isNotEmpty(type.getApi())) {
            bean = SpringUtils.getBean(type.getApi());
            log.info("建议在方法： [{}.{}]，上加入缓存，加速查询", type.getApi(), type.getMethod());
        } else {
            bean = SpringUtils.getBean(type.getFeign());
            log.info("建议在方法： [{}.{}]，上加入缓存，加速查询", type.getFeign().toString(), type.getMethod());
        }
        Map<Serializable, Object> value = ReflectUtil.invoke(bean, type.getMethod(), type.getKeys());
        return value;
    }

    /**
     * 手动注入
     *
     * @param obj        需要注入的对象、集合、IPage
     * @param isUseCache 是否使用guava缓存
     * @throws Exception
     */
    public void injection(Object obj, boolean isUseCache) {
        try {
            // key 为远程查询的对象
            // value 为 待查询的数据
            Map<InjectionFieldPo, Map<Serializable, Object>> typeMap = new HashMap();

            long parseStart = System.currentTimeMillis();
            //1. 通过反射将obj的字段上标记了@InjectionFiled注解的字段解析出来
            parse(obj, typeMap, 1, MAX_DEPTH);
            long parseEnd = System.currentTimeMillis();

            log.info("解析耗时={} ms", (parseEnd - parseStart));
            if (typeMap.isEmpty()) {
                return;
            }

            // 2. 依次查询待注入的数据
            for (Map.Entry<InjectionFieldPo, Map<Serializable, Object>> entries : typeMap.entrySet()) {
                InjectionFieldPo type = entries.getKey();
                Map<Serializable, Object> valueMap = entries.getValue();
                Set<Serializable> keys = valueMap.keySet();
                try {
                    InjectionFieldExtPo extPo = new InjectionFieldExtPo(type, keys);
                    // 根据是否启用guava缓存 决定从那里调用
                    Map<Serializable, Object> value = ips.getGuavaCache().getEnabled() && isUseCache ? caches.get(extPo) : loadMap(extPo);
                    typeMap.put(type, value);
                } catch (Exception e) {
                    log.error("远程调用方法 [{}({}).{}] 失败， 请确保系统存在该方法", type.getApi(), type.getFeign().toString(), type.getMethod(), e);
                }
            }

            long injectionStart = System.currentTimeMillis();
            log.info("批量查询耗时={} ms", (injectionStart - parseEnd));

            // 3. 将查询出来结果注入到obj的 @InjectionFiled注解的字段中
            injection(obj, typeMap, 1, MAX_DEPTH);
            long injectionEnd = System.currentTimeMillis();

            log.info("注入耗时={} ms", (injectionEnd - injectionStart));
        } catch (Exception e) {
            log.warn("注入失败", e);
        }
    }

    public void injection(Object obj) {
        injection(obj, true);
    }

    /**
     * aop方式加工
     *
     * @param pjp
     * @param anno
     * @return
     * @throws Throwable
     */
    public Object injection(ProceedingJoinPoint pjp, InjectionResult anno) throws Throwable {
        Object proceed = pjp.proceed();
        try {
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
    private void parse(Object obj, Map<InjectionFieldPo, Map<Serializable, Object>> typeMap, int depth, int maxDepth) throws Exception {
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
            InjectionField anno = field.getDeclaredAnnotation(InjectionField.class);
            if (anno == null) {
                continue;
            }
            field.setAccessible(true);

            if (isNotBaseType(obj, field)) {
                parse(field.get(obj), typeMap, depth + 1, anno.depth());
                continue;
            }

            String api = anno.api();
            Class<?> feign = anno.feign();

            if (StrUtil.isEmpty(api) && Object.class.equals(feign)) {
                log.warn("忽略解析字段: {}.{}", field.getType(), field.getName());
                continue;
            }

            String key = anno.key();
            InjectionFieldPo type = new InjectionFieldPo(anno);
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
    private void parseList(Collection list, Map typeMap, int depth, int maxDepth) throws Exception {
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
    private void injection(Object obj, Map<InjectionFieldPo, Map<Serializable, Object>> typeMap, int depth, int maxDepth) throws Exception {
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
            InjectionField anno = field.getDeclaredAnnotation(InjectionField.class);
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
            Class<?> feign = anno.feign();

            if (StrUtil.isEmpty(api) && Object.class.equals(feign)) {
                log.warn("忽略注入字段: {}.{}", field.getType(), field.getName());
                continue;
            }

            String key = anno.key();
            InjectionFieldPo type = new InjectionFieldPo(anno);
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

            Object curField = ReflectUtil.getFieldValue(obj, field);
            if (curField == null) {
                log.debug("字段[{}]为空,跳过", field.getName());
                continue;
            }

            Object newVal = valueMap.get(queryKey);
            if (ObjectUtil.isNull(newVal) && ObjectUtil.isNotEmpty(queryKey)) {
                newVal = valueMap.get(queryKey.toString());
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
