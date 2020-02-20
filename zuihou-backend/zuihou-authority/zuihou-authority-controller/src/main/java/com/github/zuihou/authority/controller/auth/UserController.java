package com.github.zuihou.authority.controller.auth;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.*;
import com.github.zuihou.authority.entity.auth.Role;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.entity.core.Station;
import com.github.zuihou.authority.enumeration.auth.Sex;
import com.github.zuihou.authority.service.auth.ResourceService;
import com.github.zuihou.authority.service.auth.RoleService;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.authority.service.core.StationService;
import com.github.zuihou.base.R;
import com.github.zuihou.base.SuperController;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.base.request.PageParams;
import com.github.zuihou.common.constant.BizConstant;
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
import com.github.zuihou.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class UserController extends SuperController<UserService, Long, User, UserPageDTO, UserSaveDTO, UserUpdateDTO> {
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


//    /**
//     * 新增用户
//     *
//     * @param data 新增对象
//     * @return 新增结果
//     */
//    @Override
//    @SysLog("'新增用户:' + #data.name")
//    public R<User> save(@RequestBody @Validated UserSaveDTO data) {
//        User user = BeanUtil.toBean(data, User.class);
//        baseService.saveUser(user);
//        return success(user);
//    }

    @Override
    protected R<User> handlerSave(UserSaveDTO data) {
        User user = BeanUtil.toBean(data, User.class);
        baseService.saveUser(user);
        return success(user);
    }


//    /**
//     * 删除用户
//     *
//     * @param ids 主键id
//     * @return 删除结果
//     */
//    @ApiOperation(value = "删除用户", notes = "根据id物理删除用户")
//    @DeleteMapping
//    @Override
//    @SysLog("删除用户")
//    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
//        baseService.remove(ids);
//        return success(true);
//    }
//    ￿

    @Override
    protected R<Boolean> handlerDelete(List<Long> ids) {
        baseService.remove(ids);
        return success(true);
    }

    @Override
    protected R<User> handlerUpdate(UserUpdateDTO data) {
        User user = BeanUtil.toBean(data, User.class);
        baseService.updateUser(user);
        return success(user);
    }

    /**
     * 修改用户
     *
     * @param data 修改对象
     * @return 修改结果
     */
//    @ApiOperation(value = "修改用户", notes = "修改用户不为空的字段")
//    @PutMapping
//    @Override
//    @SysLog("修改用户")
//    public R<User> update(@RequestBody @Validated(SuperEntity.Update.class) UserUpdateDTO data) {
//        User user = BeanUtil.toBean(data, User.class);
//        baseService.updateUser(user);
//        return success(user);
//    }
    @ApiOperation(value = "修改头像", notes = "修改头像")
    @PutMapping("/avatar")
    @SysLog("'修改头像:' + #p0.id")
    public R<User> avatar(@RequestBody @Validated(SuperEntity.Update.class) UserUpdateAvatarDTO data) {
        User user = BeanUtil.toBean(data, User.class);
        baseService.updateById(user);
        return success(user);
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PutMapping("/password")
    @SysLog("'修改密码:' + #p0.id")
    public R<Boolean> updatePassword(@RequestBody @Validated(SuperEntity.Update.class) UserUpdatePasswordDTO data) {
        return success(baseService.updatePassword(data));
    }

    @ApiOperation(value = "重置密码", notes = "重置密码")
    @GetMapping("/reset")
    @SysLog("'重置密码:' + #ids")
    public R<Boolean> resetTx(@RequestParam("ids[]") List<Long> ids) {
        baseService.reset(ids);
        return success();
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
        User user = baseService.getByIdCache(id);
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
            if (station != null) {
                SysStation sysStation = BeanUtil.toBean(station, SysStation.class);
                sysStation.setOrgId(RemoteData.getKey(station.getOrg()));
                sysUser.setStation(sysStation);
            }
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
        return baseService.getDataScopeById(id);
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
        List<User> list = baseService.findUserByRoleId(roleId, keyword);
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
        return success(baseService.save(user));
    }


    @SysLog("清除缓存并重新加载数据")
    @ApiOperation(value = "清除缓存并重新加载数据", notes = "清除缓存并重新加载数据")
    @PostMapping(value = "/reload")
    public R<LoginDTO> reload(@RequestParam Long userId) throws BizException {
        User user = baseService.getByIdCache(userId);
        if (user == null) {
            return R.fail("用户不存在");
        }

        List<com.github.zuihou.authority.entity.auth.Resource> resourceList = this.resourceService.findVisibleResource(ResourceQueryDTO.builder().userId(userId).build());
        List<String> permissionsList = resourceList.stream().map(com.github.zuihou.authority.entity.auth.Resource::getCode).collect(Collectors.toList());

        return this.success(LoginDTO.builder().user(BeanUtil.toBean(user, UserDTO.class)).permissionsList(permissionsList).token(null).build());
    }

    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @GetMapping("/find")
    @SysLog("查询所有用户")
    public R<List<Long>> findAllUserId() {
        return success(baseService.list().stream().mapToLong(User::getId).boxed().collect(Collectors.toList()));
    }

    /**
     * 调用方传递的参数类型是 Set<Serializable> ，但接收方必须指定为Long类型（实体的主键类型），否则在调用mp提供的方法时，会使得mysql出现类型隐式转换问题。
     * 问题如下: select * from org where id in ('100');
     * <p>
     * 强制转换成Long后，sql就能正常执行: select * from org where id in (100);
     *
     * <p>
     * 接口和实现类的类型不一致，但也能调用，归功于 SpingBoot 的自动转换功能
     * {@link com.github.zuihou.authority.api.UserApi#findUserByIds} 方法的实现类
     *
     * @param codes id
     * @return
     */
    @ApiOperation(value = "根据id查询用户", notes = "根据id查询用户")
    @GetMapping("/findUserByIds")
    public Map<Serializable, Object> findUserByIds(@RequestParam Set<Long> codes) {
        return this.baseService.findUserByIds(codes);
    }

    /**
     * 调用方传递的参数类型是 Set<Serializable> ，但接收方必须指定为Long类型（实体的主键类型），否则在调用mp提供的方法时，会使得mysql出现类型隐式转换问题。
     * 问题如下: select * from org where id in ('100');
     * <p>
     * 强制转换成Long后，sql就能正常执行: select * from org where id in (100);
     *
     * <p>
     * 接口和实现类的类型不一致，但也能调用，归功于 SpingBoot 的自动转换功能
     * {@link com.github.zuihou.authority.api.UserApi#findUserNameByIds} 方法的实现类
     *
     * @param codes id
     * @return
     */
    @ApiOperation(value = "根据id查询用户名称", notes = "根据id查询用户名称")
    @GetMapping("/findUserNameByIds")
    public Map<Serializable, Object> findUserNameByIds(@RequestParam Set<Long> codes) {
        return this.baseService.findUserNameByIds(codes);
    }


    @Override
    protected void handlerImport(List<Map<String, String>> list) {

        List<User> userList = list.stream().map((map) -> {
            User user = new User();
            user.setAccount(map.getOrDefault("账号", ""));
            user.setName(map.getOrDefault("姓名", ""));
            user.setOrg(new RemoteData<>(Convert.toLong(map.getOrDefault("组织", ""))));
            user.setStation(new RemoteData<>(Convert.toLong(map.getOrDefault("岗位", ""))));
            user.setEmail(map.getOrDefault("邮箱", ""));
            user.setMobile(map.getOrDefault("手机", ""));
            user.setSex(Sex.match(map.getOrDefault("性别", ""), Sex.N));
            user.setStatus(Convert.toBool(map.getOrDefault("状态", "")));
            user.setAvatar(map.getOrDefault("头像", ""));
            user.setNation(new RemoteData<>(map.getOrDefault("民族", "")));
            user.setEducation(new RemoteData<>(map.getOrDefault("学历", "")));
            user.setPositionStatus(new RemoteData<>(map.getOrDefault("职位状态", "")));
            user.setWorkDescribe(map.getOrDefault("工作描述", ""));
            user.setPassword(DigestUtils.md5Hex(BizConstant.DEF_PASSWORD));
            String lastLoginTimeStr = map.getOrDefault("最后登录时间", "");
            if (StrUtil.isNotBlank(lastLoginTimeStr)) {
                LocalDateTime lastLoginTime = LocalDateTime.parse(lastLoginTimeStr, DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_TIME_FORMAT));
                user.setLastLoginTime(lastLoginTime);
            }
            return user;
        }).collect(Collectors.toList());

        baseService.saveBatch(userList);
    }

    @Override
    protected void query(PageParams<UserPageDTO> params, IPage<User> page, Long defSize) {
        UserPageDTO userPage = params.getModel();

        LbqWrapper<User> wrapper = Wraps.lbQ();
        if (userPage.getOrg() != null && RemoteData.getKey(userPage.getOrg(), 0L) > 0) {
            List<Org> children = orgService.findChildren(Arrays.asList(userPage.getOrg().getKey()));
            wrapper.in(User::getOrg, children.stream().map((org) -> new RemoteData(org.getId())).collect(Collectors.toList()));
        }
        wrapper
//                .geHeader(User::getCreateTime, userPage.getStartCreateTime())
//                .leFooter(User::getCreateTime, userPage.getEndCreateTime())
                .like(User::getName, userPage.getName())
                .like(User::getAccount, userPage.getAccount())
                .like(User::getEmail, userPage.getEmail())
                .like(User::getMobile, userPage.getMobile())
                .eq(User::getStation, userPage.getStation())
                .eq(User::getPositionStatus, userPage.getPositionStatus())
                .eq(User::getEducation, userPage.getEducation())
                .eq(userPage.getNation() != null && StrUtil.isNotEmpty(userPage.getNation().getKey()), User::getNation, userPage.getNation())
                .eq(User::getSex, userPage.getSex())
                .eq(User::getStatus, userPage.getStatus())
                .orderByDesc(User::getId);
        baseService.findPage(page, wrapper);
    }

    /**
     * 重写 分页查询用户
     *
     * @param params 分页查询对象
     * @return 查询结果
     */
//    @Override
//    public R<IPage<User>> page(@RequestBody @Validated PageParams<UserPageDTO> params) {
//        IPage<User> page = params.getPage();
//
//        UserPageDTO userPage = params.getModel();
//
//        LbqWrapper<User> wrapper = Wraps.lbQ();
//        if (userPage.getOrg() != null && RemoteData.getKey(userPage.getOrg(), 0L) > 0) {
//            List<Org> children = orgService.findChildren(Arrays.asList(userPage.getOrg().getKey()));
//            wrapper.in(User::getOrg, children.stream().map((org) -> new RemoteData(org.getId())).collect(Collectors.toList()));
//        }
//        wrapper
////                .geHeader(User::getCreateTime, userPage.getStartCreateTime())
////                .leFooter(User::getCreateTime, userPage.getEndCreateTime())
//                .like(User::getName, userPage.getName())
//                .like(User::getAccount, userPage.getAccount())
//                .like(User::getEmail, userPage.getEmail())
//                .like(User::getMobile, userPage.getMobile())
//                .eq(User::getStation, userPage.getStation())
//                .eq(User::getPositionStatus, userPage.getPositionStatus())
//                .eq(User::getEducation, userPage.getEducation())
//                .eq(userPage.getNation() != null && StrUtil.isNotEmpty(userPage.getNation().getKey()), User::getNation, userPage.getNation())
//                .eq(User::getSex, userPage.getSex())
//                .eq(User::getStatus, userPage.getStatus())
//                .orderByDesc(User::getId);
//        baseService.findPage(page, wrapper);
//        return success(page);
//    }

//    @Override
//    public void exportExcel(@RequestBody @Validated PageParams<UserPageDTO> params, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        UserPageDTO model = params.getModel();
//        if (model != null) {
//            if (model.getOrg() != null && model.getOrg().getKey() == null) {
//                model.setOrg(null);
//            }
//            if (model.getStation() != null && model.getStation().getKey() == null) {
//                model.setStation(null);
//            }
//            if (model.getEducation() != null && StrUtil.isEmpty(model.getEducation().getKey())) {
//                model.setEducation(null);
//            }
//            if (model.getNation() != null && StrUtil.isEmpty(model.getNation().getKey())) {
//                model.setNation(null);
//            }
//            if (model.getPositionStatus() != null && StrUtil.isEmpty(model.getPositionStatus().getKey())) {
//                model.setPositionStatus(null);
//            }
//        }
//
//        super.exportExcel(params, request, response);
//    }
//
//    @Override
//    public R<String> preview(@RequestBody @Validated PageParams<UserPageDTO> params, HttpServletRequest request) {
//        UserPageDTO model = params.getModel();
//        if (model != null) {
//            if (model.getOrg() != null && model.getOrg().getKey() == null) {
//                model.setOrg(null);
//            }
//            if (model.getStation() != null && model.getStation().getKey() == null) {
//                model.setStation(null);
//            }
//            if (model.getEducation() != null && StrUtil.isEmpty(model.getEducation().getKey())) {
//                model.setEducation(null);
//            }
//            if (model.getNation() != null && StrUtil.isEmpty(model.getNation().getKey())) {
//                model.setNation(null);
//            }
//            if (model.getPositionStatus() != null && StrUtil.isEmpty(model.getPositionStatus().getKey())) {
//                model.setPositionStatus(null);
//            }
//        }
//        return super.preview(params, request);
//    }

}
