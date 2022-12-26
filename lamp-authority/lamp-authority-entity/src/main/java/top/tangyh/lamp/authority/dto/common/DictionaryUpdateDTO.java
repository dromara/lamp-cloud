package top.tangyh.lamp.authority.dto.common;

import top.tangyh.basic.base.entity.SuperEntity;

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
 * 字典项
 * </p>
 *
 * @author zuihou
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description="字典项")
public class DictionaryUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 类型
     */
    @Schema(description="类型")
    @NotEmpty(message = "类型不能为空")
    @Size(max = 255, message = "类型长度不能超过255")
    private String type;
    /**
     * 类型标签
     */
    @Schema(description="类型标签")
    @NotEmpty(message = "类型标签不能为空")
    @Size(max = 255, message = "类型标签长度不能超过255")
    private String label;
    /**
     * 编码
     */
    @Schema(description="编码")
    @NotEmpty(message = "编码不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    private String code;
    /**
     * 名称
     */
    @Schema(description="名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 64, message = "名称长度不能超过64")
    private String name;
    /**
     * 状态
     */
    @Schema(description="状态")
    private Boolean state;
    /**
     * 描述
     */
    @Schema(description="描述")
    @Size(max = 255, message = "描述长度不能超过255")
    private String describe;
    /**
     * 排序
     */
    @Schema(description="排序")
    private Integer sortValue;
    /**
     * 图标
     */
    @Schema(description="图标")
    @Size(max = 255, message = "图标长度不能超过255")
    private String icon;
    /**
     * css样式
     */
    @Schema(description="css样式")
    @Size(max = 255, message = "css样式长度不能超过255")
    private String cssStyle;
    /**
     * 类选择器
     */
    @Schema(description="类选择器")
    @Size(max = 255, message = "类选择器长度不能超过255")
    private String cssClass;
    /**
     * 内置
     */
    @Schema(description="内置")
    private Boolean readonly;
}
