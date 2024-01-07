package top.tangyh.lamp.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.log.WebLog;
import top.tangyh.basic.annotation.user.LoginUser;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.model.entity.system.SysUser;
import top.tangyh.lamp.test.entity.DefGenTestTree;
import top.tangyh.lamp.test.service.DefGenTestTreeService;
import top.tangyh.lamp.test.vo.query.DefGenTestTreePageQuery;
import top.tangyh.lamp.test.vo.result.DefGenTestTreeResultVO;
import top.tangyh.lamp.test.vo.save.DefGenTestTreeSaveVO;
import top.tangyh.lamp.test.vo.update.DefGenTestTreeUpdateVO;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 测试树结构
 * </p>
 *
 * @author zuihou
 * @date 2022-04-20 00:28:30
 * @create [2022-04-20 00:28:30] [zuihou] [代码生成器生成]
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defGenTestTree")
@Tag(name = "测试树结构")
public class DefGenTestTreeController extends SuperController<DefGenTestTreeService, Long, DefGenTestTree, DefGenTestTreeSaveVO,
        DefGenTestTreeUpdateVO, DefGenTestTreePageQuery, DefGenTestTreeResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    /**
     * 按树结构查询
     *
     * @param pageQuery 查询参数
     * @return 查询结果
     */
    @Operation(summary = "按树结构查询", description = "按树结构查询")
    @PostMapping("/tree")
    @WebLog("级联查询")
    public R<List<DefGenTestTree>> tree(@RequestBody DefGenTestTreePageQuery pageQuery) {
        return success(superService.findTree(pageQuery));
    }

    @PostMapping("/anyone/test")
    public R<Object> test(@LoginUser(isFull = true) SysUser user) {
        return success(user);
    }
}


