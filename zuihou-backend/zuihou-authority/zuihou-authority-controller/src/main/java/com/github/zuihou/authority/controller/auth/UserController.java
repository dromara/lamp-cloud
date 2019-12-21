package com.github.zuihou.authority.controller.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.*;
import com.github.zuihou.authority.entity.auth.Role;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.service.auth.RoleService;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.authority.service.core.StationService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.dozer.DozerUtils;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.msgs.api.SmsApi;
import com.github.zuihou.sms.dto.VerificationCodeDTO;
import com.github.zuihou.sms.enumeration.VerificationCodeType;
import com.github.zuihou.user.feign.UserQuery;
import com.github.zuihou.user.model.SysOrg;
import com.github.zuihou.user.model.SysRole;
import com.github.zuihou.user.model.SysStation;
import com.github.zuihou.user.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.zuihou.common.constant.BizConstant.DEMO_ORG_ID;
import static com.github.zuihou.common.constant.BizConstant.DEMO_STATION_ID;

/**
 * <p>
 * 前端控制器
 * 用户
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/user")
@Api(value = "User", tags = "用户")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StationService stationService;
    @Autowired
    private DozerUtils dozer;
    @Resource
    private SmsApi smsApi;

    /**
     * 分页查询用户
     *
     * @param userPage 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询用户", notes = "分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "分页条数", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询用户")
    public R<IPage<User>> page(UserPageDTO userPage) {
        IPage<User> page = getPage();

        User user = dozer.map2(userPage, User.class);
        if (userPage.getOrgId() != null && userPage.getOrgId() >= 0) {
            user.setOrgId(null);
        }
        LbqWrapper<User> wrapper = Wraps.lbQ(user);
        if (userPage.getOrgId() != null && userPage.getOrgId() >= 0) {
            List<Org> children = orgService.findChildren(Arrays.asList(userPage.getOrgId()));
            wrapper.in(User::getOrgId, children.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList()));
        }
        wrapper.geHeader(User::getCreateTime, userPage.getStartCreateTime())
                .leFooter(User::getCreateTime, userPage.getEndCreateTime())
                .like(User::getName, userPage.getName())
                .like(User::getAccount, userPage.getAccount())
                .like(User::getEmail, userPage.getEmail())
                .like(User::getMobile, userPage.getMobile())
                .eq(User::getSex, userPage.getSex())
                .eq(User::getStatus, userPage.getStatus())
                .orderByDesc(User::getId);
//        userService.page(page, wrapper);

        userService.findPage(page, wrapper);
        return success(page);
    }

    /**
     * 查询用户
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询用户", notes = "查询用户")
    @GetMapping("/{id}")
    @SysLog("查询用户")
    public R<User> get(@PathVariable Long id) {
        return success(userService.getById(id));
    }


    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @GetMapping("/find")
    @SysLog("查询所有用户")
    public R<List<Long>> findAllUserId() {
        return success(userService.list().stream().mapToLong(User::getId).boxed().collect(Collectors.toList()));
    }


    /**
     * 新增用户
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增用户", notes = "新增用户不为空的字段")
    @PostMapping
    @SysLog("新增用户")
    public R<User> save(@RequestBody @Validated UserSaveDTO data) {
        User user = dozer.map(data, User.class);
        userService.saveUser(user);
        return success(user);
    }

    /**
     * 修改用户
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改用户", notes = "修改用户不为空的字段")
    @PutMapping
    @SysLog("修改用户")
    public R<User> update(@RequestBody @Validated(SuperEntity.Update.class) UserUpdateDTO data) {
        User user = dozer.map(data, User.class);
        userService.updateUser(user);
        return success(user);
    }

    @ApiOperation(value = "修改头像", notes = "修改头像")
    @PutMapping("/avatar")
    @SysLog("修改头像")
    public R<User> avatar(@RequestBody @Validated(SuperEntity.Update.class) UserUpdateAvatarDTO data) {
        User user = dozer.map(data, User.class);
        userService.updateUser(user);
        return success(user);
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PutMapping("/password")
    @SysLog("修改密码")
    public R<Boolean> updatePassword(@RequestBody UserUpdatePasswordDTO data) {
        return success(userService.updatePassword(data));
    }

    @ApiOperation(value = "重置密码", notes = "重置密码")
    @GetMapping("/reset")
    @SysLog("重置密码")
    public R<Boolean> resetTx(@RequestParam("ids[]") List<Long> ids) {
        userService.reset(ids);
        return success();
    }

    /**
     * 删除用户
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除用户", notes = "根据id物理删除用户")
    @DeleteMapping
    @SysLog("删除用户")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        userService.remove(ids);
        return success(true);
    }


    /**
     * 单体查询用户
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询用户详细", notes = "查询用户详细")
    @PostMapping(value = "/anno/id/{id}")
    public R<SysUser> getById(@PathVariable Long id, @RequestBody UserQuery query) {
        User user = userService.getById(id);
        if (user == null) {
            return success(null);
        }
        SysUser sysUser = dozer.map(user, SysUser.class);

        if (query.getFull() || query.getOrg()) {
            sysUser.setOrg(dozer.map(orgService.getById(user.getOrgId()), SysOrg.class));
        }
        if (query.getFull() || query.getStation()) {
            sysUser.setStation(dozer.map(stationService.getById(user.getStationId()), SysStation.class));
        }

        if (query.getFull() || query.getRoles()) {
            List<Role> list = roleService.findRoleByUserId(id);
            sysUser.setRoles(dozer.mapList(list, SysRole.class));
        }

        return success(sysUser);
    }

    /**
     * 根据用户id，查询用户权限范围
     *
     * @param id 用户id
     * @return
     */
    @ApiOperation(value = "查询用户权限范围", notes = "根据用户id，查询用户权限范围")
    @GetMapping(value = "/ds/{id}")
    public Map<String, Object> getDataScopeById(@PathVariable("id") Long id) {
        return userService.getDataScopeById(id);
    }

    /**
     * 查询角色的已关联用户
     *
     * @param roleId  角色id
     * @param keyword 账号或名称
     * @return
     */
    @ApiOperation(value = "查询角色的已关联用户", notes = "查询角色的已关联用户")
    @GetMapping(value = "/role/{roleId}")
    public R<UserRoleDTO> findUserByRoleId(@PathVariable("roleId") Long roleId, @RequestParam(value = "keyword", required = false) String keyword) {
        List<User> list = userService.findUserByRoleId(roleId, keyword);
        List<Long> idList = list.stream().mapToLong(User::getId).boxed().collect(Collectors.toList());
        return success(UserRoleDTO.builder().idList(idList).userList(list).build());
    }


    /**
     * 注册用户
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "注册用户", notes = "注册用户")
    @PostMapping("/anno/register")
    @SysLog("注册用户")
    public R<Boolean> register(@RequestBody @Validated UserRegisterDTO data) {
        R<Boolean> result = smsApi.verification(VerificationCodeDTO.builder()
                .code(data.getVerificationCode())
                .mobile(data.getMobile()).type(VerificationCodeType.REGISTER_USER)
                .build());

        //调用失败或者发送失败
        if (result.getIsError() || !result.getData()) {
            return result;
        }

        User user = User.builder()
                .account(data.getMobile())
                .name(data.getMobile()).orgId(DEMO_ORG_ID).stationId(DEMO_STATION_ID)
                .mobile(data.getMobile())
                .password(DigestUtils.md5Hex(data.getPassword()))
                .build();
        return success(userService.save(user));
    }

}
