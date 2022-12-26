package top.tangyh.lamp.sms.dto;

import top.tangyh.lamp.sms.enumeration.SourceType;
import top.tangyh.lamp.sms.enumeration.TaskStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 发送任务
 * </p>
 *
 * @author zuihou
 * @since 2021-06-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description="发送任务")
public class SmsTaskPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 短信模板
     * #e_sms_template
     */
    @Schema(description="短信模板")
    private Long templateId;
    /**
     * 执行状态
     * (手机号具体发送状态看sms_send_status表)
     * #TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}
     */
    @Schema(description="执行状态")
    private TaskStatus status;
    /**
     * 发送渠道
     * #SourceType{APP:应用;SERVICE:服务}
     */
    @Schema(description="发送渠道")
    private SourceType sourceType;
    @Schema(description="接收者手机")
    private String telNum;
    /**
     * 主题
     */
    @Schema(description="主题")
    private String topic;
    /**
     * 参数
     * 需要封装为{‘key’:’value’, ...}格式且key必须有序
     */
    @Schema(description="参数")
    private String templateParams;
    /**
     * 发送时间
     */
    @Schema(description="发送时间")
    private LocalDateTime sendTime;
    /**
     * 发送内容
     * 需要封装正确格式化: 您好，张三，您有一个新的快递。
     */
    @Schema(description="发送内容")
    private String content;
    /**
     * 是否草稿
     */
    @Schema(description="是否草稿")
    private Boolean draft;

}
