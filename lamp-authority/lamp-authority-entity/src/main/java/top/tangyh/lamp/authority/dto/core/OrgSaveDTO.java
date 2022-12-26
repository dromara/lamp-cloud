package top.tangyh.lamp.authority.dto.core;


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
@Schema(description="组织")
public class OrgSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description="名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 255, message = "名称长度不能超过255")
    protected String label;
    /**
     * 简称
     */
    @Schema(description="简称")
    @Size(max = 255, message = "简称长度不能超过255")
    private String abbreviation;
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
     * 类型
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = EchoDictType.ORG_TYPE)
     */
    @Schema(description="类型")
    @Size(max = 2, message = "类型长度不能超过2")
    private String type;
    /**
     * 描述
     */
    @Schema(description="描述")
    @Size(max = 255, message = "描述长度不能超过255")
    private String describe;
}
