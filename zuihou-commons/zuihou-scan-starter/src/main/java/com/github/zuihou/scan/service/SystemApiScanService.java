package com.github.zuihou.scan.service;

import com.github.zuihou.scan.model.SystemApiScanSaveDTO;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/12/17
 */

public interface SystemApiScanService {
    /**
     * 批量保存
     *
     * @param data
     * @return
     */
    Boolean batchSave(SystemApiScanSaveDTO data);
}
