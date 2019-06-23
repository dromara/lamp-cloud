package com.github.zuihou.authority.controller.auth;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.ResourceDTO;
import com.github.zuihou.authority.entity.auth.Resource;
import com.github.zuihou.authority.service.auth.ResourceService;
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
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/resource")
@Api(value = "Resource", description = "资源")
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 分页查询资源
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询资源", notes = "分页查询资源")
    @PostMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<Resource>> page(@Valid ResourceDTO data) {
        IPage<Resource> page = getPage();
        // 构建查询条件
        LbqWrapper<Resource> query = Wraps.lbQ();
        resourceService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询资源
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询资源", notes = "查询资源")
    @GetMapping("/{id}")
    public Result<Resource> get(@PathVariable Long id) {
        return success(resourceService.getById(id));
    }

    /**
     * 保存资源
     *
     * @param resource 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存资源", notes = "保存资源不为空的字段")
    @PostMapping
    public Result<Resource> save(@RequestBody @Valid Resource resource) {
        resourceService.save(resource);
        return success(resource);
    }

    /**
     * 修改资源
     *
     * @param resource 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改资源", notes = "修改资源不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public Result<Resource> update(@RequestBody @Valid Resource resource) {
        resourceService.updateById(resource);
        return success(resource);
    }

    /**
     * 删除资源
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除资源", notes = "根据id物理删除资源")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        resourceService.removeById(id);
        return success(true);
    }

}
