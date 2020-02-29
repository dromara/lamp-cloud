package com.github.zuihou.file.api.fallback;

import com.github.zuihou.base.R;
import com.github.zuihou.file.api.FileGeneralApi;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 查询通用数据
 *
 * @author zuihou
 * @date 2019/07/26
 */
@Component
public class FileGeneralApiFallback implements FileGeneralApi {
    @Override
    public R<Map<String, Map<String, String>>> enums() {
        return R.timeout();
    }
}
