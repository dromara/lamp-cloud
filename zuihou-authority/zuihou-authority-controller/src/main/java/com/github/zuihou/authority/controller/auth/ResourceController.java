package com.github.zuihou.authority.controller.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.ResourceQueryDTO;
import com.github.zuihou.authority.dto.auth.ResourceSaveDTO;
import com.github.zuihou.authority.dto.auth.ResourceUpdateDTO;
import com.github.zuihou.authority.entity.auth.Resource;
import com.github.zuihou.authority.service.auth.ResourceService;
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
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/resource")
@Api(value = "Resource", tags = "资源")
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询资源")
    public R<IPage<Resource>> page(Resource data) {
        IPage<Resource> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<Resource> query = Wraps.lbQ(data);
        resourceService.page(page, query);
        return success(page);
    }

    /**
     * 查询资源
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询资源", notes = "查询资源")
    @GetMapping("/{id}")
    @SysLog("查询资源")
    public R<Resource> get(@PathVariable Long id) {
        return success(resourceService.getByIdWithCache(id));
    }

    /**
     * 新增资源
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增资源", notes = "新增资源不为空的字段")
    @PostMapping
    @SysLog("新增资源")
    public R<Resource> save(@RequestBody @Validated ResourceSaveDTO data) {
        Resource resource = BeanPlusUtil.toBean(data, Resource.class);
        resourceService.saveWithCache(resource);
        return success(resource);
    }

    /**
     * 修改资源
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改资源", notes = "修改资源不为空的字段")
    @PutMapping
    @SysLog("修改资源")
    public R<Resource> update(@RequestBody @Validated(SuperEntity.Update.class) ResourceUpdateDTO data) {
        Resource resource = BeanPlusUtil.toBean(data, Resource.class);
        resourceService.updateWithCache(resource);
        return success(resource);
    }

    /**
     * 删除资源
     * <p>
     * 链接类型的资源 只清空 menu_id
     * 按钮和数据列 则物理删除
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除资源", notes = "根据id物理删除资源")
    @DeleteMapping
    @SysLog("删除资源")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        return success(resourceService.removeByIdWithCache(ids));
    }

    /**
     * 查询用户可用的所有资源
     *
     * @param resource <br>
     *                 appCode 应用code * <br>
     *                 type 类型 <br>
     *                 group 菜单分组 <br>
     *                 resourceId 上级资源id <br>
     *                 accountId 当前登录人id
     * @return
     */
    @ApiOperation(value = "查询用户可用的所有资源", notes = "查询用户可用的所有资源")
    @GetMapping
    @SysLog("查询用户可用的所有资源")
    public R<List<Resource>> visible(ResourceQueryDTO resource) {
        if (resource == null) {
            resource = new ResourceQueryDTO();
        }

        if (resource.getUserId() == null) {
            resource.setUserId(getUserId());
        }
        return success(resourceService.findVisibleResource(resource));
    }


}
