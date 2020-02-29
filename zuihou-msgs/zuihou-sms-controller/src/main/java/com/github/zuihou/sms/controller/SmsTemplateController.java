package com.github.zuihou.sms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.sms.dto.SmsTemplateSaveDTO;
import com.github.zuihou.sms.dto.SmsTemplateUpdateDTO;
import com.github.zuihou.sms.entity.SmsTemplate;
import com.github.zuihou.sms.service.SmsTemplateService;
import com.github.zuihou.utils.BeanPlusUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 短信模板
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/smsTemplate")
@Api(value = "SmsTemplate", tags = "短信模板")
public class SmsTemplateController extends BaseController {

    @Autowired
    private SmsTemplateService smsTemplateService;

    /**
     * 分页查询短信模板
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询短信模板", notes = "分页查询短信模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询短信模板")
    public R<IPage<SmsTemplate>> page(SmsTemplate data) {
        IPage<SmsTemplate> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<SmsTemplate> query = Wraps.lbQ(data)
                .orderByDesc(SmsTemplate::getUpdateTime);
        smsTemplateService.page(page, query);
        return success(page);
    }

    /**
     * 查询短信模板
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询短信模板", notes = "查询短信模板")
    @GetMapping("/{id}")
    @SysLog("查询短信模板")
    public R<SmsTemplate> get(@PathVariable Long id) {
        return success(smsTemplateService.getById(id));
    }

    /**
     * 新增短信模板
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增短信模板", notes = "新增短信模板不为空的字段")
    @PostMapping
    @SysLog("新增短信模板")
    public R<SmsTemplate> save(@RequestBody @Validated SmsTemplateSaveDTO data) {
        SmsTemplate smsTemplate = BeanPlusUtil.toBean(data, SmsTemplate.class);
        smsTemplateService.saveTemplate(smsTemplate);
        return success(smsTemplate);
    }

    /**
     * 修改短信模板
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改短信模板", notes = "修改短信模板不为空的字段")
    @PutMapping
    @SysLog("修改短信模板")
    public R<SmsTemplate> update(@RequestBody @Validated(SuperEntity.Update.class) SmsTemplateUpdateDTO data) {
        SmsTemplate smsTemplate = BeanPlusUtil.toBean(data, SmsTemplate.class);
        smsTemplateService.updateTemplate(smsTemplate);
        return success(smsTemplate);
    }

    @ApiOperation(value = "检测自定义编码是否存在", notes = "检测自定义编码是否存在")
    @GetMapping("/check")
    @SysLog("检测自定义编码是否存在")
    public R<Boolean> check(@RequestParam(value = "customCode") String customCode) {
        int count = smsTemplateService.count(Wraps.<SmsTemplate>lbQ().eq(SmsTemplate::getCustomCode, customCode));
        return success(count > 0);
    }


    /**
     * 删除短信模板
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除短信模板", notes = "根据id物理删除短信模板")
    @DeleteMapping
    @SysLog("删除短信模板")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        smsTemplateService.removeByIds(ids);
        return success(true);
    }

}
