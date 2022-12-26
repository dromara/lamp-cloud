package top.tangyh.lamp.authority.dto.common;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
@Schema(description="地区表")
public class AreaSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @Schema(description="编码")
    @NotEmpty(message = "编码不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    private String code;
    /**
     * 全名
     */
    @Schema(description="全名")
    @Size(max = 255, message = "全名长度不能超过255")
    private String fullName;
    /**
     * 经度
     */
    @Schema(description="经度")
    @Size(max = 255, message = "经度长度不能超过255")
    private String longitude;
    /**
     * 维度
     */
    @Schema(description="维度")
    @Size(max = 255, message = "维度长度不能超过255")
    private String latitude;
    @Schema(description="名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 255, message = "名称长度不能超过255")
    protected String label;
    /**
     * 数据来源
     */
    @Schema(description="数据来源")
    @Size(max = 255, message = "数据来源长度不能超过255")
    private String source;
    /**
     * 状态
     */
    @Schema(description="状态")
    private Boolean state;
    @Schema(description="父ID")
    protected Long parentId;
    @Schema(description="排序号")
    protected Integer sortValue;
    /**
     * 行政区级
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.AREA_LEVEL)
     */
    @Schema(description="行政区级")
    @Size(max = 10, message = "行政区级长度不能超过10")
    private String level;
}
