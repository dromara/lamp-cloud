package com.tangyh.lamp.oauth.api;


import com.tangyh.basic.base.R;
import com.tangyh.lamp.oauth.api.hystrix.ParameterApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 参数API
 *
 * @author zuihou
 * @date 2020年04月02日22:53:56
 */
@FeignClient(name = "${lamp.feign.oauth-server:lamp-oauth-server}", path = "/parameter",
        qualifier = "parameterApi", fallback = ParameterApiFallback.class)
public interface ParameterApi {

    /**
     * 根据参数键查询参数值
     *
     * @param key    参数键
     * @param defVal 参数值
     * @return 参数值
     */
    @GetMapping("/value")
    R<String> getValue(@RequestParam(value = "key") String key, @RequestParam(value = "defVal") String defVal);
}
