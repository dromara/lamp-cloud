package com.tangyh.lamp.file.utils;


import cn.hutool.core.util.StrUtil;
import com.tangyh.lamp.file.enumeration.DataType;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 根据类型识别工具
 *
 * @author zuihou
 * @date 2019-05-06
 */
@Slf4j
public final class FileDataTypeUtil {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM");

    private FileDataTypeUtil() {
    }

    private static final String IMAGE = "image";
    private static final String VIDEO = "video";
    private static final String DIR = "application/x-director";
    private static final String AUDIO = "audio";
    private static final String TEXT = "text";

    private static final String[] TEXT_MIME = {
            "application/vnd.ms-excel",
            "application/msword",
            "application/pdf",
            "application/vnd.ms-project",
            "application/vnd.ms-works",
            "application/x-javascript",
            "application/vnd.openxmlformats-officedocument",
            "application/vnd.ms-word.document.macroEnabled",
            "application/vnd.ms-word.template.macroEnabled",
            "application/vnd.ms-powerpoint"
    };

    /**
     * 根据mine类型，返回文件类型
     *
     * @param mime 类型
     * @author zuihou
     * @date 2019-05-06 13:41
     */
    public static DataType getDataType(String mime) {
        if (mime == null || "".equals(mime)) {
            return DataType.OTHER;
        }
        if (mime.contains(IMAGE)) {
            return DataType.IMAGE;
        } else if (mime.contains(TEXT) || StrUtil.startWithAny(mime, TEXT_MIME)) {
            return DataType.DOC;
        } else if (mime.contains(VIDEO)) {
            return DataType.VIDEO;
        } else if (mime.contains(DIR)) {
            return DataType.DIR;
        } else if (mime.contains(AUDIO)) {
            return DataType.AUDIO;
        } else {
            return DataType.OTHER;
        }
    }

    public static String getUploadPathPrefix(String uploadPathPrefix) {
        //日期文件夹
        String secDir = LocalDate.now().format(DTF);
        // web服务器存放的绝对路径
        return Paths.get(uploadPathPrefix, secDir).toString();
    }

    public static String getRelativePath(String pathPrefix, String path) {
        String fileName = StrUtil.subAfter(path, "/", true);
        return StrUtil.subBetween(path, pathPrefix + File.separator, File.separator + fileName);
    }

}
