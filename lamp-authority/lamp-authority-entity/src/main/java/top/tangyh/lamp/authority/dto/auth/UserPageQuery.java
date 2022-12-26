package top.tangyh.lamp.authority.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.model.enumeration.Sex;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 * @author zuihou
 * @since 2021-04-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "用户")
public class UserPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @Schema(description = "账号")
    private String account;
    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;
    /**
     * 组织
     * #c_org
     *
     * @Echo(api = ORG_ID_CLASS,  beanClass = Org.class)
     */
    @Schema(description = "组织")
    private Long orgId;
    /**
     * 岗位
     * #c_station
     *
     * @Echo(api = STATION_ID_CLASS)
     */
    @Schema(description = "岗位")
    private Long stationId;
    /**
     * 内置
     */
    @Schema(description = "内置")
    private Boolean readonly;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;
    /**
     * 手机
     */
    @Schema(description = "手机")
    private String mobile;
    /**
     * 性别
     * #Sex{W:女;M:男;N:未知}
     */
    @Schema(description = "性别")
    private List<Sex> sex;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean state;
    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;
    /**
     * 民族
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.NATION)
     */
    @Schema(description = "民族")
    private List<String> nation;
    /**
     * 学历
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.EDUCATION)
     */
    @Schema(description = "学历")
    private List<String> education;
    /**
     * 职位状态
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.POSITION_STATUS)
     */
    @Schema(description = "职位状态")
    private List<String> positionStatus;
    /**
     * 工作描述
     */
    @Schema(description = "工作描述")
    private String workDescribe;
    /**
     * 最后一次输错密码时间
     */
    @Schema(description = "最后一次输错密码时间")
    private LocalDateTime passwordErrorLastTime;
    /**
     * 密码错误次数
     */
    @Schema(description = "密码错误次数")
    private Integer passwordErrorNum;
    /**
     * 密码过期时间
     */
    @Schema(description = "密码过期时间")
    private LocalDateTime passwordExpireTime;
    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;
    /**
     * 密码盐
     */
    @Schema(description = "密码盐")
    private String salt;
    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    private LocalDateTime lastLoginTime;
    private Long createdOrgId;

    @Schema(description = "范围")
    private String scope;
    @Schema(description = "角色id")
    private String roleId;

}
