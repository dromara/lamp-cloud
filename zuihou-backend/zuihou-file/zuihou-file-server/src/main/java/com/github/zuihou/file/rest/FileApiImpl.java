package com.github.zuihou.file.rest;

import com.github.zuihou.base.Result;
import com.github.zuihou.file.config.FileProperties;
import com.github.zuihou.file.repository.file.service.FileService;
import com.github.zuihou.file.repository.file.service.FolderService;
import com.github.zuihou.file.rest.dozer.DozerUtils;
import com.github.zuihou.file.rest.file.api.FileApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 文件管理的接口，跟UploadApiImpl的不同之处在于这里提供给文件管理系统
 * @author zuihou
 * @createTime 2018-01-26 22:59
 */
@Api(value = "API - FileApiImpl", description = "文件管理")
@RestController
@RequestMapping("file")
@Slf4j
public class FileApiImpl implements FileApi {
    @Autowired
    private FileService fileService;
    @Autowired
    private FolderService folderService;
    @Autowired
    private DozerUtils dozerUtils;
    @Autowired
    private FileProperties fileProperties;
    @Value("${zuihou.file.upload-path:/temp}")
    private String uploadPath;

    /**
     * 上传文件(支持批量)
     * * 1，先将文件存在本地,并且生成文件名
     * * 2，然后在上传到fastdfs
     */
    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Result<Map<String, List<String>>> upload(String type, HttpServletRequest request) {
        try {

            return Result.success(null);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }


}
