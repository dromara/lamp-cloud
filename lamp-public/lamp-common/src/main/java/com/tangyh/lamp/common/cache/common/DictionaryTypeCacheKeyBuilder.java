package com.tangyh.lamp.common.cache.common;


import com.tangyh.basic.cache.model.CacheKeyBuilder;
import com.tangyh.lamp.common.cache.CacheKeyDefinition;

/**
 * 参数 KEY
 * <p>
 * key={tenant}:dictionary_type:{type}
 * field1: {code} --> name
 * field2: {code} --> name
 *
 * <p>
 * #c_dictionary_item
 *
 * @author zuihou
 * @date 2020/9/20 6:45 下午
 */
public class DictionaryTypeCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.DICTIONARY_TYPE;
    }


}
