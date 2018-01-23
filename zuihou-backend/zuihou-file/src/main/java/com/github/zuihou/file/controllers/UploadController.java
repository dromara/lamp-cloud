package com.github.zuihou.file.controllers;

/**
 * @author zuihou
 * @createTime 2018-01-23 22:07
 */

import com.github.zuihou.file.utils.UploadUtil;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传控制器
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:11:42
 */
@RestController
@RequestMapping(value = "/upload", method = RequestMethod.POST)
public class UploadController {

    // 上传文件(支持批量)
    @RequestMapping("/imageData")
    public Object uploadImageData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        List<String> fileNames = UploadUtil.uploadImageData(request);
        if (fileNames.size() > 0) {
            modelMap.put("fileNames", fileNames);
            List<String> fileRemotePathList = new ArrayList<>();

            for (int i = 0; i < fileNames.size(); i++) {
                String filePath = UploadUtil.getUploadDir(request) + fileNames.get(i);
                String fileRemotePath = UploadUtil.remove2DFS("sysUser", "U1", filePath).getRemotePath();
                fileRemotePathList.add(fileRemotePath);
            }
            modelMap.put("fileRemotePath", fileRemotePathList);
            return modelMap;
        } else {
            modelMap.put("msg", "请选择要上传的文件！");
            return modelMap;
        }
    }




}
