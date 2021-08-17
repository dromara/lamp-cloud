package top.tangyh.lamp.file.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.tangyh.basic.annotation.log.SysLog;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.DeleteController;
import top.tangyh.basic.base.controller.QueryController;
import top.tangyh.basic.base.controller.SuperSimpleController;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.lamp.file.entity.File;
import top.tangyh.lamp.file.service.FileService;
import top.tangyh.lamp.file.vo.param.FileParamVO;
import top.tangyh.lamp.file.vo.param.FileUploadVO;
import top.tangyh.lamp.file.vo.result.FileResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static top.tangyh.basic.exception.code.ExceptionCode.BASE_VALID_PARAM;
import static top.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_MULTIPART_FILE;

/**
 * <p>
 * 前端控制器
 * 增量文件上传日志
 * </p>
 *
 * @author tangyh
 * @date 2021-06-30
 * @create [2021-06-30] [tangyh] [初始创建]
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/file")
@Api(value = "FileFileController", tags = "文件实时上传")
public class FileController extends SuperSimpleController<FileService, File>
        implements QueryController<File, Long, FileParamVO>, DeleteController<File, Long> {

    /**
     * 上传文件
     */
    @ApiOperation(value = "附件上传", notes = "附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "附件", dataType = DATA_TYPE_MULTIPART_FILE, allowMultiple = true, required = true),
    })
    @PostMapping(value = "/anyone/upload")
    @SysLog("上传附件")
    public R<FileResultVO> upload(@RequestParam(value = "file") MultipartFile file,
                                  @Validated FileUploadVO attachmentVO) {
        // 忽略路径字段,只处理文件类型
        if (file.isEmpty()) {
            return R.validFail(BASE_VALID_PARAM.build("请上传有效文件"));
        }
        return R.success(baseService.upload(file, attachmentVO));
    }


    /**
     * 根据文件相对路径，获取访问路径
     *
     * @param paths 文件路径
     */
    @ApiOperation(value = "批量根据文件相对路径，获取文件临时的访问路径", notes = "批量根据文件相对路径，获取文件临时的访问路径")
    @PostMapping(value = "/anyone/findUrlByPath")
    @SysLog("批量根据文件相对路径，获取文件临时的访问路径")
    public R<Map<String, String>> findUrlByPath(@RequestBody List<String> paths) {
        return R.success(baseService.findUrlByPath(paths));
    }

    /**
     * 根据文件id，获取访问路径
     *
     * @param ids 文件id
     */
    @ApiOperation(value = "根据文件id，获取文件临时的访问路径", notes = "根据文件id，获取文件临时的访问路径")
    @PostMapping(value = "/anyone/findUrlById")
    @SysLog("根据文件id，获取文件临时的访问路径")
    public R<Map<Long, String>> findUrlById(@RequestBody List<Long> ids) {
        return R.success(baseService.findUrlById(ids));
    }

    /**
     * 下载一个文件或多个文件打包下载
     *
     * @param ids 文件id
     */
    @ApiOperation(value = "根据文件id打包下载", notes = "根据附件id下载多个打包的附件")
    @PostMapping(value = "/download", produces = "application/octet-stream")
    @SysLog("下载附件")
    public void download(@RequestBody List<Long> ids, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArgumentAssert.notEmpty(ids, "请选择至少一个附件");
        baseService.download(request, response, ids);
    }

}
