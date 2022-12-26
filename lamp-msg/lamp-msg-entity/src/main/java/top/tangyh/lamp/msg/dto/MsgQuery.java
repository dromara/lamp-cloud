package top.tangyh.lamp.msg.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.msg.enumeration.MsgBizType;
import top.tangyh.lamp.msg.enumeration.MsgType;

import java.io.Serializable;

/**
 * 消息分页参数
 *
 * @author zuihou
 * @date 2019/08/02
 */

@Data
@ToString
@Accessors(chain = true)
@Schema(description = "消息分页参数")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MsgQuery implements Serializable {
    private static final long serialVersionUID = -2054606159972155030L;
    @Schema(description = "接收人ID")
    private Long userId;
    @Schema(description = "是否已读")
    private Boolean isRead;
    @Schema(description = "消息类型")
    private MsgType msgType;
    @Schema(description = "业务类型")
    private MsgBizType bizType;

    @Schema(description = "内容")
    private String content;
    @Schema(description = "标题")
    private String title;

    @Schema(description = "作者")
    private String author;

}
