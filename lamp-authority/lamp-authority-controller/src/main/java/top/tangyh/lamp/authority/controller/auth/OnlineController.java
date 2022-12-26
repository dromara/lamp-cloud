package top.tangyh.lamp.authority.controller.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.security.PreAuth;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.lamp.authority.dto.auth.Online;
import top.tangyh.lamp.authority.service.auth.OnlineService;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * token
 * </p>
 *
 * @author zuihou
 * @date 2020-04-02
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/online")
@Tag(name = "在线用户")
@PreAuth(replace = "authority:online:")
@RequiredArgsConstructor
public class OnlineController {
    private final OnlineService onlineService;

    @PostMapping(value = "/list")
    @PreAuth("hasAnyPermission('{}view')")
    public R<List<Online>> list(@RequestParam(required = false) String name) {
        return R.success(onlineService.list(name));
    }

    @PostMapping(value = "/page")
    @PreAuth("hasAnyPermission('{}view')")
    public R<IPage<Online>> page(@RequestBody @Validated PageParams<Online> params) {
        List<Online> list = onlineService.list(params.getModel().getName());
        IPage<Online> page = new Page<>(1, list.size(), list.size());
        page.setRecords(list);
        return R.success(page);
    }

    @Operation(summary = "T人", description = "T人")
    @PostMapping(value = "/t")
    @PreAuth("hasAnyPermission('{}delete')")
    public R<Boolean> logout(String userToken, Long userId, String clientId) {
        return R.success(onlineService.clear(userToken, userId, clientId));
    }

}
