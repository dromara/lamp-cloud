package com.tangyh.lamp.authority.service.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tangyh.basic.base.request.PageParams;
import com.tangyh.basic.base.service.SuperCacheService;
import com.tangyh.basic.database.mybatis.conditions.query.LbqWrapper;
import com.tangyh.basic.model.LoadService;
import com.tangyh.basic.security.feign.UserQuery;
import com.tangyh.basic.security.model.SysUser;
import com.tangyh.lamp.authority.dto.auth.GlobalUserPageDTO;
import com.tangyh.lamp.authority.dto.auth.UserUpdatePasswordDTO;
import com.tangyh.lamp.authority.entity.auth.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 业务接口
 * 账号
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
public interface UserService extends SuperCacheService<User>, LoadService {

    /**
     * 根据用户id 查询数据范围
     *
     * @param userId 用户id
     * @return 数据范围
     */
    Map<String, Object> getDataScopeById(Long userId);

    /**
     * 根据角色id 和 账号或名称 查询角色关联的用户
     * <p>
     * 注意，该接口只返回 id，账号，姓名，手机，性别
     *
     * @param roleId  角色id
     * @param keyword 账号或名称
     * @return 用户
     */
    List<User> findUserByRoleId(Long roleId, String keyword);

    /**
     * 检测账号是否存在
     *
     * @param id      id
     * @param account 账号
     * @return true 表示存在
     */
    boolean check(Long id, String account);

    /**
     * 修改输错密码的次数
     *
     * @param id 用户Id
     */
    void incrPasswordErrorNumById(Long id);

    /**
     * 根据账号查询用户
     *
     * @param account 账号
     * @return 用户
     */
    User getByAccount(String account);


    /**
     * 保存
     *
     * @param user 用户
     * @return 用户
     */
    User saveUser(User user);

    /**
     * 重置密码
     *
     * @param model 用户参数
     * @return 是否成功
     */
    boolean reset(UserUpdatePasswordDTO model);

    /**
     * 修改
     *
     * @param user 用户
     * @return 用户
     */
    User updateUser(User user);

    /**
     * 删除
     *
     * @param ids 用户id
     * @return 是否成功
     */
    boolean remove(List<Long> ids);

    /**
     * 数据权限 分页
     *
     * @param page    分页对象
     * @param wrapper 参数包装器
     * @return 分页数据
     */
    IPage<User> findPage(IPage<User> page, LbqWrapper<User> wrapper);

    /**
     * 修改密码
     *
     * @param data 用户信息
     * @return 是否成功
     */
    Boolean updatePassword(UserUpdatePasswordDTO data);

    /**
     * 重置密码错误次数
     *
     * @param id 用户id
     * @return 重置了多少行
     */
    int resetPassErrorNum(Long id);

    /**
     * 根据id 查询用户详情
     *
     * @param id    用户Id
     * @param query 查询参数
     * @return 系统用户
     */
    SysUser getSysUserById(Long id, UserQuery query);

    /**
     * 查询所有用户的id
     *
     * @return 用户id
     */
    List<Long> findAllUserId();

    /**
     * 初始化用户
     *
     * @param user 用户
     * @return 是否成功
     */
    boolean initUser(User user);

    /**
     * 联合查询
     *
     * @param ids 用户Id
     * @return 用户
     */
    List<User> findUser(Set<Serializable> ids);

    /**
     * 根据id集合查询用户
     *
     * @param ids id
     * @return 用户
     */
    List<User> findUserById(List<Long> ids);

    /**
     * 查询具有超级管理员权限的用户
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return 分页数据
     */
    IPage<User> pageByRole(IPage<User> page, PageParams<GlobalUserPageDTO> params);

    /**
     * 今天注册的用户数
     *
     * @return
     */
    Integer todayUserCount();
}
