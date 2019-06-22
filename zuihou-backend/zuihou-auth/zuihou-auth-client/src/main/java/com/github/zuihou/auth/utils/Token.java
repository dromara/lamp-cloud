package com.github.zuihou.auth.utils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zuihou¬
 * @createTime 2017-12-15 11:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {
    /**
     * token
     */
    private String token;
    /**
     * 有效时间：单位：秒
     */
    private Integer expire;

}
