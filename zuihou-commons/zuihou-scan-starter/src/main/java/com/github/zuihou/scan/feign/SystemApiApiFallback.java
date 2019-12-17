package com.github.zuihou.scan.feign;

import com.github.zuihou.base.R;
import com.github.zuihou.scan.model.SystemApiScanSaveDTO;


/**
 * 熔断
 *
 * @author zuihou
 * @date 2019/12/16
 */
public class SystemApiApiFallback implements SystemApiApi {

    @Override
    public R<Boolean> batchSave(SystemApiScanSaveDTO data) {
        return R.timeout();
    }
}
