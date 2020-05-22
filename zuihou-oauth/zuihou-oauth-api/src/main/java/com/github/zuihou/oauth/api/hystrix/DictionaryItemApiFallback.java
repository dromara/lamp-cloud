package com.github.zuihou.oauth.api.hystrix;

import com.github.zuihou.oauth.api.DictionaryItemApi;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DictionaryItemApiFallback implements FallbackFactory<DictionaryItemApi> {
    @Override
    public DictionaryItemApi create(Throwable throwable) {
        return new DictionaryItemApi() {
            @Override
            public Map<Serializable, Object> findDictionaryItem(Set<Serializable> codes) {
                log.info("findDictionaryItem fallback reason was:",throwable);
                return new HashMap<>(1);
            }
        };
    }
}
