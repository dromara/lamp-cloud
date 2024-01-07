package top.tangyh.lamp.system.vo.query.application;

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
 * 应用
 * </p>
 *
 * @author zuihou
 * @since 2021-09-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "应用")
public class DefApplicationPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 应用标识
     */
    @Schema(description = "应用标识")
    private String appKey;
    /**
     * 应用秘钥
     */
    @Schema(description = "应用秘钥")
    private String appSecret;
    /**
     * 应用名称
     */
    @Schema(description = "应用名称")
    private String name;
    /**
     * 版本
     */
    @Schema(description = "版本")
    private String version;
    /**
     * 应用类型;[10-自建应用 20-第三方应用]
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.APPLICATION_TYPE)
     */
    @Schema(description = "应用类型")
    private String type;
    /**
     * 简介
     */
    @Schema(description = "简介")
    private String introduce;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
    /**
     * 应用地址
     */
    @Schema(description = "应用地址")
    private String url;
    /**
     * 是否可见;0-否 1-是
     */
    @Schema(description = "是否可见")
    private Boolean isVisible;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sortValue;

    private Long id;
}
