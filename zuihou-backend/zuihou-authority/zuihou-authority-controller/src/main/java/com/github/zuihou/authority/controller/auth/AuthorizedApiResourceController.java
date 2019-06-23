package com.github.zuihou.authority.controller.auth;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.AuthorizedApiResourceDTO;
import com.github.zuihou.authority.entity.auth.AuthorizedApiResource;
import com.github.zuihou.authority.service.auth.AuthorizedApiResourceService;
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
 * 资源API分配
 * 资源中需要请求的api  并且此api会被鉴权，若不需要鉴权的api就不要加入到
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/authorizedApiResource")
@Api(value = "AuthorizedApiResource", description = "资源API分配资源中需要请求的api  并且此api会被鉴权，若不需要鉴权的api就不要加入到")
public class AuthorizedApiResourceController extends BaseController {

    @Autowired
    private AuthorizedApiResourceService authorizedApiResourceService;

    /**
     * 分页查询资源API分配
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询资源API分配", notes = "分页查询资源API分配")
    @PostMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<AuthorizedApiResource>> page(@Valid AuthorizedApiResourceDTO data) {
        IPage<AuthorizedApiResource> page = getPage();
        // 构建查询条件
        LbqWrapper<AuthorizedApiResource> query = Wraps.lbQ();
        authorizedApiResourceService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询资源API分配
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询资源API分配", notes = "查询资源API分配")
    @GetMapping("/{id}")
    public Result<AuthorizedApiResource> get(@PathVariable Long id) {
        return success(authorizedApiResourceService.getById(id));
    }

    /**
     * 保存资源API分配
     *
     * @param authorizedApiResource 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存资源API分配", notes = "保存资源API分配不为空的字段")
    @PostMapping
    public Result<AuthorizedApiResource> save(@RequestBody @Valid AuthorizedApiResource authorizedApiResource) {
        authorizedApiResourceService.save(authorizedApiResource);
        return success(authorizedApiResource);
    }

    /**
     * 修改资源API分配
     *
     * @param authorizedApiResource 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改资源API分配", notes = "修改资源API分配不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public Result<AuthorizedApiResource> update(@RequestBody @Valid AuthorizedApiResource authorizedApiResource) {
        authorizedApiResourceService.updateById(authorizedApiResource);
        return success(authorizedApiResource);
    }

    /**
     * 删除资源API分配
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除资源API分配", notes = "根据id物理删除资源API分配")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        authorizedApiResourceService.removeById(id);
        return success(true);
    }

}
