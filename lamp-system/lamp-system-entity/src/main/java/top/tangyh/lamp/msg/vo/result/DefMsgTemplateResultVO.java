package top.tangyh.lamp.msg.vo.result;

import cn.hutool.core.map.MapUtil;
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
import java.util.Map;

/**
 * <p>
 * 表单查询方法返回值VO
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
@Schema(description = "消息模板")
public class DefMsgTemplateResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;

    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "模板ID")
    private Long id;

    /**
     * 消息类型;
     * [01-短信 02-邮件 03-站内信];
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)
     */
    @Schema(description = "消息类型")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)
    private String type;

    /** 接口ID */
    @Schema(description = "接口ID")
    private Long interfaceId;

    @Schema(description = "状态")
    private Boolean state;
    /**
     * 模板标识
     */
    @Schema(description = "模板标识")
    private String code;
    /**
     * 模板名称
     */
    @Schema(description = "模板名称")
    private String name;
    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;
    /**
     * 模板内容
     */
    @Schema(description = "模板内容")
    private String content;
    /**
     * 脚本
     */
    @Schema(description = "脚本")
    private String script;
    /**
     * 模板参数
     */
    @Schema(description = "模板参数")
    private String param;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remarks;
    /**
     * 打开方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_TARGET)
     * [01-页面 02-弹窗 03-新开窗口]
     */
    @Schema(description = "打开方式")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_TARGET)
    private String target;
    /**
     * 自动已读
     */
    @Schema(description = "自动已读")
    private Boolean autoRead;
    /**
     * 提醒方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)
     * [01-待办 02-预警 03-提醒]
     */
    @Schema(description = "提醒方式")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)
    private String remindMode;
    /**
     * 跳转地址
     */
    @Schema(description = "跳转地址")
    private String url;
    @Schema(description = "短信模板")
    private String templateCode;


    @Schema(description = "签名")
    private String sign;
}
