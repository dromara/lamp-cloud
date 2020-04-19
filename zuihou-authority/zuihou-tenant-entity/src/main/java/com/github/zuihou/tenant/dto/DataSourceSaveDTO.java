package com.github.zuihou.tenant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author TaoYu
 * @date 2020/1/27
 */
@Data
public class DataSourceSaveDTO {

    @NotBlank
    @ApiModelProperty(value = "连接池名称", example = "0000")
    private String pollName;

    @NotBlank
    @ApiModelProperty(value = "JDBC driver", example = "com.mysql.cj.jdbc.Driver")
    private String driverClassName;

    @NotBlank
    @ApiModelProperty(value = "JDBC url 地址", example = "jdbc:mysql://127.0.0.1:3306/zuihou_base_0000?serverTimezone=CTT&characterEncoding=utf8&useUnicode=true&useSSL=false&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true")
    private String url;

    @NotBlank
    @ApiModelProperty(value = "JDBC 用户名", example = "root")
    private String username;

    @ApiModelProperty(value = "JDBC 密码", example = "root")
    private String password;

}
