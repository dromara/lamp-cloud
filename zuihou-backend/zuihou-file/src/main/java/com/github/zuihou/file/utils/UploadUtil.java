package com.github.zuihou.file.utils;

import com.github.zuihou.file.support.FileManager;
import com.github.zuihou.file.support.FileModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

/**
 * @author zuihou
 * @createTime 2018-01-23 22:14
 */
public class UploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(UploadUtil.class);

    private static final String uploadFileDir = "/WEB-INF/upload/";

    /**
     * 获取上传文件临时目录
     */
    public static String getUploadDir(HttpServletRequest request) {
        return request.getServletContext().getRealPath(uploadFileDir) + File.separator;
    }

    public static List<String> uploadImageData(HttpServletRequest request) {
        List<String> fileNames = new ArrayList<>();
        Enumeration<String> params = request.getParameterNames();
        String pathDir = getUploadDir(request);
        File dir = new File(pathDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        while (params.hasMoreElements()) {
            String key = params.nextElement();
            String fileStr = request.getParameter(key);
            if (fileStr != null && !"".equals(fileStr)) {
                int index = fileStr.indexOf("base64");
                if (index > 0) {
                    try {
                        String fileName = UUID.randomUUID().toString();
                        String preStr = fileStr.substring(0, index + 7);
                        String prefix = preStr.substring(preStr.indexOf("/") + 1, preStr.indexOf(";")).toLowerCase();
                        fileStr = fileStr.substring(fileStr.indexOf(",") + 1);
                        BASE64Decoder decoder = new BASE64Decoder();
                        byte[] bb = decoder.decodeBuffer(fileStr);
                        for (int j = 0; j < bb.length; ++j) {
                            // 调整异常数据
                            if (bb[j] < 0) {
                                bb[j] += 256;
                            }
                        }
                        String distPath = pathDir + fileName + "." + prefix;
                        OutputStream out = new FileOutputStream(distPath);
                        out.write(bb);
                        out.flush();
                        out.close();
                        fileNames.add(fileName + "." + prefix);
                    } catch (Exception e) {
                        logger.error("上传文件异常：", e);
                    }
                }
            }
        }
        return fileNames;
    }

    /**
     * 移动文件到fastDFS
     */
    public static FileModel remove2DFS(String namespace, String objectId, String fileName) {
        FileModel fastDFSFile = new FileModel(namespace, objectId, fileName);
        if (fastDFSFile.getKey() != null) {
            FileManager.getInstance().upload(fastDFSFile);
        }
        return fastDFSFile;
    }

}
