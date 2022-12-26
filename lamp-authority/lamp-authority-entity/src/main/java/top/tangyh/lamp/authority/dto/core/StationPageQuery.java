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
 * 岗位
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
@Schema(description="岗位")
public class StationPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description="名称")
    private String name;
    /**
     * 组织
     * #c_org
     *
     * @Echo(api = ORG_ID_CLASS,  beanClass = Org.class)
     */
    @Schema(description="组织")
    private Long orgId;
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
    private Long createdOrgId;
}
