package com.github.zuihou.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.sms.dao.SmsProviderMapper;
import com.github.zuihou.sms.entity.SmsProvider;
import com.github.zuihou.sms.service.SmsProviderService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 短信供应商
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Service
public class SmsProviderServiceImpl extends ServiceImpl<SmsProviderMapper, SmsProvider> implements SmsProviderService {

}
