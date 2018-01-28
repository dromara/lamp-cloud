package com.github.zuihou.file.rest;

import com.github.zuihou.auth.client.annotation.IgnoreAppToken;
import com.github.zuihou.base.Result;
import com.github.zuihou.commons.context.BaseContextHandler;
import com.github.zuihou.file.config.FileProperties;
import com.github.zuihou.file.entity.file.po.ZhFile;
import com.github.zuihou.file.repository.file.service.FileService;
import com.github.zuihou.file.rest.dozer.DozerUtils;
import com.github.zuihou.file.rest.file.constant.FileType;
import com.github.zuihou.file.rest.file.dto.UploadFileDto;
import com.github.zuihou.file.rest.file.dto.UploadListDto;
import com.github.zuihou.file.support.FileModel;
import com.github.zuihou.file.utils.FileUtils;
import com.github.zuihou.file.utils.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * api上传文件接口， 跟FileApiImpl的不同之处在于这里提供给前端接口
 *
 * @author zuihou
 * @createTime 2018-01-27 20:00
 */
@Api(value = "API - UploadApiImpl", description = "文件管理")
@RestController
@RequestMapping("upload")
@Slf4j
public class UploadApiImpl {
    final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM");
    @Autowired
    private FileService fileService;
    @Autowired
    private DozerUtils dozerUtils;
    @Autowired
    private FileProperties fileProperties;
    @Value("${hello:def}")
    private String hello;

    @RequestMapping("test")
    @IgnoreAppToken
    public Object list(String name) {
    System.out.println("-----------" + hello);
        return null;
    }


    /**
     * 上传文件(支持批量)
     * * 1，先将文件存在本地,并且生成文件名
     * * 2，然后在上传到fastdfs
     * <p>
     * 还没找到swagger 如何支持 多文件上传的配置。 有解决方法的朋友给我留言
     */
    @ApiOperation(value = "文件上传", notes = "文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", dataType = "string", paramType = "query", example = "API,SYSTEM", defaultValue = "API"),
            @ApiImplicitParam(name = "file0", value = "文件1", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "file1", value = "文件2", dataTypeClass = MultipartFile.class, paramType = "query")
    })
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Result<UploadListDto> uploadList(String type, HttpServletRequest request) {
        try {
            String appId = BaseContextHandler.getAppId();
            String userName = BaseContextHandler.getUserName();
            type = StringUtils.isEmpty(type) ? FileType.API.toString() : type;

            // Servlet3.0方式上传文件
            Collection<Part> parts = request.getParts();

            List<UploadFileDto> list = new ArrayList<>();
            //1，先将文件存在本地,并且生成文件名
            List<ZhFile> zhFileList = new ArrayList<>();

            for (Part part : parts) {
                // 忽略路径字段,只处理文件类型
                if (part.getContentType() != null) {
                    //文件名
                    String submittedFileName = part.getSubmittedFileName();
                    //后缀
                    String suffix = FileUtils.getExtension(submittedFileName);
                    //生成文件名
                    String fileName = UUID.randomUUID().toString() + suffix;

                    try {
                        //日期文件夹
                        String secDir = LocalDate.now().format(DTF);
                        // /home/zuihou/APP_ID/YYYY/MM
                        String relativePath = Paths.get(appId, secDir).toString();
                        String absolutePath = Paths.get(fileProperties.getUploadPathPrefix(), relativePath).toString();

                        //存到web服务器
                        FileUtils.write(part.getInputStream(), absolutePath, fileName);

                        //上传到fastdfs 并且返回 访问 url
                        FileModel fileModel = UploadUtil.remove2DFS(appId, "U1",
                                fileProperties, absolutePath, relativePath, fileName);

                        ZhFile zhFile = dozerUtils.map(fileModel, ZhFile.class);
                        zhFile.setAppId(appId);
                        zhFile.setCreateName(userName);
                        zhFile.setUpdateName(userName);
                        zhFile.setIcon("");
                        zhFile.setType(type);
                        zhFileList.add(zhFile);
                    } catch (Exception e) {
                        log.error("保存文件到服务器临时目录失败:", e);
                    }
                }

                //存储


                //4,转换
                list = zhFileList.stream().map((zhFile) -> {
                    UploadFileDto uploadFile = new UploadFileDto();
                    uploadFile.setId(zhFile.getId());
                    uploadFile.setUrl(zhFile.getUrl());
                    return uploadFile;
                }).collect(Collectors.toList());
            }

            UploadListDto uploadListDto = new UploadListDto();
            uploadListDto.setList(list);
            return Result.success(uploadListDto);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }

    /**
     * 测试 swagger2 如何支持文件
     */
    @ApiOperation(value = "单文件上传文件")
    @RequestMapping(value = "u2", method = RequestMethod.POST)
    public Result<UploadFileDto> upload2(
            Part part, MultipartFile file,
            HttpServletRequest request) {
        System.out.println("---");
        return null;
    }

    /**
     * 测试 swagger2 如何支持文件
     */
    @ApiOperation(value = "单文件上传文件")
    @RequestMapping(value = "u3", method = RequestMethod.POST)
    public Result<UploadFileDto> uploa3(
            Part part, MultipartFile[] file,
            HttpServletRequest request) {
        System.out.println("---");
        return null;
    }

    @ApiOperation(value = "单文件上传文件")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result<UploadFileDto> upload(String type, HttpServletRequest request) {
        //try {
        //    String appId = BaseContextHandler.getAppId();
        //    String userName = BaseContextHandler.getUserName();
        //    type = StringUtils.isEmpty(type) ? "" : type;
        //
        //    // Servlet3.0方式上传文件
        //    Collection<Part> parts = request.getParts();
        //
        //    //判断是否启用fastdfs进行文件上传
        //    boolean fastDfsEnabled = fileProperties.isEnabled();
        //    String fileUploadPath = uploadPath;
        //    if (fastDfsEnabled) {
        //        fileUploadPath = fileProperties.getTempUploadPath();
        //    }
        //
        //    List<UploadFileDto> list = new ArrayList<>();
        //    //1，先将文件存在本地,并且生成文件名
        //    List<String> fileNames = UploadUtil.uploadFileData(fileUploadPath, parts);
        //
        //    //2，将文件异步上传到fastdfs
        //    if (!fileNames.isEmpty()) {
        //
        //        String fileUploadPath2 = fileUploadPath;
        //        String type2 = type;
        //
        //        List<ZhFile> zhFileList = new ArrayList<>();
        //        fileNames.forEach((fileName) -> {
        //            String localPath = fileUploadPath2 + fileName;
        //            ZhFile zhFile = null;
        //
        //            FileModel fileModel = UploadUtil.remove2DFS(appId, "U1",
        //                    fileProperties.getRemoteUriPrefix(), localPath, fastDfsEnabled);
        //            zhFile = dozerUtils.map(fileModel, ZhFile.class);
        //
        //            zhFile.setAppId(appId);
        //            zhFile.setCreateName(userName);
        //            zhFile.setUpdateName(userName);
        //            zhFile.setIcon("");
        //            zhFile.setType(type2);
        //            zhFileList.add(zhFile);
        //        });
        //
        //        //3, 入库
        //        fileService.save(zhFileList);
        //
        //        //4,转换
        //        list = zhFileList.stream().map((zhFile)->{
        //            UploadFileDto uploadFile = new UploadFileDto();
        //            uploadFile.setId(zhFile.getId());
        //            uploadFile.setUrl(zhFile.getUrl());
        //            return uploadFile;
        //        }).collect(Collectors.toList());
        //
        //    }
        //
        //    UploadListDto uploadListDto = new UploadListDto();
        //    uploadListDto.setList(list);
        //    return Result.success(uploadListDto);
        //} catch (Exception e) {
        //    return Result.fail(e);
        //}
        return null;
    }
}
