package top.tangyh.lamp.sms.service;

import top.tangyh.basic.base.service.SuperService;
import top.tangyh.basic.model.LoadService;
import top.tangyh.lamp.sms.entity.SmsTemplate;

/**
 * <p>
 * 业务接口
 * 短信模板
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
public interface SmsTemplateService extends SuperService<SmsTemplate>, LoadService {
    /**
     * 保存模板，并且将模板内容解析成json格式
     *
     * @param smsTemplate 短信模版
     */
    void saveTemplate(SmsTemplate smsTemplate);

    /**
     * 修改
     *
     * @param smsTemplate 短信模版
     */
    void updateTemplate(SmsTemplate smsTemplate);
}
