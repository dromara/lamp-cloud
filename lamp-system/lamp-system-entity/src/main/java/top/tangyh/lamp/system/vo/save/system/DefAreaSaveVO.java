package top.tangyh.lamp.system.vo.save.system;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
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
 * 地区表
 * </p>
 *
 * @author zuihou
 * @since 2021-10-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "地区表")
public class DefAreaSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description = "名称")
    @NotEmpty(message = "请填写名称")
    @Size(max = 255, message = "名称长度不能超过{max}")
    protected String name;
    @Schema(description = "父节点")
    protected Long parentId;
    @Schema(description = "排序号")
    protected Integer sortValue;
    /**
     * 编码
     */
    @Schema(description = "编码")
    @NotEmpty(message = "请填写编码")
    @Size(max = 64, message = "编码长度不能超过{max}")
    private String code;
    /**
     * 全名
     */
    @Schema(description = "全名")
    @Size(max = 255, message = "全名长度不能超过{max}")
    private String fullName;
    /**
     * 经度
     */
    @Schema(description = "经度")
    @Size(max = 255, message = "经度长度不能超过{max}")
    private String longitude;
    /**
     * 维度
     */
    @Schema(description = "维度")
    @Size(max = 255, message = "维度长度不能超过{max}")
    private String latitude;
    /**
     * 行政级别;[10-国家 20-省份/直辖市 30-地市 40-区县 50-乡镇]@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Global.AREA_LEVEL)
     */
    @Schema(description = "行政级别")
    @Size(max = 2, message = "行政级别长度不能超过{max}")
    private String level;
    /**
     * 数据来源;[10-爬取 20-新增]	@Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.AREA_SOURCE)
     */
    @Schema(description = "数据来源")
    @Size(max = 2, message = "数据来源长度不能超过{max}")
    private String source;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean state;
}
