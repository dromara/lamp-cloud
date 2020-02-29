package com.github.zuihou.authority.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.authority.entity.common.DictionaryItem;

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
public interface DictionaryItemService extends IService<DictionaryItem> {
    /**
     * 根据字典编码查询字典
     *
     * @param codes
     * @return
     */
    Map<String, Map<String, String>> map(String[] codes);

    Map<Serializable, Object> findDictionaryItem(Set<Serializable> codes);
}
