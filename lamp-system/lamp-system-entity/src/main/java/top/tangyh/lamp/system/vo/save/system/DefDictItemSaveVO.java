package top.tangyh.lamp.system.vo.save.system;

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

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 字典项
 * </p>
 *
 * @author zuihou
 * @since 2021-10-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "字典项")
public class DefDictItemSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     */
    @Schema(description = "字典ID")
    @NotNull(message = "请填写字典ID")
    private Long parentId;
    /**
     * 标识
     */
    @Schema(description = "标识")
    @NotEmpty(message = "请填写标识")
    @Size(max = 255, message = "标识长度不能超过{max}")
    private String key;
    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotEmpty(message = "请填写名称")
    @Size(max = 255, message = "名称长度不能超过{max}")
    private String name;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean state;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 255, message = "备注长度不能超过{max}")
    private String remark;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sortValue;
    /**
     * 图标
     */
    @Schema(description = "图标")
    @Size(max = 255, message = "图标长度不能超过{max}")
    private String icon;
    /**
     * css样式
     */
    @Schema(description = "css样式")
    @Size(max = 255, message = "css样式长度不能超过{max}")
    private String cssStyle;
    /**
     * css类元素
     */
    @Schema(description = "css类元素")
    @Size(max = 255, message = "css类元素长度不能超过{max}")
    private String cssClass;

}
