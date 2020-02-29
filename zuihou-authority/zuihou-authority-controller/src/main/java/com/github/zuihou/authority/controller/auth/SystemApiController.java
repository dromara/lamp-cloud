package com.github.zuihou.authority.controller.auth;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.SystemApiSaveDTO;
import com.github.zuihou.authority.dto.auth.SystemApiScanSaveDTO;
import com.github.zuihou.authority.dto.auth.SystemApiUpdateDTO;
import com.github.zuihou.authority.entity.auth.SystemApi;
import com.github.zuihou.authority.service.auth.SystemApiService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.utils.BeanPlusUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * API接口
 * </p>
 *
 * @author zuihou
 * @date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/systemApi")
@Api(value = "SystemApi", tags = "API接口")
public class SystemApiController extends BaseController {

    @Autowired
    private SystemApiService systemApiService;

    /**
     * 分页查询API接口
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询API接口", notes = "分页查询API接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询API接口")
    public R<IPage<SystemApi>> page(SystemApi data) {
        IPage<SystemApi> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<SystemApi> query = Wraps.lbQ(data);
        query.orderByDesc(SystemApi::getId);
        systemApiService.page(page, query);
        return success(page);
    }

    /**
     * 查询API接口
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询API接口", notes = "查询API接口")
    @GetMapping("/{id}")
    @SysLog("查询API接口")
    public R<SystemApi> get(@PathVariable Long id) {
        return success(systemApiService.getById(id));
    }

    /**
     * 新增API接口
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "批量新增API接口", notes = "批量新增API接口不为空的字段")
    @PostMapping("/batch")
    @SysLog("批量新增API接口")
    public R<Boolean> batchSave(@RequestBody @Validated SystemApiScanSaveDTO data) {
        return success(systemApiService.batchSave(data));
    }


    /**
     * 新增API接口
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增API接口", notes = "新增API接口不为空的字段")
    @PostMapping
    @SysLog("新增API接口")
    public R<SystemApi> save(@RequestBody @Validated SystemApiSaveDTO data) {
        SystemApi systemApi = BeanPlusUtil.toBean(data, SystemApi.class);
        systemApi.setIsPersist(false);
        if (StrUtil.isEmpty(systemApi.getCode())) {
            systemApi.setCode(DigestUtils.md5Hex(systemApi.getServiceId() + systemApi.getPath()));
        }

        systemApiService.save(systemApi);
        return success(systemApi);
    }

    /**
     * 修改API接口
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改API接口", notes = "修改API接口不为空的字段")
    @PutMapping
    @SysLog("修改API接口")
    public R<SystemApi> update(@RequestBody @Validated(SuperEntity.Update.class) SystemApiUpdateDTO data) {
        SystemApi systemApi = BeanPlusUtil.toBean(data, SystemApi.class);
        systemApiService.updateById(systemApi);
        return success(systemApi);
    }

    /**
     * 删除API接口
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除API接口", notes = "根据id物理删除API接口")
    @DeleteMapping
    @SysLog("删除API接口")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        systemApiService.removeByIds(ids);
        return success(true);
    }

}
