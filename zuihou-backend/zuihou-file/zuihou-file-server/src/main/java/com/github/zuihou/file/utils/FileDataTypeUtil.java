package com.github.zuihou.file.utils;


import com.github.zuihou.file.rest.file.constant.DataType;

public class FileDataTypeUtil {

    public static DataType getDataType(String mime) {
        if (mime == null || "".equals(mime)) {
            return DataType.OTHER;
        }
        if (mime.startsWith("image/")) {
            return DataType.IMAGE;
        } else if (mime.startsWith("text/")
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
        } else if (mime.startsWith("video/")) {
            return DataType.VIDEO;
        } else {
            return DataType.OTHER;
        }
    }
}
