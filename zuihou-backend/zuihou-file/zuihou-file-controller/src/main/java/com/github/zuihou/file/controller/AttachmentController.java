package com.github.zuihou.file.controller;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.Result;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.file.dto.AttachmentDTO;
import com.github.zuihou.file.entity.Attachment;
import com.github.zuihou.file.service.AttachmentService;
import com.github.zuihou.mybatis.conditions.Wraps;
import com.github.zuihou.mybatis.conditions.query.LbqWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 附件
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/attachment")
@Api(value = "Attachment", description = "附件")
public class AttachmentController extends BaseController {

    @Autowired
    private AttachmentService attachmentService;

    /**
     * 分页查询附件
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询附件", notes = "分页查询附件")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<Attachment>> page(@Valid AttachmentDTO data) {
        IPage<Attachment> page = getPage();
        // 构建查询条件
        LbqWrapper<Attachment> query = Wraps.lbQ();
        attachmentService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询附件
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询附件", notes = "查询附件")
    @GetMapping("/{id}")
    public Result<Attachment> get(@PathVariable Long id) {
        return success(attachmentService.getById(id));
    }

    /**
     * 保存附件
     *
     * @param attachment 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存附件", notes = "保存附件不为空的字段")
    @PostMapping
    public Result<Attachment> save(@RequestBody @Valid Attachment attachment) {
        attachmentService.save(attachment);
        return success(attachment);
    }

    /**
     * 修改附件
     *
     * @param attachment 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改附件", notes = "修改附件不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public Result<Attachment> update(@RequestBody @Valid Attachment attachment) {
        attachmentService.updateById(attachment);
        return success(attachment);
    }

    /**
     * 删除附件
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除附件", notes = "根据id物理删除附件")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        attachmentService.removeById(id);
        return success(true);
    }

}
