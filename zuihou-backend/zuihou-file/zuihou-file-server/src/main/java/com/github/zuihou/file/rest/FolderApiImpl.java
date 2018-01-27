package com.github.zuihou.file.rest;

import com.github.zuihou.file.repository.file.service.FolderService;
import com.github.zuihou.file.rest.file.api.FolderApi;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zuihou
 * @createTime 2018-01-26 23:02
 */
@Api(value = "API - FolderApiImpl", description = "文件夹管理")
@RestController
@RequestMapping("folder")
@Slf4j
public class FolderApiImpl implements FolderApi {
    @Autowired
    private FolderService folderService;
}
