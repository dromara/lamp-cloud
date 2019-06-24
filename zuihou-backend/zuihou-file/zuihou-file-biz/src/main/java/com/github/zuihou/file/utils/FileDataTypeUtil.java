package com.github.zuihou.file.utils;


import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.github.zuihou.file.enumeration.DataType;

import org.apache.commons.lang3.StringUtils;

/**
 * 根据类型识别工具
 *
 * @author tangyh
 * @date 2019-05-06
 */
public class FileDataTypeUtil {
    /**
     * url 分割符
     */
    public static final String URI_SEPARATOR = "/";
    /**
     * 文件后缀风格符
     */
    public static final String EXT_SEPARATOR = ".";
    final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM");
    private final static String IMAGE = "image";
    private final static String VIDEO = "video";
    private final static String DIR = "application/x-director";
    private final static String AUDIO = "audio";
    private final static String TEXT = "text";

    /**
     * 根据mine类型，返回文件类型
     *
     * @param
     * @return
     * @author tangyh
     * @date 2019-05-06 13:41
     */
    public static DataType getDataType(String mime) {
        if (mime == null || "".equals(mime)) {
            return DataType.OTHER;
        }
        if (mime.contains(IMAGE)) {
            return DataType.IMAGE;
        } else if (mime.contains(TEXT)
                || mime.startsWith("application/vnd.ms-excel")
                || mime.startsWith("application/msword")
                || mime.startsWith("application/pdf")
                || mime.startsWith("application/vnd.ms-project")
                || mime.startsWith("application/vnd.ms-works")
                || mime.startsWith("application/x-javascript")
                || mime.startsWith("application/vnd.openxmlformats-officedocument")
                || mime.startsWith("application/vnd.ms-word.document.macroEnabled")
                || mime.startsWith("application/vnd.ms-word.template.macroEnabled")
                || mime.startsWith("application/vnd.ms-powerpoint")
        ) {
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
        String absolutePath = Paths.get(uploadPathPrefix, secDir).toString();
        return absolutePath;
    }

    public static String getRelativePath(String pathPrefix, String path) {
        String remove = StringUtils.remove(path, pathPrefix);

        String relativePath = StringUtils.substring(remove, 0, remove.lastIndexOf("/"));
        return relativePath;
    }

}
