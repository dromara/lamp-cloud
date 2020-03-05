package com.github.zuihou.authority.controller.auth;


import cn.hutool.core.util.StrUtil;
import com.github.zuihou.auth.utils.JwtUserInfo;
import com.github.zuihou.authority.dto.auth.LoginDTO;
import com.github.zuihou.authority.dto.auth.LoginParamDTO;
import com.github.zuihou.authority.service.auth.ValidateCodeService;
import com.github.zuihou.authority.service.auth.impl.AuthManager;
import com.github.zuihou.base.R;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 客户端获取token
 * jwt token管理
 *
 * @author zuihou
 * @date Created on 2019/5/28 9:17
 */
@RestController
@RequestMapping("/anno")
@Api(value = "UserAuthController", tags = "登录")
@Slf4j
public class LoginController {

    @Autowired
    private AuthManager authManager;

    @Autowired
    private ValidateCodeService validateCodeService;

    /**
     * 管理员登录 zuihou-admin-ui 系统
     *
     * @return
     * @throws BizException
     */
    @ApiOperation(value = "超级管理员登录", notes = "超级管理员登录")
    @PostMapping(value = "/admin/login")
    public R<LoginDTO> loginAdminTx(@Validated @RequestBody LoginParamDTO login) throws BizException {
        log.info("account={}", login.getAccount());
        if (this.validateCodeService.check(login.getKey(), login.getCode())) {
            return this.authManager.adminLogin(login.getAccount(), login.getPassword());
        }
        return R.success(null);
    }


    /**
     * 租户登录 zuihou-ui 系统
     *
     * @param login
     * @return
     * @throws BizException
     */
    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping(value = "/login")
    public R<LoginDTO> loginTx(@Validated @RequestBody LoginParamDTO login) throws BizException {
        log.info("account={}", login.getAccount());
        if (StrUtil.isEmpty(login.getTenant())) {
            login.setAccount(BaseContextHandler.getTenant());
        }
        if (this.validateCodeService.check(login.getKey(), login.getCode())) {
            return this.authManager.login(login.getTenant(), login.getAccount(), login.getPassword());
        }
        return R.success(null);
    }

    /**
     * 刷新token
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "仅供测试使用的登录方法", notes = "仅供测试使用的登录方法")
    @GetMapping(value = "/token")
    @Deprecated
    public R<LoginDTO> tokenTx(
            @RequestParam(value = "tenant", required = false) String tenant,
            @RequestParam(value = "account") String account,
            @RequestParam(value = "password") String password) throws BizException {
        if (StrUtil.isEmpty(tenant)) {
            tenant = BaseContextHandler.getTenant();
        }
        return this.authManager.login(tenant, account, password);
    }


    /**
     * 验证token
     *
     * @param token
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "验证token", notes = "验证token")
    @GetMapping(value = "/verify")
    public R<JwtUserInfo> verify(@RequestParam(value = "token") String token) throws BizException {
        return R.success(this.authManager.validateUserToken(token));
    }

    /**
     * 验证验证码
     *
     * @param key  验证码唯一uuid key
     * @param code 验证码
     * @return
     * @throws BizException
     */
    @ApiOperation(value = "验证验证码", notes = "验证验证码")
    @GetMapping(value = "/check")
    public R<Boolean> check(@RequestParam(value = "key") String key, @RequestParam(value = "code") String code) throws BizException {
        return R.success(this.validateCodeService.check(key, code));
    }

    @ApiOperation(value = "验证码", notes = "验证码")
    @GetMapping(value = "/captcha", produces = "image/png")
    public void captcha(@RequestParam(value = "key") String key, HttpServletResponse response) throws IOException {
        this.validateCodeService.create(key, response);
    }

}
