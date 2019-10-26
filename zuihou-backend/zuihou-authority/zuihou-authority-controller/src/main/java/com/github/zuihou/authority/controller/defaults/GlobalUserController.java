package com.github.zuihou.authority.controller.defaults;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.defaults.GlobalUserSaveDTO;
import com.github.zuihou.authority.dto.defaults.GlobalUserUpdateDTO;
import com.github.zuihou.authority.entity.defaults.GlobalUser;
import com.github.zuihou.authority.service.defaults.GlobalUserService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.dozer.DozerUtils;
import com.github.zuihou.log.annotation.SysLog;

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
 * 全局账号
 * </p>
 *
 * @author zuihou
 * @date 2019-10-25
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/globalUser")
@Api(value = "GlobalUser", tags = "全局账号")
public class GlobalUserController extends BaseController {

    @Autowired
    private GlobalUserService globalUserService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询全局账号
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询全局账号", notes = "分页查询全局账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "分页条数", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询全局账号")
    public R<IPage<GlobalUser>> page(GlobalUser data) {
        IPage<GlobalUser> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<GlobalUser> query = Wraps.lbQ(data);
        globalUserService.page(page, query);
        return success(page);
    }

    /**
     * 查询全局账号
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询全局账号", notes = "查询全局账号")
    @GetMapping("/{id}")
    @SysLog("查询全局账号")
    public R<GlobalUser> get(@PathVariable Long id) {
        return success(globalUserService.getById(id));
    }

    /**
     * 新增全局账号
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增全局账号", notes = "新增全局账号不为空的字段")
    @PostMapping
    @SysLog("新增全局账号")
    public R<GlobalUser> save(@RequestBody @Validated GlobalUserSaveDTO data) {
        GlobalUser globalUser = dozer.map(data, GlobalUser.class);
        globalUserService.save(globalUser);
        return success(globalUser);
    }

    /**
     * 修改全局账号
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改全局账号", notes = "修改全局账号不为空的字段")
    @PutMapping
    @SysLog("修改全局账号")
    public R<GlobalUser> update(@RequestBody @Validated(SuperEntity.Update.class) GlobalUserUpdateDTO data) {
        GlobalUser globalUser = dozer.map(data, GlobalUser.class);
        globalUserService.updateById(globalUser);
        return success(globalUser);
    }

    /**
     * 删除全局账号
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除全局账号", notes = "根据id物理删除全局账号")
    @DeleteMapping(value = "/{id}")
    @SysLog("删除全局账号")
    public R<Boolean> delete(@PathVariable Long id) {
        globalUserService.removeById(id);
        return success(true);
    }

}
