package com.github.zuihou.file.rest.file.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DataType {
    DIR("文件夹"),
    IMAGE("图片"),
    VIDEO("视频"),
    AUDIO("音频"),
    DOC("文档"),
    OTHER("其他"),
    ;

    private String describe;


}
