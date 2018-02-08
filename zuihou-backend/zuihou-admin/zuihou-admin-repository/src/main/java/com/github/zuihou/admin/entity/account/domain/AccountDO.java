package com.github.zuihou.admin.entity.account.domain;

import lombok.Data;

@Data
public class AccountDO {
    private Long adminId;
    private String userName;
    private String name;
    private String appId;
    private String appName;
}
