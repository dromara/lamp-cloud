package com.github.zuihou.tenant.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 租户数据源关系
 * </p>
 *
 * @author zuihou
 * @since 2020-08-21
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("d_tenant_datasource_config")
@ApiModel(value = "TenantDatasourceConfig", description = "租户数据源关系")
@AllArgsConstructor
public class TenantDatasourceConfig extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id")
    @TableField(value = "tenant_id")
    private Long tenantId;
    /**
     * 数据源id
     */
    @ApiModelProperty(value = "数据源id")
    @TableField(value = "datasource_config_id")
    private Long datasourceConfigId;
    /**
     * 服务
     */
    @ApiModelProperty(value = "服务")
    @TableField(value = "application")
    private String application;


    @Builder
    public TenantDatasourceConfig(Long id, LocalDateTime createTime, Long createUser,
                                  Long tenantId, Long datasourceConfigId, String application) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.tenantId = tenantId;
        this.datasourceConfigId = datasourceConfigId;
        this.application = application;
    }

}
