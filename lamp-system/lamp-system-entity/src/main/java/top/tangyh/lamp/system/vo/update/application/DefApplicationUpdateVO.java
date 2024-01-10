package top.tangyh.lamp.system.vo.update.application;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
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
import top.tangyh.basic.base.entity.SuperEntity;
import top.tangyh.lamp.model.vo.save.AppendixSaveVO;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 应用
 * </p>
 *
 * @author zuihou
 * @since 2021-09-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "应用")
public class DefApplicationUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "请填写主键", groups = SuperEntity.Update.class)
    private Long id;
    @Schema(description = "重定向")
    @Size(max = 255, message = "重定向长度不能超过255")
    private String redirect;
    /**
     * 应用名称
     */
    @Schema(description = "应用名称")
    @NotEmpty(message = "请填写应用名称")
    @Size(max = 255, message = "应用名称长度不能超过255")
    private String name;
    /**
     * 版本
     */
    @Schema(description = "版本")
    @Size(max = 255, message = "版本长度不能超过255")
    @NotEmpty(message = "请填写版本")
    private String version;
    /**
     * 应用类型;[10-自建应用 20-第三方应用]
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.APPLICATION_TYPE)
     */
    @Schema(description = "应用类型")
    @NotEmpty(message = "请选择应用类型")
    @Size(max = 2, message = "应用类型长度不能超过2")
    private String type;
    /**
     * 简介
     */
    @Schema(description = "简介")
    @Size(max = 255, message = "简介长度不能超过255")
    private String introduce;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 255, message = "备注长度不能超过255")
    private String remark;
    /**
     * 应用地址
     */
    @Schema(description = "应用地址")
    @Size(max = 255, message = "应用地址长度不能超过255")
    private String url;
    /**
     * 是否可见;0-否 1-是
     */
    @Schema(description = "是否可见")
    private Boolean isVisible;
    /**
     * 是否公共应用;0-否 1-是
     */
    @Schema(description = "是否公共应用")
    private Boolean isGeneral;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sortValue;

    /**
     * 图标
     */
    @Schema(description = "图标")
    @Valid
    private AppendixSaveVO appendixIcon;
}
