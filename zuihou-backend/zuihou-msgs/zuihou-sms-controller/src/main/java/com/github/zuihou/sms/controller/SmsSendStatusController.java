package com.github.zuihou.sms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.sms.entity.SmsSendStatus;
import com.github.zuihou.sms.service.SmsSendStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 短信发送状态
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/smsSendStatus")
@Api(value = "SmsSendStatus", tags = "短信发送状态")
public class SmsSendStatusController extends BaseController {

    @Autowired
    private SmsSendStatusService smsSendStatusService;

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R<IPage<SmsSendStatus>> page(SmsSendStatus data) {
        IPage<SmsSendStatus> page = getPage();
        LbqWrapper<SmsSendStatus> query = Wraps.lbQ(data)
                .orderByDesc(SmsSendStatus::getCreateTime);
        smsSendStatusService.page(page, query);
        return success(page);
    }

    @ApiOperation(value = "单体查询", notes = "单体查询")
    @GetMapping("/{id}")
    public R<SmsSendStatus> get(@PathVariable Long id) {
        return success(smsSendStatusService.getById(id));
    }

}
