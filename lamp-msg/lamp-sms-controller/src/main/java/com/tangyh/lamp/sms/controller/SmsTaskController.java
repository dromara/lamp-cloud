package com.tangyh.lamp.sms.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tangyh.basic.annotation.log.SysLog;
import com.tangyh.basic.annotation.security.PreAuth;
import com.tangyh.basic.base.R;
import com.tangyh.basic.base.controller.SuperController;
import com.tangyh.basic.base.request.PageParams;
import com.tangyh.basic.database.mybatis.conditions.Wraps;
import com.tangyh.basic.echo.core.EchoService;
import com.tangyh.lamp.sms.dto.SmsTaskPageQuery;
import com.tangyh.lamp.sms.dto.SmsTaskResultVO;
import com.tangyh.lamp.sms.dto.SmsTaskSaveDTO;
import com.tangyh.lamp.sms.dto.SmsTaskUpdateDTO;
import com.tangyh.lamp.sms.entity.SmsSendStatus;
import com.tangyh.lamp.sms.entity.SmsTask;
import com.tangyh.lamp.sms.service.SmsSendStatusService;
import com.tangyh.lamp.sms.service.SmsTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * 发送任务
 * 所有的短息发送调用，都视为是一次短信任务，任务表只保存数据和执行状态等信息，
 * 具体的发送状态查看发送状态（#sms_send_status）表
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/smsTask")
@Api(value = "SmsTask", tags = "短信发送")
@PreAuth(replace = "msg:sms:")
@RequiredArgsConstructor
public class SmsTaskController extends SuperController<SmsTaskService, Long, SmsTask, SmsTaskPageQuery, SmsTaskSaveDTO, SmsTaskUpdateDTO> {

    private final SmsSendStatusService smsSendStatusService;
    private final EchoService echoService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = "long", paramType = "query"),
    })
    @ApiOperation(value = "详情", notes = "单体查询")
    @GetMapping("/detail/{id}")
    @SysLog("'查询:' + #id")
    @PreAuth("hasAnyPermission('{}view')")
    public R<SmsTaskResultVO> detail(@PathVariable Long id) {
        SmsTaskResultVO resultVO = BeanUtil.toBean(getBaseService().getById(id), SmsTaskResultVO.class);
        List<SmsSendStatus> list = smsSendStatusService.list(Wraps.<SmsSendStatus>lbQ()
                .eq(SmsSendStatus::getTaskId, id));
        resultVO.setTelNumList(list.stream().map(SmsSendStatus::getTelNum).collect(Collectors.toList()));
        return success(resultVO);
    }

    @Override
    public IPage<SmsTask> query(PageParams<SmsTaskPageQuery> params) {
        IPage<SmsTask> page = params.buildPage();
        baseService.pageSmsTask(page, params);
        echoService.action(page);
        return page;
    }


    @Override
    public R<SmsTask> handlerSave(SmsTaskSaveDTO data) {
        return success(baseService.saveTask(data));
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        boolean bool = baseService.removeByIds(ids);
        smsSendStatusService.remove(Wraps.<SmsSendStatus>lbQ().in(SmsSendStatus::getTaskId, ids));
        return success(bool);
    }

    @Override
    public R<SmsTask> handlerUpdate(SmsTaskUpdateDTO data) {
        return success(baseService.update(data));
    }

}
