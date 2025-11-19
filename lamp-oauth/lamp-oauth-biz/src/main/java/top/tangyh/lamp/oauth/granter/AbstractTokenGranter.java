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

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.temp.SaTempUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.json.JSONObject;
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
import top.tangyh.lamp.common.cache.auth.TempAdminCacheKeyBuilder;
import top.tangyh.lamp.common.properties.SystemProperties;
import top.tangyh.lamp.common.utils.Base64Util;
import top.tangyh.lamp.model.enumeration.StateEnum;
import top.tangyh.lamp.model.enumeration.base.OrgTypeEnum;
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

import java.util.List;

import static top.tangyh.basic.context.ContextConstants.CLIENT_KEY;
import static top.tangyh.basic.context.ContextConstants.JWT_KEY_COMPANY_ID;
import static top.tangyh.basic.context.ContextConstants.JWT_KEY_DEPT_ID;
import static top.tangyh.basic.context.ContextConstants.JWT_KEY_EMPLOYEE_ID;
import static top.tangyh.basic.context.ContextConstants.JWT_KEY_TOP_COMPANY_ID;
import static top.tangyh.basic.context.ContextConstants.JWT_KEY_USER_ID;

/**
 * 验证码TokenGranter
 *
 * @author zuihou
 */
@Slf4j
public abstract class AbstractTokenGranter implements TokenGranter {

    @Autowired
    protected SystemProperties systemProperties;
    @Autowired
    protected DefClientService defClientService;
    @Autowired
    protected DefUserService defUserService;
    @Autowired
    protected BaseEmployeeService baseEmployeeService;
    @Autowired
    protected BaseOrgService baseOrgService;
    @Autowired
    protected SaTokenConfig saTokenConfig;
    @Autowired
    protected CacheOps cacheOps;


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

        boolean checkPassword = true;
        // 演示环境专用代码，可直接删除 start
        if (defUser == null) {
            CacheKey cacheKey = TempAdminCacheKeyBuilder.builder(loginParam.getUsername());
            CacheKey typeKey = TempAdminCacheKeyBuilder.builder(loginParam.getUsername(), "type");
            CacheResult<String> cacheResult = cacheOps.get(cacheKey);
            CacheResult<String> typeKeyResult = cacheOps.get(typeKey);
            if (StrUtil.equals(cacheResult.getValue(), loginParam.getPassword())) {
                if (StrUtil.equals(typeKeyResult.getValue(), "admin")) {
                    // 管理员账号
                    defUser = defUserService.getUserByUsername("lamp");
                } else {
                    // 普通账号
                    defUser = defUserService.getUserByUsername("test1");
                }
                checkPassword = false;
            }
        }
        // 演示环境专用代码，可直接删除 end

        if (checkPassword) {
            // 3. 判断密码
            result = checkUserPassword(loginParam, defUser);
            if (!result.getIsSuccess()) {
                return result;
            }
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
     * @param tenantId       tenantId
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

        // 当前所属部门
        Long currentDeptId = null;
        // 当前所属单位
        Long currentCompanyId = null;
        // 当前所属顶级单位
        Long currentTopCompanyId = null;
        if (employeeId != null) {
            BaseEmployee baseEmployee = baseEmployeeService.getByIdCache(employeeId);

            // 当前用户尚不属于任意租户
            if (baseEmployee == null) {
                return Org.builder()
                        .currentTopCompanyId(null)
                        .currentCompanyId(null)
                        .currentDeptId(null).build();
            }

            boolean flag = false;
            // 上次登录的部门
            if (baseEmployee.getLastDeptId() != null) {
                currentDeptId = baseEmployee.getLastDeptId();
                // TODO 若用户变更了部门，是否有问题
            } else {
                // 上次登录部门为空，则随机选择一个部门
                List<BaseOrg> deptList = baseOrgService.findDeptByEmployeeId(employeeId, null);
                BaseOrg defaultDept = baseOrgService.getDefaultOrg(deptList, null);

                currentDeptId = defaultDept != null ? defaultDept.getId() : null;
                baseEmployee.setLastDeptId(currentDeptId);

                flag = currentDeptId != null;
            }

            BaseOrg defaultCompany;
            if (baseEmployee.getLastCompanyId() != null) {
                currentCompanyId = baseEmployee.getLastCompanyId();

                defaultCompany = baseOrgService.getByIdCache(currentCompanyId);
            } else {
                if (currentDeptId != null) {
                    defaultCompany = baseOrgService.getCompanyByDeptId(currentDeptId);
                } else {
                    // currentDeptId 为空，员工可能直接挂在单位下、也可能挂不属于任何部门
                    List<BaseOrg> companyList = baseOrgService.findCompanyByEmployeeId(employeeId);
                    defaultCompany = baseOrgService.getDefaultOrg(companyList, baseEmployee.getLastCompanyId());
                }

                currentCompanyId = defaultCompany != null ? defaultCompany.getId() : null;
                baseEmployee.setLastCompanyId(currentCompanyId);
                flag = flag || currentCompanyId != null;

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
        //此登录接口登录web端
        StpUtil.login(defUser.getId(), "PC");
        SaSession tokenSession = StpUtil.getTokenSession();
        tokenSession.setLoginId(defUser.getId());
        if (org.getCurrentTopCompanyId() != null) {
            tokenSession.set(JWT_KEY_TOP_COMPANY_ID, org.getCurrentTopCompanyId());
        } else {
            tokenSession.delete(JWT_KEY_TOP_COMPANY_ID);
        }
        if (org.getCurrentCompanyId() != null) {
            tokenSession.set(JWT_KEY_COMPANY_ID, org.getCurrentCompanyId());
        } else {
            tokenSession.delete(JWT_KEY_COMPANY_ID);
        }
        if (org.getCurrentDeptId() != null) {
            tokenSession.set(JWT_KEY_DEPT_ID, org.getCurrentDeptId());
        } else {
            tokenSession.delete(JWT_KEY_DEPT_ID);
        }
        if (employee.getEmployeeId() != null) {
            tokenSession.set(JWT_KEY_EMPLOYEE_ID, employee.getEmployeeId());
        } else {
            tokenSession.delete(JWT_KEY_EMPLOYEE_ID);
        }

        LoginResultVO resultVO = new LoginResultVO();
        resultVO.setToken(StpUtil.getTokenValue());
        resultVO.setExpire(StpUtil.getTokenTimeout());

        JSONObject obj = new JSONObject();
        obj.set(JWT_KEY_USER_ID, defUser.getId());
        obj.set(JWT_KEY_TOP_COMPANY_ID, tokenSession.get(JWT_KEY_TOP_COMPANY_ID));
        obj.set(JWT_KEY_COMPANY_ID, tokenSession.get(JWT_KEY_COMPANY_ID));
        obj.set(JWT_KEY_DEPT_ID, tokenSession.get(JWT_KEY_DEPT_ID));
        obj.set(JWT_KEY_EMPLOYEE_ID, tokenSession.get(JWT_KEY_EMPLOYEE_ID));

        resultVO.setRefreshToken(SaTempUtil.createToken(obj.toString(), 2 * saTokenConfig.getTimeout()));


        log.info("用户：{}  {} 登录成功", defUser.getUsername(), defUser.getNickName());
        return resultVO;
    }

    @Override
    public R<Boolean> logout() {
        try {
            StpUtil.logout();
        } catch (Exception e) {
            log.debug("token已经过期，无需清理缓存");
        }
        return R.success(true);
    }

    @Override
    public LoginResultVO switchOrg(Long orgId) {
        StpUtil.checkLogin();
        Long userId = ContextUtil.getUserId();
        DefUser defUser = defUserService.getByIdCache(userId);
        if (defUser == null) {
            throw UnauthorizedException.wrap(ExceptionCode.JWT_TOKEN_EXPIRED);
        }

        if (!Convert.toBool(defUser.getState(), true)) {
            throw UnauthorizedException.wrap(ExceptionCode.JWT_USER_DISABLE);
        }

        BaseEmployee employee = baseEmployeeService.getEmployeeByUser(userId);
        ArgumentAssert.notNull(employee, "您不属于该公司，无法切换");
        if (!Convert.toBool(employee.getState(), true)) {
            throw BizException.wrap(ExceptionCode.JWT_EMPLOYEE_DISABLE);
        }

        Long topCompanyId = null;
        Long companyId = null;
        Long deptId = null;
        if (orgId != null) {
            BaseOrg selectOrg = baseOrgService.getByIdCache(orgId);
            ArgumentAssert.notNull(selectOrg, "该部门不存在");

            if (OrgTypeEnum.COMPANY.eq(selectOrg.getType())) {
                companyId = selectOrg.getId();

                Long rootId = TreeUtil.getTopNodeId(selectOrg.getTreePath());
                if (rootId != null) {
                    BaseOrg rootCompany = baseOrgService.getByIdCache(rootId);
                    topCompanyId = rootCompany != null ? rootCompany.getId() : companyId;
                } else {
                    topCompanyId = companyId;
                }
            } else {
                deptId = selectOrg.getId();

                BaseOrg company = baseOrgService.getCompanyByDeptId(deptId);
                if (company != null) {
                    companyId = company.getId();

                    Long rootId = TreeUtil.getTopNodeId(company.getTreePath());
                    if (rootId != null) {
                        BaseOrg rootCompany = baseOrgService.getByIdCache(rootId);
                        topCompanyId = rootCompany != null ? rootCompany.getId() : companyId;
                    } else {
                        topCompanyId = companyId;
                    }
                }
            }

            baseEmployeeService.updateOrgInfo(employee.getId(), companyId, deptId);
        } else {
            baseEmployeeService.updateOrgInfo(employee.getId(), companyId, deptId);
        }


        Employee e = Employee.builder()
                .employeeId(employee.getId())
                .build();

        Org org = Org.builder()
                .currentTopCompanyId(topCompanyId)
                .currentCompanyId(companyId)
                .currentDeptId(deptId)
                .build();

        LoginResultVO loginResultVO = buildResult(e, org, defUser);

        LoginStatusDTO loginStatus = LoginStatusDTO.switchOrg(defUser.getId(), employee.getId());
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
