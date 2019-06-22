package com.github.zuihou.authority.controller.auth;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.UserDTO;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.service.auth.UserService;
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
 * 账号
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/user")
@Api(value = "User", description = "账号")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 分页查询账号
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询账号", notes = "分页查询账号")
    @PostMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<User>> page(@Valid UserDTO data) {
        IPage<User> page = getPage();
        // 构建查询条件
        LbqWrapper<User> query = Wraps.lbQ();
        userService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询账号
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询账号", notes = "查询账号")
    @GetMapping("/{id}")
    public Result<User> get(@PathVariable Long id) {
        return success(userService.getById(id));
    }

    /**
     * 保存账号
     *
     * @param user 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存账号", notes = "保存账号不为空的字段")
    @PostMapping
    public Result<User> save(@RequestBody @Valid User user) {
        userService.save(user);
        return success(user);
    }

    /**
     * 修改账号
     *
     * @param user 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改账号", notes = "修改账号不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public Result<User> update(@RequestBody @Valid User user) {
        userService.updateById(user);
        return success(user);
    }

    /**
     * 删除账号
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除账号", notes = "根据id物理删除账号")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        userService.removeById(id);
        return success(true);
    }

}
