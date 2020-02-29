package com.github.zuihou.authority.controller.defaults;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.defaults.GlobalUserPageDTO;
import com.github.zuihou.authority.dto.defaults.GlobalUserSaveDTO;
import com.github.zuihou.authority.dto.defaults.GlobalUserUpdateDTO;
import com.github.zuihou.authority.entity.defaults.GlobalUser;
import com.github.zuihou.authority.service.defaults.GlobalUserService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.utils.BeanPlusUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 分页查询全局账号
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询全局账号", notes = "分页查询全局账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "分页条数", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    public R<IPage<GlobalUser>> page(GlobalUserPageDTO data) {
        GlobalUser user = BeanPlusUtil.toBean(data, GlobalUser.class);
        IPage<GlobalUser> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<GlobalUser> query = Wraps.<GlobalUser>lbQ(user)
                .geHeader(GlobalUser::getCreateTime, data.getStartCreateTime())
                .leFooter(GlobalUser::getCreateTime, data.getEndCreateTime())
                .orderByDesc(GlobalUser::getCreateTime);
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
    public R<GlobalUser> get(@PathVariable Long id) {
        return success(globalUserService.getById(id));
    }

    /**
     * 新增全局账号
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增企业管理员", notes = "新增企业管理员")
    @PostMapping
    public R<GlobalUser> save(@RequestBody @Validated GlobalUserSaveDTO data) {
        return success(globalUserService.save(data));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantCode", value = "企业编码", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "账号", dataType = "string", paramType = "query"),
    })
    @ApiOperation(value = "检测账号是否可用", notes = "检测账号是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam String tenantCode, @RequestParam String account) {
        return success(globalUserService.check(tenantCode, account));
    }

    /**
     * 修改全局账号
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改全局账号", notes = "修改全局账号不为空的字段")
    @PutMapping
    public R<GlobalUser> update(@RequestBody @Validated(SuperEntity.Update.class) GlobalUserUpdateDTO data) {
        return success(globalUserService.update(data));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantCode", value = "企业编码", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "ids[]", value = "id", dataType = "array", paramType = "query"),
    })
    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping(value = "/remove")
    public R<Boolean> remove(@RequestParam String tenantCode, @RequestParam("ids[]") Long[] ids) {
        globalUserService.removeByIds(tenantCode, ids);
        return success(true);
    }
}
