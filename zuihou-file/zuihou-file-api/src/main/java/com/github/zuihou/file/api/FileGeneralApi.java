package com.github.zuihou.file.api;

import com.github.zuihou.base.R;
import com.github.zuihou.file.api.fallback.FileGeneralApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * 通用API
 *
 * @author zuihou
 * @date 2019/07/26
 */
@FeignClient(name = "${zuihou.feign.file-server:zuihou-file-server}", fallback = FileGeneralApiFallback.class)
public interface FileGeneralApi {
    /**
     * 查询系统中常用的枚举类型等
     *
     * @return
     */
    @GetMapping("/enums")
    R<Map<String, Map<String, String>>> enums();
}
