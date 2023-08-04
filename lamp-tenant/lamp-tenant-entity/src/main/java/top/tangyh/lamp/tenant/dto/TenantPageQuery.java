package top.tangyh.lamp.tenant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.model.enumeration.system.TenantConnectTypeEnum;
import top.tangyh.lamp.model.enumeration.system.TenantStatusEnum;
import top.tangyh.lamp.model.enumeration.system.TenantTypeEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 企业
 * </p>
 *
 * @author zuihou
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "企业")
public class TenantPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业编码
     */
    @Schema(description = "企业编码")
    private String code;
    /**
     * 企业名称
     */
    @Schema(description = "企业名称")
    private String name;
    /**
     * 类型
     * #{CREATE:创建;REGISTER:注册}
     */
    @Schema(description = "类型")
    private TenantTypeEnum type;
    /**
     * 连接类型
     * #TenantConnectTypeEnum{SYSTEM:系统;CUSTOM:自定义}
     */
    @Schema(description = "连接类型")
    private TenantConnectTypeEnum connectType;
    /**
     * 状态
     * #{NORMAL:正常;WAIT_INIT:待初始化;FORBIDDEN:禁用;WAITING:待审核;REFUSE:拒绝;DELETE:已删除}
     */
    @Schema(description = "状态")
    private TenantStatusEnum status;
    /**
     * 内置
     */
    @Schema(description = "内置")
    private Boolean readonly;
    /**
     * 责任人
     */
    @Schema(description = "责任人")
    private String duty;
    /**
     * 有效期
     * 为空表示永久
     */
    @Schema(description = "有效期")
    private LocalDateTime expirationTime;
    /**
     * logo地址
     */
    @Schema(description = "logo地址")
    private String logo;
    /**
     * 企业简介
     */
    @Schema(description = "企业简介")
    private String describe;

}
