package com.github.zuihou.authority.controller.core;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.core.StationPageDTO;
import com.github.zuihou.authority.dto.core.StationSaveDTO;
import com.github.zuihou.authority.dto.core.StationUpdateDTO;
import com.github.zuihou.authority.entity.core.Station;
import com.github.zuihou.authority.service.core.StationService;
import com.github.zuihou.base.R;
import com.github.zuihou.base.controller.SuperCacheController;
import com.github.zuihou.base.request.PageParams;
import com.github.zuihou.model.RemoteData;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
@Api(value = "Station", tags = "岗位")
public class StationController extends SuperCacheController<StationService, Long, Station, StationPageDTO, StationSaveDTO, StationUpdateDTO> {

    @Override
    public void query(PageParams<StationPageDTO> params, IPage<Station> page, Long defSize) {
        baseService.findStationPage(page, params.getModel());
    }

    /**
     * 调用方传递的参数类型是 Set<Serializable> ，但接收方必须指定为Long类型（实体的主键类型），否则在调用mp提供的方法时，会使得mysql出现类型隐式转换问题。
     * 问题如下： select * from org where id in ('100');
     * <p>
     * 强制转换成Long后，sql就能正常执行： select * from org where id in (100);
     *
     * <p>
     * 接口和实现类的类型不一致，但也能调用，归功于 SpingBoot 的自动转换功能
     * {@link com.github.zuihou.authority.api.StationApi#findStationByIds} 方法的实现类
     *
     * @param ids id
     * @return
     */
    @GetMapping("/findStationByIds")
    public Map<Serializable, Object> findStationByIds(@RequestParam("ids") Set<Serializable> ids) {
        return baseService.findStationByIds(ids);
    }

    /**
     * 调用方传递的参数类型是 Set<Serializable> ，但接收方必须指定为Long类型（实体的主键类型），否则在调用mp提供的方法时，会使得mysql出现类型隐式转换问题。
     * 问题如下： select * from org where id in ('100');
     * <p>
     * 强制转换成Long后，sql就能正常执行： select * from org where id in (100);
     *
     * <p>
     * 接口和实现类的类型不一致，但也能调用，归功于 SpingBoot 的自动转换功能
     * {@link com.github.zuihou.authority.api.StationApi#findStationNameByIds} 方法的实现类
     *
     * @param ids id
     * @return
     */
    @GetMapping("/findStationNameByIds")
    public Map<Serializable, Object> findStationNameByIds(@RequestParam("ids") Set<Serializable> ids) {
        return baseService.findStationNameByIds(ids);
    }

    @Override
    public R<Boolean> handlerImport(List<Map<String, String>> list) {
        List<Station> stationList = list.stream().map((map) -> {
            Station item = new Station();
            item.setDescribe(map.getOrDefault("描述", ""));
            item.setName(map.getOrDefault("名称", ""));
            item.setOrg(new RemoteData<>(Convert.toLong(map.getOrDefault("组织", ""))));
            item.setStatus(Convert.toBool(map.getOrDefault("状态", "")));
            return item;
        }).collect(Collectors.toList());

        return R.success(baseService.saveBatch(stationList));
    }


//    @Override
//    public R<Boolean> importExcel(MultipartFile simpleFile) throws Exception {
//        ImportParams params = new ImportParams();
//        params.setTitleRows(0);
//        params.setHeadRows(1);
//        params.setNeedVerify(true);
//
//        List<Map<String, String>> list = ExcelImportUtil.importExcel(simpleFile.getInputStream(), Map.class, params);
//
//        return success();
//    }
}
