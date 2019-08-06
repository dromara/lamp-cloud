package com.github.zuihou.sms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.dozer.DozerUtils;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.sms.dto.SmsSendTaskDTO;
import com.github.zuihou.sms.dto.SmsTaskSaveDTO;
import com.github.zuihou.sms.dto.SmsTaskUpdateDTO;
import com.github.zuihou.sms.entity.SmsTask;
import com.github.zuihou.sms.enumeration.SourceType;
import com.github.zuihou.sms.manager.SmsManager;
import com.github.zuihou.sms.service.SmsTaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@Api(value = "SmsTask", tags = "发送任务")
public class SmsTaskController extends BaseController {

    @Autowired
    private SmsManager smsManager;
    @Autowired
    private SmsTaskService smsTaskService;

    @Autowired
    private DozerUtils dozer;

    @ApiOperation(value = "发送短信", notes = "短信发送，需要先在短信系统，或者短信数据库中进行配置供应商和模板")
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public R<SmsTask> save(@RequestBody SmsSendTaskDTO smsTaskDTO) {
        SmsTask smsTask = dozer.map2(smsTaskDTO, SmsTask.class);
        smsTask.setSourceType(SourceType.SERVICE);
        smsTask.setTemplateParams(smsTaskDTO.getTemplateParam().toString());
        smsManager.saveTask(smsTask, smsTaskDTO.getCustomCode());
        return success(smsTask);
    }

    /**
     * 新增发送任务
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增发送任务", notes = "新增发送任务不为空的字段")
    @PostMapping
    @SysLog("新增发送任务")
    public R<SmsTask> save(@RequestBody @Validated SmsTaskSaveDTO data) {
        SmsTask smsTask = dozer.map(data, SmsTask.class);
        smsTask.setSourceType(SourceType.APP);
        smsManager.saveTask(smsTask, null);
        return success(smsTask);
    }

    /**
     * 分页查询发送任务
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询发送任务", notes = "分页查询发送任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "分页条数", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询发送任务")
    public R<IPage<SmsTask>> page(SmsTask data) {
        IPage<SmsTask> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<SmsTask> query = Wraps.lbQ(data).orderByDesc(SmsTask::getCreateTime);
        smsTaskService.page(page, query);
        return success(page);
    }

    /**
     * 查询发送任务
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询发送任务", notes = "查询发送任务")
    @GetMapping("/{id}")
    @SysLog("查询发送任务")
    public R<SmsTask> get(@PathVariable Long id) {
        return success(smsTaskService.getById(id));
    }


    /**
     * 修改发送任务
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改发送任务", notes = "修改发送任务不为空的字段")
    @PutMapping
    @SysLog("修改发送任务")
    public R<SmsTask> update(@RequestBody @Validated(SuperEntity.Update.class) SmsTaskUpdateDTO data) {
        SmsTask smsTask = dozer.map(data, SmsTask.class);
        smsTaskService.updateById(smsTask);
        return success(smsTask);
    }

    /**
     * 删除发送任务
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除发送任务", notes = "根据id物理删除发送任务")
    @DeleteMapping(value = "/{id}")
    @SysLog("删除发送任务")
    public R<Boolean> delete(@PathVariable Long id) {
        smsTaskService.removeById(id);
        return success(true);
    }


}
