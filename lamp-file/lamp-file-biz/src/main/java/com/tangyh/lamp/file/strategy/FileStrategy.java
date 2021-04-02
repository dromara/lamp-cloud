package com.tangyh.lamp.file.strategy;

import com.tangyh.lamp.file.domain.FileDeleteDO;
import com.tangyh.lamp.file.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件策略接口
 *
 * @author zuihou
 * @date 2019/06/17
 */
public interface FileStrategy {
    /**
     * 文件上传
     *
     * @param file 文件
     * @return 文件对象
     */
    Attachment upload(MultipartFile file);

    /**
     * 删除源文件
     *
     * @param list 列表
     * @return 是否成功
     */
    boolean delete(List<FileDeleteDO> list);

    /**
     * 根据路径获取访问地址
     *
     * @param paths
     * @param expiry
     * @return
     */
    List<String> getUrls(List<String> paths, Integer expiry);

    /**
     * 根据路径获取访问地址
     *
     * @param path
     * @param expiry
     * @return
     */
    String getUrl(String path, Integer expiry);
}
