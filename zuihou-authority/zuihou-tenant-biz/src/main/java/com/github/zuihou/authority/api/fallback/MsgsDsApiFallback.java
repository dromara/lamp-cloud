package com.github.zuihou.authority.api.fallback;

import com.github.zuihou.authority.api.MsgsDsApi;
import com.github.zuihou.base.R;
import org.springframework.stereotype.Component;

/**
 * 实现
 *
 * @author zuihou
 * @date 2020年04月05日18:20:22
 */
@Component
public class MsgsDsApiFallback implements MsgsDsApi {
    @Override
    public R<Boolean> init(String tenant) {
        return R.timeout();
    }


    @Override
    public R<Boolean> remove(String tenant) {
        return R.timeout();
    }
}
