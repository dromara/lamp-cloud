package com.github.zuihou.center.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShiroPrincipal implements Serializable {
    private String appId;
    private String appName;
    private String name;
    private Long adminId;
    private String userName;

    @Override
    public String toString() {
        return this.getUserName();
    }
}
