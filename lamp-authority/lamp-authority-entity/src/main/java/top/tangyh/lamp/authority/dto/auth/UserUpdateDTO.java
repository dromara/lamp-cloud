package top.tangyh.lamp.authority.dto.auth;

import top.tangyh.basic.base.entity.SuperEntity;
import top.tangyh.lamp.model.enumeration.Sex;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

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
@Schema(description="用户")
public class UserUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 账号
     */
    @Schema(description="账号")
    @NotEmpty(message = "账号不能为空")
    @Size(max = 30, message = "账号长度不能超过30")
    private String account;
    /**
     * 姓名
     */
    @Schema(description="姓名")
    @NotEmpty(message = "姓名不能为空")
    @Size(max = 50, message = "姓名长度不能超过50")
    private String name;
    /**
     * 组织
     * #c_org
     *
     * @Echo(api = ORG_ID_CLASS,  beanClass = Org.class)
     */
    @Schema(description="组织")
    private Long orgId;
    /**
     * 岗位
     * #c_station
     *
     * @Echo(api = STATION_ID_CLASS)
     */
    @Schema(description="岗位")
    private Long stationId;
    /**
     * 邮箱
     */
    @Schema(description="邮箱")
    @Size(max = 255, message = "邮箱长度不能超过255")
    private String email;
    /**
     * 手机
     */
    @Schema(description="手机")
    @Size(max = 20, message = "手机长度不能超过20")
    private String mobile;
    /**
     * 性别
     * #Sex{W:女;M:男;N:未知}
     */
    @Schema(description="性别")
    private Sex sex;
    /**
     * 状态
     */
    @Schema(description="状态")
    private Boolean state;
    /**
     * 头像
     */
    @Schema(description="头像")
    @Size(max = 255, message = "头像长度不能超过255")
    private String avatar;
    /**
     * 民族
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.NATION)
     */
    @Schema(description="民族")
    @Size(max = 2, message = "民族长度不能超过2")
    private String nation;
    /**
     * 学历
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.EDUCATION)
     */
    @Schema(description="学历")
    @Size(max = 2, message = "学历长度不能超过2")
    private String education;
    /**
     * 职位状态
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.POSITION_STATUS)
     */
    @Schema(description="职位状态")
    @Size(max = 2, message = "职位状态长度不能超过2")
    private String positionStatus;
    /**
     * 工作描述
     */
    @Schema(description="工作描述")
    @Size(max = 255, message = "工作描述长度不能超过255")
    private String workDescribe;
}
