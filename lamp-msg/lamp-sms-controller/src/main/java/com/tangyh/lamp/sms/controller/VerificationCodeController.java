package com.tangyh.lamp.sms.controller;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.tangyh.basic.base.R;
import com.tangyh.basic.base.entity.SuperEntity;
import com.tangyh.basic.cache.model.CacheKey;
import com.tangyh.basic.cache.repository.CacheOps;
import com.tangyh.lamp.common.cache.VerificationCodeCacheKeyBuilder;
import com.tangyh.lamp.sms.dto.VerificationCodeDTO;
import com.tangyh.lamp.sms.entity.SmsTask;
import com.tangyh.lamp.sms.enumeration.SourceType;
import com.tangyh.lamp.sms.enumeration.TemplateCodeType;
import com.tangyh.lamp.sms.service.SmsTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通用验证码
 *
 * @author zuihou
 * @date 2019/08/06
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/verification")
@Api(value = "VerificationCode", tags = "验证码")
@RequiredArgsConstructor
public class VerificationCodeController {

    private final CacheOps cacheOps;
    private final SmsTaskService smsTaskService;

    /**
     * 通用的发送验证码功能
     * <p>
     * 一个系统可能有很多地方需要发送验证码（注册、找回密码等），每增加一个场景，VerificationCodeType 就需要增加一个枚举值
     */
    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    @PostMapping(value = "/send")
    public R<Boolean> send(@Validated @RequestBody VerificationCodeDTO data) {
        String code = RandomUtil.randomNumbers(6);

        SmsTask smsTask = SmsTask.builder().build();
        smsTask.setSourceType(SourceType.SERVICE);
        JSONObject param = new JSONObject();
        param.put("1", code);
        smsTask.setTemplateParams(param.toString());
        smsTask.setReceiver(data.getMobile());
        smsTask.setDraft(false);
        smsTaskService.saveTask(smsTask, TemplateCodeType.COMMON_SMS);

        CacheKey cacheKey = new VerificationCodeCacheKeyBuilder().key(data.getType().name(), data.getMobile());
        cacheOps.set(cacheKey, code);
        return R.success();
    }

    /**
     * 对某种类型的验证码进行校验
     *
     */
    @ApiOperation(value = "验证验证码", notes = "验证验证码")
    @PostMapping
    public R<Boolean> verification(@Validated(SuperEntity.Update.class) @RequestBody VerificationCodeDTO data) {
        CacheKey cacheKey = new VerificationCodeCacheKeyBuilder().key(data.getType().name(), data.getMobile());
        String code = cacheOps.get(cacheKey);
        return R.success(data.getCode().equals(code));
    }
}
