package top.tangyh.lamp.oauth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tangyh.basic.cache.redis2.CacheResult;
import top.tangyh.basic.cache.repository.CacheOps;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.lamp.base.entity.user.BaseEmployee;
import top.tangyh.lamp.base.entity.user.BaseOrg;
import top.tangyh.lamp.base.service.user.BaseEmployeeService;
import top.tangyh.lamp.base.service.user.BaseOrgService;
import top.tangyh.lamp.common.cache.common.CaptchaCacheKeyBuilder;
import top.tangyh.lamp.common.properties.SystemProperties;
import top.tangyh.lamp.oauth.service.UserInfoService;
import top.tangyh.lamp.oauth.vo.param.RegisterByEmailVO;
import top.tangyh.lamp.oauth.vo.param.RegisterByMobileVO;
import top.tangyh.lamp.oauth.vo.result.OrgResultVO;
import top.tangyh.lamp.system.entity.tenant.DefUser;
import top.tangyh.lamp.system.service.tenant.DefUserService;

import java.util.List;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/9/16 12:21 PM
 * @create [2022/9/16 12:21 PM ] [tangyh] [初始创建]
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    protected BaseEmployeeService baseEmployeeService;
    @Autowired
    protected BaseOrgService baseOrgService;
    @Autowired
    protected DefUserService defUserService;
    @Autowired
    protected CacheOps cacheOps;

    @Autowired
    protected SystemProperties systemProperties;

    @Override
    public OrgResultVO findCompanyAndDept() {
        Long userId = ContextUtil.getUserId();
        Long companyId = ContextUtil.getCurrentCompanyId();
        Long deptId = ContextUtil.getCurrentDeptId();
        BaseEmployee baseEmployee = baseEmployeeService.getEmployeeByUser(userId);
        ArgumentAssert.notNull(baseEmployee, "用户不属于该企业");

        // 上次登录的单位
        List<BaseOrg> companyList = baseOrgService.findCompanyByEmployeeId(baseEmployee.getId());
        Long currentCompanyId = companyId != null ? companyId : baseEmployee.getLastCompanyId();

        // 上次登录的部门
        List<BaseOrg> deptList = baseOrgService.findDeptByEmployeeId(baseEmployee.getId(), currentCompanyId);
        Long currentDeptId = deptId != null ? deptId : baseEmployee.getLastDeptId();
        return OrgResultVO.builder()
                .companyList(companyList).currentCompanyId(currentCompanyId)
                .deptList(deptList).currentDeptId(currentDeptId).build();
    }

    @Override
    public List<BaseOrg> findDeptByCompany(Long companyId) {
        Long employeeId = ContextUtil.getEmployeeId();
        return baseOrgService.findDeptByEmployeeId(employeeId, companyId);
    }

    @Override
    public String registerByMobile(RegisterByMobileVO register) {
        if (systemProperties.getVerifyCaptcha()) {
//            短信验证码
            CacheKey cacheKey = new CaptchaCacheKeyBuilder().key(register.getMobile(), register.getKey());
            CacheResult<String> code = cacheOps.get(cacheKey);
            ArgumentAssert.equals(code.getValue(), register.getCode(), "验证码不正确");
        }
        ArgumentAssert.equals(register.getConfirmPassword(), register.getPassword(), "密码和确认密码不一致");
        DefUser defUser = BeanUtil.toBean(register, DefUser.class);

        defUserService.register(defUser);

        return defUser.getMobile();
    }

    @Override
    public String registerByEmail(RegisterByEmailVO register) {
        if (systemProperties.getVerifyCaptcha()) {
//            短信验证码
            CacheKey cacheKey = new CaptchaCacheKeyBuilder().key(register.getEmail(), register.getKey());
            CacheResult<String> code = cacheOps.get(cacheKey);
            ArgumentAssert.equals(code.getValue(), register.getCode(), "验证码不正确");
        }
        ArgumentAssert.equals(register.getConfirmPassword(), register.getPassword(), "密码和确认密码不一致");
        DefUser defUser = BeanUtil.toBean(register, DefUser.class);

        defUserService.registerByEmail(defUser);

        return defUser.getEmail();
    }
}
