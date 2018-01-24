package com.github.zuihou.file.support;

import lombok.Data;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
     * 新文件名
     */
    private String filename;
    /**
     * 原始文件名
     */
    private String submittedFileName;

    /**
     * FastDFS 文件全路径
     */
    private String remotePath;

    /**
     * @param namespace 名称空间
     * @param objectId  文件标识
     * @param filePath  文件在web服务器的临时路径
     */
    public FileModel(String namespace, String objectId, String filePath) {
        this.namespace = namespace;
        this.objectId = objectId;
        if (filePath == null || "".equals(filePath.trim())) {
            return;
        }
        this.ext = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        byte[] fileBuff = null;
        FileInputStream fileInputStream = null;
        try {
            File file = new File(filePath);
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
