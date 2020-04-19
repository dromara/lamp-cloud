package com.github.zuihou.authority.controller.auth;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.controller.poi.ExcelUserVerifyHandlerImpl;
import com.github.zuihou.authority.dto.auth.*;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.common.DictionaryItemService;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.base.R;
import com.github.zuihou.base.controller.SuperCacheController;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.base.request.PageParams;
import com.github.zuihou.common.constant.BizConstant;
import com.github.zuihou.common.constant.DictionaryCode;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.database.mybatis.conditions.query.QueryWrap;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.model.RemoteData;
import com.github.zuihou.security.annotation.PreAuth;
import com.github.zuihou.utils.MapHelper;
import com.github.zuihou.utils.StrPool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
@PreAuth(replace = "user:")
public class UserController extends SuperCacheController<UserService, Long, User, UserPageDTO, UserSaveDTO, UserUpdateDTO> {
    @Autowired
    private OrgService orgService;
    @Autowired
    private ExcelUserVerifyHandlerImpl excelUserVerifyHandler;
    @Autowired
    private DictionaryItemService dictionaryItemService;

    /**
     * 重写保存逻辑
     *
     * @param data
     * @return
     */
    @Override
    public R<User> handlerSave(UserSaveDTO data) {
        User user = BeanUtil.toBean(data, User.class);
        baseService.saveUser(user);
        return success(user);
    }

    /**
     * 重写删除逻辑
     *
     * @param ids
     * @return
     */
    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        baseService.remove(ids);
        return success(true);
    }

    /**
     * 重写修改逻辑
     *
     * @param data
     * @return
     */
    @Override
    public R<User> handlerUpdate(UserUpdateDTO data) {
        User user = BeanUtil.toBean(data, User.class);
        baseService.updateUser(user);
        return success(user);
    }

    /**
     * 修改
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "修改基础信息")
    @PutMapping("/base")
    @SysLog(value = "'修改基础信息:' + #data?.id", request = false)
    @PreAuth("hasPermit('{}update')")
    public R<User> updateBase(@RequestBody @Validated({SuperEntity.Update.class}) UserUpdateBaseInfoDTO data) {
        User user = BeanUtil.toBean(data, User.class);
        baseService.updateById(user);
        return success(user);
    }

    /**
     * 修改头像
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "修改头像", notes = "修改头像")
    @PutMapping("/avatar")
    @SysLog("'修改头像:' + #p0.id")
    public R<User> avatar(@RequestBody @Validated(SuperEntity.Update.class) UserUpdateAvatarDTO data) {
        User user = BeanUtil.toBean(data, User.class);
        baseService.updateById(user);
        return success(user);
    }

    /**
     * 修改密码
     *
     * @param data 修改实体
     * @return
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PutMapping("/password")
    @SysLog("'修改密码:' + #p0.id")
    public R<Boolean> updatePassword(@RequestBody @Validated(SuperEntity.Update.class) UserUpdatePasswordDTO data) {
        return success(baseService.updatePassword(data));
    }

    /**
     * 重置密码
     *
     * @param ids 用户ID
     * @return
     */
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @GetMapping("/reset")
    @SysLog("'重置密码:' + #ids")
    public R<Boolean> resetTx(@RequestParam("ids[]") List<Long> ids) {
        baseService.reset(ids);
        return success();
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



    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @GetMapping("/find")
    @SysLog("查询所有用户")
    public R<List<Long>> findAllUserId() {
        return success(baseService.findAllUserId());
    }

    @Override
    public R<Boolean> importExcel(@RequestParam("file") MultipartFile simpleFile, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setNeedVerify(true);
        params.setVerifyGroup(new Class[]{Default.class});
        params.setVerifyHandler(excelUserVerifyHandler);
        //用官方提供的DictHandler有性能问题
//        params.setDictHandler();
        ExcelImportResult<UserExcelVO> result = ExcelImportUtil.importExcelMore(simpleFile.getInputStream(), UserExcelVO.class, params);

        if (result.isVerifyFail()) {
            String errorMsgs = result.getFailList().stream().map((item) -> StrUtil.format("第{}行检验错误: {}", item.getRowNum(), item.getErrorMsg()))
                    .collect(Collectors.joining("<br/>"));
            return R.validFail(errorMsgs);
        }

        List<UserExcelVO> list = result.getList();
        if (list.isEmpty()) {
            return this.validFail("导入数据不能为空");
        }
        //数据转换
        Map<String, Map<String, String>> dictMap = dictionaryItemService.map(new String[]{DictionaryCode.EDUCATION, DictionaryCode.NATION, DictionaryCode.POSITION_STATUS});

        Map<String, String> educationMap = MapHelper.inverse(dictMap.get(DictionaryCode.EDUCATION));
        Map<String, String> nationMap = MapHelper.inverse(dictMap.get(DictionaryCode.NATION));
        Map<String, String> positionStatusMap = MapHelper.inverse(dictMap.get(DictionaryCode.POSITION_STATUS));

        List<User> userList = list.stream().map((item) -> {
            User user = new User();
            String[] ignore = new String[]{
                    "org", "station", "nation", "education", "positionStatus"
            };
            BeanUtil.copyProperties(item, user, ignore);
            user.setOrg(new RemoteData<>(item.getOrg()));
            user.setStation(new RemoteData<>(item.getStation()));
            user.setEducation(new RemoteData<>(educationMap.getOrDefault(item.getEducation(), StrPool.EMPTY)));
            user.setNation(new RemoteData<>(nationMap.getOrDefault(item.getNation(), StrPool.EMPTY)));
            user.setPositionStatus(new RemoteData<>(positionStatusMap.getOrDefault(item.getPositionStatus(), StrPool.EMPTY)));
            user.setPassword(BizConstant.DEF_PASSWORD_MD5);
            return user;
        }).collect(Collectors.toList());

        baseService.saveBatch(userList);

        return this.success(true);
    }


    /**
     * 分页、导出、导出预览 方法的共用查询条件
     *
     * @param params
     * @param page
     * @param defSize
     */
    @Override
    public void query(PageParams<UserPageDTO> params, IPage<User> page, Long defSize) {
        UserPageDTO userPage = params.getModel();

        QueryWrap<User> wrap = Wraps.q();
        handlerWrapper(wrap, params);

        LbqWrapper<User> wrapper = wrap.lambda();
        if (userPage.getOrg() != null && RemoteData.getKey(userPage.getOrg(), 0L) > 0) {
            List<Org> children = orgService.findChildren(Arrays.asList(userPage.getOrg().getKey()));
            wrapper.in(User::getOrg, children.stream().map((org) -> new RemoteData(org.getId())).collect(Collectors.toList()));
        }
        wrapper.like(User::getName, userPage.getName())
                .like(User::getAccount, userPage.getAccount())
                .like(User::getEmail, userPage.getEmail())
                .like(User::getMobile, userPage.getMobile())
                .eq(User::getStation, userPage.getStation())
                .eq(User::getPositionStatus, userPage.getPositionStatus())
                .eq(User::getEducation, userPage.getEducation())
                .eq(userPage.getNation() != null && StrUtil.isNotEmpty(userPage.getNation().getKey()), User::getNation, userPage.getNation())
                .eq(User::getSex, userPage.getSex())
                .eq(User::getStatus, userPage.getStatus());
        baseService.findPage(page, wrapper);
    }
}
