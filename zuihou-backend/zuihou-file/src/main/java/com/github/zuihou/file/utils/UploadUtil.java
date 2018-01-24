package com.github.zuihou.file.utils;

import com.github.zuihou.file.support.FileManager;
import com.github.zuihou.file.support.FileModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Part;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author zuihou
 * @createTime 2018-01-23 22:14
 */
@Slf4j
public class UploadUtil {


    private static final String LINUX_PATH = "/";
    private static final String WIN_PATH = "\\";

    /**
     * 获取上传文件临时目录
     */
    public static String getUploadDir(String uploadPath) {
        if (!uploadPath.endsWith(LINUX_PATH) && !uploadPath.endsWith(WIN_PATH)) {
            uploadPath += File.separator;
        }
        return uploadPath;
    }


    /**
     * 移动文件到fastDFS
     */
    public static FileModel remove2DFS(String namespace, String objectId, String remoteUriPrefix, String fileName) {
        FileModel fastDFSFile = new FileModel(namespace, objectId, fileName);
        if (fastDFSFile.getKey() != null) {
            FileManager.getInstance().upload(remoteUriPrefix, fastDFSFile);
        }
        return fastDFSFile;
    }

    /**
     * 上传文件到临时路径
     *
     * @param pathDir 文件上传基础路径  eg: /home/zuihou/tempfile
     * @param parts
     * @return
     */
    public static List<String> uploadFileData(String pathDir, Collection<Part> parts) {
        List<String> fileNames = new ArrayList<>();
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
                    File file = new File(pathDir, fileName);
                    FileUtils.write(part.getInputStream(), file);

                    fileNames.add(fileName);
                } catch (Exception e) {
                    log.error("保存文件到服务器临时目录失败:", e);
                }
            }
        }
        return fileNames;
    }
}
