package com.tangyh.lamp.file.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tangyh.basic.annotation.log.SysLog;
import com.tangyh.basic.annotation.security.PreAuth;
import com.tangyh.basic.base.R;
import com.tangyh.basic.base.controller.DeleteController;
import com.tangyh.basic.base.controller.QueryController;
import com.tangyh.basic.base.controller.SuperSimpleController;
import com.tangyh.basic.base.request.PageParams;
import com.tangyh.basic.utils.BizAssert;
import com.tangyh.lamp.file.dto.AttachmentGetVO;
import com.tangyh.lamp.file.dto.AttachmentRemoveDTO;
import com.tangyh.lamp.file.dto.AttachmentResultDTO;
import com.tangyh.lamp.file.dto.AttachmentUploadVO;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.tangyh.basic.exception.code.ExceptionCode.BASE_VALID_PARAM;
import static com.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_MULTIPART_FILE;
import static com.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_STRING;
import static com.tangyh.lamp.common.constant.SwaggerConstants.PARAM_TYPE_QUERY;

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

    @Override
    public IPage<Attachment> query(PageParams<FilePageReqDTO> params) {
        IPage<Attachment> page = params.buildPage();
        baseService.page(page, params.getModel());
        return page;
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
            @ApiImplicitParam(name = "file", value = "附件", dataType = DATA_TYPE_MULTIPART_FILE, allowMultiple = true, required = true),
    })
    @PostMapping(value = "/upload")
    @SysLog("上传附件")
    @PreAuth("hasAnyPermission('{}add')")
    public R<Attachment> upload(@RequestParam(value = "file") MultipartFile file, @Validated AttachmentUploadVO attachmentVO) {
        // 忽略路径字段,只处理文件类型
        if (file.isEmpty()) {
            return R.fail(BASE_VALID_PARAM.build("请求中必须至少包含一个有效文件"));
        }
        return R.success(baseService.upload(file, attachmentVO));
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizIds", value = "业务id", dataType = DATA_TYPE_STRING, allowMultiple = true, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "bizTypes", value = "业务类型", dataType = DATA_TYPE_STRING, allowMultiple = true, paramType = PARAM_TYPE_QUERY),
    })
    @PreAuth("hasAnyPermission('{}view')")
    public R<List<AttachmentResultDTO>> findAttachment(@RequestParam(value = "bizTypes", required = false) String[] bizTypes,
                                                       @RequestParam(value = "bizIds", required = false) String[] bizIds) {
        //不能同时为空
        BizAssert.isTrue(!(ArrayUtils.isEmpty(bizTypes) && ArrayUtils.isEmpty(bizIds)), BASE_VALID_PARAM.build("业务类型不能为空"));
        return R.success(baseService.find(bizTypes, bizIds));
    }

    /**
     * 下载一个文件或多个文件打包下载
     *
     * @param ids 文件id
     */
    @ApiOperation(value = "根据文件id打包下载", notes = "根据附件id下载多个打包的附件")
    @PostMapping(value = "/downloadByIds", produces = "application/octet-stream")
    @SysLog("下载附件")
    @PreAuth("hasAnyPermission('{}download')")
    public void download(@RequestBody Long[] ids,
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
            @ApiImplicitParam(name = "bizIds[]", value = "业务id数组", dataType = DATA_TYPE_STRING, allowMultiple = true, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "bizTypes[]", value = "业务类型数组", dataType = DATA_TYPE_STRING, allowMultiple = true, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "根据业务类型/业务id打包下载", notes = "根据业务id下载一个文件或多个文件打包下载")
    @GetMapping(value = "/downloadByBiz", produces = "application/octet-stream")
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
    @GetMapping(value = "/downloadByUrl", produces = "application/octet-stream")
    @SysLog("根据文件连接下载文件")
    @PreAuth("hasAnyPermission('{}download')")
    public void downloadUrl(@RequestParam(value = "url") String url, @RequestParam(value = "filename", required = false) String filename,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BizAssert.isTrue(StrUtil.isNotEmpty(url), BASE_VALID_PARAM.build("附件下载地址不能为空"));
        log.info("name={}, url={}", filename, url);
        baseService.downloadByUrl(request, response, url, filename);
    }


    /**
     * 根据下载地址下载文件
     *
     * @param path  文件相对路径
     * @param group 桶
     * @author zuihou
     * @date 2019-05-12 21:24
     */
    @ApiOperation(value = "根据文件path下载文件", notes = "根据文件path下载文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "文件相对路径", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY, required = true),
            @ApiImplicitParam(name = "group", value = "桶", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
    })
    @GetMapping(value = "/downloadByPath", produces = "application/octet-stream")
    @SysLog("根据文件path下载文件")
    @PreAuth("hasAnyPermission('{}download')")
    public void downloadByPath(
            @RequestParam(value = "group", required = false) String group,
            @RequestParam(value = "path") String path,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        baseService.downloadByPath(request, response, group, path);
    }

    /**
     * 根据文件相对路径，获取访问路径
     *
     * @param paths 文件路径
     */
    @ApiOperation(value = "批量根据文件相对路径，获取访问路径", notes = "批量根据文件相对路径，获取访问路径")
    @PostMapping(value = "/getUrls")
    public R<List<String>> getUrls(@RequestBody List<AttachmentGetVO> paths) {
        return R.success(baseService.getUrls(paths, 172800));
    }

    /**
     * 根据文件相对路径，获取访问路径
     *
     * @param path   文件路径
     * @param expiry 有效期
     */
    @ApiOperation(value = "根据文件相对路径，获取访问路径", notes = "根据文件相对路径，获取访问路径")
    @GetMapping(value = "/getUrl")
    public R<String> getUrl(
            @ApiParam(name = "group", value = "group")
            @RequestParam(value = "group", required = false) String group,
            @ApiParam(name = "path", value = "文件路径")
            @RequestParam(value = "path") String path,
            @ApiParam(name = "expiry", value = "过期时间")
            @RequestParam(value = "expiry", defaultValue = "172800") Integer expiry) {
        return R.success(baseService.getUrl(group, path, expiry));
    }
}
