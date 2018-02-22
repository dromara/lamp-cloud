package com.github.zuihou.file.rest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.zuihou.auth.client.annotation.IgnoreAppToken;
import com.github.zuihou.base.Result;
import com.github.zuihou.commons.constant.DeleteStatus;
import com.github.zuihou.commons.context.BaseContextHandler;
import com.github.zuihou.commons.context.CommonConstants;
import com.github.zuihou.commons.exception.core.ExceptionCode;
import com.github.zuihou.file.config.FileProperties;
import com.github.zuihou.file.constant.IconType;
import com.github.zuihou.file.entity.file.po.ZhFile;
import com.github.zuihou.file.repository.file.example.ZhFileExample;
import com.github.zuihou.file.repository.file.service.FileService;
import com.github.zuihou.file.rest.dozer.DozerUtils;
import com.github.zuihou.file.rest.file.api.FileApi;
import com.github.zuihou.file.rest.file.constant.DataType;
import com.github.zuihou.file.rest.file.constant.FileType;
import com.github.zuihou.file.rest.file.dto.FileDTO;
import com.github.zuihou.file.rest.file.dto.FileListDTO;
import com.github.zuihou.file.rest.file.dto.FilePageReqDTO;
import com.github.zuihou.file.rest.file.dto.FileUpdateDTO;
import com.github.zuihou.file.rest.file.dto.FolderDTO;
import com.github.zuihou.file.rest.file.dto.FolderSaveDTO;
import com.github.zuihou.file.support.FileModel;
import com.github.zuihou.file.utils.FileDataTypeUtil;
import com.github.zuihou.file.utils.FileUtils;
import com.github.zuihou.file.utils.UploadUtil;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;
import com.github.zuihou.utils.BizAssert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * 文件管理的接口，跟UploadApiImpl的不同之处在于这里提供给文件管理系统
 *
 * @author zuihou
 * @createTime 2018-01-26 22:59
 */
@Api(value = "API - FileApiImpl", description = "文件管理")
@RestController
@RequestMapping("/file")
@Slf4j
public class FileApiImpl implements FileApi {
    final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM");
    @Autowired
    private FileService fileService;
    @Autowired
    private DozerUtils dozerUtils;
    @Autowired
    private FileProperties fileProperties;

    @Override
    @ApiOperation(value = "保存文件夹", notes = "Response Messages 中的HTTP Status Code 值的是errcode的值")
    @RequestMapping(value = "/folder", method = RequestMethod.POST)
    public Result<FolderDTO> folder(@RequestBody FolderSaveDTO folderSaveDto) {
        BizAssert.assertNotNull(ExceptionCode.FOLDER_NULL, folderSaveDto);
        BizAssert.assertNotNull(ExceptionCode.FOLDER_NAME_EMPTY, folderSaveDto.getSubmittedFileName());
        String appId = BaseContextHandler.getAppId();
        String userName = BaseContextHandler.getUserName();


        ZhFile folder = dozerUtils.map2(folderSaveDto, ZhFile.class);
        if (folderSaveDto.getParentId() == null || folderSaveDto.getParentId() <= 0) {
            folderSaveDto.setParentId(CommonConstants.PARENT_ID_DEF);
        } else {
            ZhFile parent = fileService.getByAppIdAndId(appId, folderSaveDto.getParentId());
            BizAssert.assertNotNull(ExceptionCode.FOLDER_PARENT_NULL, parent);
            BizAssert.assertFalse(ExceptionCode.FOLDER_PARENT_NULL, parent.getIsDelete());
            BizAssert.assertEquals(ExceptionCode.FOLDER_PARENT_NULL, DataType.DIR.toString(), parent.getDataType());
            folder.setFolderName(parent.getSubmittedFileName());
            folder.setTreePath(parent.getTreePath() + parent.getId() + ",");
        }
        if (folderSaveDto.getOrderNum() == null) {
            folderSaveDto.setOrderNum(0);
        }

        folder.setAppId(appId);
        folder.setCreateName(userName);
        folder.setUpdateName(userName);
        folder.setIsDelete(DeleteStatus.UN_DELETE.getVal());
        folder.setDataType(DataType.DIR.toString());
        folder.setType(FileType.SYSTEM.toString());
        folder.setIcon(IconType.DIR.getIcon());
        folder = fileService.saveSelective(folder);
        return Result.success(dozerUtils.map(folder, FolderDTO.class));
    }

    @Override
    @ApiOperation(value = "获取文件", notes = "获取文件")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<FileDTO> get(@PathVariable(value = "id") Long id) {
        String appId = BaseContextHandler.getAppId();
        ZhFile file = fileService.getByAppIdAndId(appId, id);
        if (file != null && file.getIsDelete()) {
            return Result.success(null);
        }
        return Result.success(dozerUtils.map(file, FileDTO.class));
    }

    @Override
    @ApiOperation(value = "获取文件分页", notes = "获取文件分页")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Result<PageInfo<FileDTO>> page(OpenApiReq openApiReq, FilePageReqDTO filePageReqDTO) {
        String appId = BaseContextHandler.getAppId();
        if (filePageReqDTO.getFolderId() == null ||
                filePageReqDTO.getFolderId() < 0) {
            filePageReqDTO.setFolderId(CommonConstants.PARENT_ID_DEF);
        }
        ZhFileExample example = new ZhFileExample();
        example.createCriteria().andAppIdEqualTo(appId).andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal())
                .andDataTypeEqualTo(filePageReqDTO.getDataType())
                .andFolderIdEqualTo(filePageReqDTO.getFolderId()).andSubmittedFileNameEqualTo(filePageReqDTO.getSubmittedFileName());
        PageHelper.startPage(openApiReq.getPageNo(), openApiReq.getPageSize());
        List<ZhFile> list = fileService.find(example);
        List<FileDTO> pageList = dozerUtils.mapPage(list, FileDTO.class);
        return Result.success(new PageInfo<>(pageList));
    }


    @Override
    @ApiOperation(value = "上传文件", notes = "上传文件")
    @ApiResponses({
            @ApiResponse(code = 55502, message = "文件夹为空"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "folderId", value = "文件夹id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "file0", value = "附件1", dataType = "file", paramType = "query"),
            @ApiImplicitParam(name = "file1", value = "附件2", dataType = "file", paramType = "query"),
    })
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result<FileListDTO> upload(@RequestParam(value = "folderId") Long folderId, HttpServletRequest request) throws Exception {
        BizAssert.assertNotNull(ExceptionCode.FILE_FOLDER_NULL, folderId);
        String appId = BaseContextHandler.getAppId();
        String userName = BaseContextHandler.getUserName();
        String treePath = "";
        if (folderId != null && folderId > 0) {
            ZhFile folder = fileService.getByAppIdAndId(appId, folderId);
            BizAssert.assertNotNull(ExceptionCode.FILE_FOLDER_NULL, folder);
            BizAssert.assertFalse(ExceptionCode.FILE_FOLDER_NULL, folder.getIsDelete());
            BizAssert.assertEquals(ExceptionCode.FOLDER_PARENT_NULL, DataType.DIR.toString(), folder.getDataType());
            treePath = folder.getTreePath() + folder.getId() + ",";
        } else {
            folderId = CommonConstants.PARENT_ID_DEF;
        }

        // Servlet3.0方式上传文件
        Collection<Part> parts = request.getParts();

        //1，先将文件存在本地,并且生成文件名
        List<ZhFile> fileList = new ArrayList<>();
        for (Part part : parts) {
            log.info("contenttype={}, name={} , sfname={}", part.getContentType(), part.getName(), part.getSubmittedFileName());
            // 忽略路径字段,只处理文件类型
            //if (part.getContentType() != null && !"folderId".equals(part.getName())) {
            if (part.getContentType() != null && part.getName().startsWith("file")) {
                //文件名
                String submittedFileName = part.getSubmittedFileName();
                //后缀
                String suffix = FileUtils.getExtension(submittedFileName);
                //生成文件名
                String fileName = UUID.randomUUID().toString() + suffix;

                try {
                    //日期文件夹
                    String secDir = LocalDate.now().format(DTF);
                    // /home/tyh/APP_ID/YYYY/MM
                    String relativePath = Paths.get(appId, secDir).toString();
                    String absolutePath = Paths.get(fileProperties.getUploadPathPrefix(), relativePath).toString();

                    //存到web服务器
                    FileUtils.write(part.getInputStream(), absolutePath, fileName);

                    //上传到fastdfs 并且返回 访问 url
                    FileModel fileModel = UploadUtil.remove2DFS(appId, "FILE",
                            fileProperties, absolutePath, relativePath, fileName);

                    ZhFile file = dozerUtils.map(fileModel, ZhFile.class);
                    file.setAppId(appId);
                    file.setFolderId(folderId);
                    file.setCreateName(userName);
                    file.setUpdateName(userName);
                    file.setIsDelete(DeleteStatus.UN_DELETE.getVal());
                    file.setSubmittedFileName(submittedFileName);
                    file.setDataType(FileDataTypeUtil.getDataType(file.getExt()).toString());
                    file.setIcon(IconType.getIcon(file.getMime()).getIcon());
                    file.setType(FileType.SYSTEM.toString());
                    file.setTreePath(treePath);
                    fileList.add(file);
                } catch (Exception e) {
                    log.error("保存文件到服务器临时目录失败:", e);
                }
            }
        }

        //存储
        if (!fileList.isEmpty()) {
            fileService.save(fileList);
            //4,转换
            List<FileDTO> list = dozerUtils.mapList(fileList, FileDTO.class);
            return Result.success(new FileListDTO(list));
        }
        return Result.success(null);
    }

    /**
     * 修改文件
     *
     * @param fileUpdateDTO
     * @return
     */
    @Override
    @ApiOperation(value = "修改文件信息", notes = "修改文件信息")
    @ApiResponses({
            @ApiResponse(code = 55500, message = "文件为空"),
            @ApiResponse(code = 55502, message = "文件夹为空"),
    })
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Result<Boolean> update(@RequestBody FileUpdateDTO fileUpdateDTO) {
        BizAssert.assertNotNull(ExceptionCode.FILE_NULL, fileUpdateDTO);
        BizAssert.assertNotNull(ExceptionCode.FILE_NULL, fileUpdateDTO.getId());
        String appId = BaseContextHandler.getAppId();
        String userName = BaseContextHandler.getUserName();
        ZhFile file = dozerUtils.map2(fileUpdateDTO, ZhFile.class);
        String parentTreePath = "";
        if (fileUpdateDTO.getFolderId() != null) {
            ZhFile folder = fileService.getByAppIdAndId(appId, fileUpdateDTO.getFolderId());
            BizAssert.assertNotNull(ExceptionCode.FILE_FOLDER_NULL, folder);
            BizAssert.assertFalse(ExceptionCode.FILE_FOLDER_NULL, folder.getIsDelete());
            BizAssert.assertEquals(ExceptionCode.FOLDER_PARENT_NULL, DataType.DIR.toString(), folder.getDataType());
            file.setFolderName(folder.getSubmittedFileName());
            parentTreePath = folder.getTreePath() + folder.getId() + ",";
        }

        file.setUpdateName(userName);
        int result = fileService.updateByAppIdAndIdSelective(appId, file);
        if (result > 0) {
            List<ZhFile> treeList = new ArrayList<>();

            //setTreePath(treeList, parentTreePath);
        }
        return Result.success(true);
    }

    //private List<ZhFile> setTreePath(List<ZhFile> fileList, String parentTreePath) {
    //    for (ZhFile f : fileList) {
    //        f.setTreePath(parentTreePath);
    //        fileService.updateByAppIdAndIdSelective(f.getAppId(), f);
    //        if (f.getChildFile() != null) {
    //            setTreePath(f.getChildFile(), f.getTreePath() + f.getId() + ",");
    //        }
    //    }
    //    return fileList;
    //}

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    @Override
    @ApiOperation(value = "删除文件", notes = "删除文件")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result<Boolean> remove(@PathVariable(value = "id") Long id) {
        String appId = BaseContextHandler.getAppId();

        ZhFile folder = fileService.getByAppIdAndId(appId, id);
        if (folder != null && DataType.DIR.toString().equals(folder.getDataType())) {
            fileService.removeDirByAppIdAndId(appId, id);
        } else {
            fileService.removeByAppIdAndId(appId, id);
        }
        return Result.success(true);
    }


    /**
     * 下载指定文件
     *
     * @param url      文件url
     * @param filename 文件名
     * @return
     */
    @Override
    @ApiOperation(value = "下载文件", notes = "下载文件, 这个接口不需要token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "文件url", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "filename", value = "文件名", dataType = "string", paramType = "query"),
    })
    @IgnoreAppToken
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(@RequestParam(value = "url") String url, @RequestParam(value = "filename") String filename,
                         HttpServletResponse response) throws Exception {
        if (StringUtils.isEmpty(url)) {
            return;
        }
        if (StringUtils.isEmpty(filename)) {
            try {
                filename = StringUtils.substring(url, url.lastIndexOf("/"));
            } catch (Exception e) {
                filename = "未知文件.txt";
            }
        }
        HttpURLConnection connection = null;
        try {
            URL uri = new URL(url);
            // 打开连接
            connection = (HttpURLConnection) uri.openConnection();
            // 连接会话
            connection.connect();

            download(filename, connection.getInputStream(), response);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * 下载指定输入流的图片
     *
     * @param filename
     * @param inputStream
     * @param response
     * @throws Exception
     */
    private void download(String filename, InputStream inputStream,
                          HttpServletResponse response) throws Exception {
        if (StringUtils.isEmpty(filename)) {
            filename = "未知文件.txt";
        }
        OutputStream os = null;
        try {
            response.setContentType("application/force-download");
            if (!StringUtils.isEmpty(filename)) {
                response.setHeader(
                        "Content-disposition",
                        "attachment; filename="
                                + URLEncoder.encode(filename, "UTF-8"));
            } else {
                response.setHeader("Content-disposition", "attachment");
            }

            os = response.getOutputStream();

            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        } finally {
            if (os != null) {
                os.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

}
