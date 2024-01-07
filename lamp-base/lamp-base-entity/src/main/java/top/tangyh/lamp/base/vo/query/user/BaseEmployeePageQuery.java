package top.tangyh.lamp.base.vo.query.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

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
public class BaseEmployeePageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private List<Long> positionId;
    /**
     * 机构ID
     */
    @Schema(description = "机构ID")
    private List<Long> orgIdList;
    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    private String realName;
    /**
     * 职位状态;[10-在职 20-离职]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.POSITION_STATUS)
     */
    @Schema(description = "职位状态")
    private List<String> positionStatus;
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


    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "身份证号")
    private String idCard;
    @Schema(description = "范围")
    private String scope;
    @Schema(description = "角色id")
    private String roleId;

    @JsonIgnore
    private List<Long> userIdList;

}
