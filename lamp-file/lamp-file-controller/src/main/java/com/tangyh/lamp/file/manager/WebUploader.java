package com.tangyh.lamp.file.manager;


import com.tangyh.lamp.file.dto.chunk.FileUploadDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 分片上传工具类
 *
 * @author zuihou
 * @date 2019-06-14 11:50
 */
@Service
@Scope("prototype")
@Slf4j
public class WebUploader {

    /**
     * 错误详情
     */
    private String msg;

    /**
     * 分片验证
     * 验证对应分片文件是否存在，大小是否吻合
     *
     * @param file 分片文件的路径
     * @param size 分片文件的大小
     * @return 一致返回true
     */
    public boolean chunkCheck(String file, Long size) {
        //检查目标分片是否存在且完整
        java.io.File target = new java.io.File(file);
        return target.isFile() && size == target.length();
    }

    /**
     * 为上传的文件创建对应的保存位置
     * 若上传的是分片，则会创建对应的文件夹结构和tmp文件
     *
     * @param info 上传文件的相关信息
     * @param path 文件保存根路径
     * @return 文件
     */
    public java.io.File getReadySpace(FileUploadDTO info, String path) {
        //创建上传文件所需的文件夹
        if (!this.createFileFolder(path, false)) {
            return null;
        }

        String newFileName;    //上传文件的新名称

        //如果是分片上传，则需要为分片创建文件夹
        if (info.getChunks() > 0) {
            newFileName = String.valueOf(info.getChunk());

            String fileFolder = this.md5(info.getName() + info.getType() + info.getLastModifiedDate() + info.getSize());
            log.info("fileFolder={}, md5={}", fileFolder, info.getMd5());
            if (fileFolder == null) {
                return null;
            }

            //文件上传路径更新为指定文件信息签名后的临时文件夹，用于后期合并
            path += "/" + fileFolder;

            if (!this.createFileFolder(path, true)) {
                return null;
            }
            return new java.io.File(path, newFileName);
        }
        return null;
    }

    /**
     * 创建存放上传的文件的文件夹
     *
     * @param file   文件夹路径
     * @param hasTmp 是否有临时文件
     * @return 创建成功返回true
     */
    private boolean createFileFolder(String file, boolean hasTmp) {
        //创建存放分片文件的临时文件夹
        java.io.File tmpFile = new java.io.File(file);
        if (!tmpFile.exists()) {
            try {
                tmpFile.mkdirs();
            } catch (SecurityException ex) {
                log.error("无法创建文件夹", ex);
                this.setErrorMsg("无法创建文件夹");
                return false;
            }
        }

        if (hasTmp) {
            //创建一个对应的文件，用来记录上传分片文件的修改时间，用于清理长期未完成的垃圾分片
            tmpFile = new java.io.File(file + ".tmp");
            if (tmpFile.exists()) {
                return tmpFile.setLastModified(System.currentTimeMillis());
            } else {
                try {
                    tmpFile.createNewFile();
                } catch (IOException ex) {
                    log.error("无法创建tmp文件", ex);
                    this.setErrorMsg("无法创建tmp文件");
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * MD5签名
     *
     * @param content 要签名的内容
     * @return md5
     */
    private String md5(String content) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(content.getBytes(StandardCharsets.UTF_8));
            byte[] tmpFolder = md5.digest();

            for (byte b : tmpFolder) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            log.error("无法生成文件的MD5签名", ex);
            this.setErrorMsg("无法生成文件的MD5签名");
            return null;
        }
    }

    /**
     * 获取错误详细
     *
     * @return 错误消息
     */
    public String getErrorMsg() {
        return this.msg;
    }

    /**
     * 记录异常错误信息
     *
     * @param msg 错误详细
     */
    private void setErrorMsg(String msg) {
        this.msg = msg;
    }
}
