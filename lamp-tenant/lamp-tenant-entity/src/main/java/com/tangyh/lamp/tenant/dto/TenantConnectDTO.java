package com.tangyh.lamp.tenant.dto;

import com.tangyh.lamp.tenant.enumeration.TenantConnectTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 租户连接
 *
 * @author zuihou
 * @date 2020/8/25 上午8:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
@ApiModel(value = "TenantConnectDTO", description = "租户连接")
public class TenantConnectDTO {
    @ApiModelProperty(value = "企业ID")
    private Long id;
    @NotEmpty(message = "企业编码不能为空")
    private String tenant;
    /**
     * LOCAL： 同一个数据库(物理)，链接不同的数据库实例. 从mysql.yml中读取master数据源来自动新增其他数据库
     * REMOTE： 不同的数据库(物理)，需要先在DatasourceConfig表配置链接源信息，然后指定以下字段（xxxDatasource）
     */
    @ApiModelProperty(value = "连接类型", example = "LOCAL,REMOTE")
    private TenantConnectTypeEnum connectType;

    @ApiModelProperty(value = "权限服务连接源")
    @NotNull(message = "权限服务连接源不能为空")
    private Long authorityDatasource;
    @ApiModelProperty(value = "文件服务连接源")
    private Long fileDatasource;
    @ApiModelProperty(value = "消息服务连接源")
    private Long msgDatasource;
    @ApiModelProperty(value = "认证服务连接源")
    private Long oauthDatasource;
    @ApiModelProperty(value = "网关服务连接源")
    private Long gateDatasource;
}
