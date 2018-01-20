package com.github.zuihou.admin.rest.account.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zuihou
 * @createTime 2017-12-15 14:36
 */
@Data
public class AdminRegisterDto implements Serializable {

    /**
     * 帐号
     *
     * @mbggenerated
     */
    private String username;

    /**
     * 登录密码
     *
     * @mbggenerated
     */
    private String password;
    /**
     * 确认登录密码
     *
     * @mbggenerated
     */
    private String confirmPassword;
    /** 昵称 */
    private String name;

    /**
     * 邮箱
     *
     * @mbggenerated
     */
    private String email;

    /**
     * 手机
     *
     * @mbggenerated
     */
    private String mobile;

    /** 公司名称 */
    private String appName;
}
