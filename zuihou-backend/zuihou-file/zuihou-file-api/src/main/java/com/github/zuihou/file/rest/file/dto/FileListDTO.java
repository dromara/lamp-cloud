package com.github.zuihou.file.rest.file.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author zuihou
 */
@Data
@AllArgsConstructor
public class FileListDTO implements Serializable {
    private List<FileDTO> list;
}
