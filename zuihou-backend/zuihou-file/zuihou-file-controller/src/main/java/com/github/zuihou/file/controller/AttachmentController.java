package com.github.zuihou.file.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.file.dto.AttachmentDTO;
import com.github.zuihou.file.dto.AttachmentRemoveDTO;
import com.github.zuihou.file.dto.AttachmentResultDTO;
import com.github.zuihou.file.entity.Attachment;
import com.github.zuihou.file.enumeration.DataType;
import com.github.zuihou.file.service.AttachmentService;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.utils.BizAssert;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.github.zuihou.common.excode.ExceptionCode.BASE_VALID_PARAM;
import static com.github.zuihou.utils.BizAssert.assertNotEmpty;
import static com.github.zuihou.utils.BizAssert.assertNotNull;
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
@Api(value = "附件", description = "附件")
public class AttachmentController extends BaseController {

    /**
     * 业务类型判断符
     */
    private final static String TYPE_BIZ_ID = "bizId";
    @Autowired
    private AttachmentService attachmentService;

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
            @ApiImplicitParam(name = "appCode", value = "应用编码", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "文件id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "bizId", value = "业务id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "bizType", value = "业务类型", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "file", value = "附件", dataType = "MultipartFile", allowMultiple = true, required = true),
    })
    @PostMapping(value = "/upload")
    @SysLog("上传附件")
    public R<AttachmentDTO> upload(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "appCode", required = false) String appCode,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "bizId", required = false) String bizId,
            @RequestParam(value = "bizType", required = false) String bizType) throws Exception {
        assertNotNull(BASE_VALID_PARAM.build("请求中必须至少包含一个有效文件"), file);
        assertNotEmpty(BASE_VALID_PARAM.build("业务类型不能为空"), bizType);
        // 忽略路径字段,只处理文件类型
        if (file.isEmpty()) {
            return fail(BASE_VALID_PARAM.build("请求中必须至少包含一个有效文件"));
        }

        AttachmentDTO attachment = attachmentService.upload(file, appCode, id, bizType, bizId);

        return success(attachment);
    }

    @ApiOperation(value = "删除文件", notes = "删除文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids[]", value = "文件ids", dataType = "long", paramType = "query"),
    })
    @DeleteMapping(value = "")
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
        BizAssert.assertTrue(BASE_VALID_PARAM.build("业务类型不能为空"), !(ArrayUtils.isEmpty(bizTypes) && ArrayUtils.isEmpty(bizIds)));
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
    @ApiOperation(value = "下载一个文件或多个文件打包下载", notes = "根据附件id下载多个打包的附件")
    @GetMapping(value = "/download", produces = "application/octet-stream")
    @SysLog("下载附件")
    public void download(
            @ApiParam(name = "ids[]", value = "文件id 数组")
            @RequestParam(value = "ids[]") Long[] ids,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BizAssert.assertTrue(BASE_VALID_PARAM.build("附件id不能为空"), ArrayUtils.isNotEmpty(ids));
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
    @ApiOperation(value = "下载一个文件或多个文件打包下载", notes = "根据业务id下载一个文件或多个文件打包下载")
    @GetMapping(value = "/download/biz", produces = "application/octet-stream")
    @SysLog("根据业务类型下载附件")
    public void downloadByBiz(
            @RequestParam(value = "bizIds[]", required = false) String[] bizIds,
            @RequestParam(value = "bizTypes[]", required = false) String[] bizTypes,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BizAssert.assertTrue(BASE_VALID_PARAM.build("附件业务id和业务类型不能同时为空"),
                !(ArrayUtils.isEmpty(bizTypes) && ArrayUtils.isEmpty(bizIds)));
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
    @ApiOperation(value = "下载文件", notes = "根据文件的url下载文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "文件url", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "filename", value = "文件名", dataType = "string", paramType = "query"),
    })
    @GetMapping(value = "/download/url", produces = "application/octet-stream")
    @SysLog("根据文件连接下载文件")
    public void downloadUrl(@RequestParam(value = "url") String url, @RequestParam(value = "filename", required = false) String filename,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BizAssert.assertTrue(BASE_VALID_PARAM.build("附件下载地址不能为空"), StringUtils.isNotEmpty(url));
        log.info("name={}, url={}", filename, url);
        attachmentService.downloadByUrl(request, response, url, filename);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "bizType", value = "业务类型", dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "bizId", value = "业务id", dataType = "string", paramType = "path"),
    })
    @ApiOperation(value = "获取图片", notes = "根据业务类型和业务id在前端img标签中回显图片附件， 但存在多个附件时，默认显示第一个图片")
    @GetMapping(value = "/download/{bizType}/{bizId}", produces = "image/png")
    @SysLog("获取图片")
    public R<Boolean> findAttachmentByBizId(@PathVariable String bizType, @PathVariable String bizId,
                                            HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Attachment> list = attachmentService.list(Wrappers.<Attachment>lambdaQuery()
                .eq(Attachment::getBizType, bizType).eq(Attachment::getBizId, bizId)
                .orderByDesc(Attachment::getCreateTime)
        );
        if (!list.isEmpty() && DataType.IMAGE.eq(list.get(0).getDataType())) {
            // 设置响应的类型格式为图片格式
            response.setContentType("image/jpeg");
            // 禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            //实例生成验证码对象
            //向页面输出验证码图片
            attachmentService.download(request, response, new Long[]{list.get(0).getId()});
            return success();
        }
        return fail("附件不存在");
    }
}
