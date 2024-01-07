package top.tangyh.lamp.file.api.fallback;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.tangyh.basic.base.R;
import top.tangyh.lamp.file.api.FileApi;
import top.tangyh.lamp.file.enumeration.FileStorageType;
import top.tangyh.lamp.file.vo.result.FileResultVO;

/**
 * 熔断
 *
 * @author zuihou
 * @date 2019/07/25
 */
@Component
public class FileApiFallback implements FileApi {
    @Override
    public R<FileResultVO> upload(MultipartFile file, String bizType, String bucket, FileStorageType storageType) {
        return R.timeout();
    }

}
