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
import top.tangyh.lamp.model.constant.EchoDictType;
import top.tangyh.lamp.model.entity.system.SysUser;

import java.io.Serial;
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
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
@Schema(description = "员工")
@TableName("base_employee")
public class SysEmployee extends Entity<Long> implements Serializable, EchoVO {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    /**
     * 是否默认员工;[0-否 1-是]
     */
    @Schema(description = "是否默认员工")
    @TableField(value = "is_default")
    private Boolean isDefault;
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @TableField(value = "user_id")
    private Long userId;
    /**
     * 岗位Id
     */
    @Schema(description = "岗位Id")
    @Echo(api = EchoApi.POSITION_ID_CLASS)
    @TableField(value = "position_id")
    private Long positionId;

    /**
     * 上一次登录单位ID
     */
    @Schema(description = "所属主机构")
    @Echo(api = EchoApi.POSITION_ID_CLASS)
    @TableField(value = "last_company_id")
    private Long lastCompanyId;
    /**
     * 上一次登录部门ID
     */
    @TableField(value = "last_dept_id")
    private Long lastDeptId;

    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    @TableField(value = "real_name", condition = LIKE)
    private String realName;

    /**
     * 职位状态;[10-在职 20-离职]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.POSITION_STATUS)
     */
    @Schema(description = "职位状态")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.POSITION_STATUS)
    @TableField(value = "position_status", condition = LIKE)
    private String positionStatus;
    /**
     * 激活状态;[10-未激活 20-已激活]
     */
    @Schema(description = "激活状态")
    @TableField(value = "active_status", condition = LIKE)
    private String activeStatus;

    /**
     * 状态;[0-禁用 1-启用]
     */
    @Schema(description = "状态")
    @TableField(value = "state")
    private Boolean state;

    /**
     * 创建机构Id
     */
    @TableField(value = "created_org_id")
    private Long createdOrgId;

    @Schema(description = "用户信息")
    @TableField(exist = false)
    private SysUser defUser;
}
