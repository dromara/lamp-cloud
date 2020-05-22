package com.github.zuihou.authority.api.hystrix;

import com.github.zuihou.authority.api.UserBizApi;
import com.github.zuihou.base.R;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户API熔断
 *
 * @author zuihou
 * @date 2019/07/23
 */
@Component
@Slf4j
public class UserBizApiFallback implements FallbackFactory<UserBizApi> {
    @Override
    public UserBizApi create(Throwable throwable) {
        return new UserBizApi() {
            @Override
            public R<List<Long>> findAllUserId() {
                log.info("findAllUserId fallback reason was:",throwable);
                return R.timeout();


            }
        };
    }

}
