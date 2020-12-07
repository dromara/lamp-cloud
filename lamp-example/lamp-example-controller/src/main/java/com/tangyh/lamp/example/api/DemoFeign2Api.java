package com.tangyh.lamp.example.api;

import com.tangyh.basic.base.R;
import com.tangyh.lamp.example.api.fallback.DemoFeign2ApiFallback;
import com.tangyh.lamp.example.dto.RestTestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 测试日期类型API接口
 *
 * @author zuihou
 * @date 2019/07/24
 */
@FeignClient(name = "${lamp.feign.demo-server:lamp-demo-server}", path = "/restTemplate", fallback = DemoFeign2ApiFallback.class)
public interface DemoFeign2Api {

    @PostMapping("/fallback")
    R<RestTestDTO> fallback(@RequestParam("key") String key);

}
