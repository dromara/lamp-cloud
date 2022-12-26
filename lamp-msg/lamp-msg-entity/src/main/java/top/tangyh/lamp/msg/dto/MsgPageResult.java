package top.tangyh.lamp.msg.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.tangyh.lamp.msg.entity.Msg;

import java.time.LocalDateTime;

import static top.tangyh.basic.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * 消息分页返回
 *
 * @author zuihou
 * @date 2019/08/02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "消息分页返回")
@ToString(callSuper = true)
public class MsgPageResult extends Msg {
    private static final long serialVersionUID = -44224723996050485L;
    @Schema(description = "状态")
    @Excel(name = "状态", replace = {"已读_true", "未读_false", "_null"})
    private Boolean isRead;
    @Schema(description = "读消息的时间")
    @Excel(name = "读消息时间", width = 20, format = DEFAULT_DATE_TIME_FORMAT)
    private LocalDateTime readTime;
    @Schema(description = "接收表id")
    private Long receiveId;
}
