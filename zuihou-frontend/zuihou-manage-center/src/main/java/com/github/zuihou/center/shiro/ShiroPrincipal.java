package com.github.zuihou.center.shiro;

import java.io.Serializable;

import lombok.Data;

@Data
public class ShiroPrincipal implements Serializable {
    private String account;
    private Long userId;
    private String photo;
    private String nickName;
    private String token;

    @Override
    public String toString() {
        return this.getNickName();
    }
}
