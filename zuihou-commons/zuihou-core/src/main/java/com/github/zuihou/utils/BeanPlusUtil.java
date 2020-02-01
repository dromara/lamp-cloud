package com.github.zuihou.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean增强类工具类
 *
 * <p>
 * 把一个拥有对属性进行set和get方法的类，我们就可以称之为JavaBean。
 * </p>
 *
 * @author zuihou
 * @since 3.1.2
 */
public class BeanPlusUtil extends BeanUtil {

    /**
     * 转换 list
     *
     * @param sourceList
     * @param destinationClass
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T, E> List<T> toBeanList(Collection<E> sourceList, Class<T> destinationClass) {
        if (sourceList == null || sourceList.isEmpty() || destinationClass == null) {
            return Collections.emptyList();
        }
        return sourceList.parallelStream()
                .filter(item -> item != null)
                .map((source) -> toBean(source, destinationClass))
                .collect(Collectors.toList());
    }

    /**
     * 转化Page 对象
     *
     * @param page
     * @param destinationClass
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T, E> IPage<T> toBeanPage(IPage<E> page, Class<T> destinationClass) {
        if (page == null || destinationClass == null) {
            return null;
        }
        IPage<T> newPage = new Page<>(page.getCurrent(), page.getSize());
        newPage.setPages(page.getPages());
        newPage.setTotal(page.getTotal());

        List<E> list = page.getRecords();
        if (CollUtil.isEmpty(list)) {
            return newPage;
        }

        List<T> destinationList = toBeanList(list, destinationClass);

        newPage.setRecords(destinationList);
        return newPage;
    }

}
