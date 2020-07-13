package com.github.zuihou.order.api.fallback;

import com.github.zuihou.base.R;
import com.github.zuihou.order.api.DemoFeign2Api;
import com.github.zuihou.order.dto.RestTestDTO;
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
