package top.tangyh.lamp.msg.strategy.domain.sms;

import lombok.Data;

/**
 * @author zuihou
 * @date 2021/7/15 16:13
 */
@Data
public class ClSendResult {
    private String code;
    private String failNum;
    private String successNum;
    private String msgId;
    private String time;
    private String errorMsg;

}
