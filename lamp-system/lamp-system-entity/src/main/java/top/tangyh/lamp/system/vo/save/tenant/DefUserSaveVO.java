package top.tangyh.lamp.system.vo.save.tenant;

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

import static top.tangyh.basic.utils.ValidatorUtil.REGEX_EMAIL;
import static top.tangyh.basic.utils.ValidatorUtil.REGEX_ID_CARD;
import static top.tangyh.basic.utils.ValidatorUtil.REGEX_MOBILE;
import static top.tangyh.basic.utils.ValidatorUtil.REGEX_USERNAME;

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
public class DefUserSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名;大小写数字下划线
     */
    @Schema(description = "用户名")
    @NotEmpty(message = "请填写用户名")
    @Size(min = 6, max = 255, message = "用户名长度不能小于{min}或超过{max}")
    @NotEmptyPattern(regexp = REGEX_USERNAME, message = "仅支持英文(a-zA-Z)数字(0-9)和下划线(_)，且至少包含2项")
    private String username;
    /**
     * 昵称
     */
    @Schema(description = "昵称")
    @NotEmpty(message = "请填写昵称")
    @Size(max = 255, message = "昵称长度不能超过{max}")
    private String nickName;
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    @Size(max = 255, message = "邮箱长度不能超过{max}")
    @NotEmptyPattern(regexp = REGEX_EMAIL, message = "请输入正确的邮箱地址")
    private String email;
    /**
     * 手机;1开头11位纯数字
     */
    @Schema(description = "手机")
    @Size(max = 11, message = "手机长度不能超过{max}")
    @NotEmptyPattern(regexp = REGEX_MOBILE, message = "请输入11位的手机号")
    @NotEmpty(message = "请填写手机")
    private String mobile;
    /**
     * 身份证;15或18位
     */
    @Schema(description = "身份证")
    @Size(max = 18, message = "身份证长度不能超过{max}")
    @NotEmptyPattern(regexp = REGEX_ID_CARD, message = "请输入正确的身份证号")
    private String idCard;
    /**
     * 微信OpenId
     */
    @Schema(description = "微信OpenId")
    @Size(max = 255, message = "微信OpenId长度不能超过{max}")
    private String wxOpenId;
    /**
     * 钉钉OpenId
     */
    @Schema(description = "钉钉OpenId")
    @Size(max = 255, message = "钉钉OpenId长度不能超过{max}")
    private String ddOpenId;

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
    /**
     * 状态;[0-禁用 1-启用]
     */
    @Schema(description = "状态")
    private Boolean state;

    /**
     * 工作描述
     */
    @Schema(description = "工作描述")
    @Size(max = 255, message = "工作描述长度不能超过{max}")
    private String workDescribe;

}
