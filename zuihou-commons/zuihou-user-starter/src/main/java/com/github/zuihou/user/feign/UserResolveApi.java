package com.github.zuihou.user.feign;

import com.github.zuihou.base.R;
import com.github.zuihou.user.feign.fallback.UserResolveApiFallback;
import com.github.zuihou.user.model.SysUser;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/07/10
 */
@FeignClient(name = "${zuihou.feign.authority-server:zuihou-authority-server}", fallbackFactory = UserResolveApiFallback.class)
public interface UserResolveApi {

    /**
     * 根据id 查询用户详情
     *
     * @param id
     * @param userQuery
     * @return
     */
    @PostMapping(value = "/user/anno/id/{id}")
    R<SysUser> getById(@PathVariable("id") Long id, @RequestBody UserQuery userQuery);
}
