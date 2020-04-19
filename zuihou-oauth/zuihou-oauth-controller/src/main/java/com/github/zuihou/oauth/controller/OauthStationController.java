package com.github.zuihou.oauth.controller;

import com.github.zuihou.authority.service.core.StationService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
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
public class OauthStationController {

    @Autowired
    private StationService stationService;

    /**
     * 调用方传递的参数类型是 Set<Serializable> ，但接收方必须指定为Long类型（实体的主键类型），否则在调用mp提供的方法时，会使得mysql出现类型隐式转换问题。
     * 问题如下： select * from org where id in ('100');
     * <p>
     * 强制转换成Long后，sql就能正常执行： select * from org where id in (100);
     *
     * <p>
     * 接口和实现类的类型不一致，但也能调用，归功于 SpingBoot 的自动转换功能
     * {@link com.github.zuihou.oauth.api.StationApi#findStationByIds} 方法的实现类
     *
     * @param ids id
     * @return
     */
    @GetMapping("/findStationByIds")
    public Map<Serializable, Object> findStationByIds(@RequestParam("ids") Set<Serializable> ids) {
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
     * {@link com.github.zuihou.oauth.api.StationApi#findStationNameByIds} 方法的实现类
     *
     * @param ids id
     * @return
     */
    @GetMapping("/findStationNameByIds")
    public Map<Serializable, Object> findStationNameByIds(@RequestParam("ids") Set<Serializable> ids) {
        return stationService.findStationNameByIds(ids);
    }

}
