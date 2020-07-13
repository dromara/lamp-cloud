package com.github.zuihou.order.api;

import com.github.zuihou.base.R;
import com.github.zuihou.order.api.fallback.DemoFeign2ApiFallback;
import com.github.zuihou.order.dto.RestTestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 测试日期类型API接口
 *
 * @author zuihou
 * @date 2019/07/24
 */
@FeignClient(name = "${zuihou.feign.demo-server:zuihou-demo-server}", path = "/restTemplate", fallback = DemoFeign2ApiFallback.class)
public interface DemoFeign2Api {

    @PostMapping("/fallback")
    R<RestTestDTO> fallback(@RequestParam("key") String key);

}
