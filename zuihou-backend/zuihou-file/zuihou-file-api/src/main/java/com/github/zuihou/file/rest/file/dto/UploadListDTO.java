package com.github.zuihou.file.rest.file.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zuihou
 * @createTime 2018-01-27 20:03
 */
@Data
public class UploadListDTO implements Serializable {
    private List<UploadFileDTO> list = new ArrayList<>();
}


