package com.tangyh.lamp.authority.dto.common;

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
 * 地区表
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
@ApiModel(value = "AreaPageQuery", description = "地区表")
public class AreaPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;
    /**
     * 全名
     */
    @ApiModelProperty(value = "全名")
    private String fullName;
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String longitude;
    /**
     * 维度
     */
    @ApiModelProperty(value = "维度")
    private String latitude;
    @ApiModelProperty(value = "名称")
    protected String label;
    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    private String source;
    @ApiModelProperty(value = "父ID")
    protected Long parentId;
    @ApiModelProperty(value = "排序号")
    protected Integer sortValue;
    /**
     * 行政区级
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.AREA_LEVEL)
     */
    @ApiModelProperty(value = "行政区级")
    private String level;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;
}
