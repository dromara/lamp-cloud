package com.github.shiro.autoconfigure;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Shiro.
 * @date 2019-07-23 11:59
 * @author zuihou
 */
@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {
    /**
     * 自定义 Realm
     */
    private Class<?> realm;
    /**
     * 登录URL
     */
    private String loginUrl;
    /**
     * 登录成功后的URL
     */
    private String successUrl;
    /**
     * 未授权的 URL
     */
    private String unauthorizedUrl;
    /**
     * 过滤链
     */
    private Map<String, List<String>> filterChainDefinitions;


    public Class<?> getRealm() {
        return realm;
    }

    public void setRealm(Class<?> realm) {
        this.realm = realm;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

    public Map<String, List<String>> getFilterChainDefinitions() {
        return filterChainDefinitions;
    }

    public void setFilterChainDefinitions(Map<String, List<String>> filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }


}
