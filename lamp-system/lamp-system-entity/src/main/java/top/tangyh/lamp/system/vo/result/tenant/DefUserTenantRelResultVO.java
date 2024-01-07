package top.tangyh.lamp.system.vo.result.tenant;


import cn.hutool.core.map.MapUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.Entity;
import top.tangyh.basic.interfaces.echo.EchoVO;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 实体类
 * 员工
 * </p>
 *
 * @author zuihou
 * @since 2021-10-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "员工")
public class DefUserTenantRelResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "主键")
    private Long id;

    /**
     * 是否默认员工;[0-否 1-是]
     */
    @Schema(description = "是否默认员工")
    private Boolean isDefault;
    /**
     * 用户
     */
    @Schema(description = "用户")
    
    private Long userId;
    /**
     * 状态;[0-禁用 1-启用]
     */
    @Schema(description = "状态")
    
    private Boolean state;

    @Schema(description = "企业状态")
    private Boolean tenantState;
    @Schema(description = "企业编码")
    private String tenantCode;

}
