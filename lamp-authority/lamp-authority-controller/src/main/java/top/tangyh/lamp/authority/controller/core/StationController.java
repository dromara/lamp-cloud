package top.tangyh.lamp.authority.controller.core;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.security.PreAuth;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperCacheController;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.lamp.authority.dto.core.StationPageQuery;
import top.tangyh.lamp.authority.dto.core.StationSaveDTO;
import top.tangyh.lamp.authority.dto.core.StationUpdateDTO;
import top.tangyh.lamp.authority.entity.core.Station;
import top.tangyh.lamp.authority.service.core.StationService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static top.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_LONG;
import static top.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_STRING;
import static top.tangyh.lamp.common.constant.SwaggerConstants.PARAM_TYPE_QUERY;

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
@Api(value = "Station", tags = "岗位")
@PreAuth(replace = "authority:station:")
public class StationController extends SuperCacheController<StationService, Long, Station, StationPageQuery, StationSaveDTO, StationUpdateDTO> {

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "name", value = "名称", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "检测名称是否可用", notes = "检测名称是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam(required = false) Long id, @RequestParam String name) {
        return success(baseService.check(id, name));
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
