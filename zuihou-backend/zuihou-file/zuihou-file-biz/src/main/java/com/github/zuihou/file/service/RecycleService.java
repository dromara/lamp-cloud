package com.github.zuihou.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.file.entity.Recycle;

/**
 * <p>
 * 业务接口
 * 文件回收站
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
public interface RecycleService extends IService<Recycle> {
    /**
     * 批量删除回收站和源文件
     *
     * @param userId
     * @param ids
     */
    void deleteBatch(Long userId, Long[] ids);

    /**
     * 清空回收站
     *
     * @param userId 指定用户
     * @return
     * @author zuihou
     * @date 2019-05-07 16:15
     */
    void clear(Long userId);

    /**
     * 还原
     *
     * @param userId
     * @param ids
     */
    void reset(Long userId, Long[] ids);
}
