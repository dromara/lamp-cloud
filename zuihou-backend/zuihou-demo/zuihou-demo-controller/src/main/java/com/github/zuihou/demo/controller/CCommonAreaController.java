package com.github.zuihou.demo.controller;


import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.demo.dao.CCommonAreaMapper;
import com.github.zuihou.demo.dto.CCommonAreaSaveDTO;
import com.github.zuihou.demo.dto.CCommonAreaUpdateDTO;
import com.github.zuihou.demo.entity.CCommonArea;
import com.github.zuihou.demo.service.CCommonAreaService;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.utils.BeanPlusUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * 地区表
 * </p>
 *
 * @author zuihou
 * @date 2019-08-20
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/cCommonArea")
@Api(value = "CCommonArea", tags = "地区表")
public class CCommonAreaController extends BaseController {

    @Autowired
    private CCommonAreaService cCommonAreaService;
    @Autowired
    private CCommonAreaMapper cCommonAreaMapper;

    /**
     * 分页查询地区表
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询地区表", notes = "分页查询地区表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询地区表")
    public R<IPage<CCommonArea>> page(CCommonArea data) {
        IPage<CCommonArea> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<CCommonArea> query = Wraps.lbQ(data);
        cCommonAreaService.page(page, query);
        return success(page);
    }

    /**
     * 查询地区表
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询地区表", notes = "查询地区表")
    @GetMapping("/{id}")
    @SysLog("查询地区表")
    public R<CCommonArea> get(@PathVariable Long id) {
        return success(cCommonAreaService.getById(id));
    }

    /**
     * 新增地区表
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增地区表", notes = "新增地区表不为空的字段")
    @PostMapping
    @SysLog("新增地区表")
    public R<CCommonArea> save(@RequestBody @Validated CCommonAreaSaveDTO data) {
        CCommonArea cCommonArea = BeanPlusUtil.toBean(data, CCommonArea.class);
        cCommonAreaService.save(cCommonArea);
//        int i = 1/0;
        return success(cCommonArea);
    }

    /**
     * 修改地区表
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改地区表", notes = "修改地区表不为空的字段")
    @PutMapping
    @SysLog("修改地区表")
    public R<CCommonArea> update(@RequestBody @Validated(SuperEntity.Update.class) CCommonAreaUpdateDTO data) {
        CCommonArea cCommonArea = BeanPlusUtil.toBean(data, CCommonArea.class);
        cCommonAreaService.updateById(cCommonArea);
        return success(cCommonArea);
    }

    /**
     * 删除地区表
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除地区表", notes = "根据id物理删除地区表")
    @DeleteMapping(value = "/{id}")
    @SysLog("删除地区表")
    public R<Boolean> delete(@PathVariable Long id) {
        cCommonAreaService.removeById(id);
        return success(true);
    }

    /**
     * 查询地区表
     *
     * @param id 主键id
     * @return 查询结果
     */
    @GetMapping("/get5")
    public R<Object> get5(@RequestParam Long id, @RequestParam(required = false, defaultValue = "200") Integer count) {
        for (int j = 0; j < count; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                CCommonArea join = cCommonAreaMapper.selectById(id + i + j + RandomUtil.randomLong());
            }
            long end = System.currentTimeMillis();
            log.info("跨库查询耗时={}", (end - start));
        }
        return success("跨库 耗时={}");
    }


    @GetMapping(value = "/get")
    public R<Object> get2(@RequestParam Long id, @RequestParam(required = false, defaultValue = "200") Integer count) {
        for (int j = 0; j < count; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                CCommonArea join = cCommonAreaMapper.getXxx(id + i + j);
            }
            long end = System.currentTimeMillis();
            log.info("不跨库查询耗时={}", (end - start));
        }
        return success("不跨库 耗时={}");
    }

    @GetMapping(value = "/join")
    public R<Object> join(@RequestParam Long id, @RequestParam(required = false, defaultValue = "200") Integer count) {
        for (int j = 0; j < count; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                CCommonArea join = cCommonAreaMapper.getJoin(id + i + j);
            }
            long end = System.currentTimeMillis();
            log.info("跨库join耗时={}", (end - start));
        }
        return success("跨库join耗时={}");
    }

    @GetMapping(value = "/joinNo")
    public R<Object> joinNo(@RequestParam Long id, @RequestParam(required = false, defaultValue = "200") Integer count) {
        for (int j = 0; j < count; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                CCommonArea join = cCommonAreaMapper.getJoinNo(id + i + j);
            }
            long end = System.currentTimeMillis();
            log.info("不跨库join耗时={}", (end - start));
        }
        return success("不跨库join耗时={}");
    }

    @GetMapping(value = "/update2")
    public R<Object> update2(@RequestParam Long id, @RequestParam(required = false, defaultValue = "200") Integer count) {
        for (int j = 0; j < count; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                cCommonAreaMapper.updateTest2(id + i + j);
            }
            long end = System.currentTimeMillis();
            log.info("跨库修改耗时={}", (end - start));
        }
        return success("跨库修改耗时={}");
    }

    @GetMapping(value = "/update3")
    public R<Object> update3(@RequestParam Long id, @RequestParam(required = false, defaultValue = "200") Integer count) {
        for (int j = 0; j < count; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                cCommonAreaMapper.updateTest3(id + i + j);
            }
            long end = System.currentTimeMillis();
            log.info("不跨库修改耗时={}", (end - start));
        }
        return success("不跨库修改耗时={}");
    }

    @PostMapping("/save")
    public R<CCommonArea> save2(@RequestBody @Validated CCommonAreaSaveDTO data) {
        CCommonArea cCommonArea = BeanPlusUtil.toBean(data, CCommonArea.class);
        cCommonAreaService.save(cCommonArea);
        cCommonAreaMapper.save(cCommonArea);
        int i = 1 / 0;
        return success(cCommonArea);
    }

}
