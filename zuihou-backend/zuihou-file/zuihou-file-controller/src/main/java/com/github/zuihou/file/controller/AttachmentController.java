package com.github.zuihou.file.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.file.dto.AttachmentDTO;
import com.github.zuihou.file.dto.AttachmentRemoveDTO;
import com.github.zuihou.file.dto.AttachmentResultDTO;
import com.github.zuihou.file.dto.FilePageReqDTO;
import com.github.zuihou.file.entity.Attachment;
import com.github.zuihou.file.service.AttachmentService;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.utils.BizAssert;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.github.zuihou.exception.code.ExceptionCode.BASE_VALID_PARAM;
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
public class AttachmentController extends BaseController {

    /**
     * 业务类型判断符
     */
    private final static String TYPE_BIZ_ID = "bizId";
    @Autowired
    private AttachmentService attachmentService;

    /**
     * 分页查询附件
     *
     * @author zuihou
     * @date 2019-05-06
     */
    @ApiOperation(value = "分页查询附件", notes = "分页查询附件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping(value = "/page")
    @SysLog("分页查询附件")
    public R<IPage<Attachment>> page(FilePageReqDTO data) {
        IPage<Attachment> page = getPage();
        attachmentService.page(page, data);
        return success(page);
    }


    /**
     * 上传文件
     *
     * @param
     * @return
     * @author zuihou
     * @date 2019-05-06 16:28
     */
    @ApiOperation(value = "附件上传", notes = "附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isSingle", value = "是否单文件", dataType = "boolean", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "文件id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "bizId", value = "业务id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "bizType", value = "业务类型", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "file", value = "附件", dataType = "MultipartFile", allowMultiple = true, required = true),
    })
    @PostMapping(value = "/upload")
    @SysLog("上传附件")
    public R<AttachmentDTO> upload(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "isSingle", required = false, defaultValue = "false") Boolean isSingle,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "bizId", required = false) String bizId,
            @RequestParam(value = "bizType", required = false) String bizType) {
        BizAssert.notEmpty(bizType, BASE_VALID_PARAM.build("业务类型不能为空"));
        // 忽略路径字段,只处理文件类型
        if (file.isEmpty()) {
            return fail(BASE_VALID_PARAM.build("请求中必须至少包含一个有效文件"));
        }
        String tenant = getTenant();

        AttachmentDTO attachment = attachmentService.upload(file, tenant, id, bizType, bizId, isSingle);

        return success(attachment);
    }

    @ApiOperation(value = "删除文件", notes = "删除文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids[]", value = "文件ids", dataType = "array", paramType = "query"),
    })
    @DeleteMapping
    @SysLog("删除附件")
    public R<Boolean> remove(@RequestParam(value = "ids[]") Long[] ids) {
        attachmentService.remove(ids);
        return success(true);
    }

    @ApiOperation(value = "根据业务类型或业务id删除文件", notes = "根据业务类型或业务id删除文件")
    @DeleteMapping(value = "/biz")
    @SysLog("根据业务类型删除附件")
    public R<Boolean> removeByBizIdAndBizType(@RequestBody AttachmentRemoveDTO dto) {
        attachmentService.removeByBizIdAndBizType(dto.getBizId(), dto.getBizType());
        return success(true);
    }

    @ApiOperation(value = "查询附件", notes = "查询附件")
    @ApiResponses(
            @ApiResponse(code = 60103, message = "文件id为空")
    )
    @GetMapping
    @SysLog("根据业务类型查询附件")
    public R<List<AttachmentResultDTO>> findAttachment(@RequestParam(value = "bizTypes", required = false) String[] bizTypes,
                                                       @RequestParam(value = "bizIds", required = false) String[] bizIds) {
        //不能同时为空
        BizAssert.isTrue(!(ArrayUtils.isEmpty(bizTypes) && ArrayUtils.isEmpty(bizIds)), BASE_VALID_PARAM.build("业务类型不能为空"));
        return success(attachmentService.find(bizTypes, bizIds));
    }

    @ApiOperation(value = "根据业务类型或者业务id查询附件", notes = "根据业务类型或者业务id查询附件")
    @GetMapping(value = "/{type}")
    @SysLog("根据业务类型分组查询附件")
    public R<Map<String, List<Attachment>>> findAttachmentByBiz(@PathVariable String type, @RequestParam("biz[]") String[] biz) {
        SFunction<Attachment, String> sf = Attachment::getBizType;
        if (TYPE_BIZ_ID.equalsIgnoreCase(type)) {
            sf = Attachment::getBizId;
        }
        List<Attachment> list = attachmentService.list(Wrappers.<Attachment>lambdaQuery().in(sf, biz).orderByAsc(Attachment::getCreateTime));
        if (list.isEmpty()) {
            return success(MapUtils.EMPTY_MAP);
        }
        if (TYPE_BIZ_ID.equalsIgnoreCase(type)) {
            return success(list.stream().collect(groupingBy(Attachment::getBizType)));
        } else {
            return success(list.stream().collect(groupingBy(Attachment::getBizId)));
        }
    }


    /**
     * 下载一个文件或多个文件打包下载
     *
     * @param ids      文件id
     * @param response
     * @throws Exception
     */
    @ApiOperation(value = "根据文件id打包下载", notes = "根据附件id下载多个打包的附件")
    @GetMapping(value = "/download", produces = "application/octet-stream")
    @SysLog("下载附件")
    public void download(
            @ApiParam(name = "ids[]", value = "文件id 数组")
            @RequestParam(value = "ids[]") Long[] ids,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BizAssert.isTrue(ArrayUtils.isNotEmpty(ids), BASE_VALID_PARAM.build("附件id不能为空"));
        attachmentService.download(request, response, ids);
    }

    /**
     * 根据业务类型或者业务id其中之一，或者2个同时打包下载文件
     *
     * @param bizIds   业务id
     * @param bizTypes 业务类型
     * @return
     * @author zuihou
     * @date 2019-05-12 21:23
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizIds[]", value = "业务id数组", dataType = "array", paramType = "query"),
            @ApiImplicitParam(name = "bizTypes[]", value = "业务类型数组", dataType = "array", paramType = "query"),
    })
    @ApiOperation(value = "根据业务类型/业务id打包下载", notes = "根据业务id下载一个文件或多个文件打包下载")
    @GetMapping(value = "/download/biz", produces = "application/octet-stream")
    @SysLog("根据业务类型下载附件")
    public void downloadByBiz(
            @RequestParam(value = "bizIds[]", required = false) String[] bizIds,
            @RequestParam(value = "bizTypes[]", required = false) String[] bizTypes,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BizAssert.isTrue(!(ArrayUtils.isEmpty(bizTypes) && ArrayUtils.isEmpty(bizIds)), BASE_VALID_PARAM.build("附件业务id和业务类型不能同时为空"));
        attachmentService.downloadByBiz(request, response, bizTypes, bizIds);
    }

    /**
     * 根据下载地址下载文件
     *
     * @param url      文件链接
     * @param filename 文件名称
     * @return
     * @author zuihou
     * @date 2019-05-12 21:24
     */
    @ApiOperation(value = "根据url下载文件(不推荐)", notes = "根据文件的url下载文件(不推荐使用，若要根据url下载，请执行通过nginx)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "文件url", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "filename", value = "文件名", dataType = "string", paramType = "query"),
    })
    @GetMapping(value = "/download/url", produces = "application/octet-stream")
    @SysLog("根据文件连接下载文件")
    public void downloadUrl(@RequestParam(value = "url") String url, @RequestParam(value = "filename", required = false) String filename,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BizAssert.isTrue(StringUtils.isNotEmpty(url), BASE_VALID_PARAM.build("附件下载地址不能为空"));
        log.info("name={}, url={}", filename, url);
        attachmentService.downloadByUrl(request, response, url, filename);
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "bizType", value = "业务类型", dataType = "string", paramType = "path"),
//            @ApiImplicitParam(name = "bizId", value = "业务id", dataType = "string", paramType = "path"),
//    })
//    @ApiOperation(value = "获取图片", notes = "根据业务类型和业务id在前端img标签中回显图片附件， 但存在多个附件时，默认显示第一个图片")
//    @GetMapping(value = "/download/{bizType}/{bizId}", produces = "image/png")
//    @SysLog("获取图片")
//    public void findAttachmentByBizId(@PathVariable String bizType, @PathVariable String bizId,
//                                      HttpServletRequest request, HttpServletResponse response) throws Exception {
//        List<Attachment> list = attachmentService.list(Wrappers.<Attachment>lambdaQuery()
//                .eq(Attachment::getBizType, bizType).eq(Attachment::getBizId, bizId)
//                .orderByDesc(Attachment::getCreateTime)
//        );
//        if (!list.isEmpty() && DataType.IMAGE.eq(list.get(0).getDataType())) {
//            // 设置响应的类型格式为图片格式
//            response.setContentType("image/jpeg");
//            // 禁止图像缓存。
//            response.setHeader("Pragma", "no-cache");
//            response.setHeader("Cache-Control", "no-cache");
//            response.setDateHeader("Expires", 0);
//            //实例生成验证码对象
//            //向页面输出验证码图片
//            attachmentService.download(request, response, new Long[]{list.get(0).getId()});
//        }
//    }
}
