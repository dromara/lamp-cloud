package top.tangyh.lamp.msg.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import top.tangyh.lamp.model.vo.BaseEventVO;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/7/29 10:08 PM
 * @create [2022/7/29 10:08 PM ] [tangyh] [初始创建]
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
public class MsgEventVO extends BaseEventVO {
    Long msgId;
}
