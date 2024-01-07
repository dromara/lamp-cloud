package top.tangyh.lamp.base.biz.user;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.lamp.base.entity.user.BaseEmployee;
import top.tangyh.lamp.base.service.user.BaseEmployeeOrgRelService;
import top.tangyh.lamp.base.service.user.BaseEmployeeService;
import top.tangyh.lamp.base.vo.query.user.BaseEmployeePageQuery;
import top.tangyh.lamp.base.vo.result.user.BaseEmployeeResultVO;
import top.tangyh.lamp.base.vo.save.user.BaseEmployeeSaveVO;
import top.tangyh.lamp.model.entity.system.SysUser;
import top.tangyh.lamp.model.enumeration.base.ActiveStatusEnum;
import top.tangyh.lamp.system.entity.tenant.DefUser;
import top.tangyh.lamp.system.service.tenant.DefUserService;
import top.tangyh.lamp.system.vo.query.tenant.DefUserPageQuery;
import top.tangyh.lamp.system.vo.save.tenant.DefUserSaveVO;

import java.util.List;

/**
 * 员工大业务层
 *
 * @author zuihou
 * @date 2021/10/22 10:37
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BaseEmployeeBiz {
    private final BaseEmployeeService baseEmployeeService;
    private final BaseEmployeeOrgRelService baseEmployeeOrgRelService;
    private final DefUserService defUserService;

    /**
     * 保存员工信息
     *
     * @param saveVO saveVO
     * @return top.tangyh.lamp.base.entity.user.BaseEmployee
     * @author tangyh
     * @date 2022/10/28 12:15 AM
     * @create [2022/10/28 12:15 AM ] [tangyh] [初始创建]
     */
    @Transactional(rollbackFor = Exception.class)
    public BaseEmployee save(BaseEmployeeSaveVO saveVO) {
        boolean existDefUser = defUserService.checkMobile(saveVO.getMobile(), null);
        if (existDefUser) {
            throw new BizException("手机号已被注册,请重新输入手机号 或 直接邀请它加入贵公司。");
        }
        String username = StrUtil.isBlank(saveVO.getUsername()) ? IdUtil.simpleUUID() : saveVO.getUsername();
        // 保存 用户表 和 员工表
        DefUserSaveVO userSaveVO = DefUserSaveVO.builder().username(username).nickName(saveVO.getRealName()).build();
        BeanUtil.copyProperties(saveVO, userSaveVO);
        DefUser defUser = defUserService.save(userSaveVO);

        // 保存员工表
        saveVO.setActiveStatus(ActiveStatusEnum.ACTIVATED.getCode());
        saveVO.setUserId(defUser.getId());
        saveVO.setIsDefault(true);
        return baseEmployeeService.save(saveVO);
    }

    /**
     * 根据员工ID 查询员工、用户和他所在的机构 信息
     *
     * @param employeeId 员工ID
     * @return top.tangyh.lamp.base.vo.result.user.BaseEmployeeResultVO
     * @author tangyh
     * @date 2022/10/28 12:13 AM
     * @create [2022/10/28 12:13 AM ] [tangyh] [初始创建]
     */
    public BaseEmployeeResultVO getEmployeeUserById(Long employeeId) {
        // 租户库
        BaseEmployee employee = baseEmployeeService.getById(employeeId);
        if (employee == null) {
            return null;
        }
        // 员工信息
        BaseEmployeeResultVO resultVO = new BaseEmployeeResultVO();
        BeanUtil.copyProperties(employee, resultVO);

        // 机构信息
        resultVO.setOrgIdList(baseEmployeeOrgRelService.findOrgIdListByEmployeeId(employeeId));

        // 用户信息
        DefUser defUser = defUserService.getById(employee.getUserId());
        resultVO.setDefUser(BeanUtil.toBean(defUser, SysUser.class));

        return resultVO;
    }

    /**
     * 分页查员工数据
     *
     * @param params 参数
     * @return IPage
     * @author tangyh
     * @date 2022/10/28 12:19 AM
     * @create [2022/10/28 12:19 AM ] [tangyh] [初始创建]
     */
    public IPage<BaseEmployeeResultVO> findPageResultVO(PageParams<BaseEmployeePageQuery> params) {
        BaseEmployeePageQuery pageQuery = params.getModel();
        List<Long> userIdList;
        if (!StrUtil.isAllEmpty(pageQuery.getMobile(), pageQuery.getEmail(), pageQuery.getUsername(), pageQuery.getIdCard())) {
            userIdList = defUserService.findUserIdList(BeanUtil.toBean(pageQuery, DefUserPageQuery.class));
            if (CollUtil.isEmpty(userIdList)) {
                return new Page<>(params.getCurrent(), params.getSize());
            }

            params.getModel().setUserIdList(userIdList);
        }
        IPage<BaseEmployeeResultVO> pageResultVO = baseEmployeeService.findPageResultVO(params);

        if (CollUtil.isNotEmpty(pageResultVO.getRecords())) {
            List<Long> userIds = pageResultVO.getRecords().stream().map(BaseEmployeeResultVO::getUserId).toList();
            List<DefUser> defUsers = defUserService.listByIds(userIds);
            List<SysUser> userResultVos = BeanUtil.copyToList(defUsers, SysUser.class);
            ImmutableMap<Long, SysUser> map = CollHelper.uniqueIndex(userResultVos, SysUser::getId, user -> user);

            pageResultVO.getRecords().forEach(item -> item.setDefUser(map.get(item.getUserId())));
        }

        return pageResultVO;
    }

}
