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

import static com.baomidou.mybatisplus.annotation.SqlCondition.EQUAL;
import static top.tangyh.lamp.model.constant.Condition.LIKE;


/**
 * <p>
 * 实体类
 * 消息模板
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
@TableName("def_msg_template")
public class DefMsgTemplate extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 消息类型;
     * [01-短信 02-邮件 03-站内信];
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)
     */
    @TableField(value = "type", condition = LIKE)
    private String type;
    /** 状态 */
    @TableField(value = "state")
    private Boolean state;
    /** 接口ID */
    @TableField(value = "interface_id")
    private Long interfaceId;
    /**
     * 模板标识
     */
    @TableField(value = "code", condition = LIKE)
    private String code;
    /**
     * 模板名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;
    /**
     * 标题
     */
    @TableField(value = "title", condition = LIKE)
    private String title;
    /**
     * 模板内容
     */
    @TableField(value = "content", condition = LIKE)
    private String content;
    /**
     * 脚本
     */
    @TableField(value = "script", condition = LIKE)
    private String script;
    /**
     * 短信签名名称
     */
    @TableField(value = "sign", condition = LIKE)
    private String sign;
    /**
     * 模板参数
     */
    @TableField(value = "param", condition = LIKE)
    private String param;
    /**
     * 备注
     */
    @TableField(value = "remarks", condition = LIKE)
    private String remarks;
    /**
     * 打开方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_TARGET)
     * [01-页面 02-弹窗 03-新开窗口]
     */
    @TableField(value = "target_", condition = LIKE)
    private String target;
    /**
     * 自动已读
     */
    @TableField(value = "auto_read", condition = EQUAL)
    private Boolean autoRead;
    /**
     * 提醒方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)
     * [01-待办 02-预警 03-提醒]
     */
    @TableField(value = "remind_mode", condition = LIKE)
    private String remindMode;
    /**
     * 跳转地址
     */
    @TableField(value = "url", condition = LIKE)
    private String url;
    /**
     * 短信模板
     */
    @TableField(value = "template_code", condition = LIKE)
    private String templateCode;


}
