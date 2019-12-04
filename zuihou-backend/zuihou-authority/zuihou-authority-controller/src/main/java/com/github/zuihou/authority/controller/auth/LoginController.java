package com.github.zuihou.authority.controller.auth;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.zuihou.auth.utils.JwtUserInfo;
import com.github.zuihou.authority.dto.auth.LoginDTO;
import com.github.zuihou.authority.service.auth.ValidateCodeService;
import com.github.zuihou.authority.service.auth.impl.AuthManager;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.exception.BizException;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 客户端获取token
 * jwt token管理
 *
 * @author luosh
 * @date Created on 2019/5/28 9:17
 */
@RestController
@RequestMapping("/anno")
@Api(value = "UserAuthController", tags = "登录")
@Slf4j
public class LoginController extends BaseController {

    @Autowired
    private AuthManager authManager;

    @Autowired
    private ValidateCodeService validateCodeService;

    /**
     * 超级管理员登录
     * @param key
     * @param code
     * @param account
     * @param password
     * @return
     * @throws BizException
     */
    @ApiOperation(value = "超级管理员登录", notes = "超级管理员登录")
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public R<LoginDTO> loginAdminTx(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "account") String account,
            @RequestParam(value = "password") String password) throws BizException {
        log.info("account={}", account);
        if (validateCodeService.check(key, code)) {
            return authManager.adminLogin(account, password);
        }
        return success(null);
    }


    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public R<LoginDTO> loginTx(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "tenant", required = false) String tenant,
            @RequestParam(value = "account") String account,
            @RequestParam(value = "password") String password) throws BizException {
        log.info("account={}", account);
        if (StrUtil.isEmpty(tenant)) {
            tenant = BaseContextHandler.getTenant();
        }
        if (validateCodeService.check(key, code)) {
            return authManager.login(tenant, account, password);
        }
        return success(null);
    }

    /**
     * 刷新token
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "仅供测试使用", notes = "仅供测试使用")
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    @Deprecated
    public R<LoginDTO> tokenTx(
            @RequestParam(value = "tenant", required = false) String tenant,
            @RequestParam(value = "account") String account,
            @RequestParam(value = "password") String password) throws BizException {
        if (StrUtil.isEmpty(tenant)) {
            tenant = BaseContextHandler.getTenant();
        }
        return authManager.login(tenant, account, password);
    }


    /**
     * 验证token
     *
     * @param token
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "验证token", notes = "验证token")
    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public R<JwtUserInfo> verify(@RequestParam(value = "token") String token) throws BizException {
        return success(authManager.validateUserToken(token));
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
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public R<Boolean> check(@RequestParam(value = "key") String key, @RequestParam(value = "code") String code) throws BizException {
        return success(validateCodeService.check(key, code));
    }

    @GetMapping("/captcha")
    public void captcha(@RequestParam(value = "key") String key, HttpServletRequest request, HttpServletResponse response) throws IOException {
        validateCodeService.create(key, response);
    }

}
