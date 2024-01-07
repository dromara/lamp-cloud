package top.tangyh.lamp.userinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.tangyh.basic.base.R;
import top.tangyh.lamp.model.entity.system.SysUser;
import top.tangyh.lamp.model.vo.result.UserQuery;
import top.tangyh.lamp.userinfo.biz.EmployeeHelperBiz;

/**
 * @author zuihou
 * @date 2020年02月24日10:41:49
 */
@Component
public class UserResolverServiceImpl implements UserResolverService {
    @Autowired
    private EmployeeHelperBiz employeeBiz;

    @Override
    public R<SysUser> getById(UserQuery userQuery) {
        return R.success(employeeBiz.getSysUserById(userQuery));
    }
}
