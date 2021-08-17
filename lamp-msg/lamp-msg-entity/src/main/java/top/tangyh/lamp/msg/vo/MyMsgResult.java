package top.tangyh.lamp.msg.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.tangyh.lamp.msg.dto.MsgPageResult;

import java.io.Serializable;

/**
 * 我的消息
 *
 * @author zuihou
 * @date 2021/8/8 23:48
 */
@Data
@EqualsAndHashCode
@ApiModel(value = "MyMsgResult", description = "我的消息")
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyMsgResult implements Serializable {
    private IPage<MsgPageResult> todoList;
    private IPage<MsgPageResult> notifyList;
    private IPage<MsgPageResult> noticeList;
    private IPage<MsgPageResult> earlyWarningList;
}
