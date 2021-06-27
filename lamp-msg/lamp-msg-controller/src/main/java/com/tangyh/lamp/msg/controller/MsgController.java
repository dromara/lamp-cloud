package com.tangyh.lamp.msg.controller;


import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelXorHtmlUtil;
import cn.afterturn.easypoi.excel.entity.ExcelToHtmlParams;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tangyh.basic.annotation.log.SysLog;
import com.tangyh.basic.annotation.security.PreAuth;
import com.tangyh.basic.base.R;
import com.tangyh.basic.base.request.PageParams;
import com.tangyh.basic.base.request.PageUtil;
import com.tangyh.basic.context.ContextUtil;
import com.tangyh.basic.database.mybatis.conditions.Wraps;
import com.tangyh.basic.database.mybatis.conditions.query.LbqWrapper;
import com.tangyh.lamp.authority.api.UserBizApi;
import com.tangyh.lamp.msg.dto.MsgPageResult;
import com.tangyh.lamp.msg.dto.MsgQuery;
import com.tangyh.lamp.msg.dto.MsgSaveDTO;
import com.tangyh.lamp.msg.entity.Msg;
import com.tangyh.lamp.msg.enumeration.MsgType;
import com.tangyh.lamp.msg.service.MsgService;
import com.tangyh.lamp.oauth.api.RoleApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
@RestController
@RequestMapping("/msg")
@Api(value = "MsgController", tags = "消息中心")
@Validated
@PreAuth(replace = "msg:msg:")
@RequiredArgsConstructor
public class MsgController {
    private final MsgService msgService;
    private final RoleApi roleApi;
    private final UserBizApi userBizApi;


    /**
     * 根据用户权限查询 消息
     * WAIT:待办
     * NOTIFY:通知;
     * WARN:预警;
     * 已读： msg_receive表有数据且是否已读字段为已读
     * 未读： msg_receive表无数据且是否已读字段为未读
     * <p>
     * PUBLICITY:公示公告;  默认发给所有人
     * 已读：msg_receive表有数据且是否已读字段为已读
     * 未读：msg_receive表无数据
     *
     * @param params 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询消息中心", notes = "分页查询消息中心")
    @PostMapping("/page")
    @SysLog(value = "'分页列表查询:第' + #params?.current + '页, 显示' + #params?.size + '行'", response = false)
    public R<IPage<Msg>> page(@RequestBody @Validated PageParams<MsgQuery> params) {
        IPage<Msg> page = params.buildPage();
        Msg model = BeanUtil.toBean(params.getModel(), Msg.class);
        LbqWrapper<Msg> wraps = Wraps.lbq(model, params.getExtra(), Msg.class);
        msgService.page(page, wraps);
        return R.success(page);
    }

    private IPage<MsgPageResult> query(PageParams<MsgQuery> param, IPage<MsgPageResult> page) {

        MsgQuery model = param.getModel();
        PageUtil.timeRange(param);

        model.setUserId(ContextUtil.getUserId());
        msgService.page(page, param);
        return page;
    }


    /**
     * 构建导出参数
     *
     * @param params 分页参数
     * @param page   分页参数
     * @return 导出参数
     */
    private ExportParams getExportParams(PageParams<MsgQuery> params, IPage<MsgPageResult> page) {
        query(params, page);

        Object title = params.getExtra().get("title");
        Object type = params.getExtra().getOrDefault("type", ExcelType.XSSF.name());
        Object sheetName = params.getExtra().getOrDefault("sheetName", "SheetName");

        ExcelType excelType = ExcelType.XSSF.name().equals(type) ? ExcelType.XSSF : ExcelType.HSSF;
        return new ExportParams(String.valueOf(title), sheetName.toString(), excelType);
    }

    /**
     * 导出Excel
     *
     * @param params   参数
     * @param request  请求
     * @param response 响应
     */
    @ApiOperation(value = "导出Excel")
    @RequestMapping(value = "/export", method = RequestMethod.POST, produces = "application/octet-stream")
    @SysLog("'导出Excel:'.concat(#params.extra[" + NormalExcelConstants.FILE_NAME + "]?:'')")
    public void exportExcel(@RequestBody @Validated PageParams<MsgQuery> params, HttpServletRequest request, HttpServletResponse response) {
        IPage<MsgPageResult> page = params.buildPage();
        ExportParams exportParams = getExportParams(params, page);

        Map<String, Object> map = new HashMap<>(7);
        map.put(NormalExcelConstants.DATA_LIST, page.getRecords());
        map.put(NormalExcelConstants.CLASS, MsgPageResult.class);
        map.put(NormalExcelConstants.PARAMS, exportParams);
        Object fileName = params.getExtra().getOrDefault(NormalExcelConstants.FILE_NAME, "临时文件");
        map.put(NormalExcelConstants.FILE_NAME, fileName);
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 预览Excel
     *
     * @param params 预览参数
     * @return 预览数据
     */
    @ApiOperation(value = "预览Excel")
    @SysLog("'预览Excel:' + (#params.extra[" + NormalExcelConstants.FILE_NAME + "]?:'')")
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    public R<String> preview(@RequestBody @Validated PageParams<MsgQuery> params) {
        IPage<MsgPageResult> page = params.buildPage();
        ExportParams exportParams = getExportParams(params, page);

        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, MsgPageResult.class, page.getRecords());
        return R.success(ExcelXorHtmlUtil.excelToHtml(new ExcelToHtmlParams(workbook)));
    }

    /**
     * 标记消息为已读
     *
     * @param msgCenterIds 主表id
     * @return 是否成功
     */
    @ApiOperation(value = "标记消息为已读", notes = "标记消息为已读")
    @PostMapping(value = "/mark")
    public R<Boolean> mark(@RequestBody List<Long> msgCenterIds) {
        return R.success(msgService.mark(msgCenterIds, ContextUtil.getUserId()));
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
    public R<Msg> get(@PathVariable Long id) {
        return R.success(msgService.getById(id));
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
    @PreAuth("hasAnyPermission('{}add')")
    public R<Msg> save(@RequestBody @Validated MsgSaveDTO data) {
        if (CollectionUtil.isEmpty(data.getUserIdList()) && CollectionUtil.isNotEmpty(data.getRoleCodeList())) {
            R<List<Long>> result = roleApi.findUserIdByCode(data.getRoleCodeList().toArray(new String[0]));
            if (result.getIsSuccess()) {
                if (result.getData().isEmpty()) {
                    return R.fail("已选角色下尚未分配任何用户");
                }
                data.setUserIdList(new HashSet<>(result.getData()));
            }
        }
        if (MsgType.PUBLICITY.eq(data.getMsgDTO().getMsgType())) {
            R<List<Long>> result = userBizApi.findAllUserId();
            if (result.getIsSuccess()) {
                data.setUserIdList(new HashSet<>(result.getData()));
            }
        }

        return R.success(msgService.saveMsg(data));
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
    public R<Boolean> delete(@RequestParam(value = "ids[]") List<Long> ids) {
        return R.success(msgService.delete(ids, ContextUtil.getUserId()));
    }

}
