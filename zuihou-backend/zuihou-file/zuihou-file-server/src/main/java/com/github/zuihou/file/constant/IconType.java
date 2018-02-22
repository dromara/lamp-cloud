package com.github.zuihou.file.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IconType {
    DOC("application/msword", "fa-file-word-o"),
    TXT("text/plain", "fa-file-text-o"),
    EXE("application/octet-stream", "fa-exe-o"),
    PDF("application/pdf", "fa-file-pdf-o"),
    PS("application/postscript", "fa-ps-o"),
    EXCEL("application/vnd.ms-excel", "fa-file-excel-o"),
    WPS("application/vnd.ms-works", "fa-wps-o"),
    DIR("application/x-director", "fa-folder-o"),
    JS("application/x-javascript", "fa-js-o"),
    SWF("application/x-shockwave-flash", "fa-swf-o"),

    GZIP("application/x-gzip", "fa-file-zip-o"),
    TAR("application/x-tar", "fa-file-zip-o"),
    ZIP("application/zip", "fa-file-zip-o"),

    MP3("audio/mpeg", "fa-file-sound-o"),
    BMP("image/bmp", "fa-file-photo-o"),
    COD("image/cis-cod", "fa-file-photo-o"),
    IEF("image/ief", "fa-file-photo-o"),
    JPEG("image/jpeg", "fa-file-photo-o"),
    PNG("image/png", "fa-file-photo-o"),
    SVG("image/svg+xml", "fa-file-photo-o"),
    ICO("image/x-icon", "fa-file-photo-o"),

    CSS("text/css", "fa-css-o"),
    HTML("text/html", "fa-html-o"),
    MOV("video/quicktime", "fa-file-video-o"),
    AVI("video/x-msvideo", "fa-file-video-o"),

    DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "fa-file-word-o"),
    DOTX("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "fa-file-word-o"),
    XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "fa-file-excel-o"),
    PPT("application/vnd.ms-powerpoint", "fa-file-powerpoint-o"),

    PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation", "fa-pptx-o"),
    OTHER("*", "fa-question-circle-o"),;

    private String mime;
    private String icon;

    public static IconType getIcon(String mime) {
        if (mime == null || mime.isEmpty()) {
            return OTHER;
        }
        for (IconType it : IconType.values()) {
            if (it.getMime().equals(mime)) {
                return it;
            }
        }
        return OTHER;
    }
}
