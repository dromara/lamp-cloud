package com.github.zuihou.authority.api.hystrix;

import com.github.zuihou.auth.utils.Token;
import com.github.zuihou.authority.api.LogApi;
import com.github.zuihou.authority.entity.common.Log;
import com.github.zuihou.base.R;

import org.springframework.stereotype.Component;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/07/02
 */
@Component
public class LogApiHystrix implements LogApi {
    @Override
    public R<Token> save(Log log) {
        return R.timeout();
    }
}
