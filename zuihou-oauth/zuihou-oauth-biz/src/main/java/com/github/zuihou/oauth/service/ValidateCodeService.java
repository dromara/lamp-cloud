package com.github.zuihou.oauth.service;

import com.github.zuihou.base.R;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 验证码
 *
 * @author zuihou
 * @date 2019-10-18 17:22
 */
public interface ValidateCodeService {

    /**
     * 生成验证码
     *
     * @param key      验证码 uuid
     * @param response HttpServletResponse
     * @throws IOException
     */
    void create(String key, HttpServletResponse response) throws IOException;

    /**
     * 校验验证码
     *
     * @param key   前端上送 key
     * @param value 前端上送待校验值
     * @return 是否成功
     */
    R<Boolean> check(String key, String value);
}
