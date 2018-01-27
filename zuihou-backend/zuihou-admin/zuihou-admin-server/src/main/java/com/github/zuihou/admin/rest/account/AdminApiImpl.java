package com.github.zuihou.admin.rest.account;

import com.github.zuihou.admin.constant.AdminType;
import com.github.zuihou.admin.constant.AppType;
import com.github.zuihou.admin.entity.account.po.Admin;
import com.github.zuihou.admin.entity.account.po.Applications;
import com.github.zuihou.admin.repository.account.example.AdminExample;
import com.github.zuihou.admin.repository.account.service.AdminService;
import com.github.zuihou.admin.repository.account.service.ApplicationsService;
import com.github.zuihou.admin.rest.account.api.AdminApi;
import com.github.zuihou.admin.rest.account.dto.AdminDto;
import com.github.zuihou.admin.rest.account.dto.AdminRegisterDto;
import com.github.zuihou.admin.rest.dozer.DozerUtils;
import com.github.zuihou.auth.client.annotation.IgnoreAppToken;
import com.github.zuihou.base.Result;
import com.github.zuihou.commons.constant.DeleteStatus;
import com.github.zuihou.commons.constant.EnableStatus;
import com.github.zuihou.commons.exception.core.ExceptionCode;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zuihou
 * @createTime 2017-12-15 11:20
 */
@Api(value = "API - AdminApiImpl", description = "帐号管理")
@RestController
@RequestMapping("admin")
@Slf4j
public class AdminApiImpl implements AdminApi {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ApplicationsService applicationsService;
    @Autowired
    private DozerUtils dozerUtils;

    @Override
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @IgnoreAppToken
    public Result<AdminDto> get(@RequestParam("userName") String userName) {
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(userName).andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal());
        Admin admin = adminService.getUnique(example);
        if (admin == null) {
            return Result.fail(ExceptionCode.USER_NOT_EXIST);
        }
        AdminDto dto = dozerUtils.map(admin, AdminDto.class);
        return Result.success(dto);
    }

    /**
     * 根据用户和密码查找用户
     *
     * @param userName 登录名
     * @param passWord 明文密码
     * @return
     */
    @Override
    @IgnoreAppToken
    @RequestMapping(value = "/getByPwd", method = RequestMethod.GET)
    public Result<AdminDto> getByPwd(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
        Admin admin = adminService.get(userName, passWord);
        if (admin == null) {
            return Result.fail(ExceptionCode.USER_NAME_PWD_ERROR);
        }
        AdminDto dto = dozerUtils.map(admin, AdminDto.class);
        return Result.success(dto);
    }

    /**
     * 检测登录名是否可用
     *
     * @param userName 登录名
     * @return
     */
    @Override
    @IgnoreAppToken
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Result<Boolean> check(@RequestParam("userName") String userName) {
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(userName);
        int count = adminService.count(example);
        if (count > 0) {
            return Result.fail(ExceptionCode.USER_NAME_EXIST.getCode(), ExceptionCode.USER_NAME_EXIST.getMsg());
        }
        return Result.success(true);
    }

    /**
     * 注册帐号
     *
     * @param adminRegisterDto 帐号注册
     * @return
     */
    @Override
    @IgnoreAppToken
    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public Result<Boolean> registry(@RequestBody AdminRegisterDto adminRegisterDto) {
        //1，验证参数
        //BizAssert.assertNull("", adminRegisterDto);
        //adminRegisterDto.getUsername();
        //adminRegisterDto.getPassword();
        //adminRegisterDto.getConfirmPassword();
        //adminRegisterDto.getAppName();

        //2，验证帐号是否被注册
        Result<Boolean> checkResult = this.check(adminRegisterDto.getUsername());
        if (!checkResult.isSuccess()) {
            return checkResult;
        }

        //3，创建应用
        Applications app = new Applications();
        app.setAppName(adminRegisterDto.getAppName());
        app.setAppType(AppType.APP.toString());
        app.setUpdateUser(adminRegisterDto.getUsername());
        app.setCreateUser(adminRegisterDto.getUsername());
        app = applicationsService.saveApp(app);

        //4,创建帐号
        Admin admin = dozerUtils.map(adminRegisterDto, Admin.class);
        admin.setAppId(app.getAppId());
        admin.setType(AdminType.GENERAL_ADMIN.getVal());
        admin.setIsDelete(DeleteStatus.UN_DELETE.getVal());
        admin.setIsEnable(EnableStatus.ENABLE.getVal());
        admin.setUpdateUser(adminRegisterDto.getUsername());
        admin.setCreateUser(adminRegisterDto.getUsername());
        adminService.save(admin);

        //5,初始化角色，权限，菜单等[应该异步]
        return Result.success(true);
    }
}
