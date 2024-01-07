package top.tangyh.lamp.generator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.log.WebLog;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.DownloadController;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.base.request.DownloadVO;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.generator.entity.DefGenTable;
import top.tangyh.lamp.generator.enumeration.FileOverrideStrategyEnum;
import top.tangyh.lamp.generator.enumeration.TemplateEnum;
import top.tangyh.lamp.generator.service.DefGenTableService;
import top.tangyh.lamp.generator.vo.query.DefGenTablePageQuery;
import top.tangyh.lamp.generator.vo.result.DefGenTableResultVO;
import top.tangyh.lamp.generator.vo.save.DefGenTableImportVO;
import top.tangyh.lamp.generator.vo.save.DefGenTableSaveVO;
import top.tangyh.lamp.generator.vo.save.DefGenVO;
import top.tangyh.lamp.generator.vo.update.DefGenTableUpdateVO;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 前端控制器
 * 代码生成
 * </p>
 *
 * @author zuihou
 * @date 2022-03-01
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defGenTable")
@Tag(name = "代码生成")
public class DefGenTableController
        extends SuperController<DefGenTableService, Long, DefGenTable, DefGenTableSaveVO, DefGenTableUpdateVO, DefGenTablePageQuery, DefGenTableResultVO>
        implements DownloadController<Long, DefGenTable> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Operation(summary = "分页查询代码生成表", description = "分页查询代码生成表")
    @PostMapping("/selectTableList")
    @WebLog(value = "'分页列表查询:第' + #params?.current + '页, 显示' + #params?.size + '行'", response = false)
    public R<List<DefGenTable>> selectTableList(@RequestBody @Validated PageParams<DefGenTablePageQuery> params) {
        return R.success(superService.selectTableList(params.getModel().getDsId()));
    }

    @Operation(summary = "导入检测", description = "导入检测")
    @PostMapping("/importCheck")
    @WebLog(value = "'导入检测")
    public R<Boolean> importCheck(@RequestBody @Validated List<String> tableNames) {
        return R.success(superService.importCheck(tableNames));
    }

    @Operation(summary = "导入表结构", description = "导入表结构")
    @PostMapping(value = "/importTable")
    @WebLog(value = "'导入表结构", response = false)
    public R<Boolean> importTable(@RequestBody @Validated DefGenTableImportVO importVO) {
        return R.success(superService.importTable(importVO));
    }

    @Operation(summary = "同步表的字段", description = "同步表的字段,新增或删除，不修改原来就存在的字段")
    @PostMapping("/syncField")
    @WebLog(value = "'同步表的字段")
    public R<Boolean> syncField(@RequestParam Long id) {
        superService.syncField(id);
        return R.success(true);
    }

    @Operation(summary = "批量查询", description = "批量查询")
    @PostMapping("/findTableList")
    @WebLog(value = "'批量查询")
    public R<List<DefGenTableResultVO>> findTableList(@RequestBody List<Long> idList) {
        return R.success(superService.findTableList(idList));
    }

    @Operation(summary = "预览", description = "预览")
    @PostMapping("/previewCode")
    @WebLog(value = "'预览")
    public R<Map<String, String>> previewCode(@RequestParam Long id, @RequestParam TemplateEnum template) {
        return R.success(superService.previewCode(id, template));
    }

    @Operation(summary = "批量生成代码", description = "批量生成代码")
    @PostMapping("/generatorCode")
    @WebLog(value = "'批量生成代码")
    public R<Boolean> generatorCode(@RequestBody @Validated DefGenVO defGenVO) {
        superService.generatorCode(defGenVO);
        return R.success(true);
    }

    @Operation(summary = "批量下载代码", description = "批量下载代码")
    @GetMapping(value = "/downloadZip", produces = "application/octet-stream")
    @WebLog(value = "'批量下载代码")
    public void downloadZip(HttpServletResponse response, @RequestParam List<Long> ids, @RequestParam TemplateEnum template) {
        DownloadVO download = superService.downloadZip(ids, template);
        write(download.getData(), download.getFileName(), response);
    }


    @Override
    public R<DefGenTableResultVO> getDetail(@RequestParam("id") Long id) {
        DefGenTableResultVO detail = superService.getDetail(id);
        echoService.action(detail);
        return R.success(detail);
    }

    @Operation(summary = "获取字段模板映射", description = "获取字段模板映射")
    @GetMapping("/getFieldTemplate")
    @WebLog(value = "'获取字段模板映射")
    public R<Map<String, String>> getFieldTemplate() {
        return R.success(superService.getFieldTemplate());
    }

    @Operation(summary = "获取生成代码是否覆盖的默认配置", description = "获取生成代码是否覆盖的默认配置")
    @GetMapping("/getDefFileOverrideStrategy")
    @WebLog(value = "'获取生成代码是否覆盖的默认配置")
    public R<Map<String, FileOverrideStrategyEnum>> getDefFileOverrideStrategy() {
        return R.success(superService.getDefFileOverrideStrategy());
    }


}
