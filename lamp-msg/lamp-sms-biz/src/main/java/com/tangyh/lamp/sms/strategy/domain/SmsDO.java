package com.tangyh.lamp.sms.strategy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 短信发送DO
 *
 * @author zuihou
 * @date 2018/12/20
 */
@Data
@AllArgsConstructor
@Builder
public class SmsDO {
    /**
     * 任务id
     */
    private Long taskId;
    /**
     * 接受者手机号
     */
    private String telNum;
    /**
     * 发送账号安全认证的Access Key ID
     */
    private String appId;
    /**
     * 发送账号安全认证的Secret Access Key
     */
    private String appSecret;
    /**
     * 发送使用签名
     */
    private String signName;
    /**
     * 模板编码
     */
    private String templateCode;
    /**
     * SMS服务域名 百度/其他第三方需要
     */
    private String endPoint;

    /**
     * 短信参数
     * 腾讯是数组
     * 阿里百度 是json
     */
    private String templateParams;

}
