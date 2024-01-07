package top.tangyh.lamp.base.vo.save.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.annotation.constraints.NotEmptyPattern;

import java.io.Serializable;
import java.util.List;

import static top.tangyh.basic.utils.ValidatorUtil.REGEX_MOBILE;
import static top.tangyh.basic.utils.ValidatorUtil.REGEX_USERNAME;

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
public class BaseEmployeeSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private Long id;
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @JsonIgnore
    private Long userId;
    /**
     * 激活状态;[10-未激活 20-已激活]
     */
    @Schema(description = "激活状态")
    @JsonIgnore
    private String activeStatus;
    /**
     * 是否默认员工;[0-否 1-是]
     */
    @JsonIgnore
    private Boolean isDefault;

    /**
     * 岗位Id
     */
    @Schema(description = "岗位ID")
    private Long positionId;
    /**
     * 机构ID
     */
    @Schema(description = "所属部门")
    private List<Long> orgIdList;

    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    @Size(max = 255, message = "真实姓名长度不能超过{max}")
    @NotEmpty(message = "请填写真实姓名")
    private String realName;

    /**
     * 职位状态;[10-在职 20-离职]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.POSITION_STATUS)
     */
    @Schema(description = "职位状态")
    @Size(max = 2, message = "职位状态长度不能超过{max}")
    private String positionStatus;
    /**
     * 状态;[0-禁用 1-启用]
     */
    @Schema(description = "状态")
    private Boolean state;


    //---以下是用户信息---


    @Size(max = 255, message = "用户名长度不能超过{max}")
    @NotEmptyPattern(regexp = REGEX_USERNAME, message = "仅支持英文(a-zA-Z)数字(0-9)和下划线(_)，且至少包含2项")
    private String username;
    /**
     * 登录手机号
     */
    @Schema(description = "登录手机号")
    @NotEmpty(message = "请填写登录手机号")
    @NotEmptyPattern(regexp = REGEX_MOBILE, message = "请输入11位的手机号")
    private String mobile;

    /**
     * 性别;
     */
    @Schema(description = "性别")
    @Size(max = 1, message = "性别长度不能超过{max}")
    private String sex;
    /**
     * 民族;[01-汉族 99-其他]	@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.NATION)
     */
    @Schema(description = "民族")
    @Size(max = 2, message = "民族长度不能超过{max}")
    private String nation;
    /**
     * 学历;[01-小学 02-中学 03-高中 04-专科 05-本科 06-硕士 07-博士 08-博士后 99-其他]	@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.EDUCATION)
     */
    @Schema(description = "学历")
    @Size(max = 2, message = "学历长度不能超过{max}")
    private String education;

}
