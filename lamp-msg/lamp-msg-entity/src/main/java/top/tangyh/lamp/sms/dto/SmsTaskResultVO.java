package top.tangyh.lamp.sms.dto;

import lombok.Data;
import top.tangyh.lamp.sms.entity.SmsTask;

import java.util.List;

/**
 * @author tangyh
 * @version v1.0
 * @date 2021/6/25 11:40 下午
 * @create [2021/6/25 11:40 下午 ] [tangyh] [初始创建]
 */
@Data
public class SmsTaskResultVO extends SmsTask {
    private List<String> telNumList;
}
