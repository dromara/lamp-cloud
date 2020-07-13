package com.github.zuihou.order.controller.jwt;

import com.github.zuihou.authority.dto.auth.LoginParamDTO;
import com.github.zuihou.base.R;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.jwt.TokenUtil;
import com.github.zuihou.jwt.model.AuthInfo;
import com.github.zuihou.jwt.model.JwtUserInfo;
import com.github.zuihou.jwt.utils.JwtUtil;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证Controller
 *
 * @author zuihou
 * @date 2020年03月31日10:10:36
 */
@Slf4j
@RestController
@RequestMapping("/jwt")
@AllArgsConstructor
@Api(value = "jwt", tags = "jwt")
public class JwtController {

    @Autowired
    private TokenUtil tokenUtil;

    @PostMapping(value = "/createAuthInfo")
    public R<AuthInfo> createAuthInfo(@Validated @RequestBody LoginParamDTO login) throws BizException {
        String tenant = JwtUtil.base64Decoder(login.getTenant());
        log.info("tenant={}", tenant);

        JwtUserInfo userInfo = new JwtUserInfo(1234L, login.getAccount(), "张三");

        AuthInfo authInfo = tokenUtil.createAuthInfo(userInfo, null);
        return R.success(authInfo);
    }

    @GetMapping(value = "/getAuthInfo")
    public R<AuthInfo> getAuthInfo(@RequestParam(value = "token") String token) throws BizException {
        return R.success(tokenUtil.getAuthInfo(token));
    }

    @GetMapping(value = "/parseRefreshToken")
    public R<AuthInfo> parseRefreshToken(@RequestParam(value = "token") String token) throws BizException {
        return R.success(tokenUtil.parseRefreshToken(token));
    }

}
