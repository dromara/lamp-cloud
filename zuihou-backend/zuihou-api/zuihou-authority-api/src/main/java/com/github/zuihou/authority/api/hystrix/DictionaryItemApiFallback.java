package com.github.zuihou.authority.api.hystrix;

import com.github.zuihou.authority.api.DictionaryItemApi;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 数据字典项 查询
 *
 * @author zuihou
 * @date 2019/07/26
 */
@Component
public class DictionaryItemApiFallback implements DictionaryItemApi {
//    @Override
//    public R<Map<String, Map<String, String>>> map(String[] codes) {
//        return R.timeout();
//    }

    @Override
    public Map<Serializable, Object> findDictionaryItem(Set<Serializable> codes) {
        return new HashMap<>(1);
    }
}
