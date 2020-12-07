package com.tangyh.lamp.sms.strategy.domain;


import com.tangyh.lamp.sms.enumeration.ProviderType;
import com.tangyh.lamp.sms.enumeration.SendStatus;
import lombok.Data;

/**
 * 阿里：状态码-返回OK代表请求成功,其他错误码详见错误码列表      # https://help.aliyun.com/document_detail/55284.html?spm=a2c4g.11186623.6.567.17555777p1osU3
 * <p>
 * 腾讯：错误码，0 表示成功(计费依据)，非 0 表示失败            # https://cloud.tencent.com/document/product/382/5976
 * <p>
 * 百度：短信提交状态，1000 表示提交成功                       # https://cloud.baidu.com/doc/SMS/API.html#.E7.9F.AD.E4.BF.A1.E4.B8.8B.E5.8F.91
 *
 * @author zuihou
 * @date 2019-05-15
 */
@Data
public class SmsResult {

    /**
     * 阿里：RequestId 请求ID
     * 腾讯：ext：用户的session内容，腾讯server回包中会原样返回
     * 百度：无
     */
    private String ext;
    /**
     * 阿里：发送回执ID,可根据该ID查询具体的发送状态
     * 腾讯：sid 标识本次发送id，标识一次短信下发记录
     * 百度：requestId 短信发送请求唯一流水ID
     */
    private String bizId;
    /**
     * 状态码:
     * 阿里：返回OK代表请求成功,其他错误码详见错误码列表
     * 腾讯：0表示成功(计费依据)，非0表示失败
     * 百度：1000 表示成功
     */
    private String code;
    /**
     * 状态码的描述
     */
    private String message;

    /**
     * 短信计费的条数:腾讯专用
     */
    private int fee;

    private SendStatus sendStatus = SendStatus.WAITING;

    SmsResult() {
    }

    public static SmsResult build(ProviderType type, String code, String bizId, String ext, String message, int fee) {
        SmsResult smsResult = new SmsResult();
        smsResult.setCode(code);
        smsResult.setBizId(bizId);
        smsResult.setExt(ext);
        smsResult.setMessage(message);
        smsResult.setFee(fee);

        SendStatus status = type.getTaskStatus(code);
        smsResult.setSendStatus(status);
        return smsResult;
    }

    public static SmsResult fail(String message) {
        SmsResult smsResult = new SmsResult();
        smsResult.setSendStatus(SendStatus.FAIL);
        smsResult.setMessage(message);
        return smsResult;
    }

    public boolean isSuccess() {
        return SendStatus.SUCCESS.eq(sendStatus);
    }

}
