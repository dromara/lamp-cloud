package com.github.zuihou.auth.utils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jwt 存储的 内容
 *
 * @author zuihou
 * @date 2018/11/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserInfo implements Serializable {
    /**
     * 账号id
     */
    private Long userId;
    /**
     * 账号
     */
    private String account;
    /**
     * 姓名
     */
    private String nickName;

    /**
     * 当前登录人单位id
     */
    private String orgId;

    /**
     * 当前登录人部门id
     */
    private String departmentId;

    /**
     * 实体类型
     */
    private String accountType;
}
