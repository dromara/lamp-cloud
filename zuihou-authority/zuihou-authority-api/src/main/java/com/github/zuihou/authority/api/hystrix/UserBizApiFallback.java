package com.github.zuihou.authority.api.hystrix;

import com.github.zuihou.authority.api.UserBizApi;
import com.github.zuihou.base.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户API熔断
 *
 * @author zuihou
 * @date 2019/07/23
 */
@Component
public class UserBizApiFallback implements UserBizApi {
    @Override
    public R<List<Long>> findAllUserId() {
        return R.timeout();
    }
}
