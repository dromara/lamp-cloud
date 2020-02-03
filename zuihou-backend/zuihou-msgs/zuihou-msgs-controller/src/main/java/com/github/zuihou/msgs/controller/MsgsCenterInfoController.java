package com.github.zuihou.msgs.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.api.RoleApi;
import com.github.zuihou.authority.api.UserApi;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.msgs.dto.MsgsCenterInfoPageResultDTO;
import com.github.zuihou.msgs.dto.MsgsCenterInfoQueryDTO;
import com.github.zuihou.msgs.dto.MsgsCenterInfoSaveDTO;
import com.github.zuihou.msgs.entity.MsgsCenterInfo;
import com.github.zuihou.msgs.enumeration.MsgsCenterType;
import com.github.zuihou.msgs.service.MsgsCenterInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * 消息中心
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/msgsCenterInfo")
@Api(value = "MsgsCenterInfo", tags = "消息中心")
public class MsgsCenterInfoController extends BaseController {

    @Autowired
    private MsgsCenterInfoService msgsCenterInfoService;
    @Resource
    private RoleApi roleApi;
    @Resource
    private UserApi userApi;


    /**
     * 根据用户权限查询 消息
     * WAIT:待办
     * NOTIFY:通知;
     * WARN:预警;
     * 已读： msgs_center_info_receive表有数据且是否已读字段为已读
     * 未读： msgs_center_info_receive表无数据且是否已读字段为未读
     * <p>
     * PUBLICITY:公示公告;  默认发给所有人
     * 已读：msgs_center_info_receive表有数据且是否已读字段为已读
     * 未读：msgs_center_info_receive表无数据
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询消息中心", notes = "分页查询消息中心")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询消息中心")
    public R<IPage<MsgsCenterInfoPageResultDTO>> page(MsgsCenterInfoQueryDTO data) {
        IPage<MsgsCenterInfoPageResultDTO> page = getPage();

        if (data.getStartCreateTime() != null) {
            data.setStartCreateTime(LocalDateTime.of(data.getStartCreateTime().toLocalDate(), LocalTime.MIN));
        }
        if (data.getEndCreateTime() != null) {
            data.setEndCreateTime(LocalDateTime.of(data.getEndCreateTime().toLocalDate(), LocalTime.MAX));
        }
        data.setUserId(getUserId());
        msgsCenterInfoService.page(page, data);
        return success(page);
    }

    /**
     * 标记消息为已读
     *
     * @param msgCenterIds 主表id
     * @return
     */
    @ApiOperation(value = "标记消息为已读", notes = "标记消息为已读")
    @GetMapping(value = "/mark")
    public R<Boolean> mark(@RequestParam(value = "msgCenterIds[]") List<Long> msgCenterIds) {
        return success(msgsCenterInfoService.mark(msgCenterIds, getUserId()));
    }

    /**
     * 查询消息中心
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询消息中心", notes = "查询消息中心")
    @GetMapping("/{id}")
    @SysLog("查询消息中心")
    public R<MsgsCenterInfo> get(@PathVariable Long id) {
        return success(msgsCenterInfoService.getById(id));
    }

    /**
     * 新增消息中心
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增消息中心", notes = "新增消息中心不为空的字段")
    @PostMapping
    @SysLog("新增消息中心")
    public R<MsgsCenterInfo> save(@RequestBody @Validated MsgsCenterInfoSaveDTO data) {
        if (CollectionUtil.isEmpty(data.getUserIdList()) && CollectionUtil.isNotEmpty(data.getRoleCodeList())) {
            R<List<Long>> result = roleApi.findUserIdByCode(data.getRoleCodeList().stream().toArray(String[]::new));
            if (result.getIsSuccess()) {
                if (result.getData().isEmpty()) {
                    return fail("已选角色下尚未分配任何用户");
                }
                data.setUserIdList(new HashSet<>(result.getData()));
            }
        }
        if (MsgsCenterType.PUBLICITY.eq(data.getMsgsCenterInfoDTO().getMsgsCenterType())) {
            R<List<Long>> result = userApi.findAllUserId();
            if (result.getIsSuccess()) {
                data.setUserIdList(new HashSet<>(result.getData()));
            }
        }

        return success(msgsCenterInfoService.saveMsgs(data));
    }

    /**
     * 删除消息中心
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除消息中心", notes = "根据id物理删除消息中心")
    @DeleteMapping
    @SysLog("删除消息中心")
    public R<Boolean> delete(@RequestParam(value = "ids[]") Long[] ids) {
        return success(msgsCenterInfoService.delete(ids, getUserId()));
    }

}
