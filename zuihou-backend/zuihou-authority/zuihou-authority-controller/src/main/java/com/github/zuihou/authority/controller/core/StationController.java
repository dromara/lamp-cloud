package com.github.zuihou.authority.controller.core;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.core.StationPageDTO;
import com.github.zuihou.authority.dto.core.StationSaveDTO;
import com.github.zuihou.authority.dto.core.StationUpdateDTO;
import com.github.zuihou.authority.entity.core.Station;
import com.github.zuihou.authority.service.core.StationService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
public class StationController extends BaseController {

    @Autowired
    private StationService stationService;


    /**
     * 分页查询岗位
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询岗位", notes = "分页查询岗位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询岗位")
    public R<IPage<Station>> page(StationPageDTO data) {
        IPage<Station> page = getPage();
        stationService.findStationPage(page, data);
        return success(page);
    }

    /**
     * 查询岗位
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询岗位", notes = "查询岗位")
    @GetMapping("/{id}")
    @SysLog("查询岗位")
    public R<Station> get(@PathVariable Long id) {
        return success(stationService.getById(id));
    }

    /**
     * 新增岗位
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增岗位", notes = "新增岗位不为空的字段")
    @PostMapping
    @SysLog("新增岗位")
    public R<Station> save(@RequestBody @Validated StationSaveDTO data) {
        Station station = BeanUtil.toBean(data, Station.class);
        stationService.save(station);
        return success(station);
    }

    /**
     * 修改岗位
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改岗位", notes = "修改岗位不为空的字段")
    @PutMapping
    @SysLog("修改岗位")
    public R<Station> update(@RequestBody @Validated(SuperEntity.Update.class) StationUpdateDTO data) {
        Station station = BeanUtil.toBean(data, Station.class);
        stationService.updateById(station);
        return success(station);
    }

    /**
     * 删除岗位
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除岗位", notes = "根据id物理删除岗位")
    @SysLog("删除岗位")
    @DeleteMapping
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        stationService.removeByIds(ids);
        return success();
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
     * @param codes id
     * @return
     */
    @GetMapping("/findStationByIds")
    public Map<Serializable, Object> findStationByIds(@RequestParam("ids") Set<Long> ids) {
        return stationService.findStationByIds(ids);
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
     * @param codes id
     * @return
     */
    @GetMapping("/findStationNameByIds")
    public Map<Serializable, Object> findStationNameByIds(@RequestParam("ids") Set<Long> ids) {
        return stationService.findStationNameByIds(ids);
    }

}
