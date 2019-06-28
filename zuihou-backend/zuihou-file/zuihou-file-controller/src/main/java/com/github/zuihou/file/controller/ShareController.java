package com.github.zuihou.file.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.base.id.IdGenerate;
import com.github.zuihou.common.enums.DateType;
import com.github.zuihou.file.dto.ShareFileDTO;
import com.github.zuihou.file.dto.SharePageDTO;
import com.github.zuihou.file.dto.ShareSaveDTO;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.entity.Share;
import com.github.zuihou.file.entity.ShareFile;
import com.github.zuihou.file.properties.FileServerProperties;
import com.github.zuihou.file.service.FileService;
import com.github.zuihou.file.service.ShareFileService;
import com.github.zuihou.file.service.ShareService;
import com.github.zuihou.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.utils.StringHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.github.zuihou.common.excode.ExceptionCode.BASE_VALID_PARAM;
import static com.github.zuihou.utils.BizAssert.assertNotNull;


/**
 * <p>
 * 分享目录表 前端控制器
 * </p>
 *
 * @author luosh
 * @since 2019-05-07
 */
@Validated
@Slf4j
@RestController
@RequestMapping("/share")
@Api(value = "Share", description = "分享目录表")
public class ShareController extends BaseController {

    private static final String NAME_SUFFIX = "等...";
    @Autowired
    private ShareService shareService;
    @Autowired
    private ShareFileService shareFileService;
    @Autowired
    private FileService fileService;
    @Autowired
    private IdGenerate<Long> idGenerate;
    @Autowired
    private FileServerProperties fileServerProperties;

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public R<IPage<Share>> page(@Valid Share data) {
        IPage<Share> page = getPage();
        Wrapper<Share> query = LbqWrapper.<Share>lambdaQuery()
                .eq(Share::getCreateUser, getUserId())
                .like(Share::getName, data.getName())
                .orderByDesc(Share::getUpdateTime);
        shareService.page(page, query);
        return success(page);
    }

    @ApiOperation(value = "分页查询分享的具体文件", notes = "分页查询分享的具体文件")
    @GetMapping("/page/file")
    @Validated(SuperEntity.OnlyQuery.class)
    public R<IPage<ShareFileDTO>> pageFile(@Valid SharePageDTO data) {

        return success(shareFileService.pageFile(getPage(), data));
    }

    @ApiOperation(value = "保存", notes = "保存不为空的字段")
    @ApiResponses({})
    @PostMapping
    public R<Share> save(@Valid @RequestBody ShareSaveDTO shareSave) {
        if (shareSave.getDateType() == null) {
            shareSave.setDateType(DateType.NUL);
        }

        Long userId = getUserId();
        String userName = getNickName();

        File firstFile = fileService.getById(shareSave.getIds().get(0));
        assertNotNull(BASE_VALID_PARAM.build("您要分享的文件不存在"), firstFile);

        Share share = new Share();
        //是否为分享加密-随机四位密码
        if (shareSave.getIsPwd()) {
            share.setPassword(RandomStringUtils.randomAlphanumeric(4));
        }
        switch (shareSave.getDateType()) {
            case NUL:
                break;
            case DAY:
                share.setExpireTime(LocalDateTime.now().plusDays(1));
                break;
            case WEEK:
                share.setExpireTime(LocalDateTime.now().plusDays(7));
                break;
            default:
                break;
        }
        Long id = idGenerate.generate();
        share.setId(id);
        String shareName = shareSave.getIds().size() == 1 ?
                firstFile.getSubmittedFileName() : firstFile.getSubmittedFileName() + NAME_SUFFIX;
        share.setName(shareName);
        share.setCreateUserName(StringHelper.encode(userName));
        share.setUrl(fileServerProperties.getShareFileUrl(id));
        share.setIcon(firstFile.getIcon());
        shareService.save(share);

        List<ShareFile> list = shareSave.getIds().stream()
                .map((fileId) -> ShareFile.builder()
                        .fileId(fileId).shareId(id).accountId(userId).build())
                .collect(Collectors.toList());
        shareFileService.saveBatch(list);

        return success(share);
    }


    @ApiOperation(value = "取消分享", notes = "取消分享")
    @DeleteMapping(value = "/cancel")
    public R<Boolean> cancel(@RequestParam(value = "ids[]") Long[] ids) {
        shareService.cancel(getUserId(), ids);
        return success(true);
    }

}
