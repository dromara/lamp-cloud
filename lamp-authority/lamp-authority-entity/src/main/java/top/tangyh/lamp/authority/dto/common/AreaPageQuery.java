package top.tangyh.lamp.authority.dto.common;


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
@Schema(description = "地区表")
public class AreaPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "名称")
    protected String label;
    @Schema(description = "父ID")
    protected Long parentId;
    @Schema(description = "排序号")
    protected Integer sortValue;
    /**
     * 编码
     */
    @Schema(description = "编码")
    private String code;
    /**
     * 全名
     */
    @Schema(description = "全名")
    private String fullName;
    /**
     * 经度
     */
    @Schema(description = "经度")
    private String longitude;
    /**
     * 维度
     */
    @Schema(description = "维度")
    private String latitude;
    /**
     * 数据来源
     */
    @Schema(description = "数据来源")
    private String source;
    /**
     * 行政区级
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.AREA_LEVEL)
     */
    @Schema(description = "行政区级")
    private String level;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean state;
}
