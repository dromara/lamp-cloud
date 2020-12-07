package com.tangyh.lamp.tenant.dto;

import com.tangyh.lamp.tenant.enumeration.TenantConnectTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author zuihou
 * @date 2020/8/26 下午11:25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
@ApiModel(value = "DataSourcePropertyDTO", description = "数据源链接")
public class DataSourcePropertyDTO {
    /**
     * LOCAL： 同一个数据库(物理)，链接不同的数据库实例. 从mysql.yml中读取master数据源来自动新增其他数据库
     * REMOTE： 不同的数据库(物理)，需要先在DatasourceConfig表配置链接源信息，然后指定以下字段（xxxDatasource）
     */
    @ApiModelProperty(value = "连接类型", example = "LOCAL,REMOTE")
    private TenantConnectTypeEnum type;
    /**
     * 连接池名称(只是一个名称标识)</br> 默认是配置文件上的名称
     */
    private String poolName;
    /**
     * JDBC driver
     */
    private String driverClassName;
    /**
     * JDBC url 地址
     */
    private String url;
    /**
     * JDBC 用户名
     */
    private String username;
    /**
     * JDBC 密码
     */
    private String password;
}
