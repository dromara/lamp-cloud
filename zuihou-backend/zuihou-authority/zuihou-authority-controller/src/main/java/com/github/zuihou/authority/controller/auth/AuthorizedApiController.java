package com.github.zuihou.authority.controller.auth;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.AuthorizedApiDTO;
import com.github.zuihou.authority.entity.auth.AuthorizedApi;
import com.github.zuihou.authority.service.auth.AuthorizedApiService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.Result;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.mybatis.conditions.Wraps;
import com.github.zuihou.mybatis.conditions.query.LbqWrapper;

import io.swagger.annotations.Api;
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
 * API资源
 * 后端需要授权方可访问的api集合
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/authorizedApi")
@Api(value = "AuthorizedApi", description = "API资源后端需要授权方可访问的api集合")
public class AuthorizedApiController extends BaseController {

    @Autowired
    private AuthorizedApiService authorizedApiService;

    /**
     * 分页查询API资源
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询API资源", notes = "分页查询API资源")
    @PostMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<AuthorizedApi>> page(@Valid AuthorizedApiDTO data) {
        IPage<AuthorizedApi> page = getPage();
        // 构建查询条件
        LbqWrapper<AuthorizedApi> query = Wraps.lbQ();
        authorizedApiService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询API资源
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询API资源", notes = "查询API资源")
    @GetMapping("/{id}")
    public Result<AuthorizedApi> get(@PathVariable Long id) {
        return success(authorizedApiService.getById(id));
    }

    /**
     * 保存API资源
     *
     * @param authorizedApi 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存API资源", notes = "保存API资源不为空的字段")
    @PostMapping
    public Result<AuthorizedApi> save(@RequestBody @Valid AuthorizedApi authorizedApi) {
        authorizedApiService.save(authorizedApi);
        return success(authorizedApi);
    }

    /**
     * 修改API资源
     *
     * @param authorizedApi 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改API资源", notes = "修改API资源不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public Result<AuthorizedApi> update(@RequestBody @Valid AuthorizedApi authorizedApi) {
        authorizedApiService.updateById(authorizedApi);
        return success(authorizedApi);
    }

    /**
     * 删除API资源
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除API资源", notes = "根据id物理删除API资源")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        authorizedApiService.removeById(id);
        return success(true);
    }

}
