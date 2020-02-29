package com.github.zuihou.authority.controller.auth;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.ApplicationSystemApiSaveDTO;
import com.github.zuihou.authority.dto.auth.ApplicationSystemApiUpdateDTO;
import com.github.zuihou.authority.entity.auth.ApplicationSystemApi;
import com.github.zuihou.authority.service.auth.ApplicationSystemApiService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 应用接口
 * </p>
 *
 * @author zuihou
 * @date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/applicationSystemApi")
@Api(value = "ApplicationSystemApi", tags = "应用接口")
public class ApplicationSystemApiController extends BaseController {

    @Autowired
    private ApplicationSystemApiService applicationSystemApiService;

    /**
     * 分页查询应用接口
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询应用接口", notes = "分页查询应用接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询应用接口")
    public R<IPage<ApplicationSystemApi>> page(ApplicationSystemApi data) {
        IPage<ApplicationSystemApi> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<ApplicationSystemApi> query = Wraps.lbQ(data);
        applicationSystemApiService.page(page, query);
        return success(page);
    }

    /**
     * 查询应用接口
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询应用接口", notes = "查询应用接口")
    @GetMapping("/{id}")
    @SysLog("查询应用接口")
    public R<ApplicationSystemApi> get(@PathVariable Long id) {
        return success(applicationSystemApiService.getById(id));
    }

    /**
     * 新增应用接口
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增应用接口", notes = "新增应用接口不为空的字段")
    @PostMapping
    @SysLog("新增应用接口")
    public R<ApplicationSystemApi> save(@RequestBody @Validated ApplicationSystemApiSaveDTO data) {
        ApplicationSystemApi applicationSystemApi = BeanPlusUtil.toBean(data, ApplicationSystemApi.class);
        applicationSystemApiService.save(applicationSystemApi);
        return success(applicationSystemApi);
    }

    /**
     * 修改应用接口
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改应用接口", notes = "修改应用接口不为空的字段")
    @PutMapping
    @SysLog("修改应用接口")
    public R<ApplicationSystemApi> update(@RequestBody @Validated(SuperEntity.Update.class) ApplicationSystemApiUpdateDTO data) {
        ApplicationSystemApi applicationSystemApi = BeanPlusUtil.toBean(data, ApplicationSystemApi.class);
        applicationSystemApiService.updateById(applicationSystemApi);
        return success(applicationSystemApi);
    }

    /**
     * 删除应用接口
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除应用接口", notes = "根据id物理删除应用接口")
    @DeleteMapping
    @SysLog("删除应用接口")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        applicationSystemApiService.removeByIds(ids);
        return success(true);
    }

}
