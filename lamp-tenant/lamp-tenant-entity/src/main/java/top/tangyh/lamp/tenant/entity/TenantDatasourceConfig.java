package top.tangyh.lamp.tenant.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import top.tangyh.basic.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static top.tangyh.lamp.model.constant.Condition.LIKE;

/**
 * <p>
 * 实体类
 * 租户数据源关系
 * </p>
 *
 * @author zuihou
 * @since 2020-11-19
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_tenant_datasource_config")
@ApiModel(value = "TenantDatasourceConfig", description = "租户数据源关系")
@AllArgsConstructor
public class TenantDatasourceConfig extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id")
    @NotNull(message = "租户id不能为空")
    @TableField("tenant_id")
    @Excel(name = "租户id")
    private Long tenantId;

    /**
     * 数据源id
     */
    @ApiModelProperty(value = "数据源id")
    @NotNull(message = "数据源id不能为空")
    @TableField("datasource_config_id")
    @Excel(name = "数据源id")
    private Long datasourceConfigId;
    /**
     * 数据库名前缀
     */
    @ApiModelProperty(value = "数据库名前缀")
    @TableField(value = "db_prefix", condition = LIKE)
    private String dbPrefix;


    @Builder
    public TenantDatasourceConfig(Long id, LocalDateTime createTime, Long createdBy,
                                  Long tenantId, Long datasourceConfigId, String dbPrefix) {
        this.id = id;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.tenantId = tenantId;
        this.datasourceConfigId = datasourceConfigId;
        this.dbPrefix = dbPrefix;
    }

}
