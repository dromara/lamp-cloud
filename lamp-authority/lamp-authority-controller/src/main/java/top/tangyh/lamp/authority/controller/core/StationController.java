package top.tangyh.lamp.authority.controller.core;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.security.PreAuth;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperCacheController;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.lamp.authority.dto.core.StationPageQuery;
import top.tangyh.lamp.authority.dto.core.StationSaveDTO;
import top.tangyh.lamp.authority.dto.core.StationUpdateDTO;
import top.tangyh.lamp.authority.entity.core.Station;
import top.tangyh.lamp.authority.service.core.StationService;
import top.tangyh.lamp.model.entity.base.SysUser;
import top.tangyh.lamp.userinfo.service.UserHelperService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * 岗位
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@RestController
@RequestMapping("/station")
@Tag(name = "岗位")
@PreAuth(replace = "authority:station:")
@RequiredArgsConstructor
public class StationController extends SuperCacheController<StationService, Long, Station, StationPageQuery, StationSaveDTO, StationUpdateDTO> {
    private final UserHelperService userHelperService;

    @Parameters({
            @Parameter(name = "id", description = "ID", schema = @Schema(type = "long"), in = ParameterIn.QUERY),
            @Parameter(name = "orgId", description = "orgId", schema = @Schema(type = "long"), in = ParameterIn.QUERY),
            @Parameter(name = "name", description = "名称", schema = @Schema(type = "string"), in = ParameterIn.QUERY),
    })
    @Operation(summary = "检测名称是否可用", description = "检测名称是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam(required = false) Long id, @RequestParam Long orgId, @RequestParam String name) {
        return success(baseService.check(id, orgId, name));
    }

    @Override
    public R<Station> handlerSave(StationSaveDTO model) {
        SysUser sysUser = userHelperService.getUserByIdCache(ContextUtil.getUserId());
        if (sysUser != null) {
            model.setCreatedOrgId(sysUser.getOrgId());
        }
        return super.handlerSave(model);
    }

    @Override
    public IPage<Station> query(PageParams<StationPageQuery> params) {
        IPage<Station> page = params.buildPage(Station.class);
        baseService.findStationPage(page, params);
        return page;
    }

    @Override
    public R<Boolean> handlerImport(List<Map<String, String>> list) {
        // 组织id 和 状态需要自行转义，可以参考UserController里面的用户导入如何转义
        List<Station> stationList = list.stream().map((map) -> {
            Station item = new Station();
            item.setDescribe(map.getOrDefault("描述", ""));
            item.setName(map.getOrDefault("名称", ""));
            item.setOrgId(Convert.toLong(map.getOrDefault("组织", "0")));
            item.setState(Convert.toBool(map.getOrDefault("状态", "false")));
            ArgumentAssert.notEmpty(item.getName(), "请填入岗位名称");
            return item;
        }).collect(Collectors.toList());

        return R.success(baseService.saveBatch(stationList));
    }

}
