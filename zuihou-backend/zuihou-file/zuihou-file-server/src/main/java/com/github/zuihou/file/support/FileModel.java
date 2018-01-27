package com.github.zuihou.file.support;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author ShenHuaJie
 * @version 2016年6月27日 上午9:50:51
 */
@Data
public class FileModel implements Serializable {
    /**
     * 命名空间
     */
    private String namespace;
    /**
     * 对象id
     */
    private String objectId;
    /**
     * key
     */
    private String key;
    /**
     * 文件内容字节
     */
    private byte[] content;
    /**
     * FastDFS 文件全路径
     */
    private String remotePath;
    /**
     * 文件在服务器的相对路径
     * 文件的完整路径=absolutePath + filename
     * linux eg: 2015/01/01
     * win eg: 2015\\01\\01
     */
    private String relativePath;
    /**
     * 文件在服务器的绝对路径
     * 文件的完整路径=absolutePath + filename
     * linux eg: /home/zuihou/file/RELATIVEPATH
     * win eg: d:\\file
     */
    private String absolutePath;
    /**
     * 新文件名
     * 文件的完整路径=absolutePath + filename
     */
    private String filename;
    /**
     * 原始文件名
     */
    private String submittedFileName;
    /**
     * 后缀
     */
    private String ext;
    /**
     * 文件类型
     */
    private String mime;
    /**
     * 文件大小
     */
    private String size;


    /**
     * @param namespace     名称空间
     * @param objectId      文件标识
     * @param fileName 文件在web服务器的临时路径
     */
    public FileModel(String namespace, String objectId,
                     String absolutePath, String relativePath,
                     String fileName) {
        this.namespace = namespace;
        this.objectId = objectId;
        this.absolutePath = absolutePath;
        this.relativePath = relativePath;
        String localFilePath = Paths.get(absolutePath, fileName).toString();
        if (localFilePath == null || "".equals(localFilePath.trim())) {
            return;
        }
        this.ext = StringUtils.substring(fileName, StringUtils.lastIndexOf(fileName, ".") + 1);
        InputStream is = null;
        byte[] fileBuff = null;
        FileInputStream fileInputStream = null;
        try {
            File file = new File(localFilePath);
            this.size = String.valueOf(file.length());
            this.filename = file.getName();

            fileInputStream = new FileInputStream(file);
            if (fileInputStream != null) {
                int len = fileInputStream.available();
                fileBuff = new byte[len];
                fileInputStream.read(fileBuff);
            }
            this.content = fileBuff;
            //读取服务器支持的文件类型列表
            is = getClass().getResourceAsStream("/mime.types");
            MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap(is);
            this.mime = mimetypesFileTypeMap.getContentType(filename);
            this.key = UUID.randomUUID().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                }
            }
        }

    }

}
