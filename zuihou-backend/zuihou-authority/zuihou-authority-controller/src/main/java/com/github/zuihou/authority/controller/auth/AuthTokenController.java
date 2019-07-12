package com.github.zuihou.authority.controller.auth;


import com.github.zuihou.auth.utils.JwtUserInfo;
import com.github.zuihou.auth.utils.Token;
import com.github.zuihou.authority.dto.auth.LoginDTO;
import com.github.zuihou.authority.mananger.AuthManager;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.exception.BizException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@Api(value = "UserAuthController", tags = "用户级别的token管理")
@Slf4j
@RefreshScope
public class AuthTokenController extends BaseController {

    @Autowired
    private AuthManager authManager;

    /**
     * 获取token
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "刷新并获取token", notes = "刷新并获取token")
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public R<Token> token(@RequestParam(value = "account") String account,
                          @RequestParam(value = "password") String password) throws BizException {
        return success(authManager.generateToken(account, password));
    }


    @ApiOperation(value = "验证登录并刷新token", notes = "验证登录并刷新token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public R<LoginDTO> login(@RequestParam(value = "account") String account,
                             @RequestParam(value = "password") String password) throws BizException {
        return success(authManager.login(account, password));
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "验证token", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public R<JwtUserInfo> verify(@RequestParam(value = "token") String token) throws BizException {
        return success(authManager.validateUserToken(token));
    }


}
