package top.tangyh.lamp.model.entity.system;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
import top.tangyh.lamp.model.entity.base.SysEmployee;
import top.tangyh.lamp.model.entity.base.SysOrg;
import top.tangyh.lamp.model.entity.base.SysPosition;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static top.tangyh.lamp.model.constant.Condition.LIKE;

/**
 * 用户实体
 * 字段基于 def_user 表, 可能还会扩展其他字段
 *
 * @author zuihou
 * @date 2022年04月13日12:30:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@ToString(callSuper = true)
@Builder
@Schema(description = "系统用户")
@TableName("def_user")
public class SysUser extends Entity<Long> implements Serializable, EchoVO {
    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    /**
     * 用户名;大小写数字下划线
     */
    @Schema(description = "用户名")
    @TableField(value = "username", condition = LIKE)
    private String username;
    /**
     * 昵称
     */
    @Schema(description = "昵称")
    @TableField(value = "nick_name", condition = LIKE)
    private String nickName;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    @TableField(value = "email", condition = LIKE)
    private String email;
    /**
     * 手机;1开头11位纯数字
     */
    @Schema(description = "手机")
    @TableField(value = "mobile", condition = LIKE)
    private String mobile;
    /**
     * 身份证;15或18位
     */
    @Schema(description = "身份证")
    @TableField(value = "id_card", condition = LIKE)
    private String idCard;
    /**
     * 微信OpenId
     */
    @Schema(description = "微信OpenId")
    @TableField(value = "wx_open_id", condition = LIKE)
    private String wxOpenId;
    /**
     * 钉钉OpenId
     */
    @Schema(description = "钉钉OpenId")
    @TableField(value = "dd_open_id", condition = LIKE)
    private String ddOpenId;
    /**
     * 内置;[0-否 1-是]
     */
    @Schema(description = "内置")
    @TableField(value = "readonly")
    private Boolean readonly;
    /**
     * 性别;
     * #Sex{W:女;M:男;N:未知}
     */
    @Schema(description = "性别")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.SEX)
    @TableField(value = "sex")
    private String sex;
    /**
     * 民族;[01-汉族 99-其他]	@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.NATION)
     */
    @Schema(description = "民族")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.NATION)
    @TableField(value = "nation", condition = LIKE)
    private String nation;
    /**
     * 学历;[01-小学 02-中学 03-高中 04-专科 05-本科 06-硕士 07-博士 08-博士后 99-其他]	@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.EDUCATION)
     */
    @Schema(description = "学历")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.EDUCATION)
    @TableField(value = "education", condition = LIKE)
    private String education;
    /**
     * 状态;[0-禁用 1-启用]
     */
    @Schema(description = "状态")
    @TableField(value = "state")
    private Boolean state;

    /**
     * 工作描述
     */
    @Schema(description = "工作描述")
    @TableField(value = "work_describe", condition = LIKE)
    private String workDescribe;
    /**
     * 最后一次输错密码时间
     */
    @Schema(description = "最后一次输错密码时间")
    @TableField(value = "password_error_last_time")
    private LocalDateTime passwordErrorLastTime;
    /**
     * 密码错误次数
     */
    @Schema(description = "密码错误次数")
    @TableField(value = "password_error_num")
    private Integer passwordErrorNum;
    /**
     * 密码过期时间
     */
    @Schema(description = "密码过期时间")
    @TableField(value = "password_expire_time")
    private LocalDateTime passwordExpireTime;

    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    @TableField(value = "last_login_time")
    private LocalDateTime lastLoginTime;

    @Schema(description = "员工ID")
    @TableField(exist = false)
    private Long employeeId;

    /**
     * 当前用户的角色编码
     * 启用条件： LoginUser.isFull = true || LoginUser.isRole = true
     */
    @TableField(exist = false)
    private List<String> roleCodeList;
    /**
     * 当前用户的资源编码
     * 启用条件： LoginUser.isFull = true || LoginUser.isResource = true
     */
    @TableField(exist = false)
    private List<String> resourceCodeList;
    /**
     * 当前用户所属部门
     * 启用条件： LoginUser.isFull = true || LoginUser.isOrg = true
     */
    @TableField(exist = false)
    private SysOrg dept;
    /**
     * 当前用户所属 单位
     * 启用条件： LoginUser.isFull = true || LoginUser.isOrg = true
     */
    @TableField(exist = false)
    private SysOrg company;
    /**
     * 当前用户的所属的 公司列表
     */
    @TableField(exist = false)
    private List<SysOrg> companyList;
    /**
     * 当前用户的所属的 部门列表
     */
    @TableField(exist = false)
    private List<SysOrg> deptList;
    /**
     * 当前用户的 岗位
     * 启用条件： LoginUser.isFull = true || LoginUser.isStation = true
     */
    @TableField(exist = false)
    private SysPosition position;

    @TableField(exist = false)
    private SysEmployee employee;

}
