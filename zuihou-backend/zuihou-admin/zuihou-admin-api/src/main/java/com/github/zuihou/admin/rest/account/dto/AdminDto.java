package com.github.zuihou.admin.rest.account.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zuihou
 * @createTime 2017-12-15 13:50
 */
@Data
public class AdminDto implements Serializable {
    private Long id;

    /**
     * 帐号类型： 0:超级管理 1:普通管理
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * 帐号
     *
     * @mbggenerated
     */
    private String username;

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

    /**
     * 头像
     *
     * @mbggenerated
     */
    private String logoUrl;
    private String description;
}
