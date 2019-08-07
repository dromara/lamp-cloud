package com.github.zuihou.authority.api.hystrix;

import java.util.Map;

import com.github.zuihou.authority.api.DictionaryItemApi;
import com.github.zuihou.base.R;

import org.springframework.stereotype.Component;

/**
 * 数据字典项 查询
 *
 * @author zuihou
 * @date 2019/07/26
 */
@Component
public class DictionaryItemApiFallback implements DictionaryItemApi {
    @Override
    public R<Map<String, Map<String, String>>> map(String[] codes) {
        return R.timeout();
    }
}
