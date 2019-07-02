package com.github.zuihou.authority.controller.auth;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.UserSaveDTO;
import com.github.zuihou.authority.dto.auth.UserUpdateDTO;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.common.utils.context.DozerUtils;
import com.github.zuihou.log.annotation.SysLog;
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
 * @date 2019-07-03
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/user")
@Api(value = "User", description = "账号")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询账号
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询账号", notes = "分页查询账号")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    @SysLog("分页查询账号")
    public R<IPage<User>> page(@Valid User data) {
        IPage<User> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<User> query = Wraps.lbQ(data);
        userService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询账号
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "单体查询账号", notes = "单体查询账号")
    @GetMapping("/{id}")
    @SysLog("单体查询账号")
    public R<User> get(@PathVariable Long id) {
        return success(userService.getById(id));
    }

    /**
     * 保存账号
     *
     * @param data 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存账号", notes = "保存账号不为空的字段")
    @PostMapping
    @SysLog("保存账号")
    public R<User> save(@RequestBody @Valid UserSaveDTO data) {
        User user = dozer.map(data, User.class);
        userService.save(user);
        return success(user);
    }

    /**
     * 修改账号
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改账号", notes = "修改账号不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    @SysLog("修改账号")
    public R<User> update(@RequestBody @Valid UserUpdateDTO data) {
        User user = dozer.map(data, User.class);
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
    @SysLog("删除账号")
    public R<Boolean> delete(@PathVariable Long id) {
        userService.removeById(id);
        return success(true);
    }

}
