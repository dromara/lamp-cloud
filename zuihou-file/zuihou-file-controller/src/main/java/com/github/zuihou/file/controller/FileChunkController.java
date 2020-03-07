package com.github.zuihou.file.controller;

import com.github.zuihou.base.R;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.file.domain.FileAttrDO;
import com.github.zuihou.file.dto.chunk.FileChunkCheckDTO;
import com.github.zuihou.file.dto.chunk.FileChunksMergeDTO;
import com.github.zuihou.file.dto.chunk.FileUploadDTO;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.manager.WebUploader;
import com.github.zuihou.file.properties.FileServerProperties;
import com.github.zuihou.file.service.FileService;
import com.github.zuihou.file.strategy.FileChunkStrategy;
import com.github.zuihou.file.strategy.FileStrategy;
import com.github.zuihou.file.utils.FileDataTypeUtil;
import com.github.zuihou.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
public class FileChunkController {
    @Autowired
    private FileServerProperties fileProperties;
    @Autowired
    private FileService fileService;
    @Resource
    private FileStrategy fileStrategy;
    @Resource
    private FileChunkStrategy fileChunkStrategy;
    @Autowired
    private WebUploader wu;


    /**
     * 采用md5 上传前的验证
     *
     * @param md5 文件md5
     * @return
     */
    @ApiOperation(value = "秒传接口，上传文件前先验证， 存在则启动秒传", notes = "前端通过webUploader获取文件md5，上传前的验证")
    @RequestMapping(value = "/md5", method = RequestMethod.POST)
    @ResponseBody
    public R<Boolean> saveMd5Check(@RequestParam(name = "md5") String md5,
                                   @RequestParam(name = "folderId", defaultValue = "0") Long folderId) {
        Long accountId = BaseContextHandler.getUserId();
        File file = fileChunkStrategy.md5Check(md5, folderId, accountId);
        return R.success(file != null ? true : false);
    }

    /**
     * 检查分片存不存在
     *
     * @param info
     * @return
     */
    @ApiOperation(value = "续传接口，检查每个分片存不存在", notes = "断点续传功能检查分片是否存在， 已存在的分片无需重复上传， 达到续传效果")
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public R<Boolean> chunkCheck(@RequestBody FileChunkCheckDTO info) {
        log.info("info={}", info);
        String uploadFolder = FileDataTypeUtil.getUploadPathPrefix(fileProperties.getStoragePath());
        //检查目标分片是否存在且完整
        boolean chunkCheck = wu.chunkCheck(Paths.get(uploadFolder, info.getName(), String.valueOf(info.getChunkIndex())).toString(), info.getSize());
        return R.success(chunkCheck);
    }


    /**
     * 分片上传
     * 该接口不能用作 单文件上传！
     *
     * @param info
     * @param file
     * @return
     */
    @ApiOperation(value = "分片上传", notes = "前端通过webUploader获取截取分片， 然后逐个上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public R<String> uploadFile(FileUploadDTO info, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        String uploadFolder = FileDataTypeUtil.getUploadPathPrefix(fileProperties.getStoragePath());
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
            File upload = fileStrategy.upload(file);

            FileAttrDO fileAttrDO = fileService.getFileAttrDo(info.getFolderId());
            upload.setFolderId(info.getFolderId());
            upload.setFileMd5(info.getMd5());
            upload.setFolderName(fileAttrDO.getFolderName());
            upload.setGrade(fileAttrDO.getGrade());
            upload.setTreePath(fileAttrDO.getTreePath());
            fileService.save(upload);
            return R.success(file.getOriginalFilename());
        } else {
            //为上传的文件准备好对应的位置
            java.io.File target = wu.getReadySpace(info, uploadFolder);
            log.info("target={}", target.getAbsolutePath());
            if (target == null) {
                return R.fail(wu.getErrorMsg());
            }
            //保存上传文件
            file.transferTo(target);
            return R.success(target.getName());
        }


    }

    /**
     * 分片通过nio合并， 合并成功后，将文件上传至fastdfs
     * nio合并优点： 有效防止大文件的内存溢出
     *
     * @param info
     * @return
     */
    @ApiOperation(value = "分片合并", notes = "所有分片上传成功后，调用该接口对分片进行合并")
    @RequestMapping(value = "/merge", method = RequestMethod.POST)
    @ResponseBody
    @SysLog("上传大文件")
    public R<File> saveChunksMerge(@RequestBody FileChunksMergeDTO info) {
        log.info("info={}", info);

        return fileChunkStrategy.chunksMerge(info);
    }

}
