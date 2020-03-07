package com.github.zuihou.file.service;

import com.github.zuihou.base.service.SuperService;
import com.github.zuihou.file.domain.FileAttrDO;
import com.github.zuihou.file.domain.FileStatisticsDO;
import com.github.zuihou.file.dto.FileOverviewDTO;
import com.github.zuihou.file.dto.FileStatisticsAllDTO;
import com.github.zuihou.file.dto.FolderDTO;
import com.github.zuihou.file.dto.FolderSaveDTO;
import com.github.zuihou.file.entity.File;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 业务接口
 * 文件表
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
public interface FileService extends SuperService<File> {
    /**
     * 保存文件夹
     *
     * @param folderSaveDto 文件夹
     * @return
     */
    FolderDTO saveFolder(FolderSaveDTO folderSaveDto);

    /**
     * 根据文件id下载文件，并统计下载次数
     *
     * @param request  请求
     * @param response 响应
     * @param ids      文件id集合
     * @param userId   用户id
     * @throws Exception
     */
    void download(HttpServletRequest request, HttpServletResponse response,
                  Long[] ids, Long userId) throws Exception;

    /**
     * 根据文件id和用户id 删除文件或者文件夹
     *
     * @param userId 用户id
     * @param ids    文件id集合
     * @return
     */
    Boolean removeList(Long userId, List<Long> ids);

    /**
     * 根据文件夹id查询
     *
     * @param folderId
     * @return
     */
    FileAttrDO getFileAttrDo(Long folderId);

    /**
     * 文件上传
     *
     * @param simpleFile 文件
     * @param folderId   文件夹id
     * @return
     */
    File upload(MultipartFile simpleFile, Long folderId);

    /**
     * 首页概览
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    FileOverviewDTO findOverview(Long userId, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 首页个人文件发展概览
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    FileStatisticsAllDTO findAllByDate(Long userId, LocalDateTime startTime, LocalDateTime endTime);


    /**
     * 按照 数据类型分类查询 当前人的所有文件的数量和大小
     *
     * @param userId
     * @return
     */
    List<FileStatisticsDO> findAllByDataType(Long userId);

    /**
     * 查询下载排行前20的文件
     *
     * @param userId
     * @return
     */
    List<FileStatisticsDO> downTop20(Long userId);

    /**
     * 根据日期查询，特定类型的数量和大小
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    FileStatisticsAllDTO findNumAndSizeToTypeByDate(Long userId, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 根据日期查询下载大小
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    FileStatisticsAllDTO findDownSizeByDate(Long userId, LocalDateTime startTime,
                                            LocalDateTime endTime);
}
