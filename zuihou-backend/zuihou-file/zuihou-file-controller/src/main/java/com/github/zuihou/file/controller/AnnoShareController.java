package com.github.zuihou.file.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.Result;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.file.dto.FilePageReqDTO;
import com.github.zuihou.file.dto.ShareFileDTO;
import com.github.zuihou.file.dto.SharePageDTO;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.entity.Share;
import com.github.zuihou.file.manager.FileRestManager;
import com.github.zuihou.file.service.ShareFileService;
import com.github.zuihou.file.service.ShareService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.github.zuihou.common.excode.ExceptionCode.BASE_VALID_PARAM;
import static com.github.zuihou.file.excode.FileExceptionCode.SHARE_EXPIRE;
import static com.github.zuihou.file.excode.FileExceptionCode.SHARE_PWD_ERROR;
import static com.github.zuihou.file.excode.FileExceptionCode.SHARE_PWD_NULL;
import static com.github.zuihou.utils.BizAssert.assertNotNull;


/**
 * <p>
 * 分享目录表 前端控制器
 * </p>
 *
 * @author luosh
 * @since 2019-05-07
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/anno/share")
@Api(value = "Share", description = "分享目录表")
public class AnnoShareController extends BaseController {

    @Autowired
    private ShareService shareService;
    @Autowired
    private ShareFileService shareFileService;
    @Autowired
    private FileRestManager fileRestManager;

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<ShareFileDTO>> page(@Valid SharePageDTO data) {
        IPage<ShareFileDTO> page = this.getPage();
        Share share = shareService.getById(data.getId());
        assertNotNull(BASE_VALID_PARAM.build("分享的文件不存在"), share);

        if (share.getExpireTime() != null && share.getExpireTime().isBefore(LocalDateTime.now())) {
            return Result.validFail(SHARE_EXPIRE);
        }

        if (StringUtils.isNotEmpty(share.getPassword())) {
            if (StringUtils.isEmpty(data.getPassword())) {
                return Result.validFail(SHARE_PWD_NULL);
            }
            if (!share.getPassword().equals(data.getPassword())) {
                return Result.validFail(SHARE_PWD_ERROR);
            }
        }

        return Result.success(shareFileService.pageFile(page, data));
    }

    @ApiOperation(value = "获取文件分页", notes = "获取文件分页")
    @GetMapping(value = "/page/file")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<File>> pageFile(@Valid FilePageReqDTO data) {
        IPage<File> page = getPage();
        return Result.success(fileRestManager.page(page, data));
    }

    @ApiOperation(value = "下载一个文件或多个文件打包下载", notes = "下载一个文件或多个文件打包下载")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id， 没有请填 -1", dataType = "long", paramType = "query"),
    })
    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = "application/octet-stream")
    public void download(
            @ApiParam(name = "ids[]", value = "文件id 数组")
            @RequestParam(value = "ids[]") Long[] ids,
            @RequestParam(value = "userId", required = false) Long userId,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        fileRestManager.download(request, response, ids, userId);
    }


}
