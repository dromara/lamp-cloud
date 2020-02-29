package com.github.zuihou.sms.manager;

import com.github.zuihou.sms.entity.SmsTask;
import com.github.zuihou.sms.enumeration.TemplateCodeType;
import com.github.zuihou.sms.service.SmsTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 抽取controller 和 open 中的公共代码
 * 短信管理类，用于抽取合并open和controller层中方法的公共代码
 *
 * @author zuihou
 * @date 2018/12/24
 */
@Component
@Slf4j
public class SmsManager {
    @Autowired
    private SmsTaskService smsTaskService;

    /**
     * 保存短信任务
     *
     * @param smsTask
     */
    public void saveTask(SmsTask smsTask, TemplateCodeType type) {

    }


}
