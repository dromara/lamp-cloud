package com.tangyh.lamp.file.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.tangyh.basic.annotation.log.SysLog;
import com.tangyh.basic.annotation.security.PreAuth;
import com.tangyh.basic.base.R;
import com.tangyh.basic.base.controller.DeleteController;
import com.tangyh.basic.base.controller.QueryController;
import com.tangyh.basic.base.controller.SuperSimpleController;
import com.tangyh.basic.base.request.PageParams;
import com.tangyh.basic.context.ContextUtil;
import com.tangyh.basic.utils.BizAssert;
import com.tangyh.lamp.file.dto.AttachmentDTO;
import com.tangyh.lamp.file.dto.AttachmentRemoveDTO;
import com.tangyh.lamp.file.dto.AttachmentResultDTO;
import com.tangyh.lamp.file.dto.FilePageReqDTO;
import com.tangyh.lamp.file.entity.Attachment;
import com.tangyh.lamp.file.service.AttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.tangyh.basic.exception.code.ExceptionCode.BASE_VALID_PARAM;
import static com.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_ARRAY;
import static com.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_BOOLEAN;
import static com.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_LONG;
import static com.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_MULTIPART_FILE;
import static com.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_STRING;
import static com.tangyh.lamp.common.constant.SwaggerConstants.PARAM_TYPE_QUERY;
import static java.util.stream.Collectors.groupingBy;

/**
 * <p>
 * 附件表 前端控制器
 * </p>
 *
 * @author zuihou
 * @since 2019-04-29
 */
@RestController
@RequestMapping("/attachment")
@Slf4j
@Api(value = "附件", tags = "附件")
@Validated
@SysLog(enabled = false)
@PreAuth(replace = "file:attachment:")
public class AttachmentController extends SuperSimpleController<AttachmentService, Attachment>
        implements QueryController<Attachment, Long, FilePageReqDTO>, DeleteController<Attachment, Long> {

    /**
     * 业务类型判断符
     */
    private static final String TYPE_BIZ_ID = "bizId";

    @Override
    public void query(PageParams<FilePageReqDTO> params, IPage<Attachment> page, Long defSize) {
        baseService.page(page, params.getModel());
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        return R.success(baseService.remove(ids));
    }

    /**
     * 上传文件
     */
    @ApiOperation(value = "附件上传", notes = "附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isSingle", value = "是否单文件", dataType = DATA_TYPE_BOOLEAN, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "id", value = "文件id", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "bizId", value = "业务id", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "bizType", value = "业务类型", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "file", value = "附件", dataType = DATA_TYPE_MULTIPART_FILE, allowMultiple = true, required = true),
    })
    @PostMapping(value = "/upload")
    @SysLog("上传附件")
    @PreAuth("hasAnyPermission('{}add')")
    public R<AttachmentDTO> upload(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "isSingle", required = false, defaultValue = "false") Boolean isSingle,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "bizId", required = false) String bizId,
            @RequestParam(value = "bizType", required = false) String bizType) {
        BizAssert.notEmpty(bizType, BASE_VALID_PARAM.build("业务类型不能为空"));
        // 忽略路径字段,只处理文件类型
        if (file.isEmpty()) {
            return R.fail(BASE_VALID_PARAM.build("请求中必须至少包含一个有效文件"));
        }
        String tenant = ContextUtil.getTenant();

        AttachmentDTO attachment = baseService.upload(file, tenant, id, bizType, bizId, isSingle);

        return R.success(attachment);
    }


    @ApiOperation(value = "根据业务类型或业务id删除文件", notes = "根据业务类型或业务id删除文件")
    @DeleteMapping(value = "/biz")
    @SysLog("根据业务类型删除附件")
    @PreAuth("hasAnyPermission('{}delete')")
    public R<Boolean> removeByBizIdAndBizType(@RequestBody AttachmentRemoveDTO dto) {
        return R.success(baseService.removeByBizIdAndBizType(dto.getBizId(), dto.getBizType()));
    }

    @ApiOperation(value = "查询附件", notes = "查询附件")
    @ApiResponses(
            @ApiResponse(code = 60103, message = "文件id为空")
    )
    @GetMapping
    @SysLog("根据业务类型查询附件")
    @PreAuth("hasAnyPermission('{}view')")
    public R<List<AttachmentResultDTO>> findAttachment(@RequestParam(value = "bizTypes", required = false) String[] bizTypes,
                                                       @RequestParam(value = "bizIds", required = false) String[] bizIds) {
        //不能同时为空
        BizAssert.isTrue(!(ArrayUtils.isEmpty(bizTypes) && ArrayUtils.isEmpty(bizIds)), BASE_VALID_PARAM.build("业务类型不能为空"));
        return R.success(baseService.find(bizTypes, bizIds));
    }

    @ApiOperation(value = "根据业务类型或者业务id查询附件", notes = "根据业务类型或者业务id查询附件")
    @GetMapping(value = "/biz/{type}")
    @SysLog("根据业务类型分组查询附件")
    @PreAuth("hasAnyPermission('{}view')")
    public R<Map<String, List<Attachment>>> findAttachmentByBiz(@PathVariable String type, @RequestParam("biz[]") String[] biz) {
        SFunction<Attachment, String> sf = Attachment::getBizType;
        if (TYPE_BIZ_ID.equalsIgnoreCase(type)) {
            sf = Attachment::getBizId;
        }
        List<Attachment> list = baseService.list(Wrappers.<Attachment>lambdaQuery().in(sf, biz).orderByAsc(Attachment::getCreateTime));
        if (list.isEmpty()) {
            return R.success(Collections.emptyMap());
        }
        if (TYPE_BIZ_ID.equalsIgnoreCase(type)) {
            return R.success(list.stream().collect(groupingBy(Attachment::getBizType)));
        } else {
            return R.success(list.stream().collect(groupingBy(Attachment::getBizId)));
        }
    }


    /**
     * 下载一个文件或多个文件打包下载
     *
     * @param ids 文件id
     */
    @ApiOperation(value = "根据文件id打包下载", notes = "根据附件id下载多个打包的附件")
    @GetMapping(value = "/download", produces = "application/octet-stream")
    @SysLog("下载附件")
    @PreAuth("hasAnyPermission('{}download')")
    public void download(
            @ApiParam(name = "ids[]", value = "文件id 数组")
            @RequestParam(value = "ids[]") Long[] ids,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BizAssert.isTrue(ArrayUtils.isNotEmpty(ids), BASE_VALID_PARAM.build("附件id不能为空"));
        baseService.download(request, response, ids);
    }

    /**
     * 根据业务类型或者业务id其中之一，或者2个同时打包下载文件
     *
     * @param bizIds   业务id
     * @param bizTypes 业务类型
     * @author zuihou
     * @date 2019-05-12 21:23
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizIds[]", value = "业务id数组", dataType = DATA_TYPE_ARRAY, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "bizTypes[]", value = "业务类型数组", dataType = DATA_TYPE_ARRAY, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "根据业务类型/业务id打包下载", notes = "根据业务id下载一个文件或多个文件打包下载")
    @GetMapping(value = "/download/biz", produces = "application/octet-stream")
    @SysLog("根据业务类型下载附件")
    @PreAuth("hasAnyPermission('{}download')")
    public void downloadByBiz(
            @RequestParam(value = "bizIds[]", required = false) String[] bizIds,
            @RequestParam(value = "bizTypes[]", required = false) String[] bizTypes,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BizAssert.isTrue(!(ArrayUtils.isEmpty(bizTypes) && ArrayUtils.isEmpty(bizIds)), BASE_VALID_PARAM.build("附件业务id和业务类型不能同时为空"));
        baseService.downloadByBiz(request, response, bizTypes, bizIds);
    }

    /**
     * 根据下载地址下载文件
     *
     * @param url      文件链接
     * @param filename 文件名称
     * @author zuihou
     * @date 2019-05-12 21:24
     */
    @ApiOperation(value = "根据url下载文件(不推荐)", notes = "根据文件的url下载文件(不推荐使用，若要根据url下载，请执行通过nginx)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "文件url", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "filename", value = "文件名", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
    })
    @GetMapping(value = "/download/url", produces = "application/octet-stream")
    @SysLog("根据文件连接下载文件")
    @PreAuth("hasAnyPermission('{}download')")
    public void downloadUrl(@RequestParam(value = "url") String url, @RequestParam(value = "filename", required = false) String filename,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BizAssert.isTrue(StrUtil.isNotEmpty(url), BASE_VALID_PARAM.build("附件下载地址不能为空"));
        log.info("name={}, url={}", filename, url);
        baseService.downloadByUrl(request, response, url, filename);
    }

}
