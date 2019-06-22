package com.github.zuihou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 图标 常量
 *
 * @author zuihou
 * @date 2019-06-12
 */
@AllArgsConstructor
@Getter
public enum IconType {
    DIR("application/x-director", "fa-folder-o"),
    TXT("txt", "fa-file-text-o"),
    //    EXE("exe", "fa-exe-o"),
    PDF("pdf", "fa-file-pdf-o"),
    //    PSD("psd", "fa-ps-o"),
    XLX("xls", "fa-file-excel-o"),
    XLSX("xlsx", "fa-file-excel-o"),
    WPS("wps", "fa-file-word-o"),
    WPT("pwt", "fa-file-word-o"),
    JS("js", "fa-file-code-o"),
    JSP("js", "fa-file-code-o"),
    PROPERTIES("properties", "fa-file-code-o"),

    CSS("css", "fa-file-code-o"),
    //    SWF("swf", "fa-swf-o"),
    DOC("doc", "fa-file-word-o"),
    DOCX("docx", "fa-file-word-o"),
    DOTX("dotx", "fa-file-word-o"),
    PPS("pps", "fa-file-powerpoint-o"),
    PPT("ppt", "fa-file-powerpoint-o"),
    POT("pot", "fa-file-powerpoint-o"),
    PPTX("pptx", "fa-file-powerpoint-o"),
//    HTML("html", "fa-html-o"),
//    HTM("htm", "fa-html-o"),

    //    JAR("jar", "fa-file-jar-o"),
//    WAR("war", "fa-file-war-o"),
    GZIP("gzip", "fa-file-zip-o"),
    TAR("tar", "fa-file-zip-o"),
    ZIP("zip", "fa-file-zip-o"),
    RAR("rar", "fa-file-zip-o"),
    TAR_GZ("tar.gz", "fa-file-zip-o"),

    MP3("mp3", "fa-file-sound-o"),

    BMP("bmp", "fa-file-photo-o"),
    JPEG("jpeg", "fa-file-photo-o"),
    JPG("jpg", "fa-file-photo-o"),
    PNG("png", "fa-file-photo-o"),
    GIF("gif", "fa-file-photo-o"),
    COD("cod", "fa-file-photo-o"),
    IEF("ief", "fa-file-photo-o"),
    SVG("svg", "fa-file-photo-o"),
    ICO("ico", "fa-file-photo-o"),
    ICON("icon", "fa-file-photo-o"),

    AVI("avi", "fa-file-video-o"),
    MP4("mp4", "fa-file-video-o"),
    MOV("mov", "fa-file-video-o"),
    MPG("mpg", "fa-file-video-o"),
    WMV("wmv", "fa-file-video-o"),
    WAV("wav", "fa-file-video-o"),

//    BAT("wav", "fa-file-bat-o"),

    OTHER("*", "fa-question-circle-o"),
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
