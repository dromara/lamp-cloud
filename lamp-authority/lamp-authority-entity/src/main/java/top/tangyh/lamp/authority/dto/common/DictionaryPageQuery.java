package top.tangyh.lamp.authority.dto.common;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class DictionaryPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    @Schema(description="类型")
    private String type;
    /**
     * 类型标签
     */
    @Schema(description="类型标签")
    private String label;
    /**
     * 编码
     */
    @Schema(description="编码")
    private String code;
    /**
     * 名称
     */
    @Schema(description="名称")
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
    private String icon;
    /**
     * css样式
     */
    @Schema(description="css样式")
    private String cssStyle;
    /**
     * 类选择器
     */
    @Schema(description="类选择器")
    private String cssClass;
    /**
     * 内置
     */
    @Schema(description="内置")
    private Boolean readonly;

}
