package com.tangyh.lamp.file.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 图标 常量
 *
 * @author zuihou
 * @date 2019-06-12
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum IconType {
    /**
     * 图标
     */
    DIR("application/x-director", "el-icon-folder"),
    /**
     * 图标
     */
    TXT("txt", "el-icon-document"),
    /**
     * 图标
     */
//    EXE("exe", "fa-exe-o"),
//    PDF("pdf", "fa-file-pdf-o"),
//    //    PSD("psd", "fa-ps-o"),
//    XLX("xls", "fa-file-excel-o"),
//    /**
//     * 图标
//     */
//    XLSX("xlsx", "fa-file-excel-o"),
//    /**
//     * 图标
//     */
//    WPS("wps", "fa-file-word-o"),
//    /**
//     * 图标
//     */
//    WPT("pwt", "fa-file-word-o"),
//    /**
//     * 图标
//     */
//    JS("js", "fa-file-code-o"),
//    /**
//     * 图标
//     */
//    JSP("js", "fa-file-code-o"),
//    /**
//     * 图标
//     */
//    PROPERTIES("properties", "fa-file-code-o"),

    /**
     * 图标
     */
//    CSS("css", "fa-file-code-o"),
//    SWF("swf", "fa-swf-o"),
    /**
     * 图标
     */
//    DOC("doc", "fa-file-word-o"),
//    /**
//     * 图标
//     */
//    DOCX("docx", "fa-file-word-o"),
//    /**
//     * 图标
//     */
//    DOTX("dotx", "fa-file-word-o"),
//    /**
//     * 图标
//     */
//    PPS("pps", "fa-file-powerpoint-o"),
//    /**
//     * 图标
//     */
//    PPT("ppt", "fa-file-powerpoint-o"),
//    /**
//     * 图标
//     */
//    POT("pot", "fa-file-powerpoint-o"),
//    /**
//     * 图标
//     */
//    PPTX("pptx", "fa-file-powerpoint-o"),
//    HTML("html", "fa-html-o"),
//    HTM("htm", "fa-html-o"),

//    JAR("jar", "fa-file-jar-o"),
//    WAR("war", "fa-file-war-o"),
    /**
     * 图标
     */
//    GZIP("gzip", "fa-file-zip-o"),
//    /**
//     * 图标
//     */
//    TAR("tar", "fa-file-zip-o"),
//    /**
//     * 图标
//     */
//    ZIP("zip", "fa-file-zip-o"),
//    /**
//     * 图标
//     */
//    RAR("rar", "fa-file-zip-o"),
//    /**
//     * 图标
//     */
//    TAR_GZ("tar.gz", "fa-file-zip-o"),

    /**
     * 图标
     */
    MP3("mp3", "el-icon-microphone"),
    /**
     * 图标
     */

    BMP("bmp", "el-icon-picture"),
    /**
     * 图标
     */
    JPEG("jpeg", "el-icon-picture"),
    /**
     * 图标
     */
    JPG("jpg", "el-icon-picture"),

    /**
     * 图标
     */
    PNG("png", "el-icon-picture"),
    /**
     * 图标
     */
    GIF("gif", "el-icon-picture"),
    /**
     * 图标
     */
    COD("cod", "el-icon-picture"),
    /**
     * 图标
     */
    IEF("ief", "el-icon-picture"),
    /**
     * 图标
     */
    SVG("svg", "el-icon-picture"),
    /**
     * 图标
     */
    ICO("ico", "el-icon-picture"),
    /**
     * 图标
     */
    ICON("icon", "el-icon-picture"),
    /**
     * 图标
     */
    AVI("avi", "el-icon-video-camera"),
    /**
     * 图标
     */
    MP4("mp4", "el-icon-video-camera"),
    /**
     * 图标
     */
    MOV("mov", "el-icon-video-camera"),
    /**
     * 图标
     */
    MPG("mpg", "el-icon-video-camera"),
    /**
     * 图标
     */
    WMV("wmv", "el-icon-video-camera"),
    /**
     * 图标
     */
    WAV("wav", "el-icon-video-camera"),

    BAT("wav", "el-icon-video-camera"),
    /**
     * 图标
     */
    OTHER("*", "el-icon-question"),
    ;

    private String ext;
    private String icon;

    public static IconType getIcon(String ext) {
        if (ext == null || ext.isEmpty()) {
            return OTHER;
        }
        for (IconType it : IconType.values()) {
            if (it.getExt().equalsIgnoreCase(ext)) {
                return it;
            }
        }
        return OTHER;
    }
}
