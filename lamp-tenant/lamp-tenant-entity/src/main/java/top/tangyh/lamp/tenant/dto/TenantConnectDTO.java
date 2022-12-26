package top.tangyh.lamp.tenant.dto;

import top.tangyh.lamp.model.enumeration.system.TenantConnectTypeEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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
@Schema(description="租户连接")
public class TenantConnectDTO {
    @Schema(description="企业ID")
    @NotNull(message = "ID不能为空")
    private Long id;
    @NotEmpty(message = "企业编码不能为空")
    private String tenant;
    /**
     * LOCAL： 同一个数据库(物理)，链接不同的数据库实例. 从mysql.yml、oracle.yml、sqlserver.yml中读取master数据源来自动新增其他数据库
     * REMOTE： 不同的数据库(物理)，需要先在DatasourceConfig表配置链接源信息，然后指定以下字段（xxxDatasource）
     */
    @Schema(description="连接类型", example = "LOCAL,REMOTE")
    @NotNull(message = "连接类型不能为空")
    private TenantConnectTypeEnum connectType;

    /**
     * 数据源id
     */
    @Schema(description="基础库数据源配置")
    private Long baseDatasourceId;
    @Schema(description="扩展库数据源配置")
    private Long extendDatasourceId;
}
