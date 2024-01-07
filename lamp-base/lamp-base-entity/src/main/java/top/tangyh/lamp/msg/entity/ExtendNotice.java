package top.tangyh.lamp.msg.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.Entity;

import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.EQUAL;
import static top.tangyh.lamp.model.constant.Condition.LIKE;


/**
 * <p>
 * 实体类
 * 通知表
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
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("extend_notice")
public class ExtendNotice extends Entity<Long> {
    private static final long serialVersionUID = 1L;
    /**
     * 消息ID
     */
    @TableField(value = "msg_id", condition = LIKE)
    private Long msgId;
    /**
     * 业务ID
     */
    @TableField(value = "biz_id", condition = LIKE)
    private String bizId;
    /**
     * 业务类型
     */
    @TableField(value = "biz_type", condition = LIKE)
    private String bizType;
    /**
     * 接收人
     */
    @TableField(value = "recipient_id", condition = EQUAL)
    private Long recipientId;
    /**
     * 提醒方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)
     * [01-待办 02-预警 03-提醒]
     */
    @TableField(value = "remind_mode")
    private String remindMode;
    /**
     * 标题
     */
    @TableField(value = "title", condition = LIKE)
    private String title;
    /**
     * 内容
     */
    @TableField(value = "content", condition = LIKE)
    private String content;
    /**
     * 发布人
     */
    @TableField(value = "author", condition = LIKE)
    private String author;
    /**
     * 处理地址
     */
    @TableField(value = "url", condition = LIKE)
    private String url;
    /**
     * 打开方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_TARGET)
     * [01-页面 02-弹窗 03-新开窗口]
     */
    @TableField(value = "target_")
    private String target;
    /**
     * 自动已读
     */
    @TableField(value = "auto_read", condition = EQUAL)
    private Boolean autoRead;
    /**
     * 处理时间
     */
    @TableField(value = "handle_time", condition = EQUAL)
    private LocalDateTime handleTime;
    /**
     * 读取时间
     */
    @TableField(value = "read_time", condition = EQUAL)
    private LocalDateTime readTime;
    /**
     * 是否已读
     */
    @TableField(value = "is_read", condition = EQUAL)
    private Boolean isRead;
    /**
     * 是否处理
     */
    @TableField(value = "is_handle", condition = EQUAL)
    private Boolean isHandle;
    /**
     * 所属组织
     */
    @TableField(value = "created_org_id", condition = EQUAL)
    private Long createdOrgId;

}
