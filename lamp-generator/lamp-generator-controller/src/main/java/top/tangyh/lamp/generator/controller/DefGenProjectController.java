package top.tangyh.lamp.generator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.log.WebLog;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.DownloadController;
import top.tangyh.basic.base.request.DownloadVO;
import top.tangyh.basic.base.service.SuperService;
import top.tangyh.lamp.generator.entity.DefGenTable;
import top.tangyh.lamp.generator.service.DefGenTableService;
import top.tangyh.lamp.generator.vo.save.ProjectGeneratorVO;


/**
 * <p>
 * 前端控制器
 * 项目生成
 * </p>
 *
 * @author zuihou
 * @date 2022-03-01
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defGenProject")
@Tag(name = "项目生成")
public class DefGenProjectController implements DownloadController<Long, DefGenTable> {
    private final DefGenTableService defGenTableService;

    @Override
    public SuperService<Long, DefGenTable> getSuperService() {
        return defGenTableService;
    }

    @Override
    public Class<DefGenTable> getEntityClass() {
        return DefGenTable.class;
    }

    @Operation(summary = "获取默认配置", description = "获取默认配置")
    @PostMapping("/getDef")
    @WebLog(value = "获取默认配置")
    public R<ProjectGeneratorVO> getDef() {

        return R.success(defGenTableService.getDef());
    }

    @PostMapping("/anno/getProperties")
    public R<Object> getProperties() {
        return R.success(System.getProperties());
    }


    @Operation(summary = "下载项目", description = "下载项目")
    @PostMapping(value = "/download", produces = "application/octet-stream")
    @WebLog(value = "'下载项目")
    public void download(@RequestBody @Validated ProjectGeneratorVO projectGenerator, HttpServletResponse response) {
        DownloadVO download = defGenTableService.download(projectGenerator);
        write(download.getData(), download.getFileName(), response);
    }

    @Operation(summary = "生成项目", description = "生成项目")
    @PostMapping("/generator")
    @WebLog(value = "'生成项目")
    public R<Boolean> generator(@RequestBody @Validated ProjectGeneratorVO projectGenerator) {
        defGenTableService.generator(projectGenerator);
        return R.success(true);
    }
}
