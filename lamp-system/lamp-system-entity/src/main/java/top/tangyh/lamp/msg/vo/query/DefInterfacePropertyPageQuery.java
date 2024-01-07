package top.tangyh.lamp.msg.vo.query;

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
 * 表单查询条件VO
 * 接口属性
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 15:51:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(description = "接口属性")
public class DefInterfacePropertyPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    /**
     * 接口ID
     */
    @Schema(description = "接口ID")
    private Long interfaceId;
    /**
     * 参数名称
     */
    @Schema(description = "参数名称")
    private String name;
    /**
     * 参数键
     */
    @Schema(description = "参数键")
    private String key;
    /**
     * 参数值
     */
    @Schema(description = "参数值")
    private String value;
    /**
     * 顺序号
     */
    @Schema(description = "顺序号")
    private Integer sortValue;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remarks;


}
