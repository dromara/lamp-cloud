package top.tangyh.lamp.base.vo.result.system;


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
import top.tangyh.lamp.model.constant.EchoDictType;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 实体类
 * 角色
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
@Schema(description = "角色")
public class BaseRoleResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "主键")
    private Long id;

    /**
     * 角色类型;10-系统角色 20-自定义角色
     */
    @Schema(description = "角色类型")
    
    private String type;
    /**
     * 角色类别;[10-功能角色 20-桌面角色 30-数据角色]
     */
    @Schema(description = "角色类别")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.ROLE_CATEGORY)
    private String category;
    /**
     * 名称
     */
    @Schema(description = "名称")
    
    private String name;
    /**
     * 编码
     */
    @Schema(description = "编码")
    
    private String code;
    /**
     * 备注
     */
    @Schema(description = "备注")
    
    private String remarks;
    /**
     * 状态
     */
    @Schema(description = "状态")
    
    private Boolean state;
    /**
     * 内置角色
     */
    @Schema(description = "内置角色")
    
    private Boolean readonly;

    /**
     * 创建者组织ID
     */
    @Schema(description = "创建者组织ID")
    private Long createdOrgId;
}
