package top.tangyh.lamp.authority.dto.core;


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
 * 组织
 * </p>
 *
 * @author zuihou
 * @since 2021-04-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "组织")
public class OrgPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "名称")
    protected String label;
    @Schema(description = "父ID")
    protected Long parentId;
    @Schema(description = "排序号")
    protected Integer sortValue;
    /**
     * 简称
     */
    @Schema(description = "简称")
    private String abbreviation;
    /**
     * 树结构
     */
    @Schema(description = "树结构")
    private String treePath;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean state;
    /**
     * 描述
     */
    @Schema(description = "描述")
    private String describe;
    /**
     * 类型
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.ORG_TYPE)
     */
    @Schema(description = "类型")
    private String type;
}
