package com.github.zuihou.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO implements Serializable {
    /**
     * token
     */
    private String token;
    /**
     * 有效时间：单位：秒
     */
    private Integer expire;
}
