package com.tangyh.lamp.sms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tangyh.basic.base.R;
import com.tangyh.basic.base.request.PageParams;
import com.tangyh.basic.base.service.SuperServiceImpl;
import com.tangyh.basic.context.ContextConstants;
import com.tangyh.basic.context.ContextUtil;
import com.tangyh.basic.database.mybatis.conditions.Wraps;
import com.tangyh.basic.exception.BizException;
import com.tangyh.basic.utils.BeanPlusUtil;
import com.tangyh.basic.utils.BizAssert;
import com.tangyh.lamp.common.api.JobApi;
import com.tangyh.lamp.common.constant.JobConstant;
import com.tangyh.lamp.common.dto.XxlJobInfoVO;
import com.tangyh.lamp.sms.dao.SmsTaskMapper;
import com.tangyh.lamp.sms.dto.SmsTaskPageQuery;
import com.tangyh.lamp.sms.dto.SmsTaskSaveDTO;
import com.tangyh.lamp.sms.dto.SmsTaskUpdateDTO;
import com.tangyh.lamp.sms.entity.SmsSendStatus;
import com.tangyh.lamp.sms.entity.SmsTask;
import com.tangyh.lamp.sms.entity.SmsTemplate;
import com.tangyh.lamp.sms.enumeration.SendStatus;
import com.tangyh.lamp.sms.enumeration.SourceType;
import com.tangyh.lamp.sms.enumeration.TaskStatus;
import com.tangyh.lamp.sms.service.SmsSendStatusService;
import com.tangyh.lamp.sms.service.SmsTaskService;
import com.tangyh.lamp.sms.service.SmsTemplateService;
import com.tangyh.lamp.sms.strategy.SmsContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.tangyh.basic.exception.code.ExceptionCode.BASE_VALID_PARAM;

/**
 * <p>
 * 业务实现类
 * 发送任务
 * 所有的短息发送调用，都视为是一次短信任务，任务表只保存数据和执行状态等信息，
 * 具体的发送状态查看发送状态（#sms_send_status）表
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Service

@RequiredArgsConstructor
public class SmsTaskServiceImpl extends SuperServiceImpl<SmsTaskMapper, SmsTask> implements SmsTaskService {
    private final JobApi jobApi;
    private final SmsContext smsContext;
    private final SmsTemplateService smsTemplateService;
    private final SmsSendStatusService smsSendStatusService;

    private static String processTemplate(String template, String regex, LinkedHashMap<String, String> params) {
        log.info("regex={}, template={}", regex, template);
        log.info("params={}", params.toString());
        StringBuffer sb = new StringBuffer();
        Matcher m = Pattern.compile(regex).matcher(template);
        while (m.find()) {
            String key = m.group(1);
            String value = params.get(key);
            value = value == null ? "" : value;
            if (value.contains("$")) {
                value = value.replaceAll("\\$", "\\\\\\$");
            }
            m.appendReplacement(sb, value);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<SmsTask> pageSmsTask(IPage<SmsTask> page, PageParams<SmsTaskPageQuery> query) {
        return baseMapper.pageSmsTask(page, query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SmsTask saveTask(SmsTaskSaveDTO data) {
        if (data.getSourceType() == null) {
            data.setSourceType(SourceType.APP);
        }
        validAndInit(data);

        SmsTask smsTask = BeanPlusUtil.toBean(data, SmsTask.class);
        smsTask.setTemplateParams(JSONUtil.toJsonStr(data.getTemplateParam()));
        send(smsTask, (task) -> {
            this.save(task);
            List<SmsSendStatus> list = data.getTelNum().stream().map(telNum -> {
                SmsSendStatus sss = new SmsSendStatus();
                sss.setTaskId(task.getId());
                sss.setSendStatus(SendStatus.WAITING);
                sss.setTelNum(telNum);
                return sss;
            }).collect(Collectors.toList());
            smsSendStatusService.saveBatch(list);
            return true;
        });
        return smsTask;
    }

    /**
     * 验证数据，并初始化数据
     */
    public void validAndInit(SmsTaskSaveDTO smsTask) {
        SmsTemplate template;

        template = smsTemplateService.getById(smsTask.getTemplateId());
        BizAssert.notNull(template, BASE_VALID_PARAM.build("请选择正确的短信模板"));


        //1，验证必要参数
        BizAssert.isFalse(CollUtil.isEmpty(smsTask.getTelNum()), BASE_VALID_PARAM.build("请填写短信接收人"));

        // 验证定时发送的时间，至少大于（当前时间+5分钟） ，是为了防止 定时调度或者是保存数据跟不上
        if (smsTask.getSendTime() != null) {
            boolean flag = LocalDateTime.now().plusMinutes(4).isBefore(smsTask.getSendTime());
            BizAssert.isTrue(flag, BASE_VALID_PARAM.build("定时发送时间至少在当前时间的5分钟之后"));
        }

//        String templateParams = smsTask.getTemplateParams();
////        JSONObject obj = JSONObject.parseObject(templateParams, Feature.OrderedField);
//        JSONObject obj = new JSONObject(templateParams, true);
//        BizAssert.notNull(obj, BASE_VALID_PARAM.build("短信参数格式必须为严格的json字符串"));

        if (StrUtil.isEmpty(smsTask.getContent())) {
//            smsTask.setContent(content(template.getProviderType(), template.getContent(), smsTask.getTemplateParams()));
            smsTask.setContent(processTemplate(template.getContent(), template.getProviderType().getRegex(), smsTask.getTemplateParam()));
        } else if (smsTask.getContent().length() > 500) {
            throw new BizException(BASE_VALID_PARAM.getCode(), "发送内容不能超过500字");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SmsTask update(SmsTaskUpdateDTO data) {
        SmsTaskSaveDTO validDTO = BeanPlusUtil.toBean(data, SmsTaskSaveDTO.class);
        validAndInit(validDTO);

        SmsTask smsTask = BeanPlusUtil.toBean(validDTO, SmsTask.class);
        smsTask.setId(data.getId());
        smsTask.setSourceType(SourceType.APP);
        smsTask.setTemplateParams(data.getTemplateParam().toString());

        send(smsTask, (task) -> {
            updateById(task);
            if (task.getSendTime() == null) {
                update(Wraps.<SmsTask>lbU()
                        .set(SmsTask::getSendTime, null)
                        .eq(SmsTask::getId, task.getId()));
            }
            smsSendStatusService.remove(Wraps.<SmsSendStatus>lbQ().eq(SmsSendStatus::getTaskId, task.getId()));
            List<SmsSendStatus> list = data.getTelNum().stream().map(telNum -> {
                SmsSendStatus sss = new SmsSendStatus();
                sss.setTaskId(task.getId());
                sss.setSendStatus(SendStatus.WAITING);
                sss.setTelNum(telNum);
                return sss;
            }).collect(Collectors.toList());
            smsSendStatusService.saveBatch(list);
            return true;
        });
        return smsTask;
    }


    /**
     * 具体的短信任务保存操作
     */
    private SmsTask send(SmsTask smsTask, Function<SmsTask, Boolean> function) {
        //1， 初始化默认参数
        smsTask.setStatus(TaskStatus.WAITING);

        //2，保存or修改 短信任务
        if (!function.apply(smsTask)) {
            return smsTask;
        }

        //保存草稿，直接返回
        if (smsTask.getDraft()) {
            return smsTask;
        }

        //3, 判断是否立即发送
        if (smsTask.getSendTime() == null) {
            smsContext.smsSend(smsTask.getId());
        } else {
            JSONObject param = new JSONObject();
            param.set("id", smsTask.getId());
            param.set(ContextConstants.JWT_KEY_TENANT, ContextUtil.getTenant());
            //推送定时任务
            R<String> r = jobApi.addTimingTask(
                    XxlJobInfoVO.create(JobConstant.DEF_EXTEND_JOB_GROUP_NAME,
                            smsTask.getTopic(),
                            smsTask.getSendTime(),
                            JobConstant.SMS_SEND_JOB_HANDLER,
                            param.toString()));
            if (!r.getIsSuccess()) {
                throw BizException.wrap("定时发送失败");
            }
        }
        return smsTask;
    }
}
