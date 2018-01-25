package com.github.zuihou.auth.controller;


import com.github.zuihou.auth.common.jwt.TokenVo;
import com.github.zuihou.auth.service.ApplicationService;
import com.github.zuihou.base.Result;
import com.github.zuihou.exception.BizException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第三方应用通过API获取token
 * Created by ace on 2017/9/10.
 */
@RestController
@RequestMapping("app")
@Api(value = "应用token申请", description = "对外第三方应用的token申请")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    /**
     * 获取 第三方 token
     * @param appId appid
     * @param appSecret 密码
     * @return Result<TokenVo>
     * @throws Exception
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Result<TokenVo> applyToken(String appId, String appSecret) throws BizException {
        return Result.success(applicationService.applyToken(appId, appSecret));
    }

    /** 刷新token */
    /** 验证token */
}
