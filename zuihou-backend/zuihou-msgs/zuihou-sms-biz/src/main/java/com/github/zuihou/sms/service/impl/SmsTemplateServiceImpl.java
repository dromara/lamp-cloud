package com.github.zuihou.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.sms.dao.SmsTemplateMapper;
import com.github.zuihou.sms.entity.SmsTemplate;
import com.github.zuihou.sms.service.SmsTemplateService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 短信模板
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Service
public class SmsTemplateServiceImpl extends ServiceImpl<SmsTemplateMapper, SmsTemplate> implements SmsTemplateService {

}
