package com.tangyh.lamp.example.api.fallback;

import com.tangyh.basic.base.R;
import com.tangyh.lamp.example.api.DemoFeign2Api;
import com.tangyh.lamp.example.dto.RestTestDTO;
import org.springframework.stereotype.Component;

/**
 * @author zuihou
 * @date 2020/6/10 下午10:46
 */
@Component
public class DemoFeign2ApiFallback implements DemoFeign2Api {
    @Override
    public R<RestTestDTO> fallback(String key) {
        return R.timeout();
    }
}
