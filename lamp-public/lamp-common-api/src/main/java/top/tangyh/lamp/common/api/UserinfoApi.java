package top.tangyh.lamp.common.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import top.tangyh.basic.base.R;
import top.tangyh.basic.constant.Constants;
import top.tangyh.basic.interfaces.echo.LoadService;
import top.tangyh.lamp.model.entity.system.SysUser;
import top.tangyh.lamp.model.vo.result.UserQuery;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 用户
 *
 * @author zuihou
 * @date 2019/07/02
 */
@FeignClient(name = "${" + Constants.PROJECT_PREFIX + ".feign.oauth-server:lamp-oauth-server}")
public interface UserinfoApi extends LoadService {
    /**
     * 根据id查询实体
     *
     * @param ids 唯一键（可能不是主键ID)
     * @return
     */
    @Override
    @PostMapping("/echo/user/findByIds")
    Map<Serializable, Object> findByIds(@RequestParam(value = "ids") Set<Serializable> ids);


    /**
     * 根据id 查询用户详情
     *
     * @param userQuery 查询条件
     * @return 系统用户
     */
    @PostMapping(value = "/anyone/getSysUserById")
    R<SysUser> getById(@RequestBody UserQuery userQuery);
}
