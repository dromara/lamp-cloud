package com.github.zuihou.authority.service.common;

import com.github.zuihou.authority.entity.common.DictionaryItem;
import com.github.zuihou.base.service.SuperCacheService;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 业务接口
 * 字典项
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
public interface DictionaryItemService extends SuperCacheService<DictionaryItem> {
    /**
     * 根据类型查询字典
     *
     * @param types
     * @return
     */
    Map<String, Map<String, String>> map(String[] types);

    /**
     * 根据类型编码查询字典项
     *
     * @param codes
     * @return
     */
    Map<Serializable, Object> findDictionaryItem(Set<Serializable> codes);
}
