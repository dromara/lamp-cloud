package com.github.zuihou.authority.service.auth;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.authority.entity.auth.User;

/**
 * <p>
 * 业务接口
 * 账号
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
public interface UserService extends IService<User> {

    Map<String, Object> getDataScopeById(Long userId);
}
