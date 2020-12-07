package com.tangyh.lamp.example.controller.security;

import com.tangyh.basic.annotation.security.PreAuth;
import com.tangyh.basic.annotation.user.LoginUser;
import com.tangyh.basic.base.R;
import com.tangyh.basic.security.model.SysUser;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author zuihou
 * @date 2020年03月31日10:10:36
 */
@Slf4j
@RestController
@RequestMapping("/uri")
@AllArgsConstructor
@Api(value = "uri", tags = "uri")
@PreAuth(replace = "authority:application:")
public class UriAuthController {

    @PostMapping(value = "/user")
    public R user(@ApiIgnore @LoginUser SysUser user) {
        log.info("user={}", user);
        return R.success(user);
    }

    @PostMapping(value = "/userFull")
    public R userFull(@ApiIgnore @LoginUser(isFull = true) SysUser user) {
        log.info("user={}", user);
        return R.success(user);
    }


    @PostMapping(value = "/enabledFalse")
    @PreAuth(enabled = false, value = "hasAnyPermission('authority:user:add')")
    public R hasUserAddByFalse() {
        log.info("authentication1");
        return R.success();
    }

    @PostMapping(value = "/authentication1")
    @PreAuth(value = "hasAnyPermission('authority:user:add')")
    public R hasUserAdd() {
        log.info("authentication1");
        return R.success();
    }

    @PreAuth(value = "hasAnyPermission('{}add')")
    @PostMapping(value = "/replace")
    public R replace() {
        log.info("authentication2");
        return R.success();
    }

    @PreAuth(value = "hasAnyRole('SUPER_ADMIN','TEST')")
    @PostMapping(value = "/hasAnyRole1")
    public R hasAnyRole1() {
        log.info("authentication2");
        return R.success();
    }

    @PreAuth(value = "hasAnyRole('TEST2','TEST')")
    @PostMapping(value = "/hasAnyRole2")
    public R hasAnyRole2() {
        log.info("authentication2");
        return R.success();
    }


}
