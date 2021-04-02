package com.tangyh.lamp.authority.dto.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "OrgPageQuery", description = "组织")
public class OrgPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称")
    protected String label;
    /**
     * 简称
     */
    @ApiModelProperty(value = "简称")
    private String abbreviation;
    /**
     * 树结构
     */
    @ApiModelProperty(value = "树结构")
    private String treePath;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String describe;
    @ApiModelProperty(value = "父ID")
    protected Long parentId;
    @ApiModelProperty(value = "排序号")
    protected Integer sortValue;
    /**
     * 类型
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.ORG_TYPE)
     */
    @ApiModelProperty(value = "类型")
    private String type;
}
