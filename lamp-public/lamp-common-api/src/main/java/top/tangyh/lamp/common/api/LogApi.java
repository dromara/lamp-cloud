package top.tangyh.lamp.common.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.tangyh.basic.base.R;
import top.tangyh.basic.constant.Constants;
import top.tangyh.basic.model.log.OptLogDTO;

/**
 * 操作日志保存 API
 *
 * @author zuihou
 * @date 2019/07/02
 */
@FeignClient(name = "${" + Constants.PROJECT_PREFIX + ".feign.oauth-server:lamp-oauth-server}")
public interface LogApi {

    /**
     * 保存日志
     *
     * @param log 操作日志
     * @return 操作日志
     */
    @RequestMapping(value = "/optLog", method = RequestMethod.POST)
    R<Boolean> save(@RequestBody OptLogDTO log);

}
