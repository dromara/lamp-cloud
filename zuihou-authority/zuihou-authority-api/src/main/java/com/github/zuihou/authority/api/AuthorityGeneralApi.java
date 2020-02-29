package com.github.zuihou.authority.api;

import com.github.zuihou.authority.api.hystrix.AuthorityGeneralApiFallback;
import com.github.zuihou.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * 通用API
 *
 * @author zuihou
 * @date 2019/07/26
 */
@FeignClient(name = "${zuihou.feign.authority-server:zuihou-authority-server}", fallback = AuthorityGeneralApiFallback.class)
public interface AuthorityGeneralApi {
    /**
     * 查询系统中常用的枚举类型等
     *
     * @return
     */
    @GetMapping("/enums")
    R<Map<String, Map<String, String>>> enums();
}
