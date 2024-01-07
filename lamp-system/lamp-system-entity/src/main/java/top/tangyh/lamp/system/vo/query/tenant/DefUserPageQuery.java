package top.tangyh.lamp.system.vo.query.tenant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

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
 * @since 2021-10-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "用户")
public class DefUserPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户名;大小写数字下划线
     */
    @Schema(description = "用户名")
    private String username;
    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String nickName;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;
    /**
     * 手机;1开头11位纯数字
     */
    @Schema(description = "手机")
    private String mobile;
    /**
     * 身份证;15或18位
     */
    @Schema(description = "身份证")
    private String idCard;
    /**
     * 微信OpenId
     */
    @Schema(description = "微信OpenId")
    private String wxOpenId;
    /**
     * 钉钉OpenId
     */
    @Schema(description = "钉钉OpenId")
    private String ddOpenId;
    /**
     * 内置;[0-否 1-是]
     */
    @Schema(description = "内置")
    private Boolean readonly;
    /**
     * 性别;
     */
    @Schema(description = "性别")
    private String sex;
    /**
     * 民族;[01-汉族 99-其他]	@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.NATION)
     */
    @Schema(description = "民族")
    private List<String> nation;
    /**
     * 学历;[01-小学 02-中学 03-高中 04-专科 05-本科 06-硕士 07-博士 08-博士后 99-其他]	@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.EDUCATION)
     */
    @Schema(description = "学历")
    private List<String> education;
    /**
     * 状态;[0-禁用 1-启用]
     */
    @Schema(description = "状态")
    private Boolean state;
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

}
