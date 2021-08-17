package top.tangyh.lamp.file.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;
import top.tangyh.basic.annotation.log.SysLog;
import top.tangyh.basic.base.R;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.lamp.file.dto.chunk.FileChunkCheckDTO;
import top.tangyh.lamp.file.dto.chunk.FileChunksMergeDTO;
import top.tangyh.lamp.file.dto.chunk.FileUploadDTO;
import top.tangyh.lamp.file.entity.File;
import top.tangyh.lamp.file.manager.WebUploader;
import top.tangyh.lamp.file.properties.FileServerProperties;
import top.tangyh.lamp.file.service.FileService;
import top.tangyh.lamp.file.strategy.FileContext;
import top.tangyh.lamp.file.utils.FileTypeUtil;
import top.tangyh.lamp.file.vo.param.FileUploadVO;

import java.nio.file.Paths;

/**
 * 注意：该类下的所有方法均需要webuploder.js插件进行配合使用。
 * md5
 *
 * @author zuihou
 * @date 2018/08/24
 */
@RestController
@Slf4j
@RequestMapping("/chunk")
@Api(value = "文件续传+秒传", tags = "文件续传+秒传功能，所有方法均需要webuploder.js插件进行配合使用， 且4个方法需要配合使用，单核接口没有意义")
@RequiredArgsConstructor
@ApiIgnore
@Deprecated
public class FileChunkController {
    private final FileServerProperties fileProperties;
    private final FileContext fileContext;
    private final FileService fileService;
    private final WebUploader wu;


    /**
     * 采用md5 上传前的验证
     *
     * @param md5 文件md5
     */
    @ApiOperation(value = "秒传接口，上传文件前先验证， 存在则启动秒传", notes = "前端通过webUploader获取文件md5，上传前的验证")
    @RequestMapping(value = "/md5", method = RequestMethod.POST)
    @ResponseBody
    public R<Boolean> saveMd5Check(@RequestParam(name = "md5") String md5) {
        Long accountId = ContextUtil.getUserId();
        File file = fileContext.md5Check(md5, accountId);
        return R.success(file != null);
    }

    /**
     * 检查分片存不存在
     */
    @ApiOperation(value = "续传接口，检查每个分片存不存在", notes = "断点续传功能检查分片是否存在， 已存在的分片无需重复上传， 达到续传效果")
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public R<Boolean> chunkCheck(@RequestBody FileChunkCheckDTO info) {
        log.info("info={}", info);
        String uploadFolder = FileTypeUtil.getUploadPathPrefix(fileProperties.getLocal().getStoragePath());
        //检查目标分片是否存在且完整
        boolean chunkCheck = wu.chunkCheck(Paths.get(uploadFolder, info.getName(), String.valueOf(info.getChunkIndex())).toString(), info.getSize());
        return R.success(chunkCheck);
    }


    /**
     * 分片上传
     * 该接口不能用作 单文件上传！
     */
    @ApiOperation(value = "分片上传", notes = "前端通过webUploader获取截取分片， 然后逐个上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public R<String> uploadFile(FileUploadDTO info, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        String uploadFolder = FileTypeUtil.getUploadPathPrefix(fileProperties.getLocal().getStoragePath());
        //验证请求不会包含数据上传，所以避免NullPoint这里要检查一下file变量是否为null
        if (file == null || file.isEmpty()) {
            log.error("请求参数不完整");
            return R.fail("请求参数不完整");
        }

        log.info("info={}", info);
        /*
        将MD5签名和合并后的文件path存入持久层，注意这里这个需求导致需要修改webuploader.js源码3170行
        因为原始webuploader.js不支持为formData设置函数类型参数，这将导致不能在控件初始化后修改该参数
        文件大小 小于 单个分片时，会执行这里的代码
        */
        if (info.getChunks() == null || info.getChunks() <= 0) {
            File upload = fileContext.upload(file, new FileUploadVO());
            upload.setFileMd5(info.getMd5());
            fileService.save(upload);
            return R.success(file.getOriginalFilename());
        } else {
            //为上传的文件准备好对应的位置
            java.io.File target = wu.getReadySpace(info, uploadFolder);
            if (target == null) {
                return R.fail(wu.getErrorMsg());
            }
            log.info("target={}", target.getAbsolutePath());
            //保存上传文件
            file.transferTo(target);
            return R.success(target.getName());
        }


    }

    /**
     * 分片通过nio合并， 合并成功后，将文件上传至fastdfs
     * nio合并优点： 有效防止大文件的内存溢出
     *
     * @param info 分片合并参数
     */
    @ApiOperation(value = "分片合并", notes = "所有分片上传成功后，调用该接口对分片进行合并")
    @RequestMapping(value = "/merge", method = RequestMethod.POST)
    @ResponseBody
    @SysLog("上传大文件")
    public R<File> saveChunksMerge(@RequestBody FileChunksMergeDTO info) {
        log.info("info={}", info);
        return fileContext.chunksMerge(info);
    }

}
