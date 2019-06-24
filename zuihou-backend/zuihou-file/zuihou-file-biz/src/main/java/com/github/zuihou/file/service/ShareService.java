package com.github.zuihou.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.file.entity.Share;

/**
 * <p>
 * 业务接口
 * 分享目录表
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
public interface ShareService extends IService<Share> {
    /**
     * 取消分享
     *
     * @param userId
     * @param ids
     * @return
     * @author tangyh
     * @date 2019-05-08 08:44
     */
    void cancel(Long userId, Long[] ids);
}
