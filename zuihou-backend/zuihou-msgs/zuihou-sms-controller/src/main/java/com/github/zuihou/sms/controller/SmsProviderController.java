package com.github.zuihou.sms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.dozer.DozerUtils;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.sms.dto.SmsProviderSaveDTO;
import com.github.zuihou.sms.dto.SmsProviderUpdateDTO;
import com.github.zuihou.sms.entity.SmsProvider;
import com.github.zuihou.sms.service.SmsProviderService;

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
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 短信供应商
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/smsProvider")
@Api(value = "SmsProvider", tags = "短信供应商")
public class SmsProviderController extends BaseController {

    @Autowired
    private SmsProviderService smsProviderService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询短信供应商
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询短信供应商", notes = "分页查询短信供应商")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询短信供应商")
    public R<IPage<SmsProvider>> page(SmsProvider data) {
        IPage<SmsProvider> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<SmsProvider> query = Wraps.lbQ(data).orderByDesc(SmsProvider::getCreateTime);
        smsProviderService.page(page, query);
        return success(page);
    }

    /**
     * 查询短信供应商
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询短信供应商", notes = "查询短信供应商")
    @GetMapping("/{id}")
    @SysLog("查询短信供应商")
    public R<SmsProvider> get(@PathVariable Long id) {
        return success(smsProviderService.getById(id));
    }

    /**
     * 新增短信供应商
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增短信供应商", notes = "新增短信供应商不为空的字段")
    @PostMapping
    @SysLog("新增短信供应商")
    public R<SmsProvider> save(@RequestBody @Validated SmsProviderSaveDTO data) {
        SmsProvider smsProvider = dozer.map(data, SmsProvider.class);
        smsProviderService.save(smsProvider);
        return success(smsProvider);
    }

    /**
     * 修改短信供应商
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改短信供应商", notes = "修改短信供应商不为空的字段")
    @PutMapping
    @SysLog("修改短信供应商")
    public R<SmsProvider> update(@RequestBody @Validated(SuperEntity.Update.class) SmsProviderUpdateDTO data) {
        SmsProvider smsProvider = dozer.map(data, SmsProvider.class);
        smsProviderService.updateById(smsProvider);
        return success(smsProvider);
    }

    /**
     * 删除短信供应商
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除短信供应商", notes = "根据id物理删除短信供应商")
    @DeleteMapping(value = "/{id}")
    @SysLog("删除短信供应商")
    public R<Boolean> delete(@PathVariable Long id) {
        smsProviderService.removeById(id);
        return success(true);
    }

}
