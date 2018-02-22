package com.github.zuihou.file.rest.file.api;

import com.github.pagehelper.PageInfo;
import com.github.zuihou.base.Result;
import com.github.zuihou.file.rest.file.dto.FileDTO;
import com.github.zuihou.file.rest.file.dto.FileListDTO;
import com.github.zuihou.file.rest.file.dto.FilePageReqDTO;
import com.github.zuihou.file.rest.file.dto.FileUpdateDTO;
import com.github.zuihou.file.rest.file.dto.FolderDTO;
import com.github.zuihou.file.rest.file.dto.FolderSaveDTO;
import com.github.zuihou.page.plugins.openapi.OpenApiReq;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件api
 *
 * @author zuihou
 * @createTime 2018-01-26 23:01
 */
@FeignClient(name = "${zuihou.feign-server.gateway:zuihou-gateway-server}"/* , fallback = UploadApiHystrix.class */)
public interface FileApi {
    /**
     * 新建文件夹
     *
     * @param folderSaveDto 保存文件夹
     * @return
     */
    @RequestMapping(value = "/api/file/file/folder", method = RequestMethod.POST)
    Result<FolderDTO> folder(@RequestBody FolderSaveDTO folderSaveDto);


    /**
     * 根据id + token 查文件夹详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/file/file/{id}", method = RequestMethod.GET)
    Result<FileDTO> get(@PathVariable(value = "id") Long id);

    /**
     * 根据父id查询列表
     *
     * @param openApiReq     分页信息
     * @param filePageReqDTO 分页查询参数
     * @return PageInfo
     */
    @RequestMapping(value = "/api/file/file/page", method = RequestMethod.GET)
    Result<PageInfo<FileDTO>> page(OpenApiReq openApiReq, FilePageReqDTO filePageReqDTO);

    /**
     * 上传文件到指定文件夹
     *
     * @param folderId 文件夹id
     * @param request  文件
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/api/file/file/upload", method = RequestMethod.POST)
    Result<FileListDTO> upload(@RequestParam(value = "folderId") Long folderId, HttpServletRequest request) throws Exception;

    /**
     * 修改文件
     *
     * @param fileUpdateDTO
     * @return
     */
    @RequestMapping(value = "/api/file/file", method = RequestMethod.PUT)
    Result<Boolean> update(@RequestBody FileUpdateDTO fileUpdateDTO);

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/file/file/{id}", method = RequestMethod.DELETE)
    Result<Boolean> remove(@PathVariable(value = "id") Long id);

    /**
     * 下载指定文件
     *
     * @param url      文件url
     * @param filename 文件名
     * @param response 下载文件
     * @throws Exception Exception
     */
    @RequestMapping(value = "/api/file/file/download", method = RequestMethod.POST)
    void download(@RequestParam(value = "url") String url, @RequestParam(value = "filename") String filename,
                  HttpServletResponse response) throws Exception;
}
