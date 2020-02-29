package com.github.zuihou.file.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.file.constant.FileConstants;
import com.github.zuihou.file.dto.FilePageReqDTO;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.github.zuihou.utils.StrPool.DEF_PARENT_ID;


/**
 * 文件 公共代码 管理类
 *
 * @author zuihou
 * @date 2019/05/21
 */
@Component
public class FileRestManager {
    @Autowired
    private FileService fileService;

    public IPage<File> page(IPage<File> page, FilePageReqDTO filePageReq) {
        //查询文件分页数据
        Long userId = BaseContextHandler.getUserId();

        //类型和文件夹id同时为null时， 表示查询 全部文件
        if (filePageReq.getFolderId() == null && filePageReq.getDataType() == null) {
            filePageReq.setFolderId(DEF_PARENT_ID);
        }

        QueryWrapper<File> query = new QueryWrapper<>();
        LambdaQueryWrapper<File> lambdaQuery = query.lambda()
                .eq(File::getIsDelete, false)
                .eq(filePageReq.getDataType() != null, File::getDataType, filePageReq.getDataType())
                .eq(filePageReq.getFolderId() != null, File::getFolderId, filePageReq.getFolderId())
                .like(StringUtils.isNotEmpty(filePageReq.getSubmittedFileName()), File::getSubmittedFileName, filePageReq.getSubmittedFileName())
                .eq(userId != null && userId != 0, File::getCreateUser, userId);

        query.orderByDesc(String.format("case when %s='DIR' THEN 1 else 0 end", FileConstants.DATA_TYPE));
        lambdaQuery.orderByDesc(File::getCreateTime);

        fileService.page(page, lambdaQuery);
        return page;
    }

    public void download(HttpServletRequest request, HttpServletResponse response, Long[] ids, Long userId) throws Exception {
        userId = userId == null || userId <= 0 ? BaseContextHandler.getUserId() : userId;
        fileService.download(request, response, ids, userId);
    }
}
