package top.tangyh.lamp.system.controller.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.system.entity.system.DefArea;
import top.tangyh.lamp.system.service.system.DefAreaService;
import top.tangyh.lamp.system.vo.query.system.DefAreaPageQuery;
import top.tangyh.lamp.system.vo.result.system.DefAreaResultVO;
import top.tangyh.lamp.system.vo.save.system.DefAreaSaveVO;
import top.tangyh.lamp.system.vo.update.system.DefAreaUpdateVO;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * 地区表
 * </p>
 *
 * @author zuihou
 * @date 2021-10-13
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defArea")
@Tag(name = "地区表")
public class DefAreaController extends SuperController<DefAreaService, Long, DefArea, DefAreaSaveVO, DefAreaUpdateVO, DefAreaPageQuery, DefAreaResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    /**
     * 按树结构查询地区
     *
     * @param pageQuery 查询参数
     * @return 查询结果
     */
    @Operation(summary = "按树结构查询地区", description = "按树结构查询地区")
    @PostMapping("/tree")
    @WebLog("级联查询地区")
    public R<List<DefArea>> tree(@RequestBody DefAreaPageQuery pageQuery) {
        return success(superService.findTree(pageQuery));
    }

    @Operation(summary = "下载地区json文件", description = "下载地区json文件")
    @GetMapping(value = "/download", produces = "application/octet-stream")
    @WebLog("下载地区json文件")
    public void download(@RequestParam(required = false, defaultValue = "2") Integer treeGrade, HttpServletRequest request, HttpServletResponse response) {
        superService.downloadJson(treeGrade, request, response);
    }

    /**
     * 延迟查询
     *
     * @param parentId 父ID
     * @return 查询结果
     */
    @Operation(summary = "延迟查询", description = "延迟查询")
    @PostMapping("/lazyList")
    @WebLog("级联查询地区")
    public R<List<DefArea>> lazyList(@RequestParam Long parentId) {
        return success(superService.findLazyList(parentId));
    }

    @Operation(summary = "检测地区编码是否重复", description = "检测地区编码是否重复")
    @GetMapping("/check")
    @WebLog("检测地区编码是否重复")
    public R<Boolean> check(@RequestParam String code, @RequestParam(required = false) Long id) {
        return success(superService.check(code, id));
    }
}
