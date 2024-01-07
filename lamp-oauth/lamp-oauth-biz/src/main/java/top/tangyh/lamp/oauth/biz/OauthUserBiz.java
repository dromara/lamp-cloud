package top.tangyh.lamp.oauth.biz;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.lamp.base.entity.user.BaseEmployee;
import top.tangyh.lamp.base.service.user.BaseEmployeeService;
import top.tangyh.lamp.base.vo.result.user.BaseEmployeeResultVO;
import top.tangyh.lamp.common.constant.AppendixType;
import top.tangyh.lamp.file.service.AppendixService;
import top.tangyh.lamp.model.vo.result.AppendixResultVO;
import top.tangyh.lamp.oauth.vo.result.DefUserInfoResultVO;
import top.tangyh.lamp.system.entity.application.DefApplication;
import top.tangyh.lamp.system.entity.tenant.DefUser;
import top.tangyh.lamp.system.service.application.DefApplicationService;
import top.tangyh.lamp.system.service.tenant.DefUserService;
import top.tangyh.lamp.system.vo.result.application.DefApplicationResultVO;

/**
 * 用户大业务
 *
 * @author zuihou
 * @date 2021/10/28 13:09
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OauthUserBiz {
    private final BaseEmployeeService baseEmployeeService;
    private final DefUserService defUserService;
    private final DefApplicationService defApplicationService;
    private final AppendixService appendixService;

    public DefUserInfoResultVO getUserById(Long id) {
        // 查默认库
        DefUser defUser = defUserService.getByIdCache(id);
        if (defUser == null) {
            return null;
        }

        // 用户信息
        DefUserInfoResultVO resultVO = new DefUserInfoResultVO();
        BeanUtil.copyProperties(defUser, resultVO);

        // 用户头像
        AppendixResultVO appendix = appendixService.getByBiz(defUser.getId(), AppendixType.System.DEF__USER__AVATAR);
        if (appendix != null) {
            resultVO.setAvatarId(appendix.getId());
        }

        Long employeeId = ContextUtil.getEmployeeId();
        resultVO.setEmployeeId(employeeId);

        //查 租户库
        BaseEmployee employee = baseEmployeeService.getById(employeeId);
        resultVO.setBaseEmployee(BeanUtil.toBean(employee, BaseEmployeeResultVO.class));

        DefApplication defApplication = defApplicationService.getDefApp(id);
        resultVO.setDefApplication(BeanUtil.toBean(defApplication, DefApplicationResultVO.class));
        return resultVO;
    }
}
