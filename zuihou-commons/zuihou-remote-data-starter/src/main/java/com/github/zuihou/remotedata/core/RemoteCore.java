package com.github.zuihou.remotedata.core;

import cn.hutool.core.util.ReflectUtil;
import com.github.zuihou.model.RemoteData;
import com.github.zuihou.remotedata.annonation.RemoteField;
import com.github.zuihou.remotedata.annonation.RemoteResult;
import com.github.zuihou.remotedata.configuration.RemoteProperties;
import com.github.zuihou.remotedata.facade.DefaultRemoteResultParser;
import com.github.zuihou.remotedata.facade.RemoteResultParser;
import com.github.zuihou.utils.SpringUtils;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ace
 * @create 2018/2/2.
 */
@Slf4j
public class RemoteCore {

    private Map<String, RemoteField> remoteFieldMap;
    private ListeningExecutorService backgroundRefreshPools;
    private LoadingCache<String, Map<String, Object>> caches;

    public RemoteCore(RemoteProperties remoteProperties) {
        this.backgroundRefreshPools = MoreExecutors.listeningDecorator(
                new ThreadPoolExecutor(remoteProperties.getGuavaCacheRefreshThreadPoolSize(), remoteProperties.getGuavaCacheRefreshThreadPoolSize(),
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>())
        );
        this.remoteFieldMap = new HashMap<String, RemoteField>();
        this.caches = CacheBuilder.newBuilder()
                .maximumSize(remoteProperties.getGuavaCacheNumMaxSize())
                .refreshAfterWrite(remoteProperties.getGuavaCacheRefreshWriteTime(), TimeUnit.MINUTES)
                .build(new CacheLoader<String, Map<String, Object>>() {
                    @Override
                    public Map<String, Object> load(String key) throws Exception {
                        log.info("首次读取缓存: " + key);
                        RemoteField remoteField = remoteFieldMap.get(key);
                        Object bean = SpringUtils.getBean(remoteField.api());
                        Map<String, Object> invoke = ReflectUtil.invoke(bean, remoteField.method(), remoteField.key());
                        return invoke;
                    }

                    @Override
                    public ListenableFuture<Map<String, Object>> reload(final String key, Map<String, Object> oldValue) throws Exception {
                        return backgroundRefreshPools.submit(() -> {
                            log.info("异步刷新缓存: " + key);
                            RemoteField remoteField = remoteFieldMap.get(key);
                            Object bean = SpringUtils.getBean(remoteField.api());
                            Map<String, Object> invoke = ReflectUtil.invoke(bean, remoteField.method(), remoteField.key());
                            return invoke;
                        });
                    }
                });

    }


    /**
     * aop方式加工
     *
     * @param pjp
     * @param anno
     * @return
     * @throws Throwable
     */
    public Object injectionAnno(ProceedingJoinPoint pjp, RemoteResult anno) throws Throwable {
        Object proceed = pjp.proceed();
        try {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            Method m = signature.getMethod();
            ParameterizedType parameterizedType = (ParameterizedType) m.getGenericReturnType();
            Type rawType = parameterizedType.getRawType();
            List<?> result = null;
            // 获取当前方法的返回值
            Type[] types = parameterizedType.getActualTypeArguments();
            Class clazz = ((Class) types[0]);
            // 非list直接返回
            if (anno.resultParser().equals(DefaultRemoteResultParser.class) && ((Class) rawType).isAssignableFrom(List.class)) {
                result = (List<?>) proceed;
                injectionList(clazz, result);
                return result;
            } else {
                RemoteResultParser bean = SpringUtils.getBean(anno.resultParser());
                result = bean.parser(proceed);
                injectionList(clazz, result);
                return proceed;
            }
        } catch (Exception e) {
            log.error("某属性数据聚合失败", e);
            return proceed;
        }
    }

    /**
     * 手动调用进行配置合并
     *
     * @param clazz
     * @param result
     * @throws ExecutionException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void injectionList(Class clazz, List<?> result) throws ExecutionException {
        Field[] fields = ReflectUtil.getFields(clazz);
        List<Field> injectionFields = new ArrayList<>();
        Map<String, Map<String, Object>> cacheParam = new HashMap<>();
        String className = clazz.getName();
        // 获取属性
        for (Field field : fields) {
            RemoteField annotation = field.getAnnotation(RemoteField.class);
            if (annotation == null) {
                continue;
            }
            injectionFields.add(field);
            String args = annotation.key();
            // 表示该属性需要将值聚合成条件远程查询
            if (annotation.isValueNeedMerge()) {
                StringBuffer sb = new StringBuffer("");
                Set<String> ids = new HashSet<>();
                result.stream().forEach(obj -> {
                    field.setAccessible(true);
                    Object o = null;
                    try {
                        o = field.get(obj);
                        if (o != null) {
                            if (!ids.contains(o)) {
                                ids.add(o.toString());
                                sb.append(o.toString()).append(",");
                            }
                        }
                    } catch (IllegalAccessException e) {
                        log.error("数据属性加工失败:" + field, e);
                        throw new RuntimeException("数据属性加工失败:" + field, e);
                    }

                });
                args = sb.substring(0, sb.length() - 1);
            } else {
                String key = className + field.getName();
                remoteFieldMap.put(key, annotation);
                // 从缓存获取
                Map<String, Object> value = caches.get(key);
                if (value != null) {
                    cacheParam.put(field.getName(), value);
                    continue;
                }
            }
            Object bean = SpringUtils.getBean(annotation.api());
            Map<String, Object> value = ReflectUtil.invoke(bean, annotation.method(), args);
            cacheParam.put(field.getName(), value);
        }
        result.stream().forEach(obj -> {
            injectionObjFieldValue(obj, injectionFields, cacheParam);
        });
    }

    /**
     * 手动对单个结果进行配置合并
     *
     * @param clazz        需要合并的类class
     * @param injectionObj 需要合并的实例对象
     * @throws ExecutionException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void injectionOne(Class clazz, Object injectionObj) {
        // 获取类的所有字段
        Field[] fields = ReflectUtil.getFields(clazz);
        List<Field> injectionFields = new ArrayList<>();
        Map<String, Map<String, Object>> cacheParam = new HashMap<>();
        String className = clazz.getName();
        // 获取属性
        for (Field field : fields) {
            RemoteField annotation = field.getAnnotation(RemoteField.class);
            if (annotation == null) {
                continue;
            }

            injectionFields.add(field);
            String args = annotation.key();
            // 表示该属性需要将值聚合成条件远程查询
            if (annotation.isValueNeedMerge()) {
                field.setAccessible(true);
                Object curField = ReflectUtil.getFieldValue(injectionObj, field);

                if (curField == null) {
                    continue;
                }
                if (curField instanceof RemoteData) {
                    RemoteData remoteData = (RemoteData) curField;
                    args = remoteData.getKey().toString();
                } else {
                    args = curField.toString();
                }
            } else {
                String key = className + field.getName();
                remoteFieldMap.put(key, annotation);
                // 从缓存获取
                try {
                    Map<String, Object> value = caches.get(key);
                    if (value != null) {
                        cacheParam.put(field.getName(), value);
                        continue;
                    }
                } catch (ExecutionException e) {
                    log.warn("从缓存中加载数据失败: " + field, e);
                }
            }
            Object bean = SpringUtils.getBean(annotation.api());
            Map<String, Object> value = ReflectUtil.invoke(bean, annotation.method(), args);
            cacheParam.put(field.getName(), value);
        }

        injectionObjFieldValue(injectionObj, injectionFields, cacheParam);
    }

    /**
     * 合并对象属性值
     *
     * @param fieldObj     对象
     * @param remoteFields 需要注入值的字段集合
     * @param cacheParam   缓存的参数-值 map
     */
    private void injectionObjFieldValue(Object fieldObj, List<Field> remoteFields, Map<String, Map<String, Object>> cacheParam) {
        for (Field field : remoteFields) {
            Object curField = ReflectUtil.getFieldValue(fieldObj, field);
            if (curField == null) {
                log.debug("字段[{}]为空,跳过", field.getName());
                continue;
            }
            if (curField instanceof RemoteData) {
                RemoteData remoteData = (RemoteData) curField;

                if (!cacheParam.containsKey(field.getName())) {
                    continue;
                }

                remoteData.setData(cacheParam.get(field.getName()).get(String.valueOf(remoteData.getKey())));
//                ReflectUtil.setFieldValue(fieldObj, field, cacheParam.get(remoteData.getKey()));
            } else {
                if (cacheParam.get(field.getName()).containsKey(String.valueOf(curField))) {
                    ReflectUtil.setFieldValue(fieldObj, field, cacheParam.get(field.getName()).get(curField.toString()));
                }
            }
        }
    }
}
