package top.tangyh.lamp.msg.strategy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 消息执行返回值
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/7/25 10:43 PM
 * @create [2022/7/25 10:43 PM ] [tangyh] [初始创建]
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class MsgResult {
    /**
     * 消息标题
     */
    private String title;
    /** 消息内容 */
    private String content;
    /** 返回结果 */
    private Object result;
}
