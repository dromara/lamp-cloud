package com.github.zuihou.utils;

import java.util.Iterator;
import java.util.function.Function;

import com.google.common.collect.ImmutableMap;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Map 类增强
 *
 * @author zuihou
 * @date 2019/07/29
 */
public class MapHelper {
    /**
     * 增强 guava 的 Maps.uniqueIndex方法
     * <p>
     * guava 的 Maps.uniqueIndex方法可以实现：
     * <br>
     * 将 list&lt;V&gt 转成 Map&lt;K , V&gt
     * K 需要自己指定， V不能指定
     * </p>
     * <p>
     * 本方法实现了：
     * <p>
     * 将 list&lt;V&gt 转成 Map&lt;K , M&gt
     * K 需要自己指定， M需要自己指定
     * </p>
     *
     * @param values        需要转换的集合 可以是任何实现了 Iterable 接口的集合(如List, Set, Collection)
     * @param keyFunction   转换后Map的键的转换方式
     * @param valueFunction 转换后Map的值的转换方式
     * @param <K>           转换后Map的键 类型
     * @param <V>           转换前Iterable的迭代类型
     * @param <M>           转换后Map的值 类型
     * @return
     */
    public static <K, V, M> ImmutableMap<K, M> uniqueIndex(Iterable<V> values, Function<? super V, K> keyFunction, Function<? super V, M> valueFunction) {
        Iterator<V> iterator = values.iterator();
        checkNotNull(keyFunction);
        ImmutableMap.Builder<K, M> builder = ImmutableMap.builder();
        while (iterator.hasNext()) {
            V value = iterator.next();
            builder.put(keyFunction.apply(value), valueFunction.apply(value));
        }
        try {
            return builder.build();
        } catch (IllegalArgumentException duplicateKeys) {
            throw new IllegalArgumentException(
                    duplicateKeys.getMessage()
                            + ".若要在键下索引多个值，请使用: Multimaps.index.");
        }
    }

}
