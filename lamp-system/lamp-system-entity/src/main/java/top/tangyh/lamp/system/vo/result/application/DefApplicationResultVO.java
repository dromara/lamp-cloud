package top.tangyh.lamp.system.vo.result.application;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.annotation.echo.Echo;
import top.tangyh.basic.base.entity.Entity;
import top.tangyh.basic.interfaces.echo.EchoVO;
import top.tangyh.lamp.model.constant.EchoApi;
import top.tangyh.lamp.model.constant.EchoDictType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static top.tangyh.lamp.model.constant.Condition.LIKE;

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
public class DefApplicationResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;
    @Builder.Default
    private Map<String, Object> echoMap = new HashMap<>();

    @Schema(description = "主键")
    private Long id;

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
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.System.APPLICATION_TYPE)
    
    private String type;
    /**
     * 简介
     */
    @Schema(description = "简介")
    
    private String introduce;
    @Schema(description = "重定向")
    private String redirect;
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
    /**
     * 是否公共应用;0-否 1-是
     */
    @Schema(description = "是否公共应用")
    private Boolean isGeneral;

    /**
     * 过期时间
     */
    @Schema(description = "过期时间")
    private LocalDateTime expirationTime;

    /**
     * 过期状态 0-过期 1-有效
     */
    @Schema(description = "过期状态 0-过期 1-有效")
    private String state;

}
