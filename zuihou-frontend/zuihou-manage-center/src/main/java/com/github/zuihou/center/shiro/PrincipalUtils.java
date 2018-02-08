package com.github.zuihou.center.shiro;

import org.apache.shiro.SecurityUtils;

public class PrincipalUtils {

    public static ShiroPrincipal getAccount() {
        if (SecurityUtils.getSubject() == null) {
            return new ShiroPrincipal();
        }
        return (ShiroPrincipal) SecurityUtils.getSubject().getPrincipal();
    }
}
