package com.github.zuihou.file.rest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.zuihou.file.utils.FileUtils;
import com.github.zuihou.file.utils.RarUtils;
import com.github.zuihou.file.utils.TargzUtils;
import com.github.zuihou.file.utils.ZipUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @author zuihou
 * @createTime 2018-01-20 23:33
 */
@RestController
@RequestMapping(value = "fileManager")
public class IndexController {

    /*
     *  文件管理根目录,此处为了方便采用Hard Code
     */
    //public static String ROOT = "/home/zuihou/gxqpt/logs/";
    public static String ROOT = "E:\\etc\\cas\\";

    /**
     * 展示文件列表
     */
    @RequestMapping("list")
    public Object list(@RequestBody JSONObject json) throws ServletException {

        try {
            // 需要显示的目录路径
            String path = json.getString("path");

            // 返回的结果集
            List<JSONObject> fileItems = new ArrayList<>();

            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(ROOT, path))) {

                String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat dt = new SimpleDateFormat(DATE_FORMAT);
                for (Path pathObj : directoryStream) {
                    // 获取文件基本属性
                    BasicFileAttributes attrs = Files.readAttributes(pathObj, BasicFileAttributes.class);

                    // 封装返回JSON数据
                    JSONObject fileItem = new JSONObject();
                    fileItem.put("name", pathObj.getFileName().toString());
                    fileItem.put("rights", FileUtils.getPermissions(pathObj)); // 文件权限
                    fileItem.put("date", dt.format(new Date(attrs.lastModifiedTime().toMillis())));
                    fileItem.put("size", attrs.size());
                    fileItem.put("type", attrs.isDirectory() ? "dir" : "file");
                    fileItems.add(fileItem);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", fileItems);
            return jsonObject;
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    ///**
    // * 文件上传
    // */
    //@RequestMapping("upload")
    //public Object upload(@RequestParam("destination") String destination, HttpServletRequest request) {
    //
    //    try {
    //        // Servlet3.0方式上传文件
    //        Collection<Part> parts = request.getParts();
    //
    //        for (Part part : parts) {
    //            // 忽略路径字段,只处理文件类型
    //            if (part.getContentType() != null) {
    //                String path = ROOT + destination;
    //
    //                File f = new File(path, FileUtils.getFileName(part.getHeader("content-disposition")));
    //                if (!FileUtils.write(part.getInputStream(), f)) {
    //                    throw new Exception("文件上传失败");
    //                }
    //            }
    //        }
    //        return success();
    //    } catch (Exception e) {
    //        return error(e.getMessage());
    //    }
    //}

    /**
     * 文件下载/预览
     */
    @RequestMapping("preview")
    public void preview(HttpServletResponse response, String path) throws IOException {

        File file = new File(ROOT, path);
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource Not Found");
            return;
        }

        /*
         * 获取mimeType
         */
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", URLEncoder.encode(file.getName(), "UTF-8")));
        response.setContentLength((int) file.length());

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }

    /**
     * 创建目录
     */
    @RequestMapping("createFolder")
    public Object createFolder(@RequestBody JSONObject json) {
        try {
            String newPath = json.getString("newPath");
            File newDir = new File(ROOT + newPath);
            if (!newDir.mkdir()) {
                throw new Exception("不能创建目录: " + newPath);
            }
            return success();
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改文件或目录权限
     */
    @RequestMapping("changePermissions")
    public Object changePermissions(@RequestBody JSONObject json) {
        try {

            String perms = json.getString("perms"); // 权限
            boolean recursive = json.getBoolean("recursive"); // 子目录是否生效

            JSONArray items = json.getJSONArray("items");
            for (int i = 0; i < items.size(); i++) {
                String path = items.getString(i);
                File f = new File(ROOT, path);
                FileUtils.setPermissions(f, perms, recursive); // 设置权限
            }
            return success();
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 复制文件或目录
     */
    @RequestMapping("copy")
    public Object copy(@RequestBody JSONObject json, HttpServletRequest request) {
        try {
            String newpath = json.getString("newPath");
            JSONArray items = json.getJSONArray("items");

            for (int i = 0; i < items.size(); i++) {
                String path = items.getString(i);

                File srcFile = new File(ROOT, path);
                File destFile = new File(ROOT + newpath, srcFile.getName());

                FileCopyUtils.copy(srcFile, destFile);
            }
            return success();
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 文件压缩
     */
    @RequestMapping("compress")
    public Object compress(@RequestBody JSONObject json) {
        try {
            String destination = json.getString("destination");
            String compressedFilename = json.getString("compressedFilename");
            JSONArray items = json.getJSONArray("items");
            List<File> files = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                File f = new File(ROOT, items.getString(i));
                files.add(f);
            }

            File zip = new File(ROOT + destination, compressedFilename);

            try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip))) {
                ZipUtils.zipFiles(out, "", files.toArray(new File[files.size()]));
            }
            return success();
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 文件解压
     */
    @RequestMapping("extract")
    public Object extract(@RequestBody JSONObject json) {
        try {
            String destination = json.getString("destination");
            String zipName = json.getString("item");
            String folderName = json.getString("folderName");
            File file = new File(ROOT, zipName);

            String extension = FileUtils.getExtension(zipName);
            switch (extension) {
                case ".zip":
                    ZipUtils.unZipFiles(file, ROOT + destination);
                    break;
                case ".gz":
                    TargzUtils.unTargzFile(file, ROOT + destination);
                    break;
                case ".rar":
                    RarUtils.unRarFile(file, ROOT + destination);
            }
            return success();
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }


    private JSONObject error(String msg) {

        // { "result": { "success": false, "error": "msg" } }
        JSONObject result = new JSONObject();
        result.put("success", false);
        result.put("error", msg);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject;

    }

    private JSONObject success() {
        // { "result": { "success": true, "error": null } }
        JSONObject result = new JSONObject();
        result.put("success", true);
        result.put("error", null);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        return jsonObject;
    }
}
