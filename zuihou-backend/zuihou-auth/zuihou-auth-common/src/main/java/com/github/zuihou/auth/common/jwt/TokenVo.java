package com.github.zuihou.auth.common.jwt;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zuihou
 * @createTime 2017-12-15 11:22
 */
@Data
public class TokenVo implements Serializable{
    /** token */
    private String token;
    /** 有效时间：单位：秒 */
    private Integer expire;

    public TokenVo() {
        super();
    }

    public TokenVo(String token, Integer expire) {
        this.token = token;
        this.expire = expire;
    }
}
