package com.github.zuihou.auth.common.jwt;

import java.io.Serializable;

/**
 * Created by ace on 2017/9/10.
 */
public class JWTInfo implements Serializable, IJWTInfo {
    private String userName;
    private Long adminId;
    private String name;
    private String appId;

    public JWTInfo(String userName, Long adminId, String name, String appId) {
        this.userName = userName;
        this.adminId = adminId;
        this.name = name;
        this.appId = appId;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 重写equals 方法， 必须appid,username,adminId都相等， 两个对象相等
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JWTInfo jwtInfo = (JWTInfo) o;

        if (appId != null ? !appId.equals(jwtInfo.appId) : jwtInfo.appId != null) {
            return false;
        }
        if (userName != null ? !userName.equals(jwtInfo.userName) : jwtInfo.userName != null) {
            return false;
        }
        return adminId != null ? adminId.equals(jwtInfo.adminId) : jwtInfo.adminId == null;

    }

    @Override
    public int hashCode() {
        int result = appId != null ? appId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (adminId != null ? adminId.hashCode() : 0);
        return result;
    }
}
