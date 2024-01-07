package top.tangyh.lamp.model.entity.base;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
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

import static top.tangyh.lamp.model.constant.Condition.LIKE;

/**
 * <p>
 * 实体类
 *
 * </p>
 *
 * @author zuihou
 * @since 2019-07-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=false)
@Schema(description = "岗位")
@TableName("base_position")
public class SysPosition extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    /**
     * 名称
     */
    @Schema(description = "名称")
    @TableField(value = "name", condition = LIKE)
    private String name;
    /**
     * 所属组织;#base_org@Echo(api = EchoApi.ORG_ID_CLASS)
     */
    @Schema(description = "组织")
    @Echo(api = EchoApi.ORG_ID_CLASS)
    @TableField(value = "org_id")
    private Long orgId;
    /**
     * 状态;0-禁用 1-启用
     */
    @Schema(description = "状态")
    @TableField(value = "state")
    private Boolean state;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @TableField(value = "remarks", condition = LIKE)
    private String remarks;
    /**
     * 创建者机构
     */
    @Schema(description = "创建者机构")
    @TableField(value = "created_org_id")
    private Long createdOrgId;

}
