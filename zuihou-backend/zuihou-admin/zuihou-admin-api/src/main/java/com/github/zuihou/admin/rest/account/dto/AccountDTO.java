package com.github.zuihou.admin.rest.account.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "Account", description = "登录帐号信息")
public class AccountDTO implements Serializable {
    private Long adminId;
    private String userName;
    private String name;
    private String appId;
    private String appName;
}
