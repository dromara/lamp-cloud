package com.github.zuihou.msgs.dto;

import com.github.zuihou.msgs.enumeration.MsgsBizType;
import com.github.zuihou.msgs.enumeration.MsgsCenterType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 消息中心DTO
 *
 * @author zuihou
 * @date 2019/08/02
 */

@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@Accessors(chain = true)
@ApiModel(value = "MsgsCenterInfoDTO", description = "消息中心")
public class MsgsCenterInfoDTO implements Serializable {


    private static final long serialVersionUID = 2499447636270422316L;
    /**
     * 业务ID
     * 业务表的唯一id
     */
    @ApiModelProperty(value = "业务ID")
    @Length(max = 64, message = "业务ID长度不能超过64")
    private String bizId;
    /**
     * 业务类型
     * #MsgsBizType{USER_LOCK:账号锁定;}
     */
    @ApiModelProperty(value = "业务类型")
    private MsgsBizType bizType;
    /**
     * 消息类型
     * #MsgsCenterType{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}
     */
    @ApiModelProperty(value = "消息类型")
    @NotNull(message = "消息类型不能为空")
    private MsgsCenterType msgsCenterType;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @Length(max = 255, message = "标题长度不能超过255")
    private String title;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    @Length(max = 65535, message = "内容长度不能超过65,535")
    private String content;
    /**
     * 作者
     */
    @ApiModelProperty(value = "作者")
    @Length(max = 50, message = "作者长度不能超过50")
    private String author;
    /**
     * 处理地址
     * 以http开头时直接跳转，否则与#c_application表拼接后跳转
     * http可带参数
     */
    @ApiModelProperty(value = "处理地址")
    @Length(max = 255, message = "处理地址长度不能超过255")
    private String handlerUrl;
    /**
     * 处理参数
     */
    @ApiModelProperty(value = "处理参数")
    @Length(max = 400, message = "处理参数长度不能超过400")
    private String handlerParams;
    /**
     * 是否单人处理
     */
    @ApiModelProperty(value = "是否单人处理")
    private Boolean isSingleHandle;


    /**
     * 构建 通知类型的 消息中心
     *
     * @param bizType    业务类型
     * @param bizId      业务id
     * @param title      标题
     * @param content    内容
     * @param handlerUrl 跳转地址
     * @return
     */
    public static MsgsCenterInfoDTO buildNotify(MsgsBizType bizType, String bizId, String title, String content, String handlerUrl) {
        return MsgsCenterInfoDTO.builder()
                .bizType(bizType).bizId(bizId)
                .msgsCenterType(MsgsCenterType.NOTIFY)
                .title(title).content(content)
                .handlerUrl(handlerUrl)
                .build();
    }

    /**
     * 构建 代办类型的 消息中心
     *
     * @param bizType    业务类型
     * @param bizId      业务id
     * @param title      标题
     * @param content    内容
     * @param handlerUrl 跳转地址
     * @return
     */
    public static MsgsCenterInfoDTO buildWait(MsgsBizType bizType, String bizId,
                                              String title, String content, String handlerUrl) {
        return MsgsCenterInfoDTO.builder()
                .bizType(bizType).bizId(bizId)
                .msgsCenterType(MsgsCenterType.WAIT)
                .title(title).content(content)
                .handlerUrl(handlerUrl)
                .build();
    }

    /**
     * 构建 预警类型的 消息中心
     *
     * @param bizType    业务类型
     * @param bizId      业务id
     * @param title      标题
     * @param content    内容
     * @param handlerUrl 跳转地址
     * @return
     */
    public static MsgsCenterInfoDTO buildWarn(MsgsBizType bizType, String bizId,
                                              String title, String content, String handlerUrl) {
        return MsgsCenterInfoDTO.builder()
                .bizType(bizType).bizId(bizId)
                .msgsCenterType(MsgsCenterType.WARN)
                .title(title).content(content)
                .handlerUrl(handlerUrl)
                .build();
    }

    /**
     * 构建 公示公告类型的 消息中心
     *
     * @param bizType    业务类型
     * @param bizId      业务id
     * @param title      标题
     * @param content    内容
     * @param handlerUrl 跳转地址
     * @return
     */
    public static MsgsCenterInfoDTO buildPublicity(MsgsBizType bizType, String bizId,
                                                   String title, String content, String handlerUrl) {
        return MsgsCenterInfoDTO.builder()
                .bizType(bizType).bizId(bizId)
                .msgsCenterType(MsgsCenterType.PUBLICITY)
                .title(title).content(content)
                .handlerUrl(handlerUrl)
                .build();
    }
}
