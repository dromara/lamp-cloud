package top.tangyh.lamp.system.vo.query.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

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
public class DefDictItemPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典ID
     */
    @Schema(description = "字典ID")
    private Long parentId;

    /**
     * 分类;[10-系统字典 20-业务字典]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.DICT_CLASSIFY)
     */
    @Schema(description = "分类")
    private List<String> classify;
    /**
     * 标识
     */
    @Schema(description = "标识")
    private String key;
    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private List<Boolean> state;
    /**
     * 备注
     */
    @Schema(description = "备注")
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
    private String icon;
    /**
     * css样式
     */
    @Schema(description = "css样式")
    private String cssStyle;
    /**
     * css类元素
     */
    @Schema(description = "css类元素")
    private String cssClass;

}
