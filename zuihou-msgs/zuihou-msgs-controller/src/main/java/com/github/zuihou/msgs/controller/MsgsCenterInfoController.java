package com.github.zuihou.msgs.controller;


import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelXorHtmlUtil;
import cn.afterturn.easypoi.excel.entity.ExcelToHtmlParams;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.api.RoleApi;
import com.github.zuihou.authority.api.UserApi;
import com.github.zuihou.base.R;
import com.github.zuihou.base.request.PageParams;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.msgs.dto.MsgsCenterInfoPageResultDTO;
import com.github.zuihou.msgs.dto.MsgsCenterInfoQueryDTO;
import com.github.zuihou.msgs.dto.MsgsCenterInfoSaveDTO;
import com.github.zuihou.msgs.entity.MsgsCenterInfo;
import com.github.zuihou.msgs.enumeration.MsgsCenterType;
import com.github.zuihou.msgs.service.MsgsCenterInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
@RequestMapping("/msgsCenterInfo")
@Api(value = "MsgsCenterInfo", tags = "消息中心")
@Validated
public class MsgsCenterInfoController {

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
    @PostMapping("/page")
    @SysLog(value = "'分页列表查询:第' + #params?.current + '页, 显示' + #params?.size + '行'", response = false)
    public R<IPage<MsgsCenterInfoPageResultDTO>> page(@RequestBody @Validated PageParams<MsgsCenterInfoQueryDTO> data) {
        IPage<MsgsCenterInfoPageResultDTO> page = data.getPage();
        query(data, page);
        return R.success(page);
    }

    private IPage<MsgsCenterInfoPageResultDTO> query(PageParams<MsgsCenterInfoQueryDTO> data, IPage<MsgsCenterInfoPageResultDTO> page) {

        MsgsCenterInfoQueryDTO model = data.getModel();
        if (model.getStartCreateTime() != null) {
            model.setStartCreateTime(LocalDateTime.of(model.getStartCreateTime().toLocalDate(), LocalTime.MIN));
        }
        if (model.getEndCreateTime() != null) {
            model.setEndCreateTime(LocalDateTime.of(model.getEndCreateTime().toLocalDate(), LocalTime.MAX));
        }
        model.setUserId(BaseContextHandler.getUserId());
        msgsCenterInfoService.page(page, model);
        return page;
    }


    /**
     * 构建导出参数
     *
     * @param params 分页参数
     * @param page
     * @return
     */
    private ExportParams getExportParams(PageParams<MsgsCenterInfoQueryDTO> params, IPage<MsgsCenterInfoPageResultDTO> page) {
        query(params, page);

        String title = params.getMap().get("title");
        String type = params.getMap().getOrDefault("type", ExcelType.XSSF.name());
        String sheetName = params.getMap().getOrDefault("sheetName", "SheetName");

        ExcelType excelType = ExcelType.XSSF.name().equals(type) ? ExcelType.XSSF : ExcelType.HSSF;
        return new ExportParams(title, sheetName, excelType);
    }

    /**
     * 导出Excel
     *
     * @param params   参数
     * @param request  请求
     * @param response 响应
     * @throws IOException
     */
    @ApiOperation(value = "导出Excel")
    @RequestMapping(value = "/export", method = RequestMethod.POST, produces = "application/octet-stream")
    @SysLog("'导出Excel:'.concat(#params.map[" + NormalExcelConstants.FILE_NAME + "]?:'')")
    public void exportExcel(@RequestBody @Validated PageParams<MsgsCenterInfoQueryDTO> params, HttpServletRequest request, HttpServletResponse response) {
        IPage<MsgsCenterInfoPageResultDTO> page = params.getPage();
        ExportParams exportParams = getExportParams(params, page);

        Map<String, Object> map = new HashMap<>(5);
        map.put(NormalExcelConstants.DATA_LIST, page.getRecords());
        map.put(NormalExcelConstants.CLASS, MsgsCenterInfoPageResultDTO.class);
        map.put(NormalExcelConstants.PARAMS, exportParams);
        String fileName = params.getMap().getOrDefault(NormalExcelConstants.FILE_NAME, "临时文件");
        map.put(NormalExcelConstants.FILE_NAME, fileName);
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 预览Excel
     *
     * @param params 预览参数
     * @return
     */
    @ApiOperation(value = "预览Excel")
    @SysLog("'预览Excel:' + (#params.map[" + NormalExcelConstants.FILE_NAME + "]?:'')")
    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    public R<String> preview(@RequestBody @Validated PageParams<MsgsCenterInfoQueryDTO> params) {
        IPage<MsgsCenterInfoPageResultDTO> page = params.getPage();
        ExportParams exportParams = getExportParams(params, page);

        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, MsgsCenterInfoPageResultDTO.class, page.getRecords());
        return R.success(ExcelXorHtmlUtil.excelToHtml(new ExcelToHtmlParams(workbook)));
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
        return R.success(msgsCenterInfoService.mark(msgCenterIds, BaseContextHandler.getUserId()));
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
        return R.success(msgsCenterInfoService.getById(id));
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
                    return R.fail("已选角色下尚未分配任何用户");
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

        return R.success(msgsCenterInfoService.saveMsgs(data));
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
        return R.success(msgsCenterInfoService.delete(ids, BaseContextHandler.getUserId()));
    }

}
