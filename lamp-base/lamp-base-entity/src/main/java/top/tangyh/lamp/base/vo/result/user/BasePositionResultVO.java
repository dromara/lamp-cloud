package top.tangyh.lamp.base.vo.result.user;


import cn.hutool.core.map.MapUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.annotation.echo.Echo;
import top.tangyh.basic.base.entity.Entity;
import top.tangyh.basic.interfaces.echo.EchoVO;
import top.tangyh.lamp.model.constant.EchoApi;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 实体类
 * 岗位
 * </p>
 *
 * @author zuihou
 * @since 2021-10-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "岗位")
public class BasePositionResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "主键")
    private Long id;

    /**
     * 名称
     */
    @Schema(description = "名称")
    
    private String name;
    /**
     * 组织;#base_org@Echo(api = EchoApi.ORG_ID_CLASS)
     */
    @Schema(description = "组织")
    @Echo(api = EchoApi.ORG_ID_CLASS)
    
    private Long orgId;
    /**
     * 状态;0-禁用 1-启用
     */
    @Schema(description = "状态")
    
    private Boolean state;
    /**
     * 备注
     */
    @Schema(description = "备注")
    
    private String remarks;
}
