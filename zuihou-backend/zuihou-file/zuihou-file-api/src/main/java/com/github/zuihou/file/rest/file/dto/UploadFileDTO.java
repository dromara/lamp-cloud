package com.github.zuihou.file.rest.file.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zuihou
 * @createTime 2018-01-27 20:08
 */
@Data
public class UploadFileDTO implements Serializable {
    private Long id;
    private String url;
}
