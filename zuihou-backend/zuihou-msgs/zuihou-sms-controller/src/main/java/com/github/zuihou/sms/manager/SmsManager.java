package com.github.zuihou.sms.manager;

import java.time.LocalDateTime;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.sms.entity.SmsTask;
import com.github.zuihou.sms.entity.SmsTemplate;
import com.github.zuihou.sms.enumeration.TemplateCodeType;
import com.github.zuihou.sms.service.SmsTaskService;
import com.github.zuihou.sms.service.SmsTemplateService;
import com.github.zuihou.sms.util.PhoneUtils;
import com.github.zuihou.utils.BizAssert;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.github.zuihou.exception.code.ExceptionCode.BASE_VALID_PARAM;
import static com.github.zuihou.utils.BizAssert.assertFalse;
import static com.github.zuihou.utils.BizAssert.assertNotNull;
import static com.github.zuihou.utils.BizAssert.assertTrue;


/**
 * 抽取controller 和 open 中的公共代码
 * 短信管理类，用于抽取合并open和controller层中方法的公共代码
 *
 * @author zuihou
 * @date 2018/12/24
 */
@Component
public class SmsManager {
    @Autowired
    private SmsTaskService smsTaskService;
    @Autowired
    private SmsTemplateService smsTemplateService;

    /**
     * 保存短信任务
     *
     * @param smsTask
     */
    public void saveTask(SmsTask smsTask, TemplateCodeType type) {

        if (type != null) {
            SmsTemplate template = smsTemplateService.getOne(Wrappers.<SmsTemplate>lambdaQuery()
                    .eq(SmsTemplate::getCustomCode, type.name()));
            BizAssert.assertNotNull(BASE_VALID_PARAM.build("短信参数不能为空"), template);

            smsTask.setProviderId(template.getProviderId());
            smsTask.setTemplateId(template.getId());

            if (StringUtils.isEmpty(smsTask.getTopic())) {
                smsTask.setTopic(template.getSignName());
            }
        }

        //1，验证必要参数
        Set<String> phoneList = PhoneUtils.getPhone(smsTask.getReceiver());
        assertFalse(BASE_VALID_PARAM.build("接收人不能为空"), phoneList == null || phoneList.isEmpty());

        // 验证定时发送的时间，至少大于（当前时间+5分钟） ，是为了防止 定时调度或者是保存数据跟不上
        if (smsTask.getSendTime() != null) {
            boolean flag = LocalDateTime.now().plusMinutes(5).isBefore(smsTask.getSendTime());
            assertTrue(BASE_VALID_PARAM.build("定时发送时间至少在当前时间的5分钟之后"), flag);
        }

        if (StringUtils.isNotEmpty(smsTask.getContext()) && smsTask.getContext().length() > 450) {
            throw new BizException(BASE_VALID_PARAM.getCode(), "发送内容不能超过500字");
        }

        String templateParams = smsTask.getTemplateParams();
        JSONObject obj = JSONObject.parseObject(templateParams, Feature.OrderedField);
        assertNotNull(BASE_VALID_PARAM.build("短信参数格式必须为严格的json字符串"), obj);

        smsTaskService.saveTask(smsTask);
    }

}
