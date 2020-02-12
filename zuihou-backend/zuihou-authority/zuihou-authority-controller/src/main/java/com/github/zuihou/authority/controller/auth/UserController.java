package com.github.zuihou.authority.controller.auth;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.*;
import com.github.zuihou.authority.entity.auth.Role;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.entity.core.Station;
import com.github.zuihou.authority.service.auth.ResourceService;
import com.github.zuihou.authority.service.auth.RoleService;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.authority.service.core.StationService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.model.RemoteData;
import com.github.zuihou.msgs.api.SmsApi;
import com.github.zuihou.sms.dto.VerificationCodeDTO;
import com.github.zuihou.sms.enumeration.VerificationCodeType;
import com.github.zuihou.user.feign.UserQuery;
import com.github.zuihou.user.model.SysOrg;
import com.github.zuihou.user.model.SysRole;
import com.github.zuihou.user.model.SysStation;
import com.github.zuihou.user.model.SysUser;
import com.github.zuihou.utils.BeanPlusUtil;
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
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    @Resource
    private SmsApi smsApi;

    @Autowired
    private ResourceService resourceService;

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
    @SysLog("'分页查询用户:' + #userPage.name")
    public R<IPage<User>> page(UserPageDTO userPage) {
        IPage<User> page = getPage();

        LbqWrapper<User> wrapper = Wraps.lbQ();
        if (userPage.getOrgId() != null && userPage.getOrgId() >= 0) {
            List<Org> children = orgService.findChildren(Arrays.asList(userPage.getOrgId()));
            wrapper.in(User::getOrg, children.stream().map((org) -> new RemoteData(org.getId())).collect(Collectors.toList()));
        }
        wrapper.geHeader(User::getCreateTime, userPage.getStartCreateTime())
                .leFooter(User::getCreateTime, userPage.getEndCreateTime())
                .like(User::getName, userPage.getName())
                .like(User::getAccount, userPage.getAccount())
                .like(User::getEmail, userPage.getEmail())
                .like(User::getMobile, userPage.getMobile())
                .eq(User::getStation, userPage.getStationId())
                .eq(User::getPositionStatus, userPage.getPositionStatus())
                .eq(User::getEducation, userPage.getEducation())
                .eq(User::getNation, userPage.getNation())
                .eq(User::getSex, userPage.getSex())
                .eq(User::getStatus, userPage.getStatus())
                .orderByDesc(User::getId);
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
    @SysLog("'查询用户:' + #id")
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
    @SysLog("'新增用户:' + #data.name")
    public R<User> save(@RequestBody @Validated UserSaveDTO data) {
        User user = BeanUtil.toBean(data, User.class);
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
        User user = BeanUtil.toBean(data, User.class);
        userService.updateUser(user);
        return success(user);
    }

    @ApiOperation(value = "修改头像", notes = "修改头像")
    @PutMapping("/avatar")
    @SysLog("修改头像")
    public R<User> avatar(@RequestBody @Validated(SuperEntity.Update.class) UserUpdateAvatarDTO data) {
        User user = BeanUtil.toBean(data, User.class);
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
        SysUser sysUser = BeanUtil.toBean(user, SysUser.class);

        sysUser.setOrgId(RemoteData.getKey(user.getOrg()));
        sysUser.setStationId(RemoteData.getKey(user.getOrg()));

        if (query.getFull() || query.getOrg()) {
            sysUser.setOrg(BeanUtil.toBean(orgService.getById(user.getOrg()), SysOrg.class));
        }
        if (query.getFull() || query.getStation()) {
            Station station = stationService.getById(user.getStation());
            SysStation sysStation = BeanUtil.toBean(station, SysStation.class);
            sysStation.setOrgId(RemoteData.getKey(station.getOrg()));
            sysUser.setStation(sysStation);
        }

        if (query.getFull() || query.getRoles()) {
            List<Role> list = roleService.findRoleByUserId(id);
            sysUser.setRoles(BeanPlusUtil.toBeanList(list, SysRole.class));
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
                .name(data.getMobile()).orgId(new RemoteData<>(DEMO_ORG_ID)).stationId(new RemoteData<>(DEMO_STATION_ID))
                .mobile(data.getMobile())
                .password(DigestUtils.md5Hex(data.getPassword()))
                .build();
        return success(userService.save(user));
    }


    @SysLog("清除缓存并重新加载数据")
    @ApiOperation(value = "清除缓存并重新加载数据", notes = "清除缓存并重新加载数据")
    @PostMapping(value = "/reload")
    public R<LoginDTO> reload(@RequestParam Long userId) throws BizException {
        User user = userService.getById(userId);
        if (user == null) {
            return R.fail("用户不存在");
        }

        List<com.github.zuihou.authority.entity.auth.Resource> resourceList = this.resourceService.findVisibleResource(ResourceQueryDTO.builder().userId(userId).build());
        List<String> permissionsList = resourceList.stream().map(com.github.zuihou.authority.entity.auth.Resource::getCode).collect(Collectors.toList());

        return this.success(LoginDTO.builder().user(BeanUtil.toBean(user, UserDTO.class)).permissionsList(permissionsList).token(null).build());
    }


    @ApiOperation(value = "根据id查询用户", notes = "根据id查询用户")
    @GetMapping("/findUserByIds")
    public Map<Serializable, Object> findUserByIds(@RequestParam Set<Serializable> codes) {
        return this.userService.findUserByIds(codes);
    }

    @ApiOperation(value = "根据id查询用户名称", notes = "根据id查询用户名称")
    @GetMapping("/findUserNameByIds")
    public Map<Serializable, Object> findUserNameByIds(@RequestParam Set<Serializable> codes) {
        return this.userService.findUserNameByIds(codes);
    }

}
