package com.tangyh.lamp.example.controller.jwt;

import com.tangyh.basic.base.R;
import com.tangyh.basic.exception.BizException;
import com.tangyh.basic.jwt.TokenUtil;
import com.tangyh.basic.jwt.model.AuthInfo;
import com.tangyh.basic.jwt.model.JwtUserInfo;
import com.tangyh.lamp.authority.dto.auth.LoginParamDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Api(value = "jwt", tags = "jwt")
@RequiredArgsConstructor
public class JwtController {

    private final TokenUtil tokenUtil;

    @PostMapping(value = "/createAuthInfo")
    public R<AuthInfo> createAuthInfo(@Validated @RequestBody LoginParamDTO login) throws BizException {
//        String tenant = JwtUtil.base64Decoder(login.getTenant());
//        log.info("tenant={}", tenant);

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
