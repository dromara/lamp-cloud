package com.tangyh.lamp.authority.controller.common;


import com.tangyh.basic.annotation.log.SysLog;
import com.tangyh.basic.annotation.security.PreAuth;
import com.tangyh.basic.base.R;
import com.tangyh.basic.base.controller.SuperCacheController;
import com.tangyh.basic.database.mybatis.conditions.Wraps;
import com.tangyh.basic.database.mybatis.conditions.query.LbqWrapper;
import com.tangyh.lamp.authority.dto.common.AreaPageQuery;
import com.tangyh.lamp.authority.dto.common.AreaSaveDTO;
import com.tangyh.lamp.authority.dto.common.AreaUpdateDTO;
import com.tangyh.lamp.authority.entity.common.Area;
import com.tangyh.lamp.authority.service.common.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 地区表
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/area")
@Api(value = "Area", tags = "地区表")
@PreAuth(replace = "authority:area:")
public class AreaController extends SuperCacheController<AreaService, Long, Area, AreaPageQuery, AreaSaveDTO, AreaUpdateDTO> {

    @ApiOperation(value = "检测地区编码是否重复", notes = "检测地区编码是否重复")
    @GetMapping("/check/{code}")
    @SysLog("检测地区编码是否重复")
    public R<Boolean> check(@RequestParam(required = false) Long id, @PathVariable String code) {
        int count = baseService.count(Wraps.<Area>lbQ().eq(Area::getCode, code).ne(Area::getId, id));
        return success(count > 0);
    }


    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        //TODO 重点测试递归删除
        return R.success(baseService.recursively(ids));
    }

    /**
     * 级联查询缓存中的地区
     *
     * @param parentId 父ID
     * @return 查询结果
     */
    @ApiOperation(value = "级联查询缓存中的地区", notes = "级联查询缓存中的地区")
    @GetMapping("/linkage")
    @SysLog("级联查询地区")
    public R<List<Area>> linkageQuery(@RequestParam(defaultValue = "0", required = false) Long parentId) {
        //TODO 想办法做缓存
        LbqWrapper<Area> query = Wraps.<Area>lbQ()
                .eq(Area::getParentId, parentId)
                .orderByAsc(Area::getSortValue);
        return success(baseService.list(query));
    }

}
