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
import top.tangyh.lamp.model.constant.EchoDictType;
import top.tangyh.lamp.model.entity.system.SysUser;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 实体类
 * 员工
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
@Schema(description = "员工")
public class BaseEmployeeResultVO extends Entity<Long> implements Serializable, EchoVO {

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
     * 用户id
     */
    @Schema(description = "用户id")
    
    private Long userId;
    /**
     * 岗位Id
     */
    @Schema(description = "岗位Id")
    
    @Echo(api = EchoApi.POSITION_ID_CLASS)
    private Long positionId;
    /**
     * 组织Id
     */
    @Schema(description = "组织Id")
    
    @Echo(api = EchoApi.ORG_ID_CLASS)
    private List<Long> orgIdList;

    @Schema(description = "最后一次登录单位ID")
    @Echo(api = EchoApi.ORG_ID_CLASS)
    private Long lastCompanyId;

    @Schema(description = "最后一次登录部门ID")
    private Long lastDeptId;

    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    
    private String realName;

    /**
     * 职位状态;[10-在职 20-离职]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.POSITION_STATUS)
     */
    @Schema(description = "职位状态")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.POSITION_STATUS)
    
    private String positionStatus;
    /**
     * 状态;[0-禁用 1-启用]
     */
    @Schema(description = "状态")
    
    private Boolean state;

    /**
     * 激活状态;[10-未激活 20-已激活]
     */
    @Schema(description = "激活状态")
    private String activeStatus;

    /**
     * 创建机构Id
     */
    private Long createdOrgId;

    @Schema(description = "用户信息")
    private SysUser defUser;
}
