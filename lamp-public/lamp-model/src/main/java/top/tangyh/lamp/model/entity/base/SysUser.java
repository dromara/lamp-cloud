package top.tangyh.lamp.model.entity.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.tangyh.basic.annotation.echo.Echo;
import top.tangyh.basic.base.entity.Entity;
import top.tangyh.basic.interfaces.echo.EchoVO;
import top.tangyh.lamp.model.constant.EchoDictType;
import top.tangyh.lamp.model.enumeration.Sex;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static top.tangyh.lamp.model.constant.Condition.LIKE;
import static top.tangyh.lamp.model.constant.EchoApi.DICTIONARY_ITEM_CLASS;
import static top.tangyh.lamp.model.constant.EchoApi.ORG_ID_CLASS;
import static top.tangyh.lamp.model.constant.EchoApi.STATION_ID_CLASS;

/**
 * @author zuihou
 * 用户实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true)
@Builder
@TableName("c_user")
public class SysUser extends Entity<Long> implements EchoVO {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Map<String, Object> echoMap = new HashMap<>();
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    @Size(max = 30, message = "账号长度不能超过30")
    @TableField(value = "account", condition = LIKE)
    private String account;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空")
    @Size(max = 50, message = "姓名长度不能超过50")
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 组织
     * #c_org
     *
     * @Echo(api = ORG_ID_CLASS,  beanClass = Org.class)
     */
    @ApiModelProperty(value = "组织")
    @TableField("org_id")
    @Echo(api = ORG_ID_CLASS, beanClass = SysOrg.class)
    private Long orgId;

    /**
     * 岗位
     * #c_station
     *
     * @Echo(api = STATION_ID_CLASS)
     */
    @ApiModelProperty(value = "岗位")
    @TableField("station_id")
    @Echo(api = STATION_ID_CLASS)
    private Long stationId;

    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    @NotNull(message = "内置不能为空")
    @TableField("readonly")
    private Boolean readonly;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Size(max = 255, message = "邮箱长度不能超过255")
    @TableField(value = "email", condition = LIKE)
    private String email;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Size(max = 20, message = "手机长度不能超过20")
    @TableField(value = "mobile", condition = LIKE)
    private String mobile;

    /**
     * 性别
     * #Sex{W:女;M:男;N:未知}
     */
    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private Sex sex;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    private Boolean state;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @Size(max = 255, message = "头像长度不能超过255")
    @TableField(value = "avatar", condition = LIKE)
    private String avatar;

    /**
     * 民族
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.NATION)
     */
    @ApiModelProperty(value = "民族")
    @Size(max = 2, message = "民族长度不能超过2")
    @TableField(value = "nation", condition = LIKE)
    @Echo(api = DICTIONARY_ITEM_CLASS, dictType = EchoDictType.NATION)
    private String nation;

    /**
     * 学历
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.EDUCATION)
     */
    @ApiModelProperty(value = "学历")
    @Size(max = 2, message = "学历长度不能超过2")
    @TableField(value = "education", condition = LIKE)
    @Echo(api = DICTIONARY_ITEM_CLASS, dictType = EchoDictType.EDUCATION)
    private String education;

    /**
     * 职位状态
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.POSITION_STATUS)
     */
    @ApiModelProperty(value = "职位状态")
    @Size(max = 2, message = "职位状态长度不能超过2")
    @TableField(value = "position_status", condition = LIKE)
    @Echo(api = DICTIONARY_ITEM_CLASS, dictType = EchoDictType.POSITION_STATUS)
    private String positionStatus;

    /**
     * 工作描述
     */
    @ApiModelProperty(value = "工作描述")
    @Size(max = 255, message = "工作描述长度不能超过255")
    @TableField(value = "work_describe", condition = LIKE)
    private String workDescribe;

    /**
     * 最后一次输错密码时间
     */
    @ApiModelProperty(value = "最后一次输错密码时间")
    @TableField("password_error_last_time")
    private LocalDateTime passwordErrorLastTime;

    /**
     * 密码错误次数
     */
    @ApiModelProperty(value = "密码错误次数")
    @TableField("password_error_num")
    private Integer passwordErrorNum;

    /**
     * 密码过期时间
     */
    @ApiModelProperty(value = "密码过期时间")
    @TableField("password_expire_time")
    private LocalDateTime passwordExpireTime;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 当前登录用户的角色编码
     * 启用条件： LoginUser.isFull = true || LoginUser.isRole = true
     */
    @TableField(exist = false)
    private List<String> roles;
    /**
     * 当前登录用户的资源编码
     * 启用条件： LoginUser.isFull = true || LoginUser.isResource = true
     */
    @TableField(exist = false)
    private List<String> resources;
    /**
     * 当前登录用户的组织架构
     * 启用条件： LoginUser.isFull = true || LoginUser.isOrg = true
     */
    @TableField(exist = false)
    private SysOrg org;
    /**
     * 当前登录用户的 岗位
     * 启用条件： LoginUser.isFull = true || LoginUser.isStation = true
     */
    @TableField(exist = false)
    private SysStation station;

}
