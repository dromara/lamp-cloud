package com.github.zuihou.authority.api;

import java.util.Map;

import com.github.zuihou.authority.api.hystrix.DictionaryItemApiFallback;
import com.github.zuihou.base.R;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 数据字典API
 *
 * @author zuihou
 * @date 2019/07/26
 */
@FeignClient(name = "${zuihou.feign.authority-server:zuihou-authority-server}", fallback = DictionaryItemApiFallback.class)
public interface DictionaryItemApi {

    /**
     * 根据字典编码查询字典条目的map集合
     *
     * @param codes
     * @return
     */
    @GetMapping("/dictionaryItem/codes")
    R<Map<String, Map<String, String>>> map(@RequestParam("codes") String[] codes);
}
