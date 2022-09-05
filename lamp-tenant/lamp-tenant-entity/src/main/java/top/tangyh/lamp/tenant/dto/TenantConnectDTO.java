package top.tangyh.lamp.tenant.dto;

import top.tangyh.lamp.model.enumeration.system.TenantConnectTypeEnum;
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
    @NotNull(message = "ID不能为空")
    private Long id;
    @NotEmpty(message = "企业编码不能为空")
    private String tenant;
    /**
     * LOCAL： 同一个数据库(物理)，链接不同的数据库实例. 从mysql.yml、oracle.yml、sqlserver.yml中读取master数据源来自动新增其他数据库
     * REMOTE： 不同的数据库(物理)，需要先在DatasourceConfig表配置链接源信息，然后指定以下字段（xxxDatasource）
     */
    @ApiModelProperty(value = "连接类型", example = "LOCAL,REMOTE")
    @NotNull(message = "连接类型不能为空")
    private TenantConnectTypeEnum connectType;

    /**
     * 数据源id
     */
    @ApiModelProperty(value = "基础库数据源配置")
    private Long baseDatasourceId;
    @ApiModelProperty(value = "扩展库数据源配置")
    private Long extendDatasourceId;
}
