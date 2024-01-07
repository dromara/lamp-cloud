package top.tangyh.lamp.system.vo.update.tenant;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

import static top.tangyh.basic.utils.ValidatorUtil.REGEX_ID_CARD;

/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 * @author zuihou
 * @since 2020-02-14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "用户修改自己的基础信息实体")
public class DefUserBaseInfoUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    @NotEmpty(message = "请填写昵称")
    @Size(max = 255, message = "昵称长度不能超过{max}")
    private String nickName;
    /**
     * 身份证;15或18位
     */
    @Schema(description = "身份证")
    @Size(max = 18, message = "身份证长度不能超过{max}")
    @NotEmptyPattern(regexp = REGEX_ID_CARD, message = "请输入正确的身份证号")
    private String idCard;

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
     * 工作描述
     */
    @Schema(description = "工作描述")
    @Size(max = 255, message = "工作描述长度不能超过{max}")
    private String workDescribe;
}
