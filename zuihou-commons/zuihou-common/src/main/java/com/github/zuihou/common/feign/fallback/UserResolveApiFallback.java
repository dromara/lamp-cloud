package com.github.zuihou.common.feign.fallback;

import com.github.zuihou.base.R;
import com.github.zuihou.common.feign.UserQuery;
import com.github.zuihou.common.feign.UserResolveApi;
import com.github.zuihou.common.model.SysUser;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2019/07/10
 */
@Component
@Slf4j
public class UserResolveApiFallback implements FallbackFactory<UserResolveApi> {


    @Override
    public UserResolveApi create(Throwable throwable) {
        return new UserResolveApi() {
            /**
             * 根据id 查询用户详情
             *
             * @param id
             * @param userQuery
             * @return
             */
            @Override
            public R<SysUser> getById(Long id, UserQuery userQuery) {
                log.error("通过用户名查询用户异常:{}", id, throwable);
                return R.timeout();
            }
        };
    }
}
