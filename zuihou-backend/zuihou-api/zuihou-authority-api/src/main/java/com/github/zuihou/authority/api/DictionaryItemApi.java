package com.github.zuihou.authority.api;

import com.github.zuihou.authority.api.hystrix.DictionaryItemApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 数据字典API
 *
 * @author zuihou
 * @date 2019/07/26
 */
@FeignClient(name = "${zuihou.feign.authority-server:zuihou-authority-server}", path = "dictionaryItem",
        qualifier = "dictionaryItemApi", fallback = DictionaryItemApiFallback.class)
public interface DictionaryItemApi {

//    /**
//     * 根据字典编码查询字典条目的map集合
//     *
//     * @param codes
//     * @return
//     */
//    @GetMapping("/codes")
//    R<Map<String, Map<String, String>>> map(@RequestParam("codes") String[] codes);

    /**
     * 根据 code 查询字典
     *
     * @param codes
     * @return
     */
    @GetMapping("/findDictionaryItem")
    Map<Serializable, Object> findDictionaryItem(@RequestParam Set<Serializable> codes);
}
