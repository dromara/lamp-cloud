/*
 * Copyright 2006-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package top.tangyh.lamp.oauth.granter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import top.tangyh.basic.base.R;
import top.tangyh.basic.boot.utils.WebUtils;
import top.tangyh.basic.cache.redis2.CacheResult;
import top.tangyh.basic.cache.repository.CacheOps;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.exception.UnauthorizedException;
import top.tangyh.basic.exception.code.ExceptionCode;
import top.tangyh.basic.jwt.TokenHelper;
import top.tangyh.basic.jwt.model.JwtInfo;
import top.tangyh.basic.jwt.model.Token;
import top.tangyh.basic.jwt.properties.JwtProperties;
import top.tangyh.basic.jwt.utils.Base64Util;
import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.SpringUtils;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.basic.utils.TreeUtil;
import top.tangyh.lamp.base.entity.user.BaseEmployee;
import top.tangyh.lamp.base.entity.user.BaseOrg;
import top.tangyh.lamp.base.service.user.BaseEmployeeService;
import top.tangyh.lamp.base.service.user.BaseOrgService;
import top.tangyh.lamp.base.vo.result.user.BaseEmployeeResultVO;
import top.tangyh.lamp.common.cache.common.TokenUserIdCacheKeyBuilder;
import top.tangyh.lamp.common.properties.SystemProperties;
import top.tangyh.lamp.file.service.AppendixService;
import top.tangyh.lamp.model.enumeration.StateEnum;
import top.tangyh.lamp.model.enumeration.base.UserStatusEnum;
import top.tangyh.lamp.oauth.event.LoginEvent;
import top.tangyh.lamp.oauth.event.model.LoginStatusDTO;
import top.tangyh.lamp.oauth.vo.param.LoginParamVO;
import top.tangyh.lamp.oauth.vo.result.LoginResultVO;
import top.tangyh.lamp.system.entity.system.DefClient;
import top.tangyh.lamp.system.entity.tenant.DefUser;
import top.tangyh.lamp.system.enumeration.system.LoginStatusEnum;
import top.tangyh.lamp.system.service.system.DefClientService;
import top.tangyh.lamp.system.service.tenant.DefUserService;

import java.time.LocalDateTime;
import java.util.List;

import static top.tangyh.basic.context.ContextConstants.CLIENT_KEY;

/**
 * 验证码TokenGranter
 *
 * @author zuihou
 */
@Slf4j
public abstract class AbstractTokenGranter implements TokenGranter {
    @Autowired
    protected TokenHelper tokenUtil;
    @Autowired
    protected JwtProperties jwtProperties;
    @Autowired
    protected CacheOps cacheOps;
    @Autowired
    protected SystemProperties systemProperties;
    @Autowired
    protected AppendixService appendixService;
    @Autowired
    protected DefClientService defClientService;
    @Autowired
    protected DefUserService defUserService;
    @Autowired
    protected BaseEmployeeService baseEmployeeService;
    @Autowired
    protected BaseOrgService baseOrgService;


    @Override
    public R<LoginResultVO> login(LoginParamVO loginParam) {
        // 0. 参数校验
        R<LoginResultVO> result = checkParam(loginParam);
        if (!result.getIsSuccess()) {
            return result;
        }
        result = checkClient();
        if (!result.getIsSuccess()) {
            return result;
        }

        // 1. 验证码
        result = checkCaptcha(loginParam);
        if (!result.getIsSuccess()) {
            return result;
        }

        // 2. 查找用户
        DefUser defUser = getUser(loginParam);

        // 3. 判断密码
        result = checkUserPassword(loginParam, defUser);
        if (!result.getIsSuccess()) {
            return result;
        }

        // 4. 检查用户状态
        result = checkUserState(defUser);
        if (!result.getIsSuccess()) {
            return result;
        }

        // 5. 获取员工和租户
        Employee employee = getEmployee(defUser);

        // 6. 查询单位、部门
        Org org = findOrg(employee);

        // 7. 封装token
        LoginResultVO loginResultVO = buildResult(employee, org, defUser);
        LoginStatusDTO loginStatus = LoginStatusDTO.success(defUser.getId(), employee.getEmployeeId());
        SpringUtils.publishEvent(new LoginEvent(loginStatus));
        return R.success(loginResultVO);
    }

    /**
     * 检查参数
     *
     * @param loginParam 登录参数
     * @return top.tangyh.basic.base.R<top.tangyh.lamp.oauth.vo.result.LoginResultVO>
     * @author tangyh
     * @date 2022/10/5 12:38 PM
     * @create [2022/10/5 12:38 PM ] [tangyh] [初始创建]
     */
    protected abstract R<LoginResultVO> checkParam(LoginParamVO loginParam);

    /**
     * 检测客户端
     *
     * @return top.tangyh.basic.base.R<top.tangyh.lamp.oauth.vo.result.LoginResultVO>
     * @author tangyh
     * @date 2022/10/5 12:38 PM
     * @create [2022/10/5 12:38 PM ] [tangyh] [初始创建]
     */
    protected R<LoginResultVO> checkClient() {
        String basicHeader = JakartaServletUtil.getHeader(WebUtils.request(), CLIENT_KEY, StrPool.UTF_8);
        String[] client = Base64Util.getClient(basicHeader);
        DefClient defClient = defClientService.getClient(client[0], client[1]);

        if (defClient == null) {
            return R.fail("请在.env文件中配置正确的客户端ID或者客户端秘钥");
        }
        if (!defClient.getState()) {
            return R.fail("客户端[%s]已被禁用", defClient.getClientId());
        }
        return R.success(null);
    }


    /**
     * 检查验证码
     *
     * @param loginParam 登录参数
     * @return top.tangyh.basic.base.R<top.tangyh.lamp.oauth.vo.result.LoginResultVO>
     * @author tangyh
     * @date 2022/10/5 12:38 PM
     * @create [2022/10/5 12:38 PM ] [tangyh] [初始创建]
     */
    protected R<LoginResultVO> checkCaptcha(LoginParamVO loginParam) {
        return R.success(null);
    }

    /**
     * 查询用户
     *
     * @param loginParam 登录参数
     * @return top.tangyh.lamp.system.entity.tenant.DefUser
     * @author tangyh
     * @date 2022/10/5 12:38 PM
     * @create [2022/10/5 12:38 PM ] [tangyh] [初始创建]
     */
    protected abstract DefUser getUser(LoginParamVO loginParam);

    /**
     * 检查用户账号密码是否正确
     *
     * @param loginParam loginParam
     * @param user       user
     * @return top.tangyh.basic.base.R<top.tangyh.lamp.oauth.vo.result.LoginResultVO>
     * @author tangyh
     * @date 2022/10/5 12:38 PM
     * @create [2022/10/5 12:38 PM ] [tangyh] [初始创建]
     */

    protected R<LoginResultVO> checkUserPassword(LoginParamVO loginParam, DefUser user) {
        return R.success(null);
    }

    /**
     * 检查用户状态是否正常
     *
     * @param user user
     * @return top.tangyh.basic.base.R<top.tangyh.lamp.oauth.vo.result.LoginResultVO>
     * @author tangyh
     * @date 2022/10/5 12:38 PM
     * @create [2022/10/5 12:38 PM ] [tangyh] [初始创建]
     */
    protected R<LoginResultVO> checkUserState(DefUser user) {
        // 用户被禁用
        if (!user.getState()) {
            String msg = "您已被禁用，请联系管理员开通账号！";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), LoginStatusEnum.USER_ERROR, msg)));
            return R.fail(msg);
        }
        return R.success(null);
    }

    /**
     * 查询员工信息
     *
     * @param defUser 用户信息
     * @return top.tangyh.lamp.oauth.granter.AbstractTokenGranter.Employee
     * @author tangyh
     * @date 2022/10/5 12:38 PM
     * @create [2022/10/5 12:38 PM ] [tangyh] [初始创建]
     */
    protected Employee getEmployee(DefUser defUser) {
        // 用户被禁用无法登陆， 员工被禁用无法访问当前企业的数据， 企业被禁用所有员工无法
        List<BaseEmployeeResultVO> employeeList = baseEmployeeService.listEmployeeByUserId(defUser.getId());
        Long employeeId = null;
        Long userId = defUser.getId();
        UserStatusEnum userStatus = UserStatusEnum.NORMAL;
        if (CollUtil.isNotEmpty(employeeList)) {
            BaseEmployeeResultVO defaultEmployee = employeeList.get(0);
            // 正常状态
            if (StateEnum.ENABLE.eq(defaultEmployee.getState())) {
                employeeId = defaultEmployee.getId();
            } else {
                userStatus = UserStatusEnum.USER_DISABLE;
            }
        } else {
            userStatus = UserStatusEnum.USER_DISABLE;
        }
        log.info("userStatus={}, userId={}, employeeId={}", userStatus, userId, employeeId);
        return Employee.builder().employeeId(employeeId).build();
    }

    /**
     * 查询单位和部门信息
     *
     * @param employee 员工信息
     * @return top.tangyh.lamp.oauth.granter.AbstractTokenGranter.Org
     * @author tangyh
     * @date 2022/10/5 12:40 PM
     * @create [2022/10/5 12:40 PM ] [tangyh] [初始创建]
     */
    protected Org findOrg(Employee employee) {
        Long employeeId = employee.getEmployeeId();

        // 查询单位、部门信息
        // 所属单位
        Long currentCompanyId = null;
        Long currentTopCompanyId = null;
        Long currentDeptId = null;
        if (employeeId != null) {
            BaseEmployee baseEmployee = baseEmployeeService.getByIdCache(employeeId);
            if (baseEmployee == null) {
                return Org.builder()
                        .currentTopCompanyId(null)
                        .currentCompanyId(null)
                        .currentDeptId(null).build();
            }
            BaseOrg defaultCompany;
            boolean flag = false;
            if (baseEmployee.getLastCompanyId() != null) {
                currentCompanyId = baseEmployee.getLastCompanyId();

                defaultCompany = baseOrgService.getByIdCache(currentCompanyId);
            } else {
                // 上次登录的单位
                List<BaseOrg> companyList = baseOrgService.findCompanyByEmployeeId(employeeId);
                defaultCompany = baseOrgService.getDefaultOrg(companyList, baseEmployee.getLastCompanyId());

                currentCompanyId = defaultCompany != null ? defaultCompany.getId() : null;

                baseEmployee.setLastCompanyId(currentCompanyId);
                flag = currentCompanyId != null;
            }

            if (defaultCompany != null) {
                Long rootId = TreeUtil.getTopNodeId(defaultCompany.getTreePath());
                BaseOrg rootCompany;
                if (rootId != null) {
                    rootCompany = baseOrgService.getByIdCache(rootId);
                } else {
                    rootCompany = defaultCompany;
                }
                currentTopCompanyId = rootCompany != null ? rootCompany.getId() : null;
            }

            // 上次登录的部门
            if (baseEmployee.getLastDeptId() != null) {
                currentDeptId = baseEmployee.getLastDeptId();
            } else {
                List<BaseOrg> deptList = baseOrgService.findDeptByEmployeeId(employeeId, currentCompanyId);
                BaseOrg defaultDept = baseOrgService.getDefaultOrg(deptList, baseEmployee.getLastDeptId());

                currentDeptId = defaultDept != null ? defaultDept.getId() : null;
                baseEmployee.setLastDeptId(currentDeptId);

                flag = flag || currentDeptId != null;
            }

            if (flag) {
                baseEmployeeService.updateById(baseEmployee);
            }
        }
        return Org.builder()
                .currentTopCompanyId(currentTopCompanyId)
                .currentCompanyId(currentCompanyId)
                .currentDeptId(currentDeptId).build();
    }

    /**
     * 构建返回值
     *
     * @param employee 员工信息
     * @param org      机构信息
     * @param defUser  用户信息
     * @return top.tangyh.lamp.oauth.vo.result.LoginResultVO
     * @author tangyh
     * @date 2022/10/5 12:41 PM
     * @create [2022/10/5 12:41 PM ] [tangyh] [初始创建]
     */
    protected LoginResultVO buildResult(Employee employee, Org org, DefUser defUser) {
        JwtInfo userInfo = new JwtInfo(defUser.getId(), employee.getEmployeeId(), org.getCurrentCompanyId(),
                org.getCurrentTopCompanyId(), org.getCurrentDeptId(), UUID.randomUUID().toString(true));
        Token token = tokenUtil.buildToken(userInfo, null);

        LoginResultVO resultVO = new LoginResultVO();
        BeanUtil.copyProperties(token, resultVO);
        resultVO.setUuid(userInfo.getUuid());

        CacheKey cacheKey = TokenUserIdCacheKeyBuilder.builder(resultVO.getUuid(), jwtProperties.getExpire());
        cacheOps.set(cacheKey, resultVO.getToken());

        log.info("用户：{}  {} 登录成功", defUser.getUsername(), defUser.getNickName());
        return resultVO;
    }

    @Override
    public R<Boolean> logout(String tokenStr) {
        try {
            Token token = tokenUtil.parseToken(tokenStr);
            // 根据系统参数，做一些其他操作
            CacheKey cacheKey = TokenUserIdCacheKeyBuilder.builder(token.getUuid(), jwtProperties.getExpire());
            cacheOps.del(cacheKey);
        } catch (Exception e) {
            log.debug("token已经过期，无需清理缓存");
        }
        return R.success(true);
    }

    @Override
    public LoginResultVO switchOrg(Long companyId, Long deptId) {
        Token token = tokenUtil.parseTokenSneaky(ContextUtil.getToken());
        if (token == null) {
            throw UnauthorizedException.wrap(ExceptionCode.JWT_TOKEN_EXPIRED);
        }
        CacheKey cacheKey = TokenUserIdCacheKeyBuilder.builder(token.getUuid(), jwtProperties.getExpire());
        CacheResult<String> oldKeyVal = cacheOps.get(cacheKey);
        if (StrUtil.isEmpty(oldKeyVal.getValue())) {
            throw UnauthorizedException.wrap(ExceptionCode.JWT_TOKEN_EXPIRED);
        }
        Long userId = ContextUtil.getUserId();
        DefUser defUser = defUserService.getByIdCache(userId);
        if (defUser == null) {
            throw UnauthorizedException.wrap(ExceptionCode.JWT_TOKEN_EXPIRED);
        }

        if (!Convert.toBool(defUser.getState(), true)) {
            throw UnauthorizedException.wrap(ExceptionCode.JWT_USER_DISABLE);
        }

        BaseEmployee baseEmployee = baseEmployeeService.getEmployeeByUser(userId);
        ArgumentAssert.notNull(baseEmployee, "您不属于该公司，无法切换");
        baseEmployee.setLastCompanyId(companyId);
        baseEmployee.setLastDeptId(deptId);
        baseEmployeeService.updateAllById(baseEmployee);

        BaseOrg rootCompany = null;
        if (companyId != null) {
            BaseOrg company = baseOrgService.getByIdCache(companyId);
            if (company != null) {
                Long rootId = TreeUtil.getTopNodeId(company.getTreePath());
                if (rootId != null) {
                    rootCompany = baseOrgService.getByIdCache(rootId);
                } else {
                    rootCompany = company;
                }
            }
        }

        Employee e = Employee.builder()
                .employeeId(baseEmployee.getId())
                .build();

        Org org = Org.builder()
                .currentTopCompanyId(rootCompany != null ? rootCompany.getId() : null)
                .currentCompanyId(companyId)
                .currentDeptId(deptId)
                .build();

        LoginResultVO loginResultVO = buildResult(e, org, defUser);

        LoginStatusDTO loginStatus = LoginStatusDTO.switchOrg(defUser.getId(), baseEmployee.getId());
        SpringUtils.publishEvent(new LoginEvent(loginStatus));
        return loginResultVO;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    private static class Employee {
        private Long employeeId;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    private static class Org {
        private Long currentCompanyId;
        private Long currentTopCompanyId;
        private Long currentDeptId;
    }

}
