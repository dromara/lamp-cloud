package top.tangyh.lamp.system.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import top.tangyh.basic.base.R;
import top.tangyh.basic.constant.Constants;
import top.tangyh.lamp.system.api.hystrix.DefUserApiFallback;

import java.util.List;

/**
 * 用户
 *
 * @author zuihou
 * @date 2019/07/02
 */
@FeignClient(name = "${" + Constants.PROJECT_PREFIX + ".feign.tenant-server:lamp-system-server}", fallback = DefUserApiFallback.class, path = "/defUser")
public interface DefUserApi {


    @PostMapping(value = "/findAllUserId")
    R<List<Long>> findAllUserId();

}
