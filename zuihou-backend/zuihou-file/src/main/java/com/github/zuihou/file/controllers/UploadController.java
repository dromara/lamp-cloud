package com.github.zuihou.file.controllers;

/**
 * @author zuihou
 * @createTime 2018-01-23 22:07
 */

import com.github.zuihou.base.Result;
import com.github.zuihou.file.config.FileProperties;
import com.github.zuihou.file.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传控制器
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:11:42
 */
@RestController
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public class UploadController {

    @Autowired
    private FileProperties fileProperties;

    /**
     * 上传文件(支持批量)
     *      * 1，先将文件存在本地,并且生成文件名
     *      * 2，然后在上传到fastdfs
     */
    @RequestMapping("upload")
    public Result<Map<String, List<String>>> upload(@RequestParam("destination") String destination, HttpServletRequest request) {
        try {
            String appId = "aaa";

            // Servlet3.0方式上传文件
            Collection<Part> parts = request.getParts();

            Map<String, List<String>> data = new HashMap<>();
            //1，先将文件存在本地,并且生成文件名
            List<String> fileNames = UploadUtil.uploadFileData(fileProperties.getUploadPath(), parts);
            data.put("fileNames", fileNames);
            //2，将文件异步上传到fastdfs
            if (!fileNames.isEmpty()) {
                List<String> fileRemotePathList = new ArrayList<>();

                fileNames.forEach((fileName)->{
                    String filePath = UploadUtil.getUploadDir(fileProperties.getUploadPath()) + fileName;
                    String fileRemotePath = UploadUtil.remove2DFS(appId, "U1", fileProperties.getRemoteUriPrefix(), filePath).getRemotePath();
                    fileRemotePathList.add(fileRemotePath);
                });
                data.put("fileRemotePath", fileRemotePathList);
            }

            //3, 入库
            return Result.success(data);
        } catch (Exception e) {
            return Result.fail(e);
        }
    }


}
